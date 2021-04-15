//
// Created by MaoMao on 2021/4/15.
//
#include <arpa/inet.h>
#include <ifaddrs.h>
#include <stdbool.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>

bool str_start_with(char *str, char *prefix) {
  for (; *str != '\0' && *prefix != '\0';) {
    if (*str++ != *prefix++) {
      return false;
    }
  }
  return true;
}

int main(int argc, char *argv[]) {
  struct ifaddrs *ifaddr = NULL;
  // https://man7.org/linux/man-pages/man3/getifaddrs.3.html
  if (getifaddrs(&ifaddr) < 0) {
    perror("Error: getifaddrs");
    return EXIT_FAILURE;
  }

  for (struct ifaddrs *ifa = ifaddr; ifa != NULL; ifa = ifa->ifa_next) {
    if (ifa->ifa_addr == NULL) continue;
    if (ifa->ifa_addr->sa_family != AF_INET) continue;

    // 将通用的 sockaddr 结构转换成 IPv4 中的 sockaddr_in 结构
    struct sockaddr_in *sock_addr = (struct sockaddr_in *)ifa->ifa_addr;
    char addr_str[INET_ADDRSTRLEN];
    // https://man7.org/linux/man-pages/man3/inet_ntop.3.html
    inet_ntop(AF_INET, &(sock_addr->sin_addr), addr_str, INET_ADDRSTRLEN);

    if (str_start_with(addr_str, "127")) continue;
    printf("interface: %s, family: %d, ip: %s\n", ifa->ifa_name,
           ifa->ifa_addr->sa_family, addr_str);
  }

  freeifaddrs(ifaddr);
  return EXIT_SUCCESS;
}