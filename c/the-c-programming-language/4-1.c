/**
 * 练习 4-1
 *
 * 编写函数 strrindex(s, t)，它返回字符串 t 在 s 中最右边出现的位置。如果
 * s 中不包含 t，则返回 -1。
 */

#include <stdio.h>
#include <string.h>

/* strindex 函数：返回字符串 t 在 s 中最右边出现的位置 */
int strrindex(char s[], char t[]);

int main(int argc, char* argv[]) {
  char s[] =
      "Ah Love! could you and I with Fate conspire\n"
      "To grasp this sorry Scheme of Things entire,\n"
      "Would not we shatter it to bits -- and then\n"
      "Re-mould it nearer to the Heart's Desire!";
  char t[] = "ould";
  int i = strrindex(s, t);
  printf("[%s] index of [%s]: %d\n", t, s, i);
  return 0;
}

int strrindex(char s[], char t[]) {
  const int len1 = strlen(s);
  const int len2 = strlen(t);
  int i, j, k;
  for (i = len1 - 1; i >= 0; i--) {
    for (j = len2 - 1, k = i; j >= 0; j--, k--) {
      if (t[j] != s[k]) {
        break;
      }
    }
    if (j < 0) {
      return i - len2 + 1;
    }
  }
  return -1;
}