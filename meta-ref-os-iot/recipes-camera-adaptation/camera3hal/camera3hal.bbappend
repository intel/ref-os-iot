do_install_append() {
    install -m 0644 ${THISDIR}/../../../../recipes-camera-adaptation/camera3hal/camerahal.cfg ${D}/etc/
}

