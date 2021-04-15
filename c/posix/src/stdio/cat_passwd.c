//
// Created by MaoMao on 2021/4/15.
//
#include <errno.h>
#include <stddef.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
  const char *path = "/etc/passwd";
  FILE *file = fopen(path, "r");
  if (file == NULL) {
    printf("Error: %s \"%s\"\n", strerror(errno), path);
    return 1;
  }

  char username[20];
  char passwd[20];
  int uid;
  int gid;
  char desc[20];
  char home[20];
  char shell[20];

  char buff[128];
  for (; fgets(buff, 128, file) != NULL;) {
    // https://man7.org/linux/man-pages/man3/scanf.3.html
    int i = sscanf(buff, "%[_a-z]:%[^:]:%d:%d:%[^:]:%[^:]:%s", username, passwd,
                   &uid, &gid, desc, home, shell);
    if (i != EOF && i > 0) {
      // https://man7.org/linux/man-pages/man3/printf.3.html
      fprintf(stdout,
              "username: %s\tpasswd: %s\tuid: %d\tgid: %d\tdesc: %s\thome: "
              "%s\tshell:%s\n",
              username, passwd, uid, gid, desc, home, shell);
    }
  }

  fclose(file);
  return 0;
}