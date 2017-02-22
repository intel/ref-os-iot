# Ostro OS initramfs image. Derived from core-image-minimal-initramfs,
# with initramfs-framework instead of initramfs-live-boot and
# initramfs-live-install.
#
# Debugging tips for local.conf:
# SYSLINUX_PROMPT = "100" - show menu for 10 seconds
# AUTO_SYSLINUXMENU = "1" - automatically generate menu with choice between graphics and serial boot
# SYSLINUX_TIMEOUT = "100" - boot after waiting for 10 seconds

DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."

PACKAGE_INSTALL = "busybox base-passwd ${ROOTFS_BOOTSTRAP_INSTALL} ${FEATURE_INSTALL} kernel-modules"

PACKAGE_INSTALL += "initramfs-framework-base"
PACKAGE_INSTALL += "initramfs-module-udev"
PACKAGE_INSTALL += "initramfs-module-e2fs"
PACKAGE_INSTALL += "initramfs-module-rootfs"
PACKAGE_INSTALL += "initramfs-module-debug"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""


IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

USE_DEVFS = "0"

IMAGE_ROOTFS_SIZE = "8192"

