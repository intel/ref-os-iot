SUMMARY = "Automatic management of removable drives and media for thunar"
# Remove support for ejecting removable media
RDEPENDS_${PN}_remove += "eject"
