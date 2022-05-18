cmake_minimum_required(VERSION 3.17)

# set the project name
project(k_and_r)

# specify the C standard
set(CMAKE_C_STANDARD 99)

if (NOT CMAKE_BUILD_TYPE)
    set(CMAKE_BUILD_TYPE RelWithDebInfo)
endif ()

# add the executable
add_executable(2-1 src/2-1.c)
add_executable(2-3 src/2-3.c)
add_executable(2-4 src/2-4.c)
add_executable(2-5 src/2-5.c)
add_executable(2-10 src/2-10.c)
add_executable(3-1 src/3-1.c)
add_executable(3-2 src/3-2.c)
add_executable(3-3 src/3-3.c)
add_executable(4-1 src/4-1.c)
add_executable(4-2 src/4-2.c)
add_executable(4-12 src/4-12.c)
add_executable(4-13 src/4-13.c)
add_executable(4-14 src/4-14.c)
add_executable(5-3 src/5-3.c)
add_executable(5-4 src/5-4.c)
add_executable(5-5 src/5-5.c)
add_executable(5-6 src/5-6.c)
add_executable(5-8 src/5-8.c)
add_executable(5-9 src/5-9.c)
add_executable(5-10 src/5-10.c)
add_executable(5-13 src/5-13.c)
add_executable(5-14 src/5-14.c)
add_executable(5-15 src/5-15.c)
add_executable(6-1 src/6-1.c)
add_executable(6-4 src/6-4.c)
