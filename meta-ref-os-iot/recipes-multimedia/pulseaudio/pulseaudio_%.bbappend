PACKAGECONFIG_append = "autospawn-for-root"

PACKAGES_remove = "pulseaudio-module-console-kit"
RDEPENDS_remove_pulseaudio-server = "pulseaudio-module-console-kit"

do_install_append() {
    rm ${D}/usr/lib/pulse-9.0/modules/module-console-kit.so
}
