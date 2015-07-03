#define _XOPEN_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pwdblib.h"   /* include header declarations for pwdblib.c */

/* Define some constants. */
#define USERNAME_SIZE (32)
#define NOUSER (-1)

void read_username(char *username)
{
  printf("login: ");
  fgets(username, USERNAME_SIZE, stdin);

  /* remove the newline included by getline() */
  username[strlen(username) - 1] = '\0';
}


/*void read_passwd(char *passwd)
{
  printf("password: ");
  fgets(passwd, USERNAME_SIZE, stdin);

  passwd[strlen(passwd) - 1] = '\0';
}*/

void login () { 
  char username[USERNAME_SIZE];
  int logged_in = 0;
  
  while (!logged_in) {
    read_username(username);
    struct pwdb_passwd *p = pwdb_getpwnam(username);

    if (p == NULL) {
      printf("\nFound no user with name: %s\n", username);
    } else {
      char *passwd = crypt(getpass("password: "),p->pw_passwd);
      if (!strcmp(passwd,p->pw_passwd)) {
        printf("\nUser authenticated successfully\n");
        logged_in = 1;
      } else {
        printf("\nUnknown user or incorrect password.\n");
      }
    }
  }  
}

int main(int argc, char **argv)
{
  login();
}
