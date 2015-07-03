#include <iostream>
#include <algorithm>
#include <map>
#include "mns.h"

using namespace std;

void MNS::insert(const HostName& name, const IPAddress& address) {
	pairs.insert(pair<HostName,IPAddress>(name,address));
}

IPAddress MNS::lookup(const HostName& name) const {
	auto it = pairs.find(name);
	if (it == pairs.end())
		return NON_EXISTING_ADDRESS;
	else
		return it->second;
}

bool MNS::remove(const HostName& name) {
	auto it = pairs.find(name);
	if (it == pairs.end())
		return false;
	
	pairs.erase(it);
	return true;
}
