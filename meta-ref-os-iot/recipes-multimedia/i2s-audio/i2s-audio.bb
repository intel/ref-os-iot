DESCRIPTION = "Kernel module configuration for snd-soc-skl audio drivers for raw i2s codecs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
    file://modules.conf \
    file://arizona-i2c.conf \
"

do_install_append() {
    install -d ${D}/etc/modprobe.d
    install -m 0644 ${WORKDIR}/modules.conf ${D}/etc/modprobe.d/modules.conf
    install -d ${D}/etc/modules-load.d
    install -m 0644 ${WORKDIR}/arizona-i2c.conf ${D}/etc/modules-load.d/arizona-i2c.conf
}

FILES_${PN} += " \
    /etc/modprobe.d/modules.conf \
    /etc/modules-load.d/arizona-i2c.conf \
"
