DESCRIPTION = "Linux object recognition MW binary and headers"
SECTION="examples"
LICENSE = "Intel"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=83f213feb5545ba55f98e2c4c9da3e03"

SRC_URI="\
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-object-recognition/librealsense-object-recognition0_0.5.1.rc0-0ubuntu2~57.gbp0901bb_amd64.deb;name=lib \
	http://realsense-alm-public.s3.amazonaws.com/apt-repo/pool/xenial/main/libr/librealsense-object-recognition/librealsense-object-recognition-dev_0.5.1.rc0-0ubuntu2~57.gbp0901bb_amd64.deb;name=devlib \
	file://LICENSE.md \
"
SRC_URI[lib.md5sum] = "6a34730bdf9c564c50d9c1337b100e64"
SRC_URI[devlib.md5sum] = "7786e89fc334d06943317bd8c78dfa27"

inherit bin_package

S = "${WORKDIR}"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

# This is for skipping the QA problem where the ubuntu package has symlink to .so.x.x.x and we want to keep the same
INSANE_SKIP_${PN} += "dev-so already-stripped"

RDEPENDS_${PN} = " libopencv-imgproc libopencv-highgui libopencv-core protobuf boost-system zlib boost-filesystem boost-thread boost-system libopencv-video libopencv-imgcodecs clBLAS"
PR = "r0"
PACKAGES = "${PN} ${PN}-dev"

do_install() {
	# FILES_${PN}
	install -d "${D}${libdir}"
	mkdir -p "${D}${datadir}/librealsense/object_recognition"
	install -m 0755 ${S}/usr/lib/x86_64-linux-gnu/librealsense_object_recognition.so.0.5.1 ${D}${libdir}
	ln -sf librealsense_object_recognition.so.0.5.1	${D}${libdir}/librealsense_object_recognition.so.0
	install -m 0644 ${S}/usr/share/librealsense/object_recognition/* ${D}${datadir}/librealsense/object_recognition

	# These are hacks to solve library dependencies, the ref-os-iot has newer versions than ubuntu
	# and ubuntu binaries link explictly to specific version
	ln -sf libopencv_highgui.so.3.2 ${D}${libdir}/libopencv_highgui.so.3.1
	ln -sf libopencv_imgcodecs.so.3.2 ${D}${libdir}/libopencv_imgcodecs.so.3.1
	ln -sf libopencv_imgproc.so.3.2 ${D}${libdir}/libopencv_imgproc.so.3.1
	ln -sf libopencv_flann.so.3.2 ${D}${libdir}/libopencv_flann.so.3.1
	ln -sf libopencv_core.so.3.2 ${D}${libdir}/libopencv_core.so.3.1

	# FILES_${PN}-dev
	mkdir -p "${D}${includedir}/librealsense/object_recognition"
	install -m 0644 ${S}/usr/include/librealsense/object_recognition/* ${D}${includedir}/librealsense/object_recognition
	ln -sf librealsense_object_recognition.so.0 ${D}${libdir}/librealsense_object_recognition.so
}

FILES_${PN} = " \
	    ${libdir}/librealsense_object_recognition.so.0 \
	    ${libdir}/librealsense_object_recognition.so.0.5.1 \
	    ${libdir}/libopencv_highgui.so.3.1 \
	    ${libdir}/libopencv_imgcodecs.so.3.1 \
	    ${libdir}/libopencv_imgproc.so.3.1 \
	    ${libdir}/libopencv_flann.so.3.1 \
	    ${libdir}/libopencv_core.so.3.1 \
	    ${datadir}/librealsense/object_recognition/yor_tool.classifier \
	    ${datadir}/librealsense/object_recognition/objects_recognition.classifier \
	    ${datadir}/librealsense/object_recognition/objects_localization.classifier \
"

FILES_${PN}-dev = " \
		${libdir}/librealsense_object_recognition.so \
		${includedir}/librealsense/object_recognition/or_configuration_interface.h \
		${includedir}/librealsense/object_recognition/or_video_module_impl.h \
		${includedir}/librealsense/object_recognition/or_data_interface.h \
		${includedir}/librealsense/object_recognition/or_interface.h \
"
