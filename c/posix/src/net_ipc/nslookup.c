//
// Created by MaoMao on 2021/4/15.
//
#include <arpa/inet.h>
#include <netdb.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>

int main(int argc, char* argv[]) {
  char* host = "httpbin.org";
  struct addrinfo hints;
  struct addrinfo *result, *p;

  // hint 是一个用于过滤地址的模板
  memset(&hints, 0, sizeof(hints));
  hints.ai_flags = AI_ALL;
  hints.ai_family = AF_INET;
  hints.ai_socktype = SOCK_STREAM;
  hints.ai_protocol = IPPROTO_TCP;
  hints.ai_addrlen = 0;
  hints.ai_canonname = NULL;
  hints.ai_addr = NULL;
  hints.ai_next = NULL;

  // https://man7.org/linux/man-pages/man3/getaddrinfo.3.html
  int error = getaddrinfo(host, NULL, &hints, &result);
  if (error != 0) {
    printf("%s\n", gai_strerror(error));
    return EXIT_FAILURE;
  }

  for (p = result; p != NULL; p = p->ai_next) {
    struct sockaddr_in* sock_addr = (struct sockaddr_in*)p->ai_addr;
    char addr_str[INET_ADDRSTRLEN];
    inet_ntop(AF_INET, &(sock_addr->sin_addr), addr_str, INET_ADDRSTRLEN);
    printf("host: %s, ip: %s\n", host, addr_str);
  }

  freeaddrinfo(result);
  return EXIT_SUCCESS;
}