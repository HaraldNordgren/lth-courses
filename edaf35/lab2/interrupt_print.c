#include <stdio.h>
#include <signal.h>


void handler(int);

int main() {
	signal(SIGINT, handler);
	while(1) {}
}

void handler(int signum) {
	printf(" hej\n");
}
