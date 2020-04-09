#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_zqb_jnilearning_Jni01Hello_getHelloString(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "zzqqbbZZQQBBzzqqbb";
    return env->NewStringUTF(hello.c_str());
}
