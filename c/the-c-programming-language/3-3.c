/**
 * 练习 3-3
 *
 * 编写函数 expand(s1, s2)，将字符串 s1 中类似于 a-z 一类的速记符号在字符
 * 串 s2 中扩展为等价的完整列表 abc...xyz。该函数可以处理大小写字母和数字，并且可以处理
 * a-b-c、a-z0-9 与 -a-z 等类似的情况。作为前导和尾随的 - 符号原样排印。
 */

#include <ctype.h>
#include <stdio.h>

void expand(char s1[], char s2[]);

int main(int argc, char* argv[]) {
  char s1[] = "a-z0-9";
  char s2[36] = {};
  expand(s1, s2);
  printf("%s\n", s2);
  return 0;
}

void expand(char s1[], char s2[]) {
  int i = 0;
  for (int j = 0; s1[j] != '\0'; j++) {
    if (s1[j] == '-' && isalnum(s1[j + 1]) && isalnum(s1[j - 1]) &&
        s1[j + 1] > s1[j - 1]) {
      int n = s1[j + 1] - s1[j - 1];
      for (int k = 1; k < n; k++) {
        s2[i++] = s1[j - 1] + k;
      }
    } else {
      s2[i++] = s1[j];
    }
  }
  s2[i] = '\0';
}
