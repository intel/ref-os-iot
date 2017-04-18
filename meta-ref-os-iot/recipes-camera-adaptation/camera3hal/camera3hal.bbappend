FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "file://files/0001-Add-camerahal.cfg-file.patch"

do_install_append() {
    install -m 0644 ${S}/etc/camerahal.cfg ${D}/etc/
}

