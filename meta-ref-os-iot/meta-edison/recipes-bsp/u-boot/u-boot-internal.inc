COMPATIBLE_MACHINE = "edison"

PV = "2015.10"
S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "dtc-native"

SRC_URI = "git://github.com/01org/edison-u-boot.git;branch=edison-v2015.10;protocol=git" 
SRC_URI += "file://${MACHINE}.env"
SRC_URI += "file://fw_env.config"
SRC_URI += "file://target_env/blankcdc.env"
SRC_URI += "file://target_env/blankrndis.env"
SRC_URI += "file://target_env/ifwi.env"
SRC_URI += "file://target_env/prod.env"
SRC_URI += "file://bzimage_size.patch"
SRCREV = "1552fe5be3a3b193fe9d8e0fefc3683f5fc50d6a"
