/**
 * 练习 5-5
 *
 * 实现库函数 strncpy、strncat、strncmp，它们最多对参数字符串中的前
 * n 个字符进行操作。例如，函数 strncpy(s, t, n) 将 t 中最多前 n 个字符复制到 s中。更详细
 * 的说明请参见附录 B。
 */

#include <assert.h>
#include <stdio.h>

void _strncpy(char *s, char *t, int n);

void _strncat(char *s, char *t, int n);

int _strncmp(char *s, char *t, int n);

int main(int argc, char *argv[]) {
  char s1[] = "hello, world!";
  char t1[] = "HELLO, WORLD!";
  _strncpy(s1, t1, 5);
  printf("%s\n", s1);

  char s2[20] = "Hello, ";
  char t2[20] = "World!balabala";
  _strncat(s2, t2, 6);
  printf("%s\n", s2);

  char s3[] = "Hello, World!";
  char t3[] = "Hello!";
  assert(_strncmp(s3, t3, 5) == 0);
  assert(_strncmp(s3, t3, 6) > 0);
  return 0;
}

void _strncpy(char *s, char *t, int n) {
  for (; n > 0; n--) {
    *s++ = *t++;
  }
}

void _strncat(char *s, char *t, int n) {
  for (; *s != '\0';) {
    s++;
  }
  for (; *t != '\0' && n > 0; n--) {
    *s++ = *t++;
  }
  *s = '\0';
}

int _strncmp(char *s, char *t, int n) {
  for (; n > 0; n--, s++, t++) {
    if (*s != *t) {
      return *s - *t;
    }
  }
  return 0;
}