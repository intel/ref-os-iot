DESCRIPTION = "Linux RealSense SDK recipe"
SECTION="examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=4151db0f01967ef808d8b232acf49628"

SRC_URI="https://github.com/IntelRealSense/realsense_sdk/archive/v${PV}.tar.gz"
SRC_URI[md5sum]="e390cea82c503b5865836d24cb695e9f"
SRC_URI[sha256sum]="a8aac4b046e2bd856ae1c5a2104732ee2eed78b5e12f733675422290271174ed"

DEPENDS="librealsense log4cxx gtest iotivity opencv lz4"
RDEPENDS_${PN}= "librealsense opencv gtest"
RDEPENDS_${PN}-dev= " ${PN}-staticdev"

PR = "r0"

inherit pkgconfig cmake

S = "${WORKDIR}/realsense_sdk-${PV}/sdk"

PACKAGES = "${PN} ${PN}-dev ${PN}-staticdev ${PN}-dbg"

FILES_${PN}+=" \
                /usr/lib/librs_log_utils.so \
                /usr/lib/librs_max_depth_value_module.so \
                /usr/lib/librs_projection.so \
             "


