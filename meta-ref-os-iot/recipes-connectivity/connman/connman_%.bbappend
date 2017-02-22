FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-ignore-usb0.patch"
EXTRA_OECONF += "\
    --disable-l2tp \
    --disable-pptp \
"

DEPENDS_remove_pn-connman = "ppp"
