#include <jni.h>
#include <string>
#include <android_native_app_glue.h>
#include "vulkan-app-base.h"

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_andy_vulkan01_AlloySystemVulkanActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello Alloysystem Vulkan";
    return env->NewStringUTF(hello.c_str());
}
static android_app* androidApp;
static VulkanAppBase * pAppBase;
static bool focused = true;
void android_main(android_app* state)
{
    pAppBase = new VulkanAppBase();
    state->userData = pAppBase;
    state->onAppCmd = VulkanAppBase::handleAppCommand;
    state->onInputEvent = VulkanAppBase::handleAppInput;

    androidApp = state;
    while(true)
    {
        int ident;
        int events;
        struct android_poll_source * source;
        bool destroy = false;

        while ((ident = ALooper_pollAll(focused ? 0:-1, NULL, &events, (void **)&source)) >= 0)
        {
            if(source != NULL)
            {
                source->process(androidApp, source);
            }
            if( androidApp->destroyRequested != 0)
            {
                LOGD("Android app destroy requested");
                destroy = true;
                //ANativeActivity_finish(androidApp->activity);
                break;
            }
        }

        if(destroy == true)
        {
            ANativeActivity_finish(androidApp->activity);
            break;
        }
    }
}