FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " file://thermal-conf-BroxtonM.xml \
             file://thermald.service          \
           "

do_install_append() {
    install -m 0644 ${WORKDIR}/thermal-conf-BroxtonM.xml ${D}${sysconfdir}/thermald/thermal-conf.xml
    install -m 0644 ${WORKDIR}/thermald.service ${D}${systemd_unitdir}/system/
}
