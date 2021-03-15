/**
 * 练习 6-1
 *
 * 上述 getword 函数不能正确处理下划线、字符串常量、注释及预处理器控制指
 * 令。请编写一个更完善的 getword 函数。
 */

#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

#define MAXWORD 100

/* getword 函数：从输入中读取下一个单词，单词可以是以字母开头的字母和数字
 * 串，也可以是一个非空白字符。函数返回值可能是单词的第一个字符、文件结束符
 * EOF 或字符本身。
 */
int getword(char *word, int lim, FILE *fp);

int main(int argc, char *argv[]) {
  if (argc > 1) {
    FILE *fp = fopen(argv[1], "r");
    char word[MAXWORD];
    int c;
    for (; (c = getword(word, MAXWORD, fp)) != EOF;) {
      if (isalnum(c)) {
        printf("%s\n", word);
      }
    }
  } else {
    fprintf(stderr, "usage: %s test.c \n", argv[0]);
    exit(2);
  }
  return 0;
}

int getword(char *word, int lim, FILE *fp) {
  char c;
  char *w = word;
  // 跳过空白符
  for (; isspace(c = getc(fp));) {
    ;
  }

  // 跳过字符串常量
  if (c == '"') {
    for (; (c = getc(fp) != '"');) {
      ;
    }
  }

  // 跳过注释
  if (c == '/') {
    char c2 = getc(fp);
    if (c2 == '/') {
      for (; (c = getc(fp)) != '\n';) {
        ;
      }
    } else if (c2 == '*') {
      for (; (c = getc(fp)) != '*' || (c = getc(fp)) != '/';) {
        ;
      }
    } else {
      ungetc(c2, fp);
    }
  }

  // 跳过预处理器控制指令
  if (c == '#') {
    for (; (c = getc(fp)) != '\n';) {
      ;
    }
  }

  if (c != EOF) {
    *w++ = c;
  }
  // 单词必须是以字母开头的字母和数字串，下划线“_”被看作字母
  if (!isalpha(c) && c != '_') {
    *w = '\0';
    return c;
  }

  for (; --lim > 0 && (isalnum(c = getc(fp)) || c == '_');) {
    *w++ = c;
  }
  ungetc(c, fp);
  *w = '\0';
  return *word;
}