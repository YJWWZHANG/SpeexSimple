#include <jni.h>

jstring cpp_get_string(JNIEnv *env, jobject obj) {
    return env->NewStringUTF("动态注册");
}

jstring cpp_to_string(JNIEnv *env, jobject obj, jstring s) {
    const char *chars = env->GetStringUTFChars(s, NULL);
    env->ReleaseStringUTFChars(s, chars);
    return env->NewStringUTF(chars);
}

JNINativeMethod nativeMethod[] = {
        {"cpp_get_string", "()Ljava/lang/String;", (jstring *) cpp_get_string},
        {"cpp_to_string", "(Ljava/lang/String;)Ljava/lang/String;", (jstring *) cpp_to_string},
};

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved) {
    JNIEnv *env;
    if (jvm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }
    jclass clz = env->FindClass("com/zqb/jnilearning/Jni02DynamicRegister");
    env->RegisterNatives(clz, nativeMethod, sizeof(nativeMethod) / sizeof(JNINativeMethod));
    return JNI_VERSION_1_4;
}


