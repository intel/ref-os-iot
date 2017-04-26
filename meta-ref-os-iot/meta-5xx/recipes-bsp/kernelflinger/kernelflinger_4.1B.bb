PR="0"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=964843316bb4cda6ffb9411a16d51bd3"

SRC_URI = "git://github.com/01org/kernelflinger.git;protocol=https \
           file://0001-blank-placeholders.patch \
           file://0002-gitignore-for-temp-files-and-autoconf.patch \
           file://0003-autoconf-and-automake.patch \
           file://0004-work-around-endian.h-file-name-conflict.patch \
           file://0005-ui-stub-functions-for-building-without-an-user-inter.patch \
           file://0006-FIXME-work-around-wide-char-initialization-issues.patch \
           file://0007-disable-Android-specific-code.patch \
           file://0008-disable-boot-signature.patch \
           file://0009-Use-Linux-for-boot-target-names.patch \
           file://0010-FIXME-disable-boot-device-detection.patch \
           file://0011-work-around-confliction-EFI_DEVICE_PATH_PROTOCOL-def.patch \
           "

DEPENDS = "gnu-efi openssl"

SECURITY_CFLAGS = ""

PARALLEL_MAKE = ""

SRCREV = "e1682caaa37e824bb225ef29c8f78826bb2cbae2"

S = "${WORKDIR}/git"

inherit autotools
inherit deploy

do_deploy () {
	install ${B}/kernelflinger.efi	${DEPLOYDIR}/

}
addtask deploy after do_install

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF = " --with-gnu-efi=${STAGING_DIR_HOST}/usr"

