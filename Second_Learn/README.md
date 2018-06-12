# 学习使用CMakeLists编译so库

## 在Android Studio 2.2以上，当我们新建工程并勾选支持c++的时候，默认会在app目录下生成CMaeLists.txt文件，因此使用CMake来编译c/c++文件是官方推荐的

## CMake 常用指令解释

```
# 版本号
cmake_minimum_required(VERSION 3.4.1)

# 要显示执行构建过程中详细的信息(比如为了得到更详细的出错信息)
set(CMAKE_VERBOSE_MAKEFILE on)

# 设置生成的so动态库最后输出的路径，注意此语句要在add_library语句之前，否则不能生效
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/src/main/myLibs/${ANDROID_ABI})

# 链接外部第三方库
set(LINK_DIR ${CMAKE_CURRENT_LIST_DIR}/../jniLibs/${ANDROID_ABI})
link_directories(${LINK_DIR})

# 链接第三方库的时候，有时候需要第三方的头文件，这时候可通过该方法指定路径
set(INCLUDE_DIR "头文件路径")
include_directories(${CMAKE_CURRENT_LIST_DIR} ${INCLUDE_DIR})

# 查找当前目录下的所有源文件, 并将名称保存到 SOURCE_DIR 变量
aux_source_directory(${CMAKE_CURRENT_LIST_DIR} SOURCE_DIR)

# 配置so库信息
add_library( 
            # 生成的so库名称，此处生成的so文件名称是 secondndk.so
             secondndk

             # STATIC：静态库，是目标文件的归档文件，在链接其它目标的时候使用
             # SHARED：动态库，会被动态链接，在运行时被加载
             # MODULE：模块库，是不会被链接到其它目标中的插件，但是可能会在运行时使用dlopen-系列的函数动态链接
             SHARED

             # 使用变量来替代一个一个写入源文件地址
             ${SOURCE_DIR} )

# 从系统查找依赖库
find_library( 
              # android系统每个类型的库会存放一个特定的位置，而log库存放在log-lib中
              log-lib
              
              
              # android系统在c环境下打log到logcat的库
              log )

# 配置库的链接（依赖关系）
target_link_libraries( 
                       # 目标库
                       secondndk
                       
                       
                       # 依赖库
                       firstndk
                       ${log-lib} )
```


# 可参考资料
## [CMake官方文档][0]，[CMake中文翻译][1]
## 其他[资料1][2], [资料2][3], [资料3][4], [资料4][5]


[0]: https://cmake.org/cmake-tutorial/
[1]: https://www.zybuluo.com/khan-lau/note/254724
[2]: https://www.jianshu.com/p/6332418b12b1
[3]: https://blog.csdn.net/minghuang2017/article/details/78938852
[4]: https://www.jianshu.com/p/33126d6baa3c
[5]: https://www.cnblogs.com/chenxibobo/p/7678389.html

