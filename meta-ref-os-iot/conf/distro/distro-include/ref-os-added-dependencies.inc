RDEPENDS_pn-gstreamer1.0-meta-audio_append = " \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-good-pulse \
    gstreamer1.0-plugins-base-audiotestsrc \
    "
DEPENDS_pn-gstreamer1.0_append = " libunwind"
DEPENDS_append_pn-libgphoto2 = " libusb"
DEPENDS_append_pn-xfdesktop = " dbus-glib-native"
DEPENDS_append_pn-xfce4-notifyd = " dbus-glib-native"
DEPENDS_append_pn-thermald = " dbus-glib-native"
DEPENDS_append_pn-gtksourceview2 = " glib-2.0-native"
DEPENDS_append_pn-python-pyyaml = " python-setuptools"
DEPENDS_append_pn-glibmm = " glib-2.0-native"

HOSTTOOLS_append = " xz pkg-config cc bc svn seq"
