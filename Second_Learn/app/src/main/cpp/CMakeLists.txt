# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

# 版本号
cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

# 要显示执行构建过程中详细的信息(比如为了得到更详细的出错信息)
set(CMAKE_VERBOSE_MAKEFILE on)

# 设置生成的so动态库最后输出的路径，注意此语句要在add_library语句之前，否则不能生效
# set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/myLibs/${ANDROID_ABI})

# 链接外部第三方库
set(LINK_DIR ${CMAKE_CURRENT_LIST_DIR}/../jniLibs/${ANDROID_ABI})
link_directories(${LINK_DIR})

# 链接第三方库的时候，有时候需要第三方的头文件，这时候可通过该方法指定路径
# set(INCLUDE_DIR "头文件路径")
# include_directories(${CMAKE_CURRENT_LIST_DIR} ${INCLUDE_DIR})

# 查找当前目录下的所有源文件, 并将名称保存到 SOURCE_DIR 变量
aux_source_directory(${CMAKE_CURRENT_LIST_DIR} SOURCE_DIR)

# 配置so库信息
add_library( # Sets the name of the library.
            # 生成的so库名称，此处生成的so文件名称是 secondndk.so
             secondndk

             # Sets the library as a shared library.
             # STATIC：静态库，是目标文件的归档文件，在链接其它目标的时候使用
             # SHARED：动态库，会被动态链接，在运行时被加载
             # MODULE：模块库，是不会被链接到其它目标中的插件，但是可能会在运行时使用dlopen-系列的函数动态链接
             SHARED

             # Provides a relative path to your source file(s).
             # 使用变量来替代一个一个写入源文件地址
             ${SOURCE_DIR} )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

# 从系统查找依赖库
find_library( # Sets the name of the path variable.
              # android系统每个类型的库会存放一个特定的位置，而log库存放在log-lib中
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              # android系统在c环境下打log到logcat的库
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

# 配置库的链接（依赖关系）
target_link_libraries( # Specifies the target library.

                       # 目标库
                       secondndk

                       # Links the target library to the log library
                       # included in the NDK.
                       # 依赖库

                       firstndk
                       ${log-lib} )