PACKAGECONFIG_remove = "flac"
PACKAGECONFIG_remove = "speex"

# original line:
# PACKAGECONFIG[v4l2]       = "--enable-gst_v4l2 --enable-v4l2-probe,--disable-gst_v4l2"
PACKAGECONFIG[v4l2]       = "--enable-gst_v4l2,--disable-gst_v4l2"
# We should consider getting the "enable-v4l2-probe" flag back in again.
# Need to debug why the probe creates a large amount of warnings in the traces.
# It was originally added for a reason:
# http://lists.openembedded.org/pipermail/openembedded-core/2016-August/125736.html

# Forward to version 1.12.0:
PV = "1.12.0"
SRC_URI[md5sum] = "9294b22ddab3bec373cbc5e84ff4c084"
SRC_URI[sha256sum] = "8a1d734db7338e00c28b794a7f0a5a9e67d1c5c8b0074075b50638207d372ebc"

SRC_URI_remove = "file://0001-v4l2object-Also-add-videometa-if-there-is-padding-to.patch"
