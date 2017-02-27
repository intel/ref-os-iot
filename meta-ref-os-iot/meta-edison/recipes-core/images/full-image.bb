include base-image.bb

DESCRIPTION = "A fully functional image to run EDISON"
LICENSE = "MIT"

# Ref OS IOT image features
REF_OS_IMAGE_PKG_FEATURES += " \
     python-runtime \
     arduino-support \
     nodejs-runtime-wo-soletta \
     pam \
     omp \
     upm \
     alsa \
     pulseaudio \
     ofono \
     multimedia \
     mosquitto \
     wpa-supplicant \
     intel-xdk \
     intel-xdk-support \
"

IMAGE_FEATURES += " \
    ${REF_OS_IMAGE_PKG_FEATURES} \
"

IMAGE_INSTALL_append = "\
    ${MACHINE_EXTRA_RRECOMMENDS} \
"

inherit refosiot-image
