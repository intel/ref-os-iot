FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://background.jpg \
    file://xfce4-desktop.xml \
"

do_install_append() {
    install -d ${D}${datadir}/backgrounds/xfce
    install -m 0644 ${WORKDIR}/background.jpg ${D}${datadir}/backgrounds/xfce/

    install -d ${D}/home/root/.config/xfce4/xfconf/xfce-perchannel-xml
    install -m 0644 ${WORKDIR}/xfce4-desktop.xml ${D}/home/root/.config/xfce4/xfconf/xfce-perchannel-xml/
}

FILES_${PN} += " \
    /usr/share/backgrounds/xfce \
    /home/root/.config/xfce4/xfconf/xfce-perchannel-xml/xfce4-desktop.xml \
"
