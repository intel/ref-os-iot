FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-vulkan-Fix-uninitialized-variable.patch \
            file://0001-loader-free-new_phys_devs-as-one-block-like-it-was-a.patch \
            file://0001-Vulkan-Fix-library-handle-leak.patch \
            file://0001-Fix-out-of-bounds-access-in-loader.patch \
            file://0001-static-analysis-remove-reference-to-file_vers-after-.patch \
            file://0001-loader-fix-unterminated-string-buffer.patch \
            file://0001-Backported-strncat-patch-to-1.0.39-release.patch \
            "
