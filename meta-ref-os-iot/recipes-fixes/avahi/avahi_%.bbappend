# Remove this once refkit is fixed:
# https://github.com/intel/intel-iot-refkit/pull/175

EXTRA_OECONF_remove_refkit-config = " --enable-nls--enable-compat-libdns_sd"
EXTRA_OECONF_append_refkit-config = " --enable-nls --enable-compat-libdns_sd"

