#include <iostream>
#include <algorithm>
#include <vector>
#include "vns.h"

using namespace std;

void VNS::insert(const HostName& name, const IPAddress& address) {
	pairs.push_back(make_pair(name, address));
}

IPAddress VNS::lookup(const HostName& name) const {
	auto lambda = [name](pair<HostName,IPAddress> p) { return name == p.first; };
	auto it = find_if(pairs.begin(), pairs.end(), lambda);
	
	if (it == pairs.end()) {
		return NON_EXISTING_ADDRESS;
	} else {
		return it->second;
	}
}

bool VNS::remove(const HostName& name) {
	auto lambda = [name](pair<HostName,IPAddress> p) { return name == p.first; };
	auto it = find_if(pairs.begin(), pairs.end(), lambda);

	if (it == pairs.end()) {
		return false;
	} else {
		pairs.erase(it);
		return true;
	}
}
