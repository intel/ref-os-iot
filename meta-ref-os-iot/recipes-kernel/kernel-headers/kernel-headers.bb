SUMMARY = "Linux kernel Development Headers"
DESCRIPTION = "Development header linux kernel. When built, this recipe packages the \
headers and build collaterals of the preferred virtual/kernel provider and makes it \
available for external module builds"

SECTION = "kernel"

LICENSE = "GPLv2"

inherit module-base

# We need the kernel to be staged (unpacked, patched and configured) before
# we can grab the headers and make the package. We also need the bits from
# ${B} not to change while we install, so virtual/kernel must finish do_compile.
do_install[depends] += "virtual/kernel:do_shared_workdir"
# Need the source, not just the output of populate_sysroot
do_install[depends] += "virtual/kernel:do_install"

# There's nothing to do here, except install the source where we can package it
do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_compile[noexec] = "1"
do_configure[noexec] = "1"
do_populate_sysroot[noexec] = "1"

S = "${STAGING_KERNEL_DIR}"
B = "${STAGING_KERNEL_BUILDDIR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

KERNEL_HEADER_PATH = "${KERNEL_SRC_PATH}-headers-${KERNEL_VERSION}"

do_install() {
        kerneldir=${D}${KERNEL_HEADER_PATH}
        install -d $kerneldir

	# generate headers by copying various files from build and sources directories
	cd ${B}
	find . -name '.config' -print0 \
	     -o -name 'kernel-abiversion' -print0 \
	     -o -name 'Module*' -print0 \
	     -o -name 'System.map*' -print0 | cpio --null -pdlu $kerneldir
	find include/ -print0 | cpio --null -pdlu $kerneldir
	find arch/*/include -type d -name 'source' -prune -o -name '*.h' -print0 | cpio --null -pdlu $kerneldir
	cd ${S}
	find . -name 'Kbuild*' -print0 -o -name 'Makefile*' -print0 \
	     -o -name '*.conf' -print0 -o -name 'Kconfig*' -print0 \
	     -o -name '*.sh' -print0 | cpio --null -pdlu $kerneldir
	find include/ -name '*.h' -print0 | cpio --null -pdlu $kerneldir
	find arch/ -name '*.h' -print0 | cpio --null -pdlu $kerneldir
	find scripts/ -type d -name '.debug' -prune -o -type f -print0 | cpio --null -pdlu $kerneldir
	find tools/ -name '*.h' -print0 | cpio --null -pdlu $kerneldir
	find security/ -name '*.h' -print0 | cpio --null -pdlu $kerneldir

	if [ -L $kerneldir/source ]; then
            bbnote "Removing $kerneldir/source symlink"
            rm -f $kerneldir/source
        fi

	# create a ubuntu-like symlink for the kernel headers
	install -d ${D}/lib/modules/${KERNEL_VERSION}/
	ln -sf ${KERNEL_HEADER_PATH} ${D}/lib/modules/${KERNEL_VERSION}/build

        chown -R root:root ${D}
}
# Ensure we don't race against "make scripts" during cpio
do_install[lockfiles] = "${TMPDIR}/kernel-scripts.lock"

PACKAGES = "kernel-headers"
FILES_${PN} = "${KERNEL_HEADER_PATH} /lib/"
RDEPENDS_${PN} = "bc"
