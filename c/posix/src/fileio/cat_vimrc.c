//
// Created by MaoMao on 2021/4/11.
//
#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
  char *home = getenv("HOME");
  const char *path = strcat(home, "/.vimrc");

  // https://www.man7.org/linux/man-pages/man3/open.3p.html
  int fd = open(path, O_RDONLY);
  if (fd < 0) {
    printf("Error: %s \"%s\"\n", strerror(errno), path);
    return 1;
  }

  char *buff[128];
  // https://www.man7.org/linux/man-pages/man3/read.3p.html
  for (int i = 0; (i = read(fd, buff, 128)) > 0;) {
    // https://www.man7.org/linux/man-pages/man3/write.3p.html
    write(STDOUT_FILENO, buff, i);
  }

  // https://www.man7.org/linux/man-pages/man3/close.3p.html
  close(fd);
  return 0;
}