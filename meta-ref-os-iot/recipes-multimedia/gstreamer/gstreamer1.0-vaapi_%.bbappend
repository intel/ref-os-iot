
# Forward to version 1.12.0:
PV = "1.12.0"
SRC_URI[md5sum] = "7548a48eece6c76298561f2ec890a240"
SRC_URI[sha256sum] = "4a1a5a174b3d3a76ce6c123203e7ee535a347d77f9d4f4ffaf69bfbbfeb03ddb"

# This patch is already in 1.12.0 so don't include it again:
SRC_URI_remove = "file://vaapivideobufferpool-create-allocator-if-needed.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "file://0001-HACK-add-dmabuf-alloc-tiled-property-1.12.0.patch \
            file://0002-HACK-dmabuf-immediatey-release-derived-image.patch \
            file://0001-plugins-remove-par-from-caps-negotiation.patch \
           "
