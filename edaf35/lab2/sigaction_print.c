#include <stdio.h>
#include <signal.h>


void handler1();
void handler2();

int main() {
	struct sigaction sa;
	sa.sa_handler = handler1;
	
	sigaction(SIGINT, &sa, NULL);

	printf("Entering infinite main loop\n");
	printf("waiting to be interrupted\n");
	while(1) {}
}

void handler1() {
	struct sigaction sa;
	
	printf("\n\nSignal SIGINT given\n");
	printf("entering infinite loop in handler\n");
	
	signal(SIGUSR1, SIG_IGN);
	
	sa.sa_handler = handler2;
	sigaction(SIGUSR2, &sa, NULL);
	
	while(1) {}
}

void handler2() {
	printf("\nSignal USR2 given\n");
}
