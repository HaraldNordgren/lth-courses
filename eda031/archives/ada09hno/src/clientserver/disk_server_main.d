disk_server_main.o disk_server_main.d : disk_server_main.cc ../server.h ../connection.h \
  ../connectionclosedexception.h ../protocol.h disk_database.h \
  database.h inmemory_database.h messagehandler.h
