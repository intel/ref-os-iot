SUMMARY = "Alsa scenario files to enable alsa state restoration"
FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI += " \
    file://asound_5xx.state \
"

do_install_append() {
    # Remove the asound.state file installed via alsa-states
    rm ${D}${localstatedir}/lib/alsa/asound.state

    install -d ${D}/var/lib/alsa/
    install -m 0644 ${WORKDIR}/asound_5xx.state ${D}/var/lib/alsa/asound.state
}

FILES_${PN} += " \
    /var/lib/alsa/asound.state \
"
