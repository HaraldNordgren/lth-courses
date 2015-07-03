#define _XOPEN_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include "pwdblib.h"   /* include header declarations for pwdblib.c */

/* Define some constants. */
#define USERNAME_SIZE (32)
#define NOUSER (-1)
#define PROGRAM "/usr/bin/xterm"

void read_username(char *username)
{
  printf("login: ");
  fgets(username, USERNAME_SIZE, stdin);

  /* remove the newline included by getline() */
  username[strlen(username) - 1] = '\0';
}

void login () {
  signal(SIGINT, SIG_IGN);
 
  char username[USERNAME_SIZE];
  
  while (1) {
    read_username(username);
		if (!strcmp(username,"quit"))
			return;
		
    struct pwdb_passwd *p = pwdb_getpwnam(username);

    if (p == NULL) {
      printf("Found no user with name: %s\n", username);
    } else if (p->pw_failed == -1) {
      printf("User has been locked out\n");
    }  else {
      char *passwd = crypt(getpass("password: "),p->pw_passwd);
      if (!strcmp(passwd,p->pw_passwd)) {
        printf("User authenticated successfully\n");
				p->pw_failed = 0;
				if(++p->pw_age > 10)
					printf("WARNING: Password is too old and should be changed\n");
        
        pid_t pid = fork();
        if (pid == 0) {
					setgid(p->pw_gid);
					//setegid(p->pw_gid);
          setuid(p->pw_uid);
					//seteuid(p->pw_uid);
          
					execl(PROGRAM,PROGRAM,"-e",p->pw_shell,"-l",NULL);
          _exit(-1);
        } else {
          int status;
          waitpid(pid, &status, 0);
        }
      } else {
        printf("Unknown user or incorrect password\n");
				if (++p->pw_failed > 5) {
          p->pw_failed = -1;
          printf("WARNING: User has been locked out\n");
        }
      }
      pwdb_update_user(p);
    }
    printf("\n");
  }  
}

int main(int argc, char **argv)
{
  login();
}
