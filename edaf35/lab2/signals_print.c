#include <stdio.h>
#include <signal.h>


void handler(int);

int main() {
	signal(SIGINT, handler);
	while(1) {
		/*printf("hej");*/
	}
}

void handler(int signum) {
	printf(" hej\n");
}
