/**
 * 练习 3-1
 *
 * 在上面有关折半查找的例子中，while 循环语句内共执行了两次测试，其实只
 * 要一次就足够（代价是将更多的测试在循环外执行）。重写该函数，使得在循环内部只执行一
 * 次测试。比较两种版本的函数的运行时间。
 */

#include <assert.h>

#define NUMS_LENGTH 10

int binsearch(int x, int v[], int n);

int main(int argc, char *argv[]) {
  int nums[NUMS_LENGTH] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
  int p = binsearch(6, nums, NUMS_LENGTH);
  assert(p == 6);
  return 0;
}

int binsearch(int x, int v[], int n) {
  int low, high, mid;

  low = 0;
  high = n - 1;
  while (low < high) {
    mid = (low + high) / 2;
    if (x <= v[mid]) {
      high = mid;
    } else {
      low = mid + 1;
    }
  }
  return v[high] == x ? high : -1;
}
