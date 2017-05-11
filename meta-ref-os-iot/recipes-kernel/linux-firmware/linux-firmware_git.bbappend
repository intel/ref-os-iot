FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
            file://iwlwifi-8000C-27.ucode \
            file://bxt_i2s.conf \
"

DEPENDS += "alsa-utils-native alsa-lib-native"

do_install_append() {
	cp -r ${WORKDIR}/iwlwifi-8000C-27.ucode ${D}/lib/firmware/
	alsatplg -c ${WORKDIR}/bxt_i2s.conf -o ${D}/lib/firmware/1a98-INTEL-EDK2-2-tplg.bin
}
