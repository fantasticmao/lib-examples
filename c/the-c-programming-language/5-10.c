/**
 * 练习 5-10
 *
 * 编写程序 expr，以计算从命令行输入的逆波兰表达式的值，其中每个运算符
 * 或操作数用一个单独的参数表示。例如，命令 expr 2 3 4 + * 将计算表达
 * 式 2 * (3 + 4) 的值。
 */

#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

#define STACK_SIZE 100

static unsigned char op = 0;

static char stack[STACK_SIZE];

static int push(char c);

static char pop(void);

/* expr 函数：计算从命令行输入的逆波兰表达式的值 */
double expr(int argc, char *argv[]);

int main(int argc, char *argv[]) {
  if (argc == 1) {
    printf("Usage: expr EXPRESSION\n");
    exit(1);
  }
  double n = expr(argc, argv);
  printf("%.2f\n", n);
  return 0;
}

double expr(int argc, char *argv[]) {
  char c;
  for (; --argc > 0;) {
    c = (*++argv)[0];
    if (isdigit(c)) {
      push(c - '0');
    } else if (c == '+') {
      push(pop() + pop());
    } else if (c == '-') {
      char c1 = pop();
      char c2 = pop();
      push(c2 - c1);
    } else if (c == '*') {
      push(pop() * pop());
    } else if (c == '/') {
      char c1 = pop();
      char c2 = pop();
      push(c2 / c1);
    } else {
      fprintf(stderr, "error: unknow command %c\n", c);
      exit(3);
    }
  }
  return pop();
}

static int push(char c) {
  extern unsigned char op;
  extern char stack[];
  if (op >= 0 && op < STACK_SIZE) {
    stack[op++] = c;
    return 0;
  } else {
    fprintf(stderr, "error: stack full, can't push %d\n", c);
    exit(2);
  }
}

static char pop() {
  extern unsigned char op;
  extern char stack[];
  if (op >= 0 && op < STACK_SIZE) {
    return stack[--op];
  } else {
    fprintf(stderr, "error: stack empty\n");
    exit(2);
  }
}
