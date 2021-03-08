/**
 * 练习 5-6
 *
 * 采用指针而非数组索引方式改写章节和练习中的某些程序，例如 getline
 * （第 1、4 章），atoi、itoa 以及它们的变体形式（第 2、3、4 章），reverse（第 3章），
 * strindex、getop（第 4 章） 等等。
 */

#include <assert.h>
#include <math.h>
#include <stdio.h>
#include <string.h>

#define MAXLINE 1000

/* _getline 函数：将一行读取 s 中，并返回其长度 */
int _getline(char *s, int lim);

/* _atoi 函数：将字符串 s 转换为相应的整型数  */
int _atoi(char *s);

/* _itoa 函数：将数字 n 转换为相应的字符串 */
void _itoa(int n, char *s);

/* _reverse 函数：倒置字符串 s 中各个字符的位置 */
void _reverse(char *s);

/* _strindex 函数：返回 t 在 s 中的位置，若未找到则返回 -1 */
int _strindex(char *s, char *t);

int main(int argc, char *argv[]) {
  char s[100];
  int len = _getline(s, MAXLINE);
  printf("\"%s\" length: %d\n", s, len);

  char s1[] = "123456";
  int i1 = _atoi(s1);
  printf("%d\n", i1);

  int i2 = 123456;
  char s2[10];
  _itoa(i2, s2);
  printf("%s\n", s2);

  char s3[] = "Hello, World!";
  _reverse(s3);
  printf("%s\n", s3);

  char s4[] =
      "Ah Love! could you and I with Fate conspire\n"
      "To grasp this sorry Scheme of Things entire,\n"
      "Would not we shatter it to bits -- and then\n"
      "Re-mould it nearer to the Heart's Desire!";
  int i4 = _strindex(s4, "ould");
  assert(i4 == 10);
  return 0;
}

int _getline(char *s, int lim) {
  char c;
  for (; lim-- > 0 && (c = getchar()) != EOF && c != '\n';) {
    *s++ = c;
  }
  if (c == '\n') {
    *s++ = c;
  }
  *s = '\0';
  return MAXLINE - lim;
}

int _atoi(char *s) {
  int i = 0;
  int base = 10;
  for (; *s != '\0';) {
    i = i * base + (*s++ - '0');
  }
  return i;
}

void _itoa(int n, char *s) {
  const int len = (int)floor(log10(n));
  int stack[len + 1];
  for (int i = len; i >= 0; i--) {
    stack[i] = n % 10;
    n = n / 10;
  }
  for (int i = 0; i <= len; i++) {
    *s++ = stack[i] + '0';
  }
  *s = '\0';
}

void _reverse(char *s) {
  char tmp;
  int len = strlen(s);
  for (int i = 0, j = len - 1; i < j; i++, j--) {
    tmp = *(s + i);
    *(s + i) = *(s + j);
    *(s + j) = tmp;
  }
}

int _strindex(char *s, char *t) {
  int i = 0;
  char *p, *q;
  for (; *s != '\0'; s++, i++) {
    for (p = s, q = t; *q != '\0' && *p == *q;) {
      p++;
      q++;
    }
    if (*q == '\0') {
      return i;
    }
  }
  return -1;
}