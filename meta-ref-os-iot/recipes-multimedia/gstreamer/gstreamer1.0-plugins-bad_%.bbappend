PACKAGECONFIG = ""

# Forward to version 1.11.1:
PV = "1.11.1"
SRC_URI[md5sum] = "3a995f3a765f938242cfc1c687ed4de1"
SRC_URI[sha256sum] = "af354f958ece864fa4f4372cad5fcf25a2863d0940962a3de1665e75f673b278"

# These patches don't compile together with version 1.11.1 so remove them for now.
# Once OpenEmbedded pulls in 1.11.1 officially, they will properly figure out
# how to do this properly
SRC_URI_remove = " \
    file://0001-gstreamer-gl.pc.in-don-t-append-GL_CFLAGS-to-CFLAGS.patch \
    file://0001-Prepend-PKG_CONFIG_SYSROOT_DIR-to-pkg-config-output.patch \
    file://0001-smoothstreaming-implement-adaptivedemux-s-get_live_s.patch \
    file://0001-smoothstreaming-use-the-duration-from-the-list-of-fr.patch \
    file://0001-mssdemux-improved-live-playback-support.patch"

# Reduce the amount of license checks compared to the original bb file for
# plug-ins bad, since the files has changed.
LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5"

# Remove these options since they are not understood by version 1.11.1:
EXTRA_OECONF_remove = "\
	--disable-libvisual \
	--disable-nas \
	--disable-sdl \
	--disable-wininet \
	--disable-xvid \
	--disable-pvr \
	--disable-mimic \
	--disable-linsys \
	--disable-sndio \
	--disable-sdltest \
	--disable-timidity \
	--disable-apexsink"
