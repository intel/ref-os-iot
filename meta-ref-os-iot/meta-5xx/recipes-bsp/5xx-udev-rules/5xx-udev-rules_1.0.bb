LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI += " \
    file://sysfs-control.sh \
    file://hdmi-hotplug.rules \
    file://hdmi-hotplug.sh \
    file://hdmi-hotplug@.service \
    file://xhci-power.rules \
    file://pci-power.rules \
"

do_install_append () {
    install -m 0755 -d ${D}/${base_sbindir}
    install -m 0755 ${WORKDIR}/sysfs-control.sh ${D}/${base_sbindir}
    install -m 0755 -d ${D}/${base_libdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/hdmi-hotplug.rules ${D}/${base_libdir}/udev/rules.d/79-hdmi-hotplug.rules
    install -m 0755 -d ${D}/${base_libdir}/systemd/system
    install -m 0644 ${WORKDIR}/hdmi-hotplug@.service ${D}/${base_libdir}/systemd/system/hdmi-hotplug@.service
    install -m 0755 -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/hdmi-hotplug.sh ${D}/usr/bin/hdmi-hotplug.sh
    install -m 0644 ${WORKDIR}/xhci-power.rules ${D}/${base_libdir}/udev/rules.d/80-xhci-power.rules
    install -m 0644 ${WORKDIR}/pci-power.rules ${D}/${base_libdir}/udev/rules.d/80-pci-power.rules
#    install -m 0644 ${WORKDIR}/usb-power.rules ${D}/${base_libdir}/udev/rules.d/80-usb-power.rules
#    install -m 0644 ${WORKDIR}/usb-autosuspend-blacklist ${D}/${base_libdir}/udev/
}

FILES_${PN} += "${base_libdir}/systemd/system/hdmi-hotplug@.service"
#FILES_${PN} += "${base_libdir}/udev/usb-autosuspend-blacklist"
