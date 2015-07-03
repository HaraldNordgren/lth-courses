# Define the compiler and the linker. The linker must be defined since
# the implicit rule for linking uses CC as the linker. g++ can be
# changed to clang++.
#CXX = g++
CC  = gcc

#LDFLAGS +=  -L.
LDLIBS =    -lcrypt

# Targets
PROGS = openshell_demo userinfo update_user mylogin mylogin_new

all:	$(PROGS)

# Targets rely on implicit rules for compiling and linking
#openshell_demo:	dictionary.o word.o
userinfo:	pwdblib.o
update_user:	pwdblib.o
mylogin:	pwdblib.o
mylogin_new:	pwdblib.o

# Phony targets
.PHONY: all clean

# Standard clean
clean:
	rm -f *.o $(PROGS)

