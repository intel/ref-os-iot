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
# To support the alternative configuration where the imx214 is attached to
# JCAM2 and ov5670 to JCAM1, uncomment the below line:
#SRC_URI += "file://files/0001-Change-HAL-configurations-to-swap-imx214-and-ov5670.patch"

