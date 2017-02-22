FILESEXTRAPATHS_prepend_edison := "${THISDIR}/files:"

SRC_URI_append_edison = " file://usb0.network"

PACKAGECONFIG_append_edison = " networkd"

do_install_append_edison () {
	sed -i -e 's/#RuntimeWatchdogSec=0/RuntimeWatchdogSec=90/' ${D}${sysconfdir}/systemd/system.conf

	# Push our custom virtual USB network conf file to target
	install -m 0644 ${WORKDIR}/usb0.network ${D}${sysconfdir}/systemd/network
}
