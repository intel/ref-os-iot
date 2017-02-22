PACKAGECONFIG_remove = "flac"
PACKAGECONFIG_remove = "speex"

# original line:
# PACKAGECONFIG[v4l2]       = "--enable-gst_v4l2 --enable-v4l2-probe,--disable-gst_v4l2"
PACKAGECONFIG[v4l2]       = "--enable-gst_v4l2,--disable-gst_v4l2"
# We should consider getting the "enable-v4l2-probe" flag back in again.
# Need to debug why the probe creates a large amount of warnings in the traces.
# It was originally added for a reason:
# http://lists.openembedded.org/pipermail/openembedded-core/2016-August/125736.html

# Forward to version 1.11.1:
PV = "1.11.1"
SRC_URI[md5sum] = "f75b5894f200b863098706570a708348"
SRC_URI[sha256sum] = "04273b255ce2071fdb901bfc900edb77471578c1f4e23684943bffc441a0fc0c"

# This patch doesn't compile together with version 1.11.1 so remove it for now.
# Once OpenEmbedded pulls in 1.11.1 officially, they will properly figure out
# how to do this properly
SRC_URI_remove = "file://qtdemux-free-seqh-after-calling-qtdemux_parse_svq3_s.patch"
