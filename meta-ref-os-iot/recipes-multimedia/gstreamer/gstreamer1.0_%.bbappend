
# Forward to version 1.12.0:
PV = "1.12.0"
SRC_URI[md5sum] = "8f76b6b5e4b3307e505bd6ab9304dd03"
SRC_URI[sha256sum] = "14d5eef8297d2bf2a728d38fa43cd92cc267a0ad260cf83d770215212aff4302"

DEPENDS_append = " libunwind"

# Remove this option since it is not understood by version 1.12.0:
EXTRA_OECONF_remove = " \
    --disable-docbook"

# Remove this patch included from the OpenEmbedded integration of 1.10.4
# and replace with an updated version matching 1.12.0
SRC_URI_remove = " file://deterministic-unwind.patch"
FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += " file://deterministic-unwind_1_12_0.patch"

EXTRA_OECONF_remove = " --without-unwind "

