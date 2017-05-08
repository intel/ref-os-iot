PV := "1.34"
SRC_URI[md5sum] = "e200028702c831d5f535d20d61e608ef"
SRC_URI[sha256sum] = "a9a0808c729c1f348fc36d8cecb52d19b72bc34cb411c502608cb0e0190fc71e"
SRC_URI  = "${KERNELORG_MIRROR}/linux/network/${BPN}/${BP}.tar.xz \
            file://0001-plugin.h-Change-visibility-to-default-for-debug-symb.patch \
            file://connman \
            file://no-version-scripts.patch \
            file://includes.patch \
            "

