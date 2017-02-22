SUMMARY = "Xfce4 settings"
RRECOMMENDS_${PN}_remove += "libcanberra-pulse"
RRECOMMENDS_${PN}_remove += "libcanberra-alsa"
PACKAGECONFIG[sound-setter] = "--enable-sound-settings, --disable-sound-settings, sound-theme-freedesktop"
