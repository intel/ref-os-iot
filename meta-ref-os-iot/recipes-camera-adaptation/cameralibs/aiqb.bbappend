do_install_append() {
    ln -rsf ${D}/etc/atomisp/01ov5670.aiqb ${D}/etc/atomisp/00ov5670.aiqb
    ln -rsf ${D}/etc/atomisp/01ov5670.aiqb ${D}/etc/atomisp/02ov5670.aiqb
    ln -rsf ${D}/etc/atomisp/01ov5670.aiqb ${D}/etc/atomisp/03ov5670.aiqb
}

