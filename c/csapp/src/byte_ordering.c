#include <stdio.h>
#include <string.h>

typedef unsigned char *byte_pointer;

void show_bytes(byte_pointer start, size_t len) {
  int i;
  for (i = 0; i < len; i++) {
    printf("%.2x ", start[i]);
  }
  printf("\n");
}

void show_int(int x) { show_bytes((byte_pointer)&x, sizeof(int)); }

void show_float(float x) { show_bytes((byte_pointer)&x, sizeof(int)); }

void show_pointer(void *x) { show_bytes((byte_pointer)&x, sizeof(void *)); }

void show_string(char *s) { show_bytes((byte_pointer)s, strlen(s)); }

int main() {
  int ival = 12345;
  float fval = (float)ival;
  int *pval = &ival;
  char *sval = "abcdef";

  show_int(ival);
  show_float(fval);
  show_pointer(pval);
  show_string(sval);
  return 0;
}