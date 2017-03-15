FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://mtp-server.service \
"

do_install_append () {
    install -m 0755 ${WORKDIR}/mtp-server.service ${D}/lib/systemd/system
}

SYSTEMD_SERVICE_${PN}_append = " \
    mtp-server.service \
"
