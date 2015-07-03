#include <iostream>
#include <algorithm>
#include <unordered_map>
#include "umns.h"

using namespace std;

void UMNS::insert(const HostName& name, const IPAddress& address) {
	pairs.insert(pair<HostName,IPAddress>(name,address));
}

IPAddress UMNS::lookup(const HostName& name) const {
	auto it = pairs.find(name);
	if (it == pairs.end())
		return NON_EXISTING_ADDRESS;
	else
		return it->second;
}

bool UMNS::remove(const HostName& name) {
	auto it = pairs.find(name);
	if (it == pairs.end())
		return false;
	
	pairs.erase(it);
	return true;
}
