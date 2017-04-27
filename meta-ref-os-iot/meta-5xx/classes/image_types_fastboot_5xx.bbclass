inherit image_types


IMAGE_DEPENDS_fastboot_5xx = "virtual/kernel:do_deploy fastboot-support"
IMAGE_TYPEDEP_fastboot_5xx = "ext4"

IMAGE_CMD_fastboot_5xx () {
	rm -rf 	${DEPLOY_DIR_IMAGE}/fastbootFlash

	install -d ${DEPLOY_DIR_IMAGE}/fastbootFlash/

	install ${DEPLOY_DIR_IMAGE}/fastboot/gpt_ini2bin.py	${DEPLOY_DIR_IMAGE}/fastbootFlash/
	install ${DEPLOY_DIR_IMAGE}/fastboot/partition.ini	${DEPLOY_DIR_IMAGE}/fastbootFlash/

	install ${WORKDIR}/deploy-${IMAGE_BASENAME}-image-complete/${IMAGE_BASENAME}-${MACHINE}.ext4	${DEPLOY_DIR_IMAGE}/fastbootFlash/

	python ${DEPLOY_DIR_IMAGE}/fastbootFlash/gpt_ini2bin.py ${DEPLOY_DIR_IMAGE}/fastbootFlash/partition.ini > ${DEPLOY_DIR_IMAGE}/fastbootFlash/partition.bin
}
