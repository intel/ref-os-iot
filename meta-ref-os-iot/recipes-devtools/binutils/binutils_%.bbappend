FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "eb83230b4d5c0129174937846ded9eaa9f78ff28"

SRC_URI_remove = "git://sourceware.org/git/binutils-gdb.git;branch=binutils-${BINUPV}-branch;protocol=git \
     file://0005-Only-generate-an-RPATH-entry-if-LD_RUN_PATH-is-not-e.patch \
     file://0010-warn-for-uses-of-system-directories-when-cross-linki.patch \
     file://CVE-2017-6965.patch \
     file://CVE-2017-6966.patch"


SRC_URI_prepend = " git://sourceware.org/git/binutils-gdb.git;protocol=git"
SRC_URI_append = " file://0001-Only-generate-an-RPATH-entry-if-LD_RUN_PATH-is-not-e.patch \
     file://0002-warn-for-uses-of-system-directories-when-cross-linki.patch \
"
