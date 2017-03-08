DESCRIPTION = "Linux realsense slam MW binary and headers"
SECTION="examples"
LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=83f213feb5545ba55f98e2c4c9da3e03"

SRC_URI="\
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-slam/librealsense-slam2_2.0.4-0ubuntu1~25.gbp73dfd6_amd64.deb;name=lib \
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-slam/librealsense-slam-dev_2.0.4-0ubuntu1~25.gbp73dfd6_amd64.deb;name=devlib \
	file://LICENSE.md \
"
SRC_URI[lib.md5sum] = "887d223ea0f16a28513ebadaf676c220"
SRC_URI[devlib.md5sum] = "2fcf84d687933a4c7bb7f0761e5ee530"

inherit bin_package

S = "${WORKDIR}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

# This is for skipping the QA problem where the ubuntu package has symlink to .so.x.x.x and we want to keep the same
INSANE_SKIP_${PN} += "dev-so already-stripped"

PR = "r0"
PACKAGES = "${PN} ${PN}-dev"

do_install() {
	# FILES_${PN}
	install -d "${D}${libdir}"
	install -m 0755 ${S}/usr/lib/libtracker.so.2.0.4 ${D}${libdir}
  	install -m 0755 ${S}/usr/lib/librealsense_slam.so.2.0.4 ${D}${libdir}
  	install -m 0755 ${S}/usr/lib/libSP_Core.so.2.0.4 ${D}${libdir}
	ln -sf libtracker.so.2.0.4 ${D}${libdir}/libtracker.so.2
	ln -sf librealsense_slam.so.2.0.4 ${D}${libdir}/librealsense_slam.so.2
	ln -sf libSP_Core.so.2.0.4 ${D}${libdir}/libSP_Core.so.2

	# FILES_${PN}-dev
	ln -sf libtracker.so.2 ${D}${libdir}/libtracker.so
	ln -sf librealsense_slam.so.2 ${D}${libdir}/librealsense_slam.so
	ln -sf libSP_Core.so.2 ${D}${libdir}/libSP_Core.so
	install -d ${D}${includedir}/librealsense/slam
	cp -d ${S}/usr/include/librealsense/slam/*.h ${D}${includedir}/librealsense/slam/
}

FILES_${PN} = " \
  ${libdir}/libtracker.so.2.0.4 \
  ${libdir}/librealsense_slam.so.2.0.4 \
  ${libdir}/libSP_Core.so.2.0.4 \
  ${libdir}/libtracker.so.2 \
  ${libdir}/librealsense_slam.so.2 \
  ${libdir}/libSP_Core.so.2 \
"

FILES_${PN}-dev = " \
  ${libdir}/libtracker.so \
  ${libdir}/librealsense_slam.so \
  ${libdir}/libSP_Core.so \
  ${includedir}/librealsense/slam \
"
