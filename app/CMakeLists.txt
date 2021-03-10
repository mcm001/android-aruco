cmake_minimum_required(VERSION 3.9)

project(arucomemes VERSION 2020.1.0)

# We only support C++17 and above
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED TRUE)

# Add sources
add_library(${PROJECT_NAME} SHARED
    src/main/cpp/com_mcm_lib_DevMain.h
    src/main/cpp/devmain.cpp
)
