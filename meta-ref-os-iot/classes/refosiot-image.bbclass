# Ref OS IOT image class
# for now, only a way to add features to the image

FEATURE_PACKAGES_xfce-ui = "packagegroup-xfce-base"
FEATURE_PACKAGES_usb-gadget-networking = "usbgadget mtp-server"
FEATURE_PACKAGES_opencv = "opencv"
FEATURE_PACKAGES_opencl = "beignet-${BEIGNET_BOARD}"
FEATURE_PACKAGES_linux-firmware = "linux-firmware"
FEATURE_PACKAGES_realsense = "\
			   realsense-sdk \
			   realsense-persontracking \
			   realsense-projectionopt \
			   realsense-objectrecognition \
			   realsense-slam \
			   librealsense \
			   "
FEATURE_PACKAGES_realsense-dev = "\
			   realsense-sdk-dev \
			   realsense-persontracking-dev \
			   realsense-projectionopt-dev \
			   realsense-objectrecognition-dev \
			   realsense-slam-dev \
			   librealsense-dev \
			   librealsense-graphical-examples \
"
FEATURE_PACKAGES_ros = "packagegroup-ros-comm rosinit"
FEATURE_PACKAGES_multimedia = "\
			    gstreamer1.0-plugins-base \
			    gstreamer1.0-plugins-good \
			    gstreamer1.0-plugins-bad \
			    ${FEATURE_PACKAGES_hwcodecs} \
			    "
FEATURE_PACKAGES_alsa = "alsa-lib alsa-plugins alsa-utils"
FEATURE_PACKAGES_vnc = "x11vnc"
FEATURE_PACKAGES_text-utils = "gedit nano"
FEATURE_PACKAGES_test-utils = "glmark2 fwts"

#Pulseaudio support
FEATURE_PACKAGES_pulseaudio = " \
			    pulseaudio-server \
                            pulseaudio-misc \
"
FEATURE_PACKAGES_pulseaudio-bluetooth = " \
                            pulseaudio-module-bluetooth-discover \
                            pulseaudio-module-bluetooth-policy \
                            pulseaudio-module-bluez5-device \
                            pulseaudio-module-bluez5-discover \
"

FEATURE_PACKAGES_arduino-support = " \
				 clloader \
				 sketch-check \
				 pwr-button-handler \
				 blink-led \
				 oobe \
"
FEATURE_PACKAGES_pam = "libpam"
FEATURE_PACKAGES_omp = "libgomp"
FEATURE_PACKAGES_ofono = "ofono"
FEATURE_PACKAGES_mosquitto = "mosquitto-dev mosquitto-clients"
FEATURE_PACKAGES_wpa-supplicant = "wpa-supplicant"
FEATURE_PACKAGES_wifi-hotspot = "hostapd"
# XDK daemon and the support packages needed. These are teo separate items
# as for 5xx we don't need the daemon, where as edison needs it. This is because
# existing edison releases had the OOB which improves the OOB experience.
FEATURE_PACKAGES_intel-xdk = "xdk-daemon"
FEATURE_PACKAGES_intel-xdk-support = "avahi-daemon avahi-utils nss nodejs-npm"

FEATURE_PACKAGES_dev-packages = "libjpeg-turbo-dev opencv-dev"

# tools to manipulate file sysytems
FEATURE_PACKAGES_ext2-tools =" \
		               e2fsprogs \
                               e2fsprogs-e2fsck \
                               e2fsprogs-mke2fs \
                               e2fsprogs-tune2fs \
                               e2fsprogs-badblocks \
                               libcomerr \
                               libss \
                               libe2p \
                               libext2fs \
                               dosfstools \
                               util-linux \
"

FEATURE_PACKAGES_bluetooth-tools =" \
                               bluez5-noinst-tools \
                               bluez5-obex \
                               bluez5-testtools  \
"
# Misc tools for end user
FEATURE_PACKAGES_misc-tools =" \
                               bridge-utils \
                               ethtool \
                               lsof \
                               libnl-cli \
                               parted \
                               tcpdump \
                               sudo \
                               net-tools \
                               bison \
                               elfutils \
                               nano \
"
FEATURE_PACKAGES_paho-mqtt =" \
                             paho-mqtt \
                             paho-mqtt-dev \
                             python-paho-mqtt \
"
# Taken from old refkit
FEATURE_PACKAGES_app-framework = " \
	packagegroup-app-framework \
"
FEATURE_PACKAGES_java-jdk = "packagegroup-java-jdk"
FEATURE_PACKAGES_ssh-server-openssh_append = " openssh-sftp-server"

FEATURE_PACKAGES_mraa-dev-support = "mraa-dev mraa-doc"
FEATURE_PACKAGES_tools-develop_append = " cmake"

# Transport Layer Security support
FEATURE_PACKAGES_tls-support =" \
                             gnutls-dev \
"
FEATURE_PACKAGES_python-extra =" \
                             python-pygobject \
"
# A set of profiling tools
FEATURE_PACKAGES_profiling-tools =" \
                              babeltrace \
                              groff \
                              libaio \
                              man \
                              powertop \
                              systemtap \
                              trace-cmd \
"

FEATURE_PACKAGES_python3-extra =" \
			     python3-pygobject \
"
PACKAGECONFIG_append_pn-alsa-utils = " udev"

DSK_IMAGE_LAYOUT = ' \
{ \
    "gpt_initial_offset_mb": 3, \
    "gpt_tail_padding_mb": 3, \
    "partition_01_primary_uefi_boot": { \
        "name": "primary_uefi", \
        "uuid": 0, \
        "size_mb": 30, \
        "source": "${IMAGE_ROOTFS}/boot/", \
        "filesystem": "vfat", \
        "type": "${PARTITION_TYPE_EFI}" \
    }, \
    "partition_02_secondary_uefi_boot": { \
        "name": "secondary_uefi", \
        "uuid": 0, \
        "size_mb": 30, \
        "source": "${IMAGE_ROOTFS}/boot/", \
        "filesystem": "vfat", \
        "type": "${PARTITION_TYPE_EFI_BACKUP}" \
    }, \
    "partition_03_rootfs": { \
        "name": "rootfs", \
        "uuid": "${REMOVABLE_MEDIA_ROOTFS_PARTUUID_VALUE}", \
        "size_mb": ${REF_OS_ROOTFS_SIZE}, \
        "source": "${IMAGE_ROOTFS}", \
        "filesystem": "ext4", \
        "type": "8300" \
    } \
}'

inherit refkit-image
WKS_FILE = "refosiot.wks.in"
