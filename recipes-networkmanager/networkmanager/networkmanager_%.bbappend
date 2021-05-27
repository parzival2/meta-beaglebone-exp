FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = "file://Kourosh.nmconnection"
do_install_append(){
    install -m 0600 ${WORKDIR}/Kourosh.nmconnection ${D}${sysconfdir}/NetworkManager/system-connections/
}
