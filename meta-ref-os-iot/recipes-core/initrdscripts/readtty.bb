SUMMARY = "Installer helper application to read tty's"
DESCRIPTION = "Installer helper application "
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://readtty.c \
"

S = "${WORKDIR}"
do_compile() {
    ${CC} readtty.c ${LDFLAGS} -o readtty -lpthread
}

do_install() {
    install -d ${D}/usr/bin
    install ${WORKDIR}/readtty  ${D}/usr/bin/readtty
}
