
# Forward to version 1.11.1:
PV = "1.11.1"
SRC_URI[md5sum] = "0e3f71b8672d48774c21f017cedbdd5f"
SRC_URI[sha256sum] = "8f6383d761174f221a1685960a13463cb3bdd551dfb69f7a9cb8cee9cf794793"

# These patches don't compile together with version 1.11.1 so remove them for now.
# Once OpenEmbedded pulls in 1.11.1 officially, they will properly figure out
# how to do this properly
SRC_URI_remove = " \
    file://0003-ssaparse-enhance-SSA-text-lines-parsing.patch \
    file://encodebin-Need-more-buffers-in-output-queue-for-bett.patch \
 "
