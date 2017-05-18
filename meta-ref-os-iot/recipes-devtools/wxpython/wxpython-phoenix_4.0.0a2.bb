DESCRIPTION = "wxWidgets Phoenix is a cross platform application framework utilizing native python."
HOMEPAGE = "http://www.wxpython.org"

LICENSE = "WXwindows"
LIC_FILES_CHKSUM = "file://ext/wxWidgets/docs/licence.txt;md5=18346072db6eb834b6edbd2cdc4f109b"

DEPENDS = "python-dev autoconf automake gcc gtk+ jpeg tiff libpng zlib libglu glibc gettext"

# use gitsm (git submodule) due to wxwidgets being a sub module for wxpython
SRC_URI = "gitsm://github.com/wxWidgets/Phoenix.git;protocol=https;tag=wxPython-4.0.0a2 \
	https://wxpython.org/Phoenix/tools/waf-1.7.15-p1.bz2;name=waf \
        https://wxpython.org/Phoenix/tools/doxygen-1.8.8-linux.bz2;name=doxygen \
        https://wxpython.org/Phoenix/tools/sip-4.19.1-linux64.bz2;name=sip \
        file://tune-build-target-and-parameters.patch"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI[waf.md5sum] = "3a653279de72a7b543cca249f7a7d2ef"
SRC_URI[waf.sha256sum] = "65ac58c0cc0df30997fbdb0fa5c4368a4af5d81413f26cae504adf5035537c2f"
SRC_URI[doxygen.md5sum] = "a970d94109263174325c6ee3794112e4"
SRC_URI[doxygen.sha256sum] = "219d440eef2c24dc398eee08f903c74a929c52310e1a1711895653c43f57af48"
SRC_URI[sip.md5sum] = "538cf90bce65b6bc067dc04e4c5e72aa"
SRC_URI[sip.sha256sum] = "fd310aaec521a2788c555642ceef579e1158a9afdd8ebce05fea231e61a07283"

S = "${WORKDIR}/git"

inherit pkgconfig pythonnative python-dir distutils setuptools

# leverage yocto's setuptools otherwise, but build the needed
# components before the actual compile (doc, etg and sip)
addtask prepare_compile before do_compile after do_configure
do_prepare_compile() {
    # these are fetched by build.py, but do_compile doesn't allow network access
    # (or it doesn't expose *_PROXY env variables)
    # thus we need to prepare them before compile
    cp ${WORKDIR}/waf-1.7.15-p1 ${S}/bin/
    cp ${WORKDIR}/doxygen-1.8.8-linux ${S}/bin/
    cp ${WORKDIR}/sip-4.19.1-linux64 ${S}/bin/
    chmod +x ${S}/bin/doxygen-1.8.8-linux
    chmod +x ${S}/bin/sip-4.19.1-linux64

    cd ${S}
    python build.py dox etg --nodoc sip
}

INSANE_SKIP_${PN} = "dev-so"
