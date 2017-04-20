do_configure_append () {
	sed -ie 's/After=dbus.service network-pre.target systemd-sysusers.service/After=dbus.service network-pre.target systemd-sysusers.service mtp-server.service/g' ${WORKDIR}/${PN}-${PV}/src/connman.service.in
}