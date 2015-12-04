# include <stdio.h>
# include <stdlib.h>

# define SIZE	40
# define POW	10000

int read_integer();
int calculate_factorial(unsigned short list[], int n);
int print_factorial(unsigned short list[], int n);

int main()
{	
	unsigned short list[SIZE];
	list[0] = 1;
	
	int i;
	for (i = 1; i < SIZE; i++)
		list[i] = 0;
	
	printf("Input any integer 1≤n≤100: ");
	int n = read_integer();
	if (n < 1 || n > 100) {
		fprintf(stderr, "Incorrect value for n\n");
		exit(1);
	}
	
	calculate_factorial(list, n);
	print_factorial(list, n);
	return 0;
}

int read_integer()
{
	int ch = getchar();
	int n = 0;
	while (ch != '\n') {
		if (ch < '0' || ch > '9') {
			fprintf(stderr, "Syntax error!\n");
			exit(1);
		}
		n = 10 * n + (ch - 48);
		ch = getchar();
	}
	return n;
}

int calculate_factorial(unsigned short list[], int n)
{
	int i, j;
	for (i = 2; i <= n; i++) {
		int overflow = 0;
		for (j = 0; j < SIZE; j++) {
			unsigned int nbr = i * list[j] + overflow;
			list[j] = nbr % POW;
			overflow = nbr / POW;
		}
	}
}

int print_factorial(unsigned short list[], int n)
{
	int i = SIZE - 1;
	while (list[i] == 0)
		i--;
	
	printf("%d! = %d", n, list[i--]);
	for ( ; i >= 0; i--) {
		if (list[i] == 0)
			printf("0000");
		else if (list[i] < 10)
			printf("000%d", list[i]);
		else if (list[i] < 100)
			printf("00%d", list[i]);
		else if (list[i] < 1000)
			printf("0%d", list[i]);
		else
			printf("%d", list[i]);
	}
	printf("\n");
}
