/*
 * 练习 2-1
 *
 * 编写一个程序以确定分别由 signed 及 unsigned 限定的 char、short、int 与 long
 * 类型变量的取值范围。采用打印标准头文件中的相应值以及直接计算两种方式实现。后一种方法
 * 的实现困难一些，因为要确定各种浮点类型的取值范围。
 */

#include <limits.h>
#include <math.h>
#include <stdio.h>

#define SIZE 8

void print1(void);
void print2(void);

int main(int argc, char *argv[]) {
  print1();
  printf("===========================================\n");
  print2();
  return 0;
}

void print1() {
  printf("signed char: %d ~ %d\n", SCHAR_MIN, SCHAR_MAX);
  printf("unsigned char: %d ~ %u\n", 0, UCHAR_MAX);
  printf("signed short: %d ~ %d\n", SHRT_MIN, SHRT_MAX);
  printf("unsigned short: %d ~ %u\n", 0, USHRT_MAX);
  printf("signed int: %d ~ %d\n", INT_MIN, INT_MAX);
  printf("unsigned int: %d ~ %u\n", 0, UINT_MAX);
  printf("signed long: %ld ~ %ld\n", LONG_MIN, LONG_MAX);
  printf("unsigned long: %d ~ %lu\n", 0, ULONG_MAX);
}

void print2() {
  int schar_size = sizeof(char);
  int uschar_size = sizeof(unsigned char);
  int sshort_size = sizeof(short int);
  int usschort_size = sizeof(unsigned short);
  int sint_size = sizeof(int);
  int usint_size = sizeof(unsigned int);
  int slong_size = sizeof(long int);
  int uslong_size = sizeof(unsigned long int);

  printf("signed char size = %d byte, value: %0.f ~ %0.f\n", schar_size,
         -pow(2, schar_size * SIZE - 1), pow(2, schar_size * 8 - 1) - 1);
  printf("unsigned char size = %d byte, value: %d ~ %0.f\n", uschar_size, 0,
         pow(2, uschar_size * SIZE) - 1);
  printf("signed short size = %d byte, value: %0.f ~ %0.f\n", sshort_size,
         -pow(2, sshort_size * SIZE - 1), pow(2, sshort_size * 8 - 1) - 1);
  printf("unsigned short size = %d byte, value: %d ~ %0.f\n", usschort_size, 0,
         pow(2, usschort_size * SIZE) - 1);
  printf("signed int size = %d byte, value: %0.f ~ %0.f\n", sint_size,
         -pow(2, sint_size * SIZE - 1), pow(2, sint_size * 8 - 1) - 1);
  printf("unsigned int size = %d byte, value: %d ~ %0.f\n", usint_size, 0,
         pow(2, usint_size * SIZE) - 1);
  printf("signed long size = %d byte, value: %0.f ~ %0.f\n", slong_size,
         -pow(2, slong_size * SIZE - 1), pow(2, slong_size * 8 - 1) - 1);
  printf("unsigned long size = %d byte, value: %d ~ %0.f\n", uslong_size, 0,
         pow(2, uslong_size * SIZE) - 1);
}