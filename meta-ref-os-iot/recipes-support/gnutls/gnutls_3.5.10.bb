require gnutls.inc

SRC_URI += "file://correct_rpl_gettimeofday_signature.patch \
            file://0001-configure.ac-fix-sed-command.patch \
            file://use-pkg-config-to-locate-zlib.patch \
            file://arm_eabi.patch \
	    "

SRC_URI[md5sum] = "336c03a71ba90184ffd0388075dde504"
SRC_URI[sha256sum] = "af443e86ba538d4d3e37c4732c00101a492fe4b56a55f4112ff0ab39dbe6579d"

