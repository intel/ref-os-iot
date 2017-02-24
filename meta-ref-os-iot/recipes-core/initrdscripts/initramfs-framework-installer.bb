SUMMARY = "Installer module for the modular initramfs system"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
RDEPENDS_${PN} = "initramfs-framework-base gptfdisk dosfstools rsync e2fsprogs readtty"

SRC_URI = " \
    file://installer \
"

do_install () {
    install -d ${D}/init.d
    install ${WORKDIR}/installer  ${D}/init.d/80-installer
}

FILES_${PN} = "/init.d"
