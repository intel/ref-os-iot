FILESEXTRAPATHS_prepend := "${THISDIR}/linux-intel:"
SRC_URI_append = " file://touchscreen_atmel_mxt.cfg"
SRC_URI_append = " file://userspace_hid_driver.cfg"
SRC_URI_append = " file://wm8998-codec.cfg"
SRC_URI_append = " file://ti-ads1015.cfg"
SRC_URI_append = " file://usb-ethernet.cfg"
SRC_URI_append = " file://i915.cfg"
SRC_URI_append = " file://mei-spd.cfg"
SRC_URI_append = " file://bt_leds.cfg"
SRC_URI_append = " file://enable_mtp.cfg"
SRC_URI_append = " file://enable_u_serial_console.cfg"
SRC_URI_append = " file://kernel_dynamic_debug.cfg"
SRC_URI_append = " file://0001-Add-detecting-intel-board-for-atmel_mxt-driver.patch"
SRC_URI_append = " file://0002-xDCI-runtime-suspend-fix.patch"
SRC_URI_append = " file://0003-Enable-autosuspend-after-dwc3_resume.patch"
SRC_URI_append = " file://0004-Enable-USB-Type-C-HOST-mode-with-S0iX-suspend.patch"
SRC_URI_append = " file://0006-at24-Enable-M24M02-serial-EEPROM.patch"
SRC_URI_append = " file://0007-configfs-add-MTP-function.patch"
SRC_URI_append = " file://0001-serial-8250_port-export-serial8250_rpm_-get-put-_tx.patch"
SRC_URI_append = " file://0002-serial-8250_dma-power-off-device-after-TX-is-done.patch"

do_compile_kernelmodules_append() {
    rm -rf ${B}/drivers/net/wireless/intel/iwlwifi
    rm -rf ${B}/net/mac80211
    rm -rf ${B}/net/wireless
}

require camera-patches-for-4.9.inc
