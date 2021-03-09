cmake_minimum_required(VERSION 3.17)

# set the project name
project(the_c_programming_language)

# specify the C standard
set(CMAKE_C_STANDARD 99)

if(NOT CMAKE_BUILD_TYPE)
    set(CMAKE_BUILD_TYPE RelWithDebInfo)
endif()

# add the executable
add_executable(2-1 2-1.c)
add_executable(2-3 2-3.c)
add_executable(2-4 2-4.c)
add_executable(2-5 2-5.c)
add_executable(2-10 2-10.c)
add_executable(3-1 3-1.c)
add_executable(3-2 3-2.c)
add_executable(3-3 3-3.c)
add_executable(4-1 4-1.c)
add_executable(4-2 4-2.c)
add_executable(4-12 4-12.c)
add_executable(4-13 4-13.c)
add_executable(4-14 4-14.c)
add_executable(5-3 5-3.c)
add_executable(5-4 5-4.c)
add_executable(5-5 5-5.c)
add_executable(5-6 5-6.c)
add_executable(5-8 5-8.c)
add_executable(5-9 5-9.c)
add_executable(5-10 5-10.c)
add_executable(5-13 5-13.c)
