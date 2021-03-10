/**
 * 练习 5-14
 *
 * 修改排序程序，使它能处理 -r 标记。该标记表明，以逆序（递减）方式排序。
 * 保证 -r 和 -n 能够组合在一起使用。
 */

#include <stdio.h>
#include <stdlib.h>

/* _qsort 函数：以递增顺序对 v[left]...v[right] 进行排序 */
void _qsort(void *lines[], const int left, const int right,
            int (*cmp)(void *, void *));

void swap(void *v[], const int i, const int j);

/* _strcmp 函数：按字典顺序比较字符串 s1 和 s2 */
int _strcmp(char *s1, char *s2);

/* _numcmp 函数：按数值顺序比较字符串 s1 和 s2 */
int _numcmp(char *n1, char *n2);

int numberic = 0;
int reverse = 0;

int main(int argc, char *argv[]) {
  extern int numberic;
  extern int reverse;

  for (; argc-- > 1;) {
    char *option = *(++argv);
    if (_strcmp(option, "-n") == 0) {
      numberic = 1;
    } else if (_strcmp(option, "-r") == 0) {
      reverse = 1;
    }
  }

  char *lines[] = {"91", "67", "14", "83", "23", "30", "20", "59", "98", "86"};
  // char *lines[] = {
  //     "While the British tabloids like to cast Meghan in the villainous role",
  //     "of the Duchess of Windsor — the American divorcée who lured away their",
  //     "king in 1936 and lived with him in bitter exile, causing an irreparable",
  //     "family rift — Harry and Meghan seem determined to position her instead",
  //     "as a latter-day Diana, a woman mistreated by her in-laws, more sinned",
  //     "against than sinning."
  // };
  const int length = sizeof(lines) / sizeof(char *);
  _qsort((void **)lines, 0, length - 1,
         (int (*)(void *, void *))(numberic ? _numcmp : _strcmp));
  for (int i = 0; i < length; i++) {
    printf("%s\n", lines[i]);
  }
  return 0;
}

void _qsort(void *lines[], const int left, const int right,
            int (*cmp)(void *, void *)) {
  if (left >= right) {
    return;
  }

  extern int reverse;
  int i = left, j = left;
  for (; j < right; j++) {
    int less = (*cmp)(lines[j], lines[right]) <= 0;
    if (!less && reverse) {
      swap(lines, i++, j);
    } else if (less && !reverse) {
      swap(lines, i++, j);
    }
  }
  swap(lines, i, right);
  _qsort(lines, left, i - 1, cmp);
  _qsort(lines, i + 1, right, cmp);
}

void swap(void *v[], const int i, const int j) {
  void *temp = v[i];
  v[i] = v[j];
  v[j] = temp;
}

int _strcmp(char *s1, char *s2) {
  for (; *s1 != '\0' && *s2 != '\0'; s1++, s2++) {
    if (*s1 != *s2) {
      return *s1 - *s2;
    }
  }
  return *s1 - *s2;
}

int _numcmp(char *n1, char *n2) {
  int f1 = atoi(n1);
  int f2 = atoi(n2);
  return f1 - f2;
}
