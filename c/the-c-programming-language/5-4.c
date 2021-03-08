/**
 * 练习 5-4
 *
 * 编写函数 strend(s, t)。如果字符串 t 出现在字符串 s 的尾部，该函数返回
 * 1，否则返回 0。
 */

#include <assert.h>

/* strend 函数：如果字符串 t 出现在字符串 s 的尾部，该函数返回 1，否则返回 0 */
int strend(char *s, char *t);

int main(int argc, char *argv[]) {
  char s[] = "Hello, World!";
  char t[] = "ld!";
  int in = strend(s, t);
  assert(in);
  return 0;
}

int strend(char *s, char *t) {
  int l1, l2;
  for (l1 = 0; *s++ != '\0'; l1++) {
    ;
  }
  for (l2 = 0; *t++ != '\0'; l2++) {
    ;
  }
  for (; l2 >= 0 && l1 >= 0; l1--, l2--) {
    if (*--s != *--t) {
      return 0;
    }
  }
  return 1;
}