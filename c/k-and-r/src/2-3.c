/**
 * 练习 2-3
 *
 * 编写函数 htoi(s)，把由十六进制数字组成的字符串（包含可选的前缀 0x 或 0X）
 * 转换成与之等价的整形值。字符串允许包含的数字包括：0~9、a~f 以及 A~F。
 */

#include <stdio.h>
#include <string.h>

#define HEX_SIZE 16

/* htoi 函数：把由十六进制数字组成的字符串转换成与之等价的整形值 */
unsigned int htoi(const char s[]);

int main(int argc, char *argv[]) {
  if (argc > 1) {
    char *s = argv[1];
    unsigned int i = htoi(s);
    printf("htoi(\"%s\") = %u\n", s, i);
  } else {
    printf("please input a hexadecimal number string\n");
  }
  return 0;
}

unsigned int htoi(const char s[]) {
  int base = 1;
  int len = strlen(s);
  unsigned int n = 0;
  for (int i = len - 1; i >= 0; i--, base = base * HEX_SIZE) {
    if (s[i] >= '0' && s[i] <= '9') {
      n = n + base * (s[i] - '0');
    } else if (s[i] >= 'a' && s[i] <= 'f') {
      n = n + base * (s[i] - 'a' + 10);
    } else if (s[i] >= 'A' && s[i] <= 'F') {
      n = n + base * (s[i] - 'A' + 10);
    }
  }
  return n;
}
