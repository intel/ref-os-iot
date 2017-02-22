SUMMARY = "OpenGL function pointer management library"
HOMEPAGE = "https://github.com/anholt/libepoxy/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=58ef4c80d401e07bd9ee8b6b58cf464b"


SRC_URI = " \
    git://github.com/anholt/libepoxy.git \
    file://no-need-for-python3.patch \
"
SRCREV="96286708a4d7ebb6d5b06042d9f9aa5ed6a61fbf"
PV = "1.4"

S = "${WORKDIR}/git"

inherit autotools pkgconfig distro_features_check
# depends on virtual/egl
REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS = "util-macros virtual/egl"

PACKAGECONFIG[x11] = "--enable-glx, --disable-glx, virtual/libx11"
PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)}"
