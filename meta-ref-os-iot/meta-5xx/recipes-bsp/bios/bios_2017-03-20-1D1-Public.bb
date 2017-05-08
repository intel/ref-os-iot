SRC_URI = "https://downloadmirror.intel.com/26696/eng/Joule-Firmware-2017-03-20-1D1-Public.zip"

SRC_URI[md5sum] = "4ef0cad39a60d237346da0cb60f14576"
SRC_URI[sha256sum] = "f7377a0cb824ca1342f53331a03c679a0bb3f45f4d4f3a630726603391e423c6"

LICENSE = "Proprietary"
# FIXME
LIC_FILES_CHKSUM = "file://Docs/ReleaseNotes.txt;md5=3f42e58bee1ad7811f26de1e454687a5"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

S = "${WORKDIR}/joule-firmware-2017-03-20-1d1-public"

do_deploy () {
        install -d                                              ${DEPLOYDIR}/bios/DNX
        install ${S}/DNX/DNXP_0x1-prod.bin                      ${DEPLOYDIR}/bios/DNX
        install ${S}/Joule_C0-X64-Release-1D1-Public_DNX.bin    ${DEPLOYDIR}/bios
}
addtask deploy after do_install

