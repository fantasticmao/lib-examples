/**
 * 练习 2-5
 *
 * 编写函数 any(s1, s2)，将字符串 s2 中的任一字符在字符串 s1 中第一次出现
 * 的位置作为结果返回。如果 s1 中不包含 s2 中的字符，则返回 -1。（标准库函数
 * strpbrk 具有 同样的功能，但它返回的是指向该位置的指针。）
 */

#include <stdio.h>

int any(char s1[], char s2[]);

int main(int argc, char *argv[]) {
  char s1[] = "Hello, World!";
  char s2[] = "ol";
  int i = any(s1, s2);
  printf("\"%s\" in \"%s\": %d\n", s2, s1, i);
  return 0;
}

int any(char s1[], char s2[]) {
  for (int i; s1[i] != '\0'; i++) {
    for (int j = 0; s2[j] != '\0'; j++) {
      if (s1[i] == s2[j]) {
        return i;
      }
    }
  }
  return -1;
}
