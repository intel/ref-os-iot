SUMMARY = "Led driver to indicate if WiFi/BT is powered up"
DESCRIPTION = "WiFi Bluetooth Led driver "
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module
MAKE_TARGETS = "mod"
SRC_URI = "file://5xx_comms_leds.c \
file://Makefile \
"
FILES_${PN} += "/lib*"

S = "${WORKDIR}"
do_compile() {
    make all
}

do_install() {
    install -m 0755 -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/leds/
    install ${WORKDIR}/5xx_comms_leds.ko  ${D}${base_libdir}/modules/${KERNEL_VERSION}/leds
}

PACKAGES = "${PN}"
