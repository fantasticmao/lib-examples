/**
 * 练习 4-2
 *
 * 对 atof 函数进行扩充，使它可以处理形如 123.45e-6 的科学表示法，其中
 * 浮点数后面可能会紧跟一个 e 或者 E 以及一个指数（可能有正负号）。
 */

#include <ctype.h>
#include <math.h>
#include <stdio.h>

double atof(char s[]);

int main(int argc, char *argv[]) {
  char s[] = "-123.45e-6";
  double n = atof(s);
  printf("%e\n", n);
  return 0;
}

double atof(char s[]) {
  double val, power;
  int i, sign1, sign2, exponent;
  for (i = 0; isspace(s[i]); i++) {
    // 跳过空白符
    ;
  }
  sign1 = (s[i] == '-') ? -1 : 1;
  if (s[i] == '+' || s[i] == '-') {
    // 跳过符号位
    i++;
  }
  for (val = 0.0; isdigit(s[i]); i++) {
    val = 10.0 * val + (s[i] - '0');
  }
  if (s[i] == '.') {
    // 跳过点符号
    i++;
  }
  for (power = 1.0; isdigit(s[i]); i++) {
    val = 10.0 * val + (s[i] - '0');
    power = power * 10.0;
  }
  if (s[i] == 'e' || s[i] == 'E') {
    // 跳过指数符号
    i++;
  }
  sign2 = (s[i] == '-') ? -1 : 1;
  if (s[i] == '+' || s[i] == '-') {
    // 跳过符号位
    i++;
  }
  for (exponent = 0; isdigit(s[i]); i++) {
    exponent = exponent * 10 + (s[i] - '0');
  }
  return sign2 > 0 ? sign1 * val / power * pow(10, exponent)
                   : sign1 * val / power / pow(10, exponent);
}