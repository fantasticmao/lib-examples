/**
 * 练习 4-12
 *
 * 运用 printd 函数的设计思想编写一个递归版本的 itoa 函数，即通过递归调
 * 用把整数转换为字符串。
 */

#include <math.h>
#include <stdio.h>

void itoa(int n, char s[], int i);

int main(int argc, char *argv[]) {
  int n = 123456;
  char s[10] = {};
  itoa(n, s, (int)floor(log10(n)));
  printf("%s\n", s);
  return 0;
}

void itoa(int n, char s[], int i) {
  if (i >= 0) {
    itoa(n / 10, s, i - 1);
    s[i] = n % 10 + '0';
  }
}