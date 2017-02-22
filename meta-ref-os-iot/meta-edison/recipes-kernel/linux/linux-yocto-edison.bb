inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE = "edison"

SRC_URI = "git://github.com/01org/edison-linux.git;protocol=https;branch=edison-3.10.98"
SRCREV = "b83b7a6fd37424078be4c98c454c25dd4c0355ad"
PV="3.10.98"

KBUILD_DEFCONFIG_edison = "i386_edison_defconfig"

do_kernel_configme() {
  echo "skip this"
}

