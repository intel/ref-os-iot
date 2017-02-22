inherit image_types

IMAGE_DEPENDS_boot = "virtual/kernel:do_deploy dosfstools-native mtools-native"
IMAGE_TYPEDEP_boot = "ext4 tar"

IMAGE_CMD_boot () {

	BLOCKS=32768
	rm -f ${WORKDIR}/boot.img
	rm -f ${DEPLOY_DIR_IMAGE}/${IMAGE_BASENAME}-${MACHINE}.hddimg

	mkfs.vfat -n "boot" -S 512 -C ${WORKDIR}/boot.img $BLOCKS

	# Copy kernel
	mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/bzImage-edison.bin ::/vmlinuz

	# Copy kernel (includes initramfs)
	if [ -e ${DEPLOY_DIR_IMAGE}/bzImage-initramfs-edison.bin ]; then
		mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/bzImage-initramfs-edison.bin ::/vmlinuzi
	fi

	# Copy fota initramfs
#	if [ -e ${DEPLOY_DIR_IMAGE}/core-image-initramfs-edison.cpio.gz ]; then
#		mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/core-image-initramfs-edison.cpio.gz ::/initramfs
#	fi

	install ${WORKDIR}/boot.img ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.hddimg
	ln -s ${IMAGE_NAME}.hddimg ${DEPLOY_DIR_IMAGE}/${IMAGE_BASENAME}-${MACHINE}.hddimg
}

IMAGE_DEPENDS_update = "dosfstools-native mtools-native"
IMAGE_TYPEDEP_update = "tar.bz2 boot"

IMAGE_CMD_update () {
        echo "No update"
}


IMAGE_DEPENDS_toflash = "ifwi flashall u-boot u-boot-mkimage-native"
IMAGE_TYPEDEP_toflash = "ext4 boot update"

IMAGE_CMD_toflash () {

	DEPLOY_DIR_WORK=${WORKDIR}/deploy-${IMAGE_BASENAME}-image-complete

	rm -rf 	${WORKDIR}/toFlash
	install -d ${WORKDIR}/toFlash/u-boot-envs/
	install -d ${WORKDIR}/toFlash/helper/images

	install ${DEPLOY_DIR_WORK}/${IMAGE_NAME}.rootfs.ext4	${WORKDIR}/toFlash/edison-image-edison.ext4
	install ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.hddimg	${WORKDIR}/toFlash/edison-image-edison.hddimg

	install ${DEPLOY_DIR_IMAGE}/u-boot-edison.bin		${WORKDIR}/toFlash/
	install ${DEPLOY_DIR_IMAGE}/u-boot-edison.img		${WORKDIR}/toFlash/
	install ${DEPLOY_DIR_IMAGE}/u-boot-envs/*.bin		${WORKDIR}/toFlash/u-boot-envs/

	install ${DEPLOY_DIR_IMAGE}/ifwi/*.bin			${WORKDIR}/toFlash/

	install ${DEPLOY_DIR_IMAGE}/flashall/filter-dfu-out.js	${WORKDIR}/toFlash/
	install ${DEPLOY_DIR_IMAGE}/flashall/flashall.*		${WORKDIR}/toFlash/
	install ${DEPLOY_DIR_IMAGE}/flashall/pft-config-edison.xml ${WORKDIR}/toFlash/

	install ${DEPLOY_DIR_IMAGE}/flashall/FlashEdison.json	${WORKDIR}/toFlash/
	install ${DEPLOY_DIR_IMAGE}/flashall/helper/helper.html	${WORKDIR}/toFlash/helper/
	install ${DEPLOY_DIR_IMAGE}/flashall/helper/images/*.png ${WORKDIR}/toFlash/helper/images/


	# generate a formatted list of all packages included in the image
	awk '{print $1 " " $3}' ${DEPLOY_DIR_WORK}/${IMAGE_NAME}.rootfs.manifest > ${WORKDIR}/toFlash/package-list.txt

	tar -cvjf ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.toflash.tar.bz2 -C ${WORKDIR}/toFlash/ .

	rm -f ${DEPLOY_DIR_IMAGE}/${IMAGE_BASENAME}-${MACHINE}.toflash.tar.bz2
	ln -s ${IMAGE_NAME}.toflash.tar.bz2 ${DEPLOY_DIR_IMAGE}/${IMAGE_BASENAME}-${MACHINE}.toflash.tar.bz2
}
