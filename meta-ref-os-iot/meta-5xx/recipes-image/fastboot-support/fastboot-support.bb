LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea398a763463b76b18da15f013c0c531"

inherit deploy

PR = "r5"

SRC_URI = " \
	   file://LICENSE \
	   file://gpt_ini2bin.py \
	   file://partition.ini \
	  "

S="${WORKDIR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_deploy () {

	# remove any prior deployments
	rm -rf ${DEPLOYDIR}/fastboot

	install -d 			${DEPLOYDIR}/fastboot
	install ${S}/gpt_ini2bin.py	${DEPLOYDIR}/fastboot
	install ${S}/partition.ini	${DEPLOYDIR}/fastboot
}

addtask deploy after do_install
