
# Forward to version 1.11.1:
PV = "1.11.1"
SRC_URI[md5sum] = "f0ca78f679079fab91369cd810248074"
SRC_URI[sha256sum] = "856747a797746d16d9808f959647bcc96d0dd935b51914870844301689fc3e0e"

DEPENDS_append = " libunwind"

# Remove this option since it is not understood by version 1.11.1:
EXTRA_OECONF_remove = " \
    --disable-docbook"

# This patch doesn't compile together with version 1.11.1 so remove it for now.
# Once OpenEmbedded pulls in 1.11.1 officially, they will properly figure out
# how to do this properly
SRC_URI_remove = "file://deterministic-unwind.patch"

EXTRA_OECONF_remove = " --without-unwind "

