# Recipe for compiling beignet for Intel 550x

require ../../../intel-iot-refkit/meta-refkit/recipes-opencl/beignet/beignet.inc

EXTRA_OECMAKE_append = " -DGEN_PCI_ID=0x1A85"

FILESEXTRAPATHS_append := "${THISDIR}/../../../intel-iot-refkit/meta-refkit/recipes-opencl/beignet/files"
