SUMMARY = "Ref OS IOT image for local builds with swupd disabled."
DESCRIPTION = "Ref OS IOT headless image"

# In normal images without swupd it is possible to set per-image
# variables for a specific image recipe using the the _pn-<image name>
# notation.  However, that stops working once the swupd feature gets
# enabled (because that internally relies on virtual images under
# different names), so the recommended approach is to have per-recipe
# variables (like REFKIT_IMAGE_NOSWUPD_EXTRA_FEATURES) and customize
# those outside the recipe:
REFKIT_IMAGE_NOSWUPD_EXTRA_FEATURES ?= "${REFKIT_IMAGE_FEATURES_COMMON}"
REFKIT_IMAGE_NOSWUPD_EXTRA_INSTALL ?= "${REFKIT_IMAGE_INSTALL_COMMON}"
REFKIT_IMAGE_EXTRA_FEATURES += "${REFKIT_IMAGE_NOSWUPD_EXTRA_FEATURES}"
REFKIT_IMAGE_EXTRA_INSTALL += "${REFKIT_IMAGE_NOSWUPD_EXTRA_INSTALL}"

# If the default "refkit-image-noswupd" name is
# undesirable, write a custom image recipe similar to this one here (although
# refkit-image-minimal.bb might be a better starting point), or customize the
# image file names when continuing to use refkit-image-noswupd.bb.
#
# Example for customization in local.conf when building refkit-image-noswupd.bb:
# IMAGE_BASENAME_pn-refkit-image-noswupd = "my-refkit-image-reference"
# REFKIT_IMAGE_NOSWUPD_EXTRA_INSTALL_append = "my-own-package"
# REFKIT_IMAGE_NOSWUPD_EXTRA_FEATURES_append = "dev-pkgs"

# REF OS IOT image features
REF_OS_IMAGE_PKG_FEATURES = " \
     tools-interactive \
     ssh-server-openssh \
     empty-root-password \
     usb-gadget-networking \
     tools-sdk \
     package-management \
"

IMAGE_FEATURES += " \
    ${REF_OS_IMAGE_PKG_FEATURES} \
"

LICENSE = "MIT"

REF_OS_ROOTFS_SIZE = "720"

inherit refosiot-image
