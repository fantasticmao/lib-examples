//
// Created by MaoMao on 2021/4/14.
//
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
  char *home = getenv("HOME");
  const char *path = strcat(home, "/.zshrc");

  // https://man7.org/linux/man-pages/man3/fopen.3.html
  FILE *file = fopen(path, "r");
  if (file == NULL) {
    printf("Error: %s \"%s\"\n", strerror(errno), path);
    return 1;
  }

  char buff[128];
  // https://man7.org/linux/man-pages/man3/fgets.3.html
  for (; fgets(buff, 128, file) != NULL;) {
    // https://man7.org/linux/man-pages/man3/puts.3.html
    fputs(buff, stdout);
  }

  fclose(file);
  return 0;
}