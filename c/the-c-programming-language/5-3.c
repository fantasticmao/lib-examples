/**
 * 练习 5-3
 *
 * 用指针方式实现第 2 章中的函数 strcat。函数 strcat(s, t) 将 t 指向的字符
 * 串复制到 s 指向的字符串的尾部。
 */

#include <stdio.h>

void _strcat(char *s, char *t);

int main(int argc, char *argv[]) {
  char s[20] = "Hello, ";
  char t[20] = "World!";
  _strcat(s, t);
  printf("%s\n", s);
  return 0;
}

void _strcat(char *s, char *t) {
  for (; *s != '\0';) {
    s++;
  }
  for (; *t != '\0';) {
    *s++ = *t++;
  }
  *s = '\0';
}