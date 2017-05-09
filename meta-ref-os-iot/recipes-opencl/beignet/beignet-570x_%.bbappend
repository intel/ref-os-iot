# to keep applications happy which link directly with libcl
do_install_append () {
     ln -rsf ${D}${libdir}/libOpenCL.so ${D}${libdir}/libcl.so
}
