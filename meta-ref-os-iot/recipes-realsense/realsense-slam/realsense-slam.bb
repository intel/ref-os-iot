DESCRIPTION = "Linux person tracking MW binary and headers"
SECTION="examples"
LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c3a7207e0adf283763469a41486cd4f5"

SRC_URI="https://s3-eu-west-1.amazonaws.com/realsense-linux/x86_64-ostro-linux-gnu/slam/librealsense_slam_20160914.tar.bz2"
SRC_URI[md5sum] = "1cd7c8e098bbe9de495c35e414848764"
SRC_URI[sha256sum] = "04c265649d2fea6e09dc02f3e1f18bb9abe01634a10fd7e19c1605f4df2fe809"

inherit pkgconfig

RDEPENDS_${PN} = "ocl-icd opencv"

S = "${WORKDIR}/librealsense_slam"

PR = "r0"
PACKAGES = "${PN} ${PN}-dev"


do_configure() {
}

do_compile() {
}

do_install() {
	oe_runmake install DESTDIR=${D} LINUX_DISTRIBUTION=yocto
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN}="/usr/lib/*"

FILES_${PN}-dev = "/usr/include"


