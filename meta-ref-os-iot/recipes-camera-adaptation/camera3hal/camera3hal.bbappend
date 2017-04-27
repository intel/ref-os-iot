FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "file://files/0001-Add-camerahal.cfg-file.patch"

# Different HW configurations of the cameras require changes in the
# camera HAL configuration files to accomodate to cameras being attached
# to different CSI ports.
#
# For the default camera configuration where the imx214 13 Mpixel camera
# is connected to the JCAM1 connector and the ov5670 5 Mpixel camera is
# connected to the JCAM2 connector, leave the below lines commented out.
#
# Please note the below configurations are mutually exclusive.
# Enable only one of them.
#
# To support the alternative configuration where the imx214 is attached to
# JCAM2 and ov5670 to JCAM1, uncomment the below line:
#SRC_URI += "file://files/0001-Change-HAL-configurations-to-swap-imx214-and-ov5670.patch"
#
# To support a configuration of two ov5670 cameras attached to JCAM1 and two
# ov5670 cameras attached to JCAM2 (or a subset of this), uncomment the below
# lines:
#SRC_URI += "file://files/0001-c3-ipu4-Add-support-for-up-to-four-ov5670-cameras.patch"
#SRC_URI += "file://files/0002-c3-ipu4-Modify-ov5670-graph-for-720p-to-use-binned-o.patch"
#SRC_URI += "file://files/0003-c3-ipu4-Correct-I2C-bus-numbers-for-the-4.9-kernel.patch"
#SRC_URI += "file://files/libcamerahal.so.0.0.0"
#do_install_prepend() {
#    cp ${S}/files/libcamerahal.so.0.0.0 ${S}/usr/lib/libcamerahal.so.0.0.0
#}
