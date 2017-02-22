do_install_append_edison () {
	sed -i -e 's/#RuntimeWatchdogSec=0/RuntimeWatchdogSec=90/' ${D}${sysconfdir}/systemd/system.conf
}
