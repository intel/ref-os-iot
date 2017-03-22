SUMMARY = "a software library containing BLAS functions written in OpenCL"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

SRCREV = "1f3de2ae5582972f665c685b18ef0df43c1792bb"
SRC_URI = "\
	git://github.com/clMathLibraries/clBLAS.git;protocol=http \
	file://0001-Set-cmake-paths-for-begnet-opencv-libraries.patch \
	file://0001-Disable-library-test-suite-building.patch \
	file://0001-Use-native-compiler-for-tplgen.patch"

SRC_URI[blas.md5sum]="70f26f95c93fd3871a886bf4237e8268"
SRC_URI[blas.sha256sum]="55415f901bfc9afc19d7bd7cb246a559a748fc737353125fcce4c40c3dee1d86"

S = "${WORKDIR}/git/src"

DEPENDS="beignet-570x boost"
RDEPENDS_${PN}="libgcc beignet-570x"

inherit cmake pkgconfig pythonnative

do_install () {
  # FILES_${PN}
  install -d ${D}${libdir}
  install -m 0755 ${WORKDIR}/build/library/libclBLAS.so.2.12.0 ${D}${libdir}
  ln -sf libclBLAS.so.2.12.0 ${D}${libdir}/libclBLAS.so.2

  # FILES_${PN}-dev
  ln -sf libclBLAS.so.2 ${D}${libdir}/libclBLAS.so
  install -d ${D}${includedir}
  cp -d ${S}/*.h ${D}${includedir}
}

PACKAGES="${PN} ${PN}-dev ${PN}-dbg"

FILES_${PN} = "\
  ${libdir}/libclBLAS.so.2.12.0 \
  ${libdir}/libclBLAS.so.2 \
"

FILES_${PN}-dev = "\
  ${libdir}/libclBLAS.so \
  ${includedir}/clBLAS.version.h \
  ${includedir}/clBLAS-complex.h \
  ${includedir}/clAmdBlas.h \
  ${includedir}/clBLAS.h \
  ${includedir}/clAmdBlas.version.h \
  ${includedir}/targetver.h \
"

FILES_${PN}-dbg = "\
  ${libdir}/.debug/* \
"
