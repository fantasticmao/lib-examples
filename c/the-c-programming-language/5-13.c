/**
 * 练习 5-13
 *
 * 编写程序 tail，将其输入中的最后 n 行打印出来。默认情况下，n 的值位 10，
 * 但可通过一个可选参数改变 n 的值，因此，命令 tail -n 将打印其输入的最后 n
 * 行。无论输入或者 n 的值是否合理，该程序都应该能正常运行。编写的程序要充分地
 * 利用存储空间，输入行的存储方式应该同 5.6 节中排序程序的存储方式一样，而不
 * 采用固定长度的二维数组。
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXNLINES 100
#define MAXLEN 100

/* tail 函数：将其输入中的最后 n 行打印出来 */
void tail(char *lines[], int nlines, int n);

/* readlines 函数：将输入的所有行保存到 lines 中，并返回所有行的数目 */
static int readlines(char *lines[], int maxlines);

/* readline 函数：将输入的单行保存到 s 中，并返回该行的长度 */
static int readline(char *line, int maxlen);

int main(int argc, char *argv[]) {
  int n = 10;
  if (argc > 1 && *argv[1] == '-') {
    n = atoi(++argv[1]);
  }
  n = (n == 0) ? 10 : n;

  int nlines;
  char *lines[MAXNLINES];
  if ((nlines = readlines(lines, MAXNLINES)) >= 0) {
    tail(lines, nlines, n);
  };
  return 0;
}

void tail(char *lines[], int nlines, int n) {
  n = (n > nlines) ? nlines : n;
  for (int i = 0, j = nlines - n; i < nlines; i++) {
    if (i >= j) {
      printf("%s\n", lines[i]);
    }
    free(lines[i]);
  }
}

static int readlines(char *lines[], int maxlines) {
  int i, wc;
  char line[MAXLEN];
  for (i = maxlines; --i > 0 && (wc = readline(line, MAXLEN)) > 0; lines++) {
    *lines = calloc(wc, sizeof(char));
    strcpy(*lines, line);
  }
  if (wc == 0) {
    i++;
  }
  return maxlines - i;
}

static int readline(char *line, int maxlen) {
  int i, c;
  for (i = maxlen; --i > 0 && (c = getchar()) != EOF && c != '\n';) {
    *line++ = c;
  }
  if (c == '\n') {
    *line = '\0';
    i++;
  }
  return maxlen - i;
}
