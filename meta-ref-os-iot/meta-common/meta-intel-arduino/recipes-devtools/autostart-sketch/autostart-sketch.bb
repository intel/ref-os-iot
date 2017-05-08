DESCRIPTION = "This is the edison arduino sketch autostart daemon."
HOMEPAGE = "http://www.intel.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/:"

SRC_URI = "file://autostart-sketch.service \
           file://autostart-sketch.sh \
"

S = "${WORKDIR}"

inherit systemd

SYSTEMD_SERVICE_${PN} = "autostart-sketch.service"

do_install () {
    install -d ${D}${bindir}
    install -m 0755 autostart-sketch.sh ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/autostart-sketch.service ${D}${systemd_unitdir}/system/
}

FILES_${PN} += "${systemd_unitdir}/system/autostart-sketch.service \
                ${bindir}/sketch-check.sh \ 
                "
