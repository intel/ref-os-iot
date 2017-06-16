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
SRC_URI_append = " file://wcove_clk.cfg"
SRC_URI_append = " file://wcove_tmu.cfg"
SRC_URI_append = " file://0001-Add-detecting-intel-board-for-atmel_mxt-driver.patch"
SRC_URI_append = " file://0002-xDCI-runtime-suspend-fix.patch"
SRC_URI_append = " file://0003-Enable-autosuspend-after-dwc3_resume.patch"
SRC_URI_append = " file://0004-Enable-USB-Type-C-HOST-mode-with-S0iX-suspend.patch"
SRC_URI_append = " file://0006-at24-Enable-M24M02-serial-EEPROM.patch"
SRC_URI_append = " file://0007-configfs-add-MTP-function.patch"
SRC_URI_append = " file://0001-serial-8250_port-export-serial8250_rpm_-get-put-_tx.patch"
SRC_URI_append = " file://0002-serial-8250_dma-power-off-device-after-TX-is-done.patch"
SRC_URI_append = " file://0001-gpio-gpio-wcove-Correct-wcove-gpio-register-address-.patch"
SRC_URI_append = " file://0001-drm-i915-Fix-kernel-crash-after-GPU-HANG.patch"
SRC_URI_append = " file://0002-drm-i915-Suspend-GuC-prior-to-GPU-Reset-during-GEM-s.patch"
SRC_URI_append = " file://0003-drm-i915-guc-enable-GuC-loading-submission-by-defaul.patch"
SRC_URI_append = " file://0001-platform-x86-Add-Whiskey-Cove-PMIC-TMU-support.patch"
SRC_URI_append = " file://0001-drm-i915-execlists-Reset-RING-registers-upon-resume.patch"

# Audio patches
SRC_URI_append = " file://0001-VENDOR-ASoC-intel-skylake-Add-DSP-FW-load-for-Bxt-M.patch"
SRC_URI_append = " file://0002-mfd-arizona-i2c-Add-intel-5xx-audio-mezzanine-card-A.patch"
SRC_URI_append = " file://0003-Revert-mfd-arizona-Add-gating-of-external-MCLKn-cloc.patch"
SRC_URI_append = " file://0004-Add-DMIC23-Pin-platform-dai-pin-to-skl-pcm.patch"
SRC_URI_append = " file://0005-Add-HDMI-audio-support-to-bxt_florida-board-driver.patch"
SRC_URI_append = " file://0006-extcon-arizona-Use-micd-pol-gpio-conditionally.patch"
SRC_URI_append = " file://0007-extcon-arizona-Change-the-arzona-extcon-driver-alias.patch"
SRC_URI_append = " file://0008-clk-wcove-Add-clock-driver-for-Whiskey-Cove-PMIC.patch"
SRC_URI_append = " file://0009-ASoc-bxt-florida-Request-WC-PMIC-sleep-clock.patch"
SRC_URI_append = " file://0010-ASoc-bxt-florida-Fix-codec-clocking-in-bxt-florida.patch"
SRC_URI_append = " file://0011-mfd-arizona-Set-pdata-and-supplies-for-Intel-WM8998.patch"
SRC_URI_append = " file://0012-ASoc-bxt-florida-Clean-up-unused-mappings-and-struct.patch"
SRC_URI_append = " file://0013-ASoc-bxt_florida-Ensure-that-codec-driver-is-loaded.patch"
SRC_URI_append = " file://0014-mfd-arizona-Request-interrupt-gpio-for-arizona-to-fi.patch"
SRC_URI_append = " file://0015-REVERTME-HACK-Fix-NHLT-sspsp-field-for-bxt-SSP0.patch"

do_compile_kernelmodules_append() {
    rm -rf ${B}/drivers/net/wireless/intel/iwlwifi
    rm -rf ${B}/net/mac80211
    rm -rf ${B}/net/wireless
}

require camera-patches-for-4.9.inc
