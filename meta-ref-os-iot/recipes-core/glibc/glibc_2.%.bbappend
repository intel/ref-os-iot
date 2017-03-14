FILES_${PN} += "/lib64/*"
INSANE_SKIP_${PN} += "libdir"

do_install_append () {
  if [ -f ${D}/lib/ld-linux-x86-64.so.2 ]
  then
    install -d ${D}/lib64
    ln -rsf ${D}/lib/ld-linux-x86-64.so.2 ${D}/lib64/ld-linux-x86-64.so.2
  fi
}
