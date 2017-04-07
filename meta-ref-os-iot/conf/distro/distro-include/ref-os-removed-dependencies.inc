# Disable ed, quilt and intltool from tools-sdk
RDEPENDS_packagegroup-core-sdk_remove = "ed"
RDEPENDS_packagegroup-core-sdk_remove += "quilt"
RDEPENDS_packagegroup-core-sdk_remove += "intltool"
RDEPENDS_packagegroup-core-x11-base_remove += "matchbox-terminal matchbox-wm"

# OS removed dependencies
RDEPENDS_pn-xserver-common_remove = "fbset"
RDEPENDS_remove_gstreamer1.0-meta-audio = "gstreamer1.0-plugins-good-flac"

# fwts configures automatically not to include dtc, if it is missing
PNBLACKLIST[dtc] ?= "Not a needed nor intel-whitelisted test package"
DEPENDS_remove_pn-fwts = "dtc"

# Remove xinput caliberator from the image
RDEPENDS_packagegroup-core-x11-utils_remove += "xinput-calibrator"

# remove enchant from the image
DEPENDS_remove_pn-gedit = "enchant"

# Remove dependency with ocl-icd
PNBLACKLIST[ocl-icd] ?= "Removed"
PACKAGECONFIG_remove_pn-opencv = "opencl"
DEPENDS_remove_pn-beignet-570x = "ocl-icd"

# Remove libusb-compat and replace it with libusb1
PNBLACKLIST[libusb-compat] = "Not a whitelisted component"
PROVIDES_append_pn-libusb1 = " libusb"
DEPENDS_remove_pn-libgphoto2 = "virtual/libusb0"

# Remove dependency with libexif
EXTRA_OECONF_append_pn-libgphoto2 = " --with-libexif=no"
EXTRA_OECONF_append_pn-thunar = " --disable-exif"
DEPENDS_remove_pn-libgphoto2 = "libexif"

# Remove dependency with some gstreamer plugins, based on code review

# Remove libogg and dependent codecs (flac, vorbis and theora)
PNBLACKLIST[libtheora] ?= "Removed due to dependency to libogg"
DEPENDS_remove_pn-gst-plugins-base = "libogg"
DEPENDS_remove_pn-gstreamer1 = "libtheora"
DEPENDS_remove_pn-gstreamer1.0-plugins-base = "libtheora"
PACKAGECONFIG_remove_pn-gstreamer1.0-plugins-base = "ogg"
PACKAGECONFIG_remove_pn-gstreamer1.0-plugins-base = "vorbis"
DEPENDS_remove_pn-speex = "libogg"

# Remove dependencies to libogg from libsndfile1 
DEPENDS_remove_pn-libsndfile1 = "libogg"
DEPENDS_remove_pn-libsndfile1 = "libvorbis"
DEPENDS_remove_pn-libsndfile1 = "flac"
EXTRA_OECONF_append_pn-libsndfile1 = " --disable-external-libs"

# Remove dependency to cryptodev
DEPENDS_remove_pn-openssl = "cryptodev-linux"
CFLAG_remove_pn-openssl = "-DHAVE_CRYPTODEV"

# Remove dependency to mbedtls
SRC_URI_remove_pn-iotivity = "${url_mbedtls};name=mbedtls;destsuffix=${S}/extlibs/mbedtls/mbedtls;protocol=http"
# Remove dependency to sysfsutils
DEPENDS_remove_pn-bridge-utils = "sysfsutils"

# Remove xinput caliberator from the image
RDEPENDS_nativesdk-packagegroup-sdk-host_remove += "nativesdk-dnf"
