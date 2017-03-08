DESCRIPTION = "Linux reaalsense sdk projection MW binary and headers"
SECTION="examples"
LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=83f213feb5545ba55f98e2c4c9da3e03"

SRC_URI="\
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-sdk-projection/librealsense-sdk-projection0_0.7.1-0ubuntu1~336.gbp3a4257_amd64.deb;name=lib \
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-sdk-projection/librealsense-sdk-projection-dev_0.7.1-0ubuntu1~336.gbp3a4257_amd64.deb;name=devlib \
	file://LICENSE.md \
"
SRC_URI[lib.md5sum] = "6ac5fbc2816f3686c48aaa55cda92b71"
SRC_URI[devlib.md5sum] = "e67ec3b596de3b203586474c65452c22"

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
	install -m 0755  ${S}/usr/lib/librealsense_projection_opt.so.0.7.1 ${D}${libdir}
	ln -sf librealsense_projection_opt.so.0.7.1 ${D}${libdir}/librealsense_projection_opt.so.0
	ln -sf librealsense_projection_opt.so.0 ${D}${libdir}/librealsense_projection_opt.so
}

FILES_${PN} = " \
  ${libdir}/librealsense_projection_opt.so.0.7.1 \
  ${libdir}/librealsense_projection_opt.so.0 \
"

FILES_${PN}-dev = " \
  ${libdir}/librealsense_projection_opt.so \
"
