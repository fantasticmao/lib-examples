/**
 * 练习 4-14
 *
 * 定义宏 swap(t, x, y) 以交换 t 类型的两个参数。（使用程序块结构会对你有
 * 所帮助。）
 */

#define swap(t, x, y) \
  {                   \
    t tmp = x;        \
    x = y;            \
    y = tmp;          \
  }

#include <stdio.h>

int main(int argc, char *argv[]) {
  int x = 0, y = 1;
  printf("x=%d, y=%d\n", x, y);
  swap(int, x, y);
  printf("x=%d, y=%d\n", x, y);
  return 0;
}
