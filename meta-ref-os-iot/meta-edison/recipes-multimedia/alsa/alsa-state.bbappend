SUMMARY = "Alsa scenario files to enable alsa state restoration"

do_install_append() {
        # Remove the asound.state file and the directories installed via alsa-states
        # as part of alsa-lib, we are installing the correct file
        rm ${D}${localstatedir}/lib/alsa/asound.state
        rm -rf ${D}${localstatedir}/
}
