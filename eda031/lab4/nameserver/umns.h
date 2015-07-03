#include <unordered_map>
#include "nameserverinterface.h"

using namespace std;

class UMNS : public NameServerInterface {
	public:
		void insert(const HostName&, const IPAddress&);
		IPAddress lookup(const HostName&) const;
		bool remove(const HostName&);
	private:
		unordered_map<HostName,IPAddress> pairs;
 };

