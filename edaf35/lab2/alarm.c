#include <stdio.h>
#include <signal.h>
#include <time.h>
#include <unistd.h>

#define     TIME    (5)

void handler();

int volatile proceed = 1;

int main() {
	struct sigaction sa;
	unsigned long iterations = 0;

	sa.sa_handler = handler;
	sigaction(SIGALRM, &sa, NULL);

	alarm(TIME);
	
	while (proceed) {
		iterations++;
	}

	printf("Iterations: %lu\n", iterations);
    printf("Per second: %lu\n", iterations / TIME);

	return 0;
}

void handler() {
	proceed = 0;
}
