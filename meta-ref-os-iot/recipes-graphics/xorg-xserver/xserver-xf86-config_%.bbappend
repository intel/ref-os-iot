FILESEXTRAPATHS_append := "${THISDIR}"
SRC_URI += "file://20-intel.conf"
CONFFILES_${PN} += " ${sysconfdir}/X11/xorg.conf.d/20-intel.conf"

do_install_append () {
	install -Dm 0644 20-intel.conf ${D}/${sysconfdir}/X11/xorg.conf.d/20-intel.conf
}
