//
// Created by andy on 19-2-24.
//

#ifndef VULKAN01_VULKAN_APP_BASE_H
#define VULKAN01_VULKAN_APP_BASE_H

#include <stdint.h>
#include <android/input.h>
#include <android_native_app_glue.h>
#include <android/log.h>
#include "vulkan/vulkan.h"

extern PFN_vkCreateInstance vkCreateInstance_a;
extern PFN_vkGetDeviceProcAddr vkGetDeviceProcAddr_a;
extern PFN_vkGetInstanceProcAddr vkGetInstanceProcAddr_a;
extern PFN_vkEnumerateInstanceExtensionProperties vkEnumerateInstanceExtensionProperties_a;
extern PFN_vkEnumerateInstanceLayerProperties vkEnumerateInstanceLayerProperties_a;

#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "vulkanandroid", __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, "vulkanandroid", __VA_ARGS__))
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, "vulkanandroid", __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, "vulkanandroid", __VA_ARGS__))

class VulkanAppBase{
public:
    VulkanAppBase(void);
    virtual ~VulkanAppBase();
    void renderLoop();
    static int32_t handleAppInput(struct android_app* app, AInputEvent* event);
    static void handleAppCommand(android_app* app, int32_t cmd);
    bool loadVulkanLibrary(void);
};

#endif //VULKAN01_VULKAN_APP_BASE_H
