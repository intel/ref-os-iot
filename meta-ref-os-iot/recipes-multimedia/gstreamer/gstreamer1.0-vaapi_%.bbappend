
# Forward to version 1.11.1:
PV = "1.11.1"
SRC_URI[md5sum] = "87e89c0e9336d0291605c6c44ca69781"
SRC_URI[sha256sum] = "d3530b18eceb6db1acdb1542332d5d46f2c78954a68caa025f1b5197d6ba325c"

FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "file://0001-HACK-add-dmabuf-alloc-tiled-property.patch \
            file://0002-HACK-dmabuf-immediatey-release-derived-image.patch \
            file://gstreamer-vaapi-fix-compilation-in-gstvaapivideobufferpool.patch \
           "
