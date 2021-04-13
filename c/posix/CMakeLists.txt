cmake_minimum_required(VERSION 3.17)

project(c-posix)

set(CMAKE_C_STANDARD 99)

add_executable(cat_vimrc src/fileio/cat_vimrc.c)
add_executable(cat_zshrc src/stdio/cat_zshrc.c)