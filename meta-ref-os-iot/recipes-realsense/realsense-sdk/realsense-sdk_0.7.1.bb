DESCRIPTION = "Linux RealSense SDK recipe"
SECTION="examples"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=4151db0f01967ef808d8b232acf49628"

SRC_URI="https://github.com/IntelRealSense/realsense_sdk/archive/v${PV}.tar.gz"
SRC_URI[md5sum]="e390cea82c503b5865836d24cb695e9f"
SRC_URI[sha256sum]="a8aac4b046e2bd856ae1c5a2104732ee2eed78b5e12f733675422290271174ed"

DEPENDS="librealsense log4cxx gtest iotivity opencv lz4"
RDEPENDS_${PN}= "librealsense opencv gtest"

PR = "r0"

inherit pkgconfig cmake

S = "${WORKDIR}/realsense_sdk-${PV}/sdk"

OECMAKE_CXX_FLAGS+="-Wno-error=conversion -Wno-error=misleading-indentation"
