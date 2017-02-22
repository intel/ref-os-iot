# The original update has been submitted to oe-core as
# https://patchwork.openembedded.org/patch/127215/, but it won't
# make to the first release thus update it with the bbappend.
#
# This file is supposed to be dropped as soon as the original commit gets
# merged to refkit.

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
            file://1a98-INTEL-EDK2-2-tplg.bin \
            file://LICENSE.1a98-INTEL-EDK2-2-tplg;subdir=git/ \
"

LICENSE += "& Firmware-1a98-INTEL-EDK2-2-tplg"
LICENSE_${PN} += "& Firmware-1a98-INTEL-EDK2-2-tplg"

NO_GENERIC_LICENSE[Firmware-1a98-INTEL-EDK2-2-tplg] = "LICENSE.1a98-INTEL-EDK2-2-tplg"

LIC_FILES_CHKSUM += "file://LICENSE.1a98-INTEL-EDK2-2-tplg;md5=4e63f629d461663f675e72588128f896"

do_install_append() {
	cp -r ${WORKDIR}/1a98-INTEL-EDK2-2-tplg.bin ${D}/lib/firmware/
	cp -r ${S}/LICENSE.1a98-INTEL-EDK2-2-tplg ${D}/lib/firmware/
        rm ${D}/lib/firmware/iwlwifi-8000C-22.ucode
}
