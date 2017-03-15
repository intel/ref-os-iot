SUMMARY = "Small server implementation of MTP (based on Android)"
LICENSE = "GPL-3.0 & Apache-2.0 & LGPL-3.0"
LIC_FILES_CHKSUM = " \
	file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
	file://NOTICE;md5=9645f39e9db895a4aa6e02cb57294595 \
	file://MODULE_LICENSE_APACHE2;md5=d41d8cd98f00b204e9800998ecf8427e \
"

DEPENDS += "boost glog gettext-native"

SRC_URI = "https://launchpad.net/ubuntu/+archive/primary/+files/mtp_0.0.4+16.04.20160413.orig.tar.gz;name=tarball \
		https://launchpad.net/ubuntu/+archive/primary/+files/mtp_0.0.4+16.04.20160413-0ubuntu4.diff.gz;name=diff \
		file://0001-Remove-dbus-libhybris-dependencies-and-set-device-na.patch \
		file://0001-Disable-testing.patch \
		file://0001-Fix-memory-leaks.patch \
"
SRC_URI[tarball.md5sum] = "e0bdf8a67ce4e82059a5d0ec0692bb5c"
SRC_URI[tarball.sha256sum] = "4c97216fb92bce2cab30dbe976d49faba294a1c2bda3396ad8b992faf4d6bbc6"
SRC_URI[diff.md5sum] = "2c4a9ebd7990292b4a61e5ab090e179b"
SRC_URI[diff.sha256sum] = "20d71085d392d9ee0e1a9bf72d96cb43de0f8eeac7662eaef3afd5b46f597df7"

inherit cmake

S = "${WORKDIR}/"