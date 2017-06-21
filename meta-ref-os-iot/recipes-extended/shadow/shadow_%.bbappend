FILESEXTRAPATHS_prepend := "${THISDIR}/shadow:"

SRC_URI_append = "\
              file://0001-Fix-a-resource-leak-in-libmis-idmapping.c.patch \
              file://0001-get_map_ranges-check-for-overflow.patch \
              file://0001-Simplify-getulong.patch \
              file://0001-also-check-upper-for-wrap.patch \
              file://0001-idmapping-add-more-checks-for-overflow.patch \
"
