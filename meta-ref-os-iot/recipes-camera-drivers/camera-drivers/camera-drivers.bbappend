FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Add-support-for-additional-camera-configurations.patch"
SRC_URI += "file://0001-crlmodule-ov5670-imx214-Remove-voltage-rail-spec.patch"
SRC_URI += "file://0002-intel-ipu4-Support-multi-connections-for-the-BE-SOC.patch"
SRC_URI += "file://0003-intel-ipu4-Add-delay-to-ov5670-stream-on.patch"
SRC_URI += "file://0001-crlmodule-ov5670-Correct-flip-register-settings.patch"
SRC_URI += "file://0002-crlmodule-ov5670-Add-binned-720p-resolution.patch"
