do_install_append() {
  sed -i -e 's:#PubkeyAuthentication yes:PubkeyAuthentication yes:' ${D}${sysconfdir}/ssh/sshd_config
}
