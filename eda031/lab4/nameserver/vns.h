#include <vector>
#include "nameserverinterface.h"

using namespace std;

class VNS : public NameServerInterface {
	public:
		void insert(const HostName&, const IPAddress&);
		IPAddress lookup(const HostName&) const;
		bool remove(const HostName&);
	private:
		vector<pair<string,int>> pairs;
};
