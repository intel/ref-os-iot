FILESEXTRAPATHS_prepend := "${THISDIR}/rosinit:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += " \
    file://rosinit.sh \
"

do_install_append () {
    install -m 0755 -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/rosinit.sh ${D}/usr/bin
}
