inherit image_types

IMAGE_DEPENDS_fastboot_5xx = "virtual/kernel:do_deploy fastboot-support zip-native kernelflinger bios jq-native"
IMAGE_TYPEDEP_fastboot_5xx = "ext4"

FASTBOOT_FLASH_DIR = "${DEPLOY_DIR_IMAGE}/fastbootFlash-${IMAGE_BASENAME}-${MACHINE}"

UEFI_ROOT_DIR = "${WORKDIR}/uefiapp-${PN}"
UEFI_PARTITION_IMAGE = "${FASTBOOT_FLASH_DIR}/primary_uefi.vfat"


IMAGE_CMD_fastboot_5xx () {
	rm -rf 	${FASTBOOT_FLASH_DIR}

	install -d ${FASTBOOT_FLASH_DIR}/

	install ${DEPLOY_DIR_IMAGE}/fastboot/gpt_ini2bin.py	${FASTBOOT_FLASH_DIR}/
	install ${DEPLOY_DIR_IMAGE}/fastboot/partition.ini	${FASTBOOT_FLASH_DIR}/

	ROOTFS_IMAGE_FILE=$(basename ${WORKDIR}/deploy-${IMAGE_BASENAME}-image-complete/${IMAGE_BASENAME}-${MACHINE}.ext4)
	dd if=${WORKDIR}/deploy-${IMAGE_BASENAME}-image-complete/${IMAGE_BASENAME}-${MACHINE}.ext4 \
	   of=${FASTBOOT_FLASH_DIR}/${ROOTFS_IMAGE_FILE} \
	   ibs=4096 \
	   obs=1M \
	   conv=sync  # pad to 4096 byte boundary

	python ${FASTBOOT_FLASH_DIR}/gpt_ini2bin.py ${FASTBOOT_FLASH_DIR}/partition.ini > ${FASTBOOT_FLASH_DIR}/partition.bin

	# build ESP partition image
	rm -f ${UEFI_PARTITION_IMAGE}
	# FIXME: get proper size from partition table instead of a hardcoded value
	mkdosfs ${UEFI_PARTITION_IMAGE} -C 30720

	# emulate installer steps of switching to internal storage version of the EFI
	mcopy -i ${UEFI_PARTITION_IMAGE} -s ${UEFI_ROOT_DIR}/EFI_internal_storage ::/EFI

	install ${DEPLOY_DIR_IMAGE}/kernelflinger.efi		${FASTBOOT_FLASH_DIR}/

        cp -r ${DEPLOY_DIR_IMAGE}/bios/*                        ${FASTBOOT_FLASH_DIR}/

	BIOS_IMAGE_FILE=$(basename ${DEPLOY_DIR_IMAGE}/bios/*.bin)
	DNX_IMAGE_FILE=$(basename ${DEPLOY_DIR_IMAGE}/bios/DNX/*.bin)
	cat ${DEPLOY_DIR_IMAGE}/fastboot/flash.json \
	    | jq ".flash.parameters.rootfs.value = \"${ROOTFS_IMAGE_FILE}\"" \
	    | jq ".flash.parameters.bios.value   = \"${BIOS_IMAGE_FILE}\"" \
	    | jq ".flash.parameters.fw_dnx.value = \"${DNX_IMAGE_FILE}\""    \
	    > ${FASTBOOT_FLASH_DIR}/flash.json

	zip -rj ${FASTBOOT_FLASH_DIR}.zip ${FASTBOOT_FLASH_DIR}/*
}
