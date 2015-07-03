class Memory {
	private:
		unordered_map<adress,byte> cells;
		adress mem_size;
}

Memory(address size) : mem_size(size) { }

byte read(address addr) const {
	if (addr >= mem_size) {
		throw AddressingError;
	}

	auto it = cells.find(addr);
	if (it == cells.end()) {
		return static_cast<byte>(rand());
	} else {
		return it->second;
	}
}


void write(address addr, byte b) {
	if (addr >= mem_size) {
		throw AddressingError;
	}

	auto it = cells.find(addr);
	if (it != cells.end()) {
		cells.erase(it);
	} 
	cells.insert({addr,b});
}
