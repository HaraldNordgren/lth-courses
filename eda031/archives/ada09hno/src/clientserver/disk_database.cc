#include "disk_database.h"
#include "protocol.h"

#include <string>
#include <map>
#include <vector>
#include <iostream>
#include <fstream>
#include <algorithm>

#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>

using namespace std;

string DATABASE_ROOT	= "../database/";
const string COUNTER	= "counter";
const string DELIMITER	= ":";

DIR* open_directory(string);
void create_counter(string);
string count(string);
string read_article(string);

disk_database::disk_database(string database_path) {
	if (!database_path.empty()) {
		DATABASE_ROOT = database_path;
	}

	DIR* root = opendir(DATABASE_ROOT.c_str());
	if (root != nullptr) {
		closedir(root);
		return;
	}

	mkdir(DATABASE_ROOT.c_str(), S_IRWXU | S_IRWXG | S_IRWXO);
	create_counter(DATABASE_ROOT);
}

vector<pair<unsigned, string>> disk_database::list_newsgroups() const {
	DIR* root = open_directory(DATABASE_ROOT);
	vector<pair<unsigned, string>> result;
	struct dirent* folder;

	while ((folder = readdir(root)) != NULL) {
		string folder_name = folder->d_name;
		auto pos = folder_name.find(DELIMITER);
		if (pos != string::npos) {
			string id = folder_name.substr(0,pos);
			string name = folder_name.substr(pos+1);
			result.push_back({stoi(id), name});
		}
	}
	
	closedir(root);
	sort(result.begin(), result.end());
	
	return result;
}

constant disk_database::create_newsgroup(const string name) {
	DIR* root = open_directory(DATABASE_ROOT);
	struct dirent* folder;

	while ((folder = readdir(root)) != NULL) {
		string folder_name = folder->d_name;
		auto pos = folder_name.find(DELIMITER);
		if (pos != string::npos && folder_name.substr(pos+1) == name) {
			closedir(root);
			return Protocol::ERR_NG_ALREADY_EXISTS;
		}
	}

	closedir(root);
	
	const string ID = count(DATABASE_ROOT);
	const string FOLDER_NAME = ID + DELIMITER + name;
	const string NEWSGROUP_PATH = DATABASE_ROOT + FOLDER_NAME + "/";
	
	mkdir(NEWSGROUP_PATH.c_str(), S_IRWXU | S_IRWXG | S_IRWXO);
	create_counter(NEWSGROUP_PATH);

	return Protocol::ANS_ACK;
}

constant disk_database::delete_newsgroup(const unsigned group_id) {
	DIR* dir = open_directory(DATABASE_ROOT);
	
	struct dirent* folder;
	string folder_name;
	bool found_newsgroup = false;
	while (!found_newsgroup && (folder = readdir(dir)) != NULL) {
		folder_name = folder->d_name;
		auto pos = folder_name.find(DELIMITER);
		if (pos != string::npos && folder_name.substr(0,pos) == to_string(group_id)) {
			found_newsgroup = true;
		}
	}

	closedir(dir);

	if (!found_newsgroup) {
		return Protocol::ERR_NG_DOES_NOT_EXIST;
	}
	
	const string NEWSGROUP_PATH = DATABASE_ROOT + folder_name + "/";
	dir = open_directory(NEWSGROUP_PATH);
	while ((folder = readdir(dir)) != NULL) {
		const string ARTICLE_PATH = NEWSGROUP_PATH + folder->d_name;
		remove(ARTICLE_PATH.c_str());
	}

	remove(NEWSGROUP_PATH.c_str());
	closedir(dir);

	return Protocol::ANS_ACK;
}

pair<constant, map<unsigned, string>> disk_database::list_articles(const unsigned group_id) const {
	map<unsigned, string> result;
	
	DIR* dir = open_directory(DATABASE_ROOT);
	
	struct dirent* folder;
	string folder_name;
	bool found_newsgroup = false;
	while (!found_newsgroup && (folder = readdir(dir)) != NULL) {
		folder_name = folder->d_name;
		auto pos = folder_name.find(DELIMITER);
		if (pos != string::npos && folder_name.substr(0,pos) == to_string(group_id)) {
			found_newsgroup = true;
		}
	}

	closedir(dir);

	if (!found_newsgroup) {
		return {Protocol::ERR_NG_DOES_NOT_EXIST, result};
	}
	
	const string NEWSGROUP_PATH = DATABASE_ROOT + folder_name + "/";
	dir = open_directory(NEWSGROUP_PATH);
	while ((folder = readdir(dir)) != NULL) {
		string file_name = folder->d_name;
		auto pos1 = file_name.find(DELIMITER);
		auto pos2 = file_name.find(DELIMITER, pos1+1);
		if (pos1 != string::npos && pos2 != string::npos) {
			string article_id = file_name.substr(0,pos1);
			string article_title = file_name.substr(pos1+1, pos2-pos1-1);
			result.insert({stoi(article_id), article_title});
		}
	}

	closedir(dir);

	return {Protocol::ANS_ACK, result};
}


constant disk_database::create_article(const unsigned group_id, const article art) {
	DIR* root = open_directory(DATABASE_ROOT);
	
	struct dirent* folder;
	string folder_name;
	bool found_newsgroup = false;
	while (!found_newsgroup && (folder = readdir(root)) != NULL) {
		folder_name = folder->d_name;
		auto pos = folder_name.find(DELIMITER);
		if (pos != string::npos && folder_name.substr(0,pos) == to_string(group_id)) {
			found_newsgroup = true;
		}
	}

	closedir(root);

	if (!found_newsgroup) {
		return Protocol::ERR_NG_DOES_NOT_EXIST;
	}

	const string NEWSGROUP_PATH = DATABASE_ROOT + folder_name + "/";
	const string ARTICLE_ID = count(NEWSGROUP_PATH);
	const string ARTICLE_NAME = ARTICLE_ID + DELIMITER + art.title
		+ DELIMITER + art.author;

	fstream article_stream;
	article_stream.open(NEWSGROUP_PATH + ARTICLE_NAME, fstream::out | fstream::trunc);
	article_stream << art.text;
	article_stream.close();

	return Protocol::ANS_ACK;
}

constant disk_database::delete_article(const unsigned group_id, const unsigned article_id) {
	DIR* dir = open_directory(DATABASE_ROOT);
	
	struct dirent* file;
	string folder_name;
	bool found_newsgroup = false;
	while (!found_newsgroup && (file = readdir(dir)) != NULL) {
		folder_name = file->d_name;
		auto pos = folder_name.find(DELIMITER);
		if (pos != string::npos && folder_name.substr(0,pos) == to_string(group_id)) {
			found_newsgroup = true;
		}
	}

	closedir(dir);

	if (!found_newsgroup) {
		return Protocol::ERR_NG_DOES_NOT_EXIST;
	}
	
	const string NEWSGROUP_PATH = DATABASE_ROOT + folder_name + "/";
	dir = open_directory(NEWSGROUP_PATH);
	while ((file = readdir(dir)) != NULL) {
		string file_name = file->d_name;
		auto pos = file_name.find(DELIMITER);
		if (pos != string::npos) {
			string id = file_name.substr(0,pos);
			if (id == to_string(article_id)) {
				const string ARTICLE_PATH = NEWSGROUP_PATH + file->d_name;
				remove(ARTICLE_PATH.c_str());
				
				closedir(dir);
				return Protocol::ANS_ACK;
			}
		}
	}

	closedir(dir);
	return Protocol::ERR_ART_DOES_NOT_EXIST;
}

pair<constant, article> disk_database::get_article(const unsigned group_id, const unsigned article_id) const {
	DIR* dir = open_directory(DATABASE_ROOT);
	
	struct dirent* file;
	string folder_name;
	bool found_newsgroup = false;
	while (!found_newsgroup && (file = readdir(dir)) != NULL) {
		folder_name = file->d_name;
		auto pos = folder_name.find(DELIMITER);
		if (pos != string::npos && folder_name.substr(0,pos) == to_string(group_id)) {
			found_newsgroup = true;
		}
	}

	closedir(dir);

	if (!found_newsgroup) {
		article art;
		return {Protocol::ERR_NG_DOES_NOT_EXIST, art};
	}
	
	const string NEWSGROUP_PATH = DATABASE_ROOT + folder_name + "/";
	dir = open_directory(NEWSGROUP_PATH);

	while ((file = readdir(dir)) != NULL) {
		string file_name = file->d_name;
		auto pos1 = file_name.find(DELIMITER);

		if (pos1 != string::npos) {	
			string id = file_name.substr(0, pos1);
			if (id == to_string(article_id)) {
				closedir(dir);

				auto pos2 = file_name.find(DELIMITER, pos1+1);
				string title = file_name.substr(pos1+1, pos2-pos1-1);
				string author = file_name.substr(pos2+1);

				const string ARTICLE_PATH = NEWSGROUP_PATH + file_name;
				string text = read_article(ARTICLE_PATH);

				article art = {title, author, text};
				return {Protocol::ANS_ACK, art};
			}
		}
	}

	closedir(dir);
	
	article art;
	return {Protocol::ERR_ART_DOES_NOT_EXIST, art};  // Ändrade från ans_ack till err_art_does_not_exist.  Om jag tolkat rätt var det ett typo.
}

DIR* open_directory(const string path) {
	DIR* dir = opendir(path.c_str());
	if (dir == nullptr) {
		cerr << "Could not open " << path << endl;
		exit(1);
	}

	return dir;
}

void create_counter(const string path) {
	fstream counter;
	counter.open(path + COUNTER, fstream::out | fstream::trunc);
	counter << "0";
	counter.close();
}

string count(const string path) {
	fstream counter;
	counter.open(path + COUNTER);

	unsigned nbr;
	counter >> nbr;

	counter.clear();	
	counter.seekp(0);
	counter << ++nbr;
	counter.close();

	return to_string(nbr);
}

string read_article(const string path) {
	ifstream infile(path);
	
	int lines = 0;
	string result, tmp;
	while (getline(infile,tmp)) {
		result += tmp;
		result += "\n";
		lines++;
	}
	
	if (lines < 2) {
		result.resize(result.length()-1);
	}

	return result;
}
