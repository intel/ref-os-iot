require ../../../recipes-image/image/base-image.bb

DESCRIPTION = "A fully functional image to run EDISON"
LICENSE = "MIT"

# Ref OS IOT image features
REF_OS_IMAGE_PKG_FEATURES += " \
     arduino-support \
     nodejs-runtime \
     pam \
     omp \
     alsa \
     pulseaudio \
     pulseaudio-bluetooth \
     ofono \
     multimedia \
     mosquitto \
     wpa-supplicant \
     intel-xdk \
     intel-xdk-support \
     sensors \
     mraa-dev-support \
     tools-debug \
     tools-develop \
     tools-networking \
     tools-archive \
     misc-tools \
     paho-mqtt \
     ext2-tools \
     tls-support \
     python-extra \
"

IMAGE_FEATURES += " \
    ${REF_OS_IMAGE_PKG_FEATURES} \
"

IMAGE_INSTALL_append = "\
    ${MACHINE_EXTRA_RRECOMMENDS} \
    python3-modules \
    python-modules \
"

inherit refosiot-image
