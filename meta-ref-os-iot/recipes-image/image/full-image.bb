include base-image.bb

SUMMARY = "Ref OS IOT  image for local builds with swupd disabled."
DESCRIPTION = "Ref OS IOT image with everything"

# REF OS IOT image features
REF_OS_IMAGE_PKG_FEATURES += " \
     x11-base \
     xfce-ui \
     dev-packages \
     opencv \
     opencl \
     realsense \
     realsense-dev \
     multimedia \
     iotivity \
     python-runtime \
     nodejs-runtime-wo-soletta \
     alsa \
     pulseaudio \
     sensors \
     text-utils \
     intel-xdk-support \
     tools-debug \
     tools-develop \
"

IMAGE_FEATURES += " \
    ${REF_OS_IMAGE_PKG_FEATURES} \
"

IMAGE_INSTALL_append = "\
    ${MACHINE_EXTRA_RRECOMMENDS} \
"

LICENSE = "MIT"

REF_OS_ROOTFS_SIZE = "3700"

inherit refosiot-image
