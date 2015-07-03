#include <iostream>
#include <algorithm>
#include <map>
#include <functional>
#include "hns.h"

using namespace std;

HNS::HNS(unsigned int size) {
	for (unsigned int i = 0; i < size; i++) {
		vector<pair<HostName,IPAddress>> v;
		hm.push_back(v);
	}
}

void HNS::insert(const HostName& name, const IPAddress& address) {
	size_t hash_val = h(name) % hm.size();	
	auto *list = &hm[hash_val];
	auto lambda = [name](pair<HostName,IPAddress> p) { return name == p.first; };
	auto it = find_if(list->begin(), list->end(), lambda);
	
	if (it == list->end()) {
		list->push_back(make_pair(name,address));
	}
}

IPAddress HNS::lookup(const HostName& name) const {
	size_t hash_val = h(name) % hm.size();
	auto list = hm[hash_val];
	auto lambda = [name](pair<HostName,IPAddress> p) { return name == p.first; };
	auto it = find_if(list.begin(), list.end(), lambda);
	
	if (it == list.end()) {
		return NON_EXISTING_ADDRESS;
	} else {
		return it->second;
	}
}

bool HNS::remove(const HostName& name) {
	size_t hash_val = h(name) % hm.size();
	auto *list = &hm[hash_val];
	auto lambda = [name](pair<HostName,IPAddress> p) { return name == p.first; };
	auto it = find_if(list->begin(), list->end(), lambda);
	
	if (it == list->end()) {
		return false;
	} else {
		list->erase(it);
		return true;
	}
}
