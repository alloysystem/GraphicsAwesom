#include <jni.h>
#include <string>
#include <android_native_app_glue.h>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_andy_vulkan01_AlloySystemVulkanActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello Alloysystem Vulkan";
    return env->NewStringUTF(hello.c_str());
}
static android_app* androidApp;

void android_main(android_app* state)
{

    androidApp = state;

}