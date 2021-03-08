/**
 * 练习 2-10
 *
 * 重新编写将大写字母转换为小写字母的函数 lower，并用条件表达式替代其中的
 * if-else 结构。
 */

#include <stdio.h>

/* lower 函数：把字符 c 转换为小写形式 */
int lower(int c);

int main(int argc, char *argv[]) {
  char c[] = "Hello, World!";
  for (int i = 0; c[i] != '\0'; i++) {
    c[i] = lower(c[i]);
  }
  printf("%s\n", c);
  return 0;
}

int lower(int c) { return (c >= 'A' && c <= 'Z') ? c - 'A' + 'a' : c; }