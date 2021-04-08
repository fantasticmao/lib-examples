/**
 * 练习 6-4
 *
 * 编写一个程序，根据单词的出现频率按降序打印输入的各个不同单词，并在每
 * 个单词的前面标上它的出现次数。
 */

#include <ctype.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXWORD 100

struct tnode {         /* 树的节点 */
  char *word;          /* 指向单词的指针 */
  int count;           /* 单词出现的次数 */
  struct tnode *left;  /* 左子节点 */
  struct tnode *right; /* 右子节点 */
};

void freenode(struct tnode *p);

/* addnode 函数：在 p 的位置或 p 的下方增加一个节点 */
struct tnode *addnode(struct tnode *p, char *word);

/* treeprint 函数：按单词的出现频率降打印树 p */
void treeprint(struct tnode *p);

/* getword 函数：从文件中读取下一个单词 */
int getword(char *word, int lim, FILE *fp);

int main(int argc, char *argv[]) {
  if (argc <= 1) {
    fprintf(stderr, "usage: %s file\n", argv[0]);
    return 1;
  }
  FILE *fp = fopen(argv[1], "r");
  struct tnode *root = NULL;
  char word[MAXWORD];
  int c;
  for (; (c = getword(word, MAXWORD, fp)) != EOF;) {
    if (isalpha(c)) {
      root = addnode(root, word);
    }
  }
  treeprint(root);
  freenode(root);
  return 0;
}

void freenode(struct tnode *p) {
  if (p != NULL) {
    freenode(p->left);
    freenode(p->right);
    free(p);
  }
}

struct tnode *addnode(struct tnode *p, char *word) {
  if (p == NULL) {
    p = (struct tnode *)malloc(sizeof(struct tnode));
    p->word = strdup(word);
    p->count = 1;
    p->left = NULL;
    p->right = NULL;
    return p;
  }

  int r = strcmp(p->word, word);
  if (r == 0) {
    p->count++;
  } else if (r < 0) {
    p->right = addnode(p->right, word);
  } else {
    p->left = addnode(p->left, word);
  }
  return p;
}

void treeprint(struct tnode *p) {
  if (p != NULL) {
    treeprint(p->left);
    printf("%4d %s\n", p->count, p->word);
    treeprint(p->right);
  }
}

int getword(char *word, int lim, FILE *fp) {
  char c;
  char *w = word;
  for (; isspace(c = getc(fp));) {
    ;
  }
  if (c == EOF) {
    *w = '\0';
    return c;
  }

  *w++ = c;

  for (; --lim > 0 && (isalnum(c = getc(fp)) || c == '-' || c == '\'');) {
    *w++ = c;
  }
  ungetc(c, fp);
  *w = '\0';
  return *word;
}
