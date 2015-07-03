#include <map>
#include "nameserverinterface.h"

using namespace std;

class MNS : public NameServerInterface {
	public:
		void insert(const HostName&, const IPAddress&);
		IPAddress lookup(const HostName&) const;
		bool remove(const HostName&);
	private:
		map<HostName,IPAddress> pairs;
 };

