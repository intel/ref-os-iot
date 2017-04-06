
do_install_prepend_class-target() {
     install -d ${D}${sysconfdir}/OpenCL/vendors/
     touch ${D}${sysconfdir}/OpenCL/vendors/intel-beignet.icd
}

