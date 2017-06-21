FILESEXTRAPATHS_prepend := "${THISDIR}/libcroco:"

SRC_URI_append = "\
              file://0001-input-check-end-of-input-before-reading-a-byte.patch \
"
