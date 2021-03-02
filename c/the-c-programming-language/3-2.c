/**
 * 练习 3-2
 *
 * 编写一个函数 escape(s, t)，将字符串 t 复制到字符串 s 中，并在复制过程中将换
 * 行符、制表符等不可见字符分别转换为 \n、\t 等相应的可见的转义字符序列。要求使用 switch
 * 语句。再编写一个具有相反功能的函数，在复制过程中将转义字符序列转换为实际字符。
 */

#include <stdio.h>

void escape(char s[], char t[]);

void unescape(char s[], char t[]);

int main(int argc, char *argv[]) {
  char s1[] = "Hello, World\n";
  char t1[20] = {};
  escape(s1, t1);
  printf("%s", t1);

  char s2[] = "Hello, World\\n";
  char t2[20] = {};
  unescape(s2, t2);
  printf("%s", t2);
  return 0;
}

void escape(char s[], char t[]) {
  int i = 0;
  for (int j = 0; s[j] != '\0'; j++) {
    switch (s[j]) {
      case '\n':
        t[i++] = '\\';
        t[i++] = 'n';
        break;
      case '\t':
        t[i++] = '\\';
        t[i++] = 't';
        break;
      default:
        t[i++] = s[j];
        break;
    }
  }
  t[i] = '\0';
}

void unescape(char s[], char t[]) {
  int i = 0;
  for (int j = 0; s[j] != '\0'; j++) {
    if (s[j] == '\\') {
      switch (s[++j]) {
        case 'n':
          t[i++] = '\n';
          break;
        case 't':
          t[i++] = '\t';
          break;
        default:
          break;
      }
    } else {
      t[i++] = s[j];
    }
  }
  t[i] = '\0';
}