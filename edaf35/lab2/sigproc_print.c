#include <stdio.h>
#include <signal.h>
#include <time.h>
#include <unistd.h>


int main() {
	time_t start, end, current;
	int diff;
	char i;
	sigset_t set, blocked_sigs;

	sigfillset(&set);
	sigdelset(&set, SIGQUIT);
	
	printf("Entering loop\n");
	sigprocmask(SIG_BLOCK, &set, NULL);

	time(&start);
	end = start + 20;
	diff = difftime(end, current);

	while (diff > 0) {
		time(&current);
		diff = difftime(end, current);
	}

	sigpending(&blocked_sigs);

	printf("\nExited loop\n");
	printf("blocked signals: ");

	for (i = 1; i <= 64; i++) {
		if (sigismember(&blocked_sigs, i)) {
			printf("%d ", i);
		}
	}
	
	fflush(stdout);

	sigprocmask(SIG_UNBLOCK, &set, NULL);
	
	return 0;
}

