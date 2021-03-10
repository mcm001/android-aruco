// Copyright 2020-2021 Declan Freeman-Gleason
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this program.  If not, see
// <https://www.gnu.org/licenses/>.

#include "t265wrapper.hpp"

#include <algorithm>
#include <chrono>
#include <cmath>
#include <cstring>
#include <fstream>
#include <iostream>
#include <iterator>
#include <thread>
#include <vector>

#include <android/log.h>


JNIEXPORT void JNICALL Java_com_spartronics4915_lib_T265Camera_main(JNIEnv *,
                                                                       jclass) {

                                                                       }

// We use jlongs like pointers, so they better be large enough
static_assert(sizeof(jlong) >= sizeof(void *));
