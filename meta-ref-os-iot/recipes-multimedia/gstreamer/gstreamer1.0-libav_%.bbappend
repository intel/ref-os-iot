
# Forward to version 1.12.0:
PV = "1.12.0"
SRC_URI[md5sum] = "f9c4593947f8484b237c5d9782939ec3"
SRC_URI[sha256sum] = "39d1477f642ee980b008e78d716b16801eec9a6e5958c5a6cdc0cb04ab0750c4"

FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "file://0001-configure-check-for-armv7ve-variant.patch"
