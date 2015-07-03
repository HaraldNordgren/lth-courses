#include <map>
#include <vector>
#include <functional>
#include "nameserverinterface.h"

using namespace std;

class HNS : public NameServerInterface {
	public:
		HNS(unsigned int);
		void insert(const HostName&, const IPAddress&);
		IPAddress lookup(const HostName&) const;
		bool remove(const HostName&);
	private:
		vector<vector<pair<HostName,IPAddress>>> hm;
		//vector<pair<HostName,IPAddress>> pairs;
		hash<string> h;
 };

