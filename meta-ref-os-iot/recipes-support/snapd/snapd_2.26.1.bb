SUMMARY = "The snapd and snap tools enable systems to work with .snap files."
HOMEPAGE = "https://www.snapcraft.io"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = " \
	https://github.com/snapcore/snapd/releases/download/${PV}/snapd_${PV}.vendor.tar.xz \
"

SRC_URI[md5sum] = "8152560d2af809ad84185d3b341b2f13"
SRC_URI[sha256sum] = "f86f98b3da608816304dbd5a3f4f914ce93fe28d6cde1990563a2029c70798de"

GO_IMPORT = "github.com/snapcore/snapd"

DEPENDS += " \
	go-cross \
	glib-2.0 \
	udev \
	xfsprogs \
"

RDEPENDS_${PN} += " \
	ca-certificates \
	kernel-module-squashfs \
"

S = "${WORKDIR}/${PN}-${PV}"

EXTRA_OECONF += " \
	--disable-apparmor \
	--disable-seccomp \
	--libexecdir=${libdir}/snapd \
"

inherit systemd autotools pkgconfig go

# Our tools build with autotools are inside the cmd subdirectory
# and we need to tell the autotools class to look in there.
AUTOTOOLS_SCRIPT_PATH = "${S}/cmd"

SYSTEMD_SERVICE_${PN} = "snapd.service"

do_configure_prepend() {
	(cd ${S} ; ./mkversion.sh ${PV})
}

do_compile() {
	# Ensure we our component at the right place in our GOPATH
	mkdir -p ${STAGING_LIBDIR}/${TARGET_SYS}/go/src/github.com/snapcore
	ln -sf ${S} ${STAGING_LIBDIR}/${TARGET_SYS}/go/src/github.com/snapcore/snapd

	for d in snap snapd snap-exec snapctl; do
		GOPATH=${STAGING_LIBDIR}/${TARGET_SYS}/go go build github.com/snapcore/snapd/cmd/$d
	done

	oe_runmake
}

do_install() {
	install -d ${D}${libdir}/snapd
	install -d ${D}${bindir}
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}/var/lib/snapd
	install -d ${D}/var/lib/snapd/snaps
	install -d ${D}/var/lib/snapd/lib/gl
	install -d ${D}/var/lib/snapd/desktop
	install -d ${D}/var/lib/snapd/environment
	install -d ${D}/var/snap
	install -d ${D}${sysconfdir}/profile.d

	oe_runmake -C ${B} install DESTDIR=${D}
	oe_runmake -C ${S}/data/systemd install DESTDIR=${D}

	install -m 0755 ${B}/snapd ${D}${libdir}/snapd/
	install -m 0755 ${B}/snap-exec ${D}${libdir}/snapd/
	install -m 0755 ${B}/snap ${D}${bindir}
	install -m 0755 ${B}/snapctl ${D}${bindir}

	echo 'PATH=$PATH:/snap/bin' > ${D}${sysconfdir}/profile.d/20-snap.sh
	install -d ${D}/root
}

RDEPENDS_${PN} += "squashfs-tools"
FILES_${PN} += " \
	${systemd_unitdir}/system/ \
	/var/lib/snapd \
	/var/snap \
	${baselib}/udev/snappy-app-dev \
	/root \
"

# ERROR: snapd-2.23.5-r0 do_package_qa: QA Issue: No GNU_HASH in the elf binary:
# '.../snapd/usr/lib/snapd/snap-exec' [ldflags]
INSANE_SKIP_${PN} = "ldflags"
