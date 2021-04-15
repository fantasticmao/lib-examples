cmake_minimum_required(VERSION 3.17)

project(c-posix)

set(CMAKE_C_STANDARD 99)

add_executable(cat_vimrc src/fileio/cat_vimrc.c)
add_executable(cat_zshrc src/stdio/cat_zshrc.c)
add_executable(cat_passwd src/stdio/cat_passwd.c)
add_executable(ifconfig src/net_ipc/ifconfig.c)
add_executable(nslookup src/net_ipc/nslookup.c)