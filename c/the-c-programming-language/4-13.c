/**
 * 练习 4-13
 *
 * 编写一个递归版本的 reverse(s) 函数，以将字符串 s 倒置。
 */

#include <stdio.h>
#include <string.h>

void reverse(char s[], int left, int right);

int main(int argc, char *argv[]) {
  char s[] = "Hello, World!";
  reverse(s, 0, strlen(s) - 1);
  printf("%s\n", s);
  return 0;
}

void reverse(char s[], int left, int right) {
  if (left < right) {
    char c = s[left];
    s[left] = s[right];
    s[right] = c;
    reverse(s, left + 1, right - 1);
  }
}
