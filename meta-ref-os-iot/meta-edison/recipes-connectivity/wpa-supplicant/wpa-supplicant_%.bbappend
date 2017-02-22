SYSTEMD_SERVICE_${PN} = "wpa_supplicant.service wpa_supplicant-nl80211@.service wpa_supplicant-wired@.service wpa_supplicant_wlan0_event.service wpa_supplicant_p2p_event.service"
SYSTEMD_AUTO_ENABLE = "enable"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://wpa_supplicant.conf-sane \
           file://p2p_supplicant.conf-sane \
           file://fi.w1.wpa_supplicant1.service \
           file://udhcpd-p2p.conf \
           file://wpa_supplicant.service \
           file://wpa_supplicant_wlan0_event.service \
           file://wpa_supplicant_p2p_event.service \
           file://wpa_cli-actions.sh \
"

do_install_append () {

        install -d ${D}${sysconfdir}/wpa_supplicant
        install -m 600 ${WORKDIR}/wpa_supplicant.conf-sane ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant.conf
        install -m 600 ${WORKDIR}/p2p_supplicant.conf-sane ${D}${sysconfdir}/wpa_supplicant/p2p_supplicant.conf

	# overwrite the service file with our modified one
        install -m 644 ${WORKDIR}/fi.w1.wpa_supplicant1.service ${D}/${datadir}/dbus-1/system-services
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then

            install -d ${D}/${systemd_unitdir}/system
            install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants

            # Install the wpa_supplicant service
            install -m 644 ${WORKDIR}/wpa_supplicant.service ${D}${systemd_unitdir}/system

            # Install wpa_supplicant_event service for udhcp client start/stop based on wifi connection/disconnection
            install -m 755 ${WORKDIR}/wpa_cli-actions.sh ${D}${sysconfdir}/wpa_supplicant
            install -m 644 ${WORKDIR}/wpa_supplicant_wlan0_event.service ${D}${systemd_unitdir}/system
            install -m 644 ${WORKDIR}/wpa_supplicant_p2p_event.service ${D}${systemd_unitdir}/system

            # Install udhcp server configuration file for P2P GO
            install -m 644 ${WORKDIR}/udhcpd-p2p.conf ${D}${sysconfdir}/wpa_supplicant
        fi
}

