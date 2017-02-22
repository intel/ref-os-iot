FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += " \
    file://usb-gadget.sh \
    file://usb-gadget.service \
    file://gettyusb.service \
"

inherit systemd

do_install_append () {
    install -m 0755 -d ${D}/lib/systemd/system
    install -m 0755 ${WORKDIR}/usb-gadget.service ${D}/lib/systemd/system
    install -m 0755 ${WORKDIR}/gettyusb.service ${D}/lib/systemd/system
    install -m 0755 -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/usb-gadget.sh ${D}/usr/bin
}

RDEPENDS_${PN} = "systemd"
FILES_${PN} = "/usr/bin /lib/systemd/system"

SYSTEMD_SERVICE_${PN} = " \
    usb-gadget.service \
    gettyusb.service \
"
