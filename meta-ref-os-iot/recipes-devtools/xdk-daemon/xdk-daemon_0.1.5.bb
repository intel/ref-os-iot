DESCRIPTION = "Provides communication to the Intel XDK"
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://LICENSE;md5=a73c59556102dc129206ba79d12f4499"

DEPENDS = "nodejs-native avahi"
RDEPENDS_${PN} = "nodejs bash avahi openssh-sftp-server"

# http://download.xdk.intel.com/iot/xdk-daemon_0.1.5.bb RDEPENDS on
# "libarchive-bin". Because we don't have that at the moment (only the
# individual tools) and it is uncertain which tool in particular
# is needed, we depend on both here.
RDEPENDS_${PN} += "bsdcpio bsdtar"

PR = "r0"

# needed to unset no_proxy for internal development
export no_proxy = ""

SRC_URI = "https://download.xdk.intel.com/iot/xdk-daemon-0.1.5.tar.bz2"

SRC_URI[md5sum] = "51b160c4937e0020121aabc501cd28b5"
SRC_URI[sha256sum] = "44ee77a7aa3e0f25780f0d6342edd46de3834a8b776746765859947d6e41af6e"

# we don't care about debug for the few binary node modules
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# fix dns_sd linking
SECURITY_CFLAGS_append = " -fPIC"

do_compile () {
    # changing the home directory to the working directory, the .npmrc will be created in this directory
    export HOME=${WORKDIR}

    # compile in "production" mode (https://docs.npmjs.com/misc/config#production)
    npm config set production true

    # access npm registry using http
    npm set strict-ssl false
    npm config set registry http://registry.npmjs.org/

    # configure http proxy if neccessary
    if [ -n "${http_proxy}" ]; then
        npm config set proxy ${http_proxy}
    fi
    if [ -n "${HTTP_PROXY}" ]; then
        npm config set proxy ${HTTP_PROXY}
    fi

    # configure cache to be in working directory
    npm set cache ${WORKDIR}/npm_cache

    # clear local cache prior to each compile
    npm cache clear

    # NPM is picky about arch names
    if [ "${TARGET_ARCH}" == "i586" ]; then
        npm config set target_arch ia32
        export TARGET_ARCH=ia32
    fi

    export CPLUS_INCLUDE_PATH=${STAGING_INCDIR}/avahi-compat-libdns_sd

    # npm is dumb, it needs to get given --arch but not in npm config
    npm install
    cd current/ && npm install --verbose --arch=${TARGET_ARCH}
    cd node-inspector-server && npm install --build-from-source --arch=${TARGET_ARCH}

    sed -i '/TM/d' ${S}/xdk-daemon
}

do_install () {
    install -d ${D}/opt/xdk-daemon/
    cp -a ${S}/* ${D}/opt/xdk-daemon/

    # the above copies the .patch file as well. This ends up creating a broken
    # symlink, which can cause QA errors. Delete the patch directory
    rm -rf ${D}/opt/xdk-daemon/patches

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/xdk-daemon-avahi.service ${D}${systemd_unitdir}/system/xdk-daemon.service

    install -d ${D}${bindir}
}

inherit systemd

SYSTEMD_SERVICE_${PN} = "xdk-daemon.service"

FILES_${PN} = "/opt/xdk-daemon/ \
               ${systemd_unitdir}/system/xdk-daemon.service \
               ${bindir}/"

PACKAGES = "${PN}"

