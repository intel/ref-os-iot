EXTRA_OECMAKE += " -DOCLICD_COMPAT=0"

do_install_prepend_class-target() {
     install -d ${D}${sysconfdir}/OpenCL/vendors/
     touch ${D}${sysconfdir}/OpenCL/vendors/intel-beignet.icd
}

do_install_append () {
     ln -rsf ${D}${libdir}/beignet-570x/libcl.so ${D}${libdir}/libcl.so
}
