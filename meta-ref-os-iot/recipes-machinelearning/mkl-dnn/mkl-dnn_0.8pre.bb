SUMMARY = "Intel Math Kernel Library for Deep Neural Networks"
HOMEPAGE = "https://github.com/01org/mkl-dnn"
BUGTRACKER = "https://github.com/01org/mkl-dnn/issues"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/01org/mkl-dnn.git;protocol=https \
	   file://0001-add-install-for-examples.patch"

# Using a newer version than 0.7 as it fails to run
# due to "Illegal instruction" error, thus calling the version
# 0.8pre.
SRCREV = "7e0f2536c86dc4de9ad059d9fbcc21780dfa33e6"

S = "${WORKDIR}/git"

inherit cmake

PACKAGES =+ "${PN}-examples"

FILES_${PN} = "${libdir}/libmkldnn.so"
FILES_${PN}-dev = "${includedir}/mkl*"
FILES_${PN}-examples = "${bindir}/simple-*"
