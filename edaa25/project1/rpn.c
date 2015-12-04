# include <stdio.h>
# include <stdlib.h>

# define SIZE 100

int get_integer(int *ch);
int pop(int stack[], int* sp);
void push(int stack[], int* sp, int nbr);

int main()
{
	int stack[SIZE];
	int sp = 0;
	
	int ch = getchar();
	while (ch != EOF) {
		while (ch != '\n') {
			if (ch >= '0' && ch <= '9') {
				push(stack, &sp, get_integer(&ch));
			} else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				int a = pop(stack, &sp);
				int b = pop(stack, &sp);
				if (a == 0 && ch == '/') {
					fprintf(stderr, "Divide by zero!\n");
					exit(1);
				}
				
				int nbr;
				if (ch == '+')
					nbr = b + a;
				else if (ch == '-')
					nbr = b - a;
				else if (ch == '*')
					nbr = b * a;
				else
					nbr = b / a;
					
				push(stack, &sp, nbr);
				ch = getchar();
			} else if (ch == ' ') {
				ch = getchar();
			} else {
				fprintf(stderr, "Syntax error!\n");
				exit(1);
			}
		}
		printf("= %d\n\n", stack[0]);
		sp = 0;
		ch = getchar();
	}
}

int get_integer(int *ch)
{
	int nbr = 0;
	do {
		nbr = 10 * nbr + (*ch - 48);
		*ch = getchar();
	} while (*ch >= '0' && *ch <= '9');
	
	return nbr;
}

int pop(int stack[], int* sp)
{
	if (*sp < 1) {
		fprintf(stderr, "Too many operators!\n");
		exit(1);
	}
	return stack[--(*sp)];
}

void push(int stack[], int* sp, int nbr)
{
	if (*sp >= SIZE) {
		fprintf(stderr, "Memory over limit! (%d+ operands)\n", SIZE);
		exit(1);
	}
	stack[(*sp)++] = nbr;
}
