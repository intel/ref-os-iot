do_configure_append () {
	sed -ie 's/#CONFIG_WIFI_DISPLAY=y/CONFIG_WIFI_DISPLAY=y/g' wpa_supplicant/.config
	sed -ie 's/#CONFIG_P2P=y/CONFIG_P2P=y/g' wpa_supplicant/.config
	sed -ie 's/#CONFIG_CTRL_IFACE_DBUS=y/CONFIG_CTRL_IFACE_DBUS=y/g' wpa_supplicant/.config
}
