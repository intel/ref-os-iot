PACKAGECONFIG = ""

# Forward to version 1.12.0:
PV = "1.12.0"
SRC_URI[md5sum] = "a1813105dc7394aff0be6dbedbf7c6d5"
SRC_URI[sha256sum] = "11b73cfff1b315a8e9be1756435ea84937e7cb90afbab0e8e6975367dbfb8534"

SRC_URI_remove = " \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://0001-smoothstreaming-implement-adaptivedemux-s-get_live_s.patch \
    file://0001-smoothstreaming-use-the-duration-from-the-list-of-fr.patch \
    file://0001-mssdemux-improved-live-playback-support.patch"

# Remove these patches included from the OpenEmbedded integration of 1.10.4
# and replace with updated versions matching 1.12.0
SRC_URI_remove = " file://0001-Prepend-PKG_CONFIG_SYSROOT_DIR-to-pkg-config-output.patch \
                   file://0001-gstreamer-gl.pc.in-don-t-append-GL_CFLAGS-to-CFLAGS.patch"
FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += " file://0001-Prepend-PKG_CONFIG_SYSROOT_DIR-to-pkg-config-output_1_12_0.patch \
             file://0001-gstreamer-gl.pc.in-don-t-append-GL_CFLAGS-to-CFLAGS_1_12_0.patch"

# Reduce the amount of license checks compared to the original bb file for
# plug-ins bad, since the files have changed.
LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5"

EXTRA_OECONF += " --disable-introspection "

# Remove these options since they are not understood by version 1.12.0:
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
