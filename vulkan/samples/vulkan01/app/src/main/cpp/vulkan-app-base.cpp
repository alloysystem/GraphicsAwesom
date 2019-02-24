//
// Created by andy on 19-2-24.
//

#include "vulkan-app-base.h"
#include <dlfcn.h>
#include <assert.h>

PFN_vkCreateInstance vkCreateInstance_a;
PFN_vkGetDeviceProcAddr vkGetDeviceProcAddr_a;
PFN_vkGetInstanceProcAddr vkGetInstanceProcAddr_a;
PFN_vkEnumerateInstanceExtensionProperties vkEnumerateInstanceExtensionProperties_a;
PFN_vkEnumerateInstanceLayerProperties vkEnumerateInstanceLayerProperties_a;

VulkanAppBase::VulkanAppBase() {
    //load vulkan so from android
    bool result = loadVulkanLibrary();
    assert(result);

}

VulkanAppBase::~VulkanAppBase() {
    __android_log_print(ANDROID_LOG_INFO, "vulkanandroid", "Vulkan app base destroy");
}

void VulkanAppBase::renderLoop() {
    __android_log_print(ANDROID_LOG_INFO, "vulkanandroid", "Vulkan renderLoop");
}

void VulkanAppBase::handleAppCommand(android_app * app, int32_t cmd)
{
    assert(app->userData != NULL);
    VulkanAppBase* vulkanExample = reinterpret_cast<VulkanAppBase*>(app->userData);
    switch (cmd)
    {
        case APP_CMD_SAVE_STATE:
            LOGD("APP_CMD_SAVE_STATE");
            break;
        case APP_CMD_INIT_WINDOW:
            LOGD("APP_CMD_INIT_WINDOW");
            break;
        case APP_CMD_LOST_FOCUS:
            LOGD("APP_CMD_LOST_FOCUS");
            break;
        case APP_CMD_GAINED_FOCUS:
            LOGD("APP_CMD_GAINED_FOCUS");
            break;
        case APP_CMD_TERM_WINDOW:
            // Window is hidden or closed, clean up resources
            LOGD("APP_CMD_TERM_WINDOW");
            break;
    }
}

int32_t VulkanAppBase::handleAppInput(struct android_app* app, AInputEvent* event)
{
    VulkanAppBase* vulkanExample = reinterpret_cast<VulkanAppBase*>(app->userData);
    if (AInputEvent_getType(event) == AINPUT_EVENT_TYPE_MOTION)
    {
        int32_t eventSource = AInputEvent_getSource(event);
        switch (eventSource) {
            case AINPUT_SOURCE_JOYSTICK: {
                // Left thumbstick
                break;
            }

            case AINPUT_SOURCE_TOUCHSCREEN: {
                int32_t action = AMotionEvent_getAction(event);

                switch (action) {
                    case AMOTION_EVENT_ACTION_UP: {

                        return 1;
                        break;
                    }
                    case AMOTION_EVENT_ACTION_DOWN: {
                        // Detect double tap
                        break;
                    }
                    case AMOTION_EVENT_ACTION_MOVE: {
                        break;
                    }
                    default:
                        return 1;
                        break;
                }
            }

                return 1;
        }
    }

    if (AInputEvent_getType(event) == AINPUT_EVENT_TYPE_KEY)
    {
        int32_t keyCode = AKeyEvent_getKeyCode((const AInputEvent*)event);
        int32_t action = AKeyEvent_getAction((const AInputEvent*)event);
        int32_t button = 0;

        if (action == AKEY_EVENT_ACTION_UP)
            return 0;

        switch (keyCode)
        {
            case AKEYCODE_BUTTON_A:
                break;
            case AKEYCODE_BUTTON_B:
                break;
            case AKEYCODE_BUTTON_X:
                break;
            case AKEYCODE_BUTTON_Y:
                break;
            case AKEYCODE_BUTTON_L1:
                break;
            case AKEYCODE_BUTTON_R1:
                break;
            case AKEYCODE_BUTTON_START:
                break;
        };

        LOGD("Button %d pressed", keyCode);
    }

    return 0;
}


void *libVulkan;
bool VulkanAppBase::loadVulkanLibrary(void)
{
    __android_log_print(ANDROID_LOG_INFO, "vulkanandroid", "Loading libvulkan.so...\n");

    // Load vulkan library
    libVulkan = dlopen("libvulkan.so", RTLD_NOW | RTLD_LOCAL);
    if (!libVulkan)
    {
        __android_log_print(ANDROID_LOG_INFO, "vulkanandroid", "Could not load vulkan library : %s!\n", dlerror());
        return false;
    }

    // Load base function pointers
    vkEnumerateInstanceExtensionProperties_a = reinterpret_cast<PFN_vkEnumerateInstanceExtensionProperties>(dlsym(libVulkan, "vkEnumerateInstanceExtensionProperties"));
    vkEnumerateInstanceLayerProperties_a = reinterpret_cast<PFN_vkEnumerateInstanceLayerProperties>(dlsym(libVulkan, "vkEnumerateInstanceLayerProperties"));
    vkCreateInstance_a = reinterpret_cast<PFN_vkCreateInstance>(dlsym(libVulkan, "vkCreateInstance"));
    vkGetInstanceProcAddr_a = reinterpret_cast<PFN_vkGetInstanceProcAddr>(dlsym(libVulkan, "vkGetInstanceProcAddr"));
    vkGetDeviceProcAddr_a = reinterpret_cast<PFN_vkGetDeviceProcAddr>(dlsym(libVulkan, "vkGetDeviceProcAddr"));

    return true;
}