GCC_TOOLCHAIN = "virtual/${TARGET_PREFIX}gcc virtual/${TARGET_PREFIX}g++"

DEPENDS_remove_class-target = " clang-native "
DEPENDS_append_class-nativesdk = " virtual/${TARGET_PREFIX}binutils-crosssdk virtual/${TARGET_PREFIX}gcc-crosssdk virtual/${TARGET_PREFIX}g++-crosssdk"
DEPENDS_append_class-target = " clang-cross-${TARGET_ARCH} ${@bb.utils.contains('TOOLCHAIN', 'gcc', 'virtual/${TARGET_PREFIX}gcc virtual/${TARGET_PREFIX}g++', '', d)}"
