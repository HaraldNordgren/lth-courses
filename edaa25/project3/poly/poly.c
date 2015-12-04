//polynomial
//Harald Nordgren
//900404-1936
//Rättare: gda07abo

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "poly.h"
#include "error.h"

int parse_coeff(int* i, const char* string);
int parse_exp(int* i, const char* string);

struct poly_t {
	poly_t*	succ;
	int	coeff, exp;
};

poly_t* new_poly(int coeff, int exp) {
	poly_t* poly = calloc(1, sizeof(poly_t));
	if (poly == NULL)
		error(__FILE__, __LINE__, __func__, "Memory allocation error");
	
	poly->coeff = coeff;
	poly->exp = exp;
	return poly;
}

poly_t* new_poly_from_string(const char* string) {
	poly_t* poly = calloc(1, sizeof(poly_t));
	if (poly == NULL)
		error(__FILE__, __LINE__, __func__, "Memory allocation error");
	
	int i = 0;	
	poly->coeff = parse_coeff(&i, string);
	poly->exp = parse_exp(&i, string);
	
	while (string[i] == ' ')
		i++;
	
	poly_t* r = poly;
	while (string[i] != '\0') {
		r->succ = calloc(1, sizeof(poly_t));
		if (r->succ == NULL)
			error(__FILE__, __LINE__, __func__, "Memory allocation error");
		
		r = r->succ;
		r->coeff = parse_coeff(&i, string);
		r->exp = parse_exp(&i, string);
		
		while (string[i] == ' ')
			i++;
	}
	return poly;
}

int parse_coeff(int* i, const char* string) {
	char sign = 1;
	if (string[*i] == '-') {
		sign = -1;
		(*i)++;
	} else if (string[*i] == '+') {
		(*i)++;
	}
	
	while (string[*i] == ' ')
		(*i)++;
	
	int coeff = 0;
	while (isdigit(string[*i]))
		coeff = 10 * coeff + (string[(*i)++] - '0');
	
	if (coeff == 0)
		coeff = 1;
	
	return sign * coeff;
}

int parse_exp(int* i, const char* string) {
	int exp = 0;
	if (string[*i] == 'x') {
		if (string[++(*i)] == '^') {
			while (isdigit(string[++(*i)]))
				exp = 10 * exp + (string[*i] - '0');
		} else {
			exp = 1;
		}
	}
	return exp;
}

poly_t* mul(poly_t* a, poly_t* b) {
	poly_t* result = new_poly(a->coeff * b->coeff, a->exp + b->exp);
	poly_t *ra = a;
	poly_t *rb = b->succ;
	while (ra != NULL) {
		while (rb != NULL) {
			int coeff = ra->coeff * rb->coeff;
			int exp = ra->exp + rb->exp;
			
			poly_t *rr = result;
			while (rr->succ != NULL && exp < rr->exp)
				rr = rr->succ;
			
			if (rr == NULL) {
				rr->succ = new_poly(coeff, exp);
			} else if (exp == rr->exp) {
				rr->coeff += coeff;
			} else {
				poly_t* rn = new_poly(coeff, exp);
				rn->succ = rr->succ;
				rr->succ = rn;
			}
		rb = rb->succ;
		}
	ra = ra->succ;
	rb = b;
	}
	return result;
}				

void print_poly(poly_t* poly) {
	poly_t* r = poly;
	char minus = 1;
	
	while (r != NULL) {
		if (r->exp == 0) {
			printf("%d", minus * r->coeff);
		} else {
			if (r->coeff != 1)
				printf("%d ", minus * r->coeff);
			printf("x");
			if (r->exp != 1)
				printf("^%d", r->exp);
		}
		
		if (r->succ != NULL) {
			if (r->succ->coeff < 0) {
				printf(" - ");
				minus = -1;
			} else {
				printf(" + ");
				minus = 1;
			}
		}
		r = r->succ;
	}
	printf("\n");
}

void free_poly(poly_t* poly) {
	while(poly != NULL) {
		poly_t* r = poly->succ;
		free(poly);
		poly = r;
	}
}
