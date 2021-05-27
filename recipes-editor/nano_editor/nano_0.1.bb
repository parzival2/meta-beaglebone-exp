SUMMARY = "Recipe to build the `nano` editor."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

# set the Package name
PN = "nano"
# Set the Package version
PV = "5.7"
# Store the site in a variable
SITE = "http://www.nano-editor.org/dist"
# Store the Major and Minor versions in variable
PV_MAJOR = "${@d.getVar('PV', True).split('.')[0]}"
PV_MINOR = "${@d.getVar('PV', True).split('.')[1]}"
# The older versions have major.minor version folders. But the new versions
# doesnt have that format.
SRC_URI = "${SITE}/v${PV_MAJOR}/${BPN}-${PV}.tar.xz"
# Set the md5sum
# In linux md5sum can be calculated by entering md5sum package-name.tar.xz
SRC_URI[md5sum] = "c2d6540a33248edc31a5830861b4b4ea" 
# Set the sha256sum
# Like the md5sum, the sha256sum can also be calculated by entering
# sha256sum package-name.tar.gz
SRC_URI[sha256sum] = "d4b181cc2ec11def3711b4649e34f2be7a668e70ab506860514031d069cccafa"
# Add functions
python do_fetch(){
    src_uri = (d.getVar('SRC_URI',True) or "").split()
    bb.plain("Downloading source tarbar from : ", src_uri[0])
    bb.plain("Work dir : ", d.getVar('WORKDIR',True).split()[0])
    bb.plain("DL dir : ", d.getVar('DL_DIR',True).split()[0])
    bb.plain("{P} : ", d.getVar('P',True).split()[0])
    if len(src_uri) == 0:
        bb.fatal("Empty URI")
    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.download()
    except bb.fetch2.BBFetchException:
        bb.fatal("Could not download the source tarbal.")
    bb.plain("Download successful.")
}
addtask do_fetch before do_build
# Unpack task
python do_unpack(){
    import os
    import sys
    bb.plain("Work dir : ", d.getVar('WORKDIR',True).split()[0])
    bb.plain("DL dir : ", d.getVar('DL_DIR',True).split()[0])
    bb.plain("{P} : ", d.getVar('P',True).split()[0])
    bb.plain("Unpacking the source tarball...")
    dldir = d.getVar('DL_DIR',True).split()[0]
    p = d.getVar('P',True).split()[0]
    path = os.path.join(dldir, p + '.tar.xz')
    bb.plain("Path to the xz file in downloads : ", path)
    os.system("tar -xf ${DL_DIR}/${P}.tar.xz -C ${WORKDIR}")
    bb.plain("Unpacked source tarball..")
}
addtask do_unpack before do_build after do_fetch
# Configure
python do_configure(){
    bb.plain("Configuring source package---")
    os.system("cd ${WORKDIR}/${P} && ./configure")
    bb.plain("Configured source package")
}
addtask do_configure before do_build after do_unpack
# Compile
python do_compile(){
    bb.plain("Compiling package...")
    os.system("cd ${WORKDIR}/${P} && make")
    bb.plain("Compiled Package")
}
addtask do_compile before do_build after do_configure