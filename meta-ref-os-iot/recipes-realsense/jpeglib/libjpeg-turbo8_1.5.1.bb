SUMMARY = "Hardware accelerated JPEG compression/decompression library"
DESCRIPTION = "libjpeg-turbo is a derivative of libjpeg that uses SIMD instructions (MMX, SSE2, NEON) to accelerate baseline JPEG compression and decompression"
HOMEPAGE = "http://libjpeg-turbo.org/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cdjpeg.h;endline=13;md5=05bab7c7ad899d85bfba60da1a1271f2 \
                    file://jpeglib.h;endline=16;md5=f67d70e547a2662c079781c72f877f72 \
                    file://djpeg.c;endline=11;md5=b90b6d2b4119f9e5807cd273f525d2af \
"
DEPENDS = "nasm-native"

SRC_URI = "${SOURCEFORGE_MIRROR}/libjpeg-turbo/libjpeg-turbo-${PV}.tar.gz"
SRC_URI[md5sum] = "55deb139b0cac3c8200b75d485fc13f3"
SRC_URI[sha256sum] = "41429d3d253017433f66e3d472b8c7d998491d2f41caa7306b8d9a6f2a2c666c"
UPSTREAM_CHECK_URI = "http://sourceforge.net/projects/libjpeg-turbo/files/"
UPSTREAM_CHECK_REGEX = "/libjpeg-turbo/files/(?P<pver>(\d+[\.\-_]*)+)/"

PE= "1"

S = "${WORKDIR}/libjpeg-turbo-1.5.1"

inherit autotools pkgconfig

INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
EXTRA_OECONF = "--with-jpeg8 "

# Work around missing x32 ABI support
EXTRA_OECONF_append_class-target = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", "--without-simd", "", d)}"

PACKAGES = "${PN}-deb ${PN}"

DESCRIPTION_libturbojpeg8 = "A SIMD-accelerated JPEG codec which provides only TurboJPEG APIs"

do_install () {
  install -d ${D}${libdir}
  install -m 0755 ${WORKDIR}/build/.libs/libjpeg.so.8.1.2 ${D}${libdir}
  ln -sf libjpeg.so.8.1.2 ${D}${libdir}/libjpeg.so.8
}

FILES_${PN} = "\
  ${libdir}/libjpeg.so.8 \
  ${libdir}/libjpeg.so.8.1.2 \
"

FILES_${PN}-deb = "\
  ${libdir}/.debug \
"
BBCLASSEXTEND = "native"
