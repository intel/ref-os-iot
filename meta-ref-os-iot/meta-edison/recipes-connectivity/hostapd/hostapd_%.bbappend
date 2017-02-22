SYSTEMD_SERVICE_${PN} = "hostapd.service udhcpd-for-hostapd.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://defconfig \
    file://hostapd.service \
    file://udhcpd-for-hostapd.service \
    file://hostapd.conf-sane \
    file://udhcpd-for-hostapd.conf \
"

do_install_append() {
    install -d ${D}${sysconfdir}/hostapd
    install -m 0644 ${WORKDIR}/hostapd.conf-sane ${D}${sysconfdir}/hostapd/hostapd.conf
    install -m 0644 ${WORKDIR}/udhcpd-for-hostapd.conf ${D}${sysconfdir}/hostapd

    install -d ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/hostapd.service ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/udhcpd-for-hostapd.service ${D}${systemd_unitdir}/system
}

