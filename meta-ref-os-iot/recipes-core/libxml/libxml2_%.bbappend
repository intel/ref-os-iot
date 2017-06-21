FILESEXTRAPATHS_prepend := "${THISDIR}/libxml2:"

SRC_URI_append = "\
	       file://libxml2-CVE-2017-8872.patch \
	       file://0001-Fix-buffer-size-checks-in-xmlSnprintfElementContent.patch\
	       file://0001-Fix-handling-of-parameter-entity-references.patch\
"
