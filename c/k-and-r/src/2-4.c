/**
 * 练习 2-4
 *
 * 重新编写函数 squeeze(s1, s2)，将字符串 s1 中任何与字符串 s2 中的字符
 * 匹配的字符都删除。
 */

#include <stdio.h>

/* squeeze 函数：将字符串 s1 中任何与字符串 s2 中的字符匹配的字符都删除 */
void squeeze(char s1[], char s2[]);

int main(int argc, char *atgv[]) {
  char s1[] = "Hello, World!";
  char s2[] = "ol";
  squeeze(s1, s2);
  printf("%s\n", s1);
  return 0;
}

void squeeze(char s1[], char s2[]) {
  int i = 0, j = 0;
  for (; s1[j] != '\0';) {
    int in = 0;
    for (int k = 0; s2[k] != '\0'; k++) {
      if (s1[j] == s2[k]) {
        in = 1;
        break;
      }
    }
    if (in) {
      s1[i] = s1[++j];
    } else {
      s1[i++] = s1[j++];
    }
  }
  s1[i] = '\0';
}
