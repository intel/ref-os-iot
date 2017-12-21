DESCRIPTION = "Linux RealSense SDK recipe"
SECTION="examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=4151db0f01967ef808d8b232acf49628"

SRC_URI="https://github.com/IntelRealSense/realsense_sdk/archive/v${PV}.tar.gz"
SRC_URI[md5sum]="60e59f8d7f0531e4f4f2ba41d0d4b149"
SRC_URI[sha256sum]="2b5ac25c6bf8e8a05b8a74dd4c701cd16e053433cbe2b32ab79b52c11da39532"

DEPENDS="librealsense log4cxx gtest iotivity opencv lz4"
RDEPENDS_${PN}= "librealsense opencv gtest"

PR = "r0"

inherit pkgconfig cmake

S = "${WORKDIR}/realsense_sdk_zr300-${PV}/sdk"

OECMAKE_CXX_FLAGS+="-Wno-error=conversion -Wno-error=misleading-indentation"
