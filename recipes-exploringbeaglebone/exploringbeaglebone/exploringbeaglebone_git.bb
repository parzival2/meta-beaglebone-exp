# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/parzival2/ExploringBeaglebone.git;protocol=ssh;user=parzival2:KALyan1991"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "c25f4e8db2bb9a4f3e9c050577f6479e1f27d974"

S = "${WORKDIR}/git"

# Add dependencies
DEPENDS += "libgpiod"

# NOTE: the following library dependencies are unknown, ignoring: libgpiodcxx libgpiod
#       (this is based on recipes that have previously been built and packaged)
inherit cmake

python do_print(){
    bb.plain("-----------------COMPILING EXPLORING BEAGLEBONE-----------------------")
    bdir = d.getVar('B', True)
    bb.plain(bdir)
    root_dir = d.getVar('ROOT_HOME', True)
    bb.plain("ROOT_HOME directory : ", root_dir)
}

addtask print after do_install before do_populate_sysroot

FILES_${PN} += "${ROOT_HOME}/exploring_beaglebone/*"

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "\
                  -DCMAKE_INSTALL_PREFIX=/home/root"

