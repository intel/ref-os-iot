DESCRIPTION = "Linux person tracking MW binary and headers"
SECTION="examples"
LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=83f213feb5545ba55f98e2c4c9da3e03"

SRC_URI="\
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-persontracking/librealsense-persontracking0_0.5.10-0ubuntu1~87_amd64.deb;name=lib \
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-persontracking/librealsense-persontracking-dev_0.5.10-0ubuntu1~87_amd64.deb;name=devlib \
	file://LICENSE.md \
"
SRC_URI[lib.md5sum] = "216f88db8fc61d9144da4d139f2184f3"
SRC_URI[devlib.md5sum] = "bdd08569eca6e1855ad7c54f250e3d5b"

inherit bin_package

S = "${WORKDIR}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

# This is for skipping the QA problem where the ubuntu package has symlink to .so.x.x.x and we want to keep the same
INSANE_SKIP_${PN} += "dev-so already-stripped"

RDEPENDS_${PN} = "realsense-sdk libpng12 opencv libjpeg-turbo8"

PR = "r0"
PACKAGES = "${PN} ${PN}-dev"

do_install() {
	# FILES_${PN}
	install -d "${D}${libdir}"
	install -m 0755  ${S}/usr/lib/librealsense_persontracking.so.0.5.10 ${D}${libdir}
	ln -sf librealsense_persontracking.so.0.5.10 ${D}${libdir}/librealsense_persontracking.so.0
	install -d "${D}${datadir}/librealsense/pt/data/person_tracking"
	cp -dR ${S}/usr/share/librealsense/pt/data/person_tracking/* ${D}${datadir}/librealsense/pt/data/person_tracking/

	# FILES_${PN}-dev
	install -d "${D}${includedir}/librealsense"
	cp -dR ${S}/usr/include/librealsense/pt ${D}${includedir}/librealsense/
	ln -sf librealsense_persontracking.so.0 ${D}${libdir}/librealsense_persontracking.so
}

FILES_${PN} = " \
  ${libdir} \
  ${libdir}/librealsense_persontracking.so.0.5.10 \
  ${datadir}/librealsense/pt/data/person_tracking \
  ${datadir}/librealsense/pt/data/person_tracking/CNNSkeletonData \
  ${datadir}/librealsense/pt/data/person_tracking/Orientation \
"

FILES_${PN}-dev = " \
  ${includedir}/librealsense \
  ${includedir}/librealsense/pt/RealSense/PersonTracking \
"
