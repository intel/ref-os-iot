DESCRIPTION = "Collection of vulkan libraries to enable support for it"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS_${PN} = "\
    mesa-vulkan-drivers \
    vulkan \
    vulkan-bin \
    vulkan-dev \
    assimp \
    "
