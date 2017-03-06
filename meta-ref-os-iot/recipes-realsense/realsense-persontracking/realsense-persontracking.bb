DESCRIPTION = "Linux person tracking MW binary and headers"
SECTION="examples"
LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f235a25ae606c05d89cf485b7ab9663b"

SRC_URI="https://s3-eu-west-1.amazonaws.com/realsense-linux/x86_64-ostro-linux-gnu/person_tracking/librealsense_persontracking_20160907_1.tar.bz2"
SRC_URI[md5sum] = "3b4b5720701a01be3234fccff49e7e57"
SRC_URI[sha256sum] = "315c41cbea8609aca713dde70f0628e305e34c409fd160bc47f069603ea341e8"

RDEPENDS_${PN} = " realsense-sdk libpng12 opencv"
PR = "r0"

inherit pkgconfig

S = "${WORKDIR}/librealsense_persontracking/"

PR = "r0"
PACKAGES = "${PN} ${PN}-dev ${PN}-dbg"

do_configure() {
}

do_compile() {
}

do_install() {
	oe_runmake install DESTDIR=${D} LINUX_DISTRIBUTION=yocto
}

FILES_${PN}="/usr/lib/*.so \
	    /usr/local/persontracking \
	    /usr/share/librealsense"

FILES_${PN}-dev = "/usr/include \
  	          "


