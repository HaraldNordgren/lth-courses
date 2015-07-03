inmemory_server_main.o inmemory_server_main.d : inmemory_server_main.cc ../server.h \
  ../connection.h ../connectionclosedexception.h ../protocol.h \
  disk_database.h database.h inmemory_database.h messagehandler.h
