#include <stdio.h>

static int f(void) {
    static int page;

    return page = (page + 1) % 5;                           
}

int main() {

    if (0) {
        goto hej;
    }

    printf("nej\n");
hej:
    printf("hej\n");
hejhej:
    printf("hejda\n");
}
