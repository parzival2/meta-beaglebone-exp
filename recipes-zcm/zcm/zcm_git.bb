# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=435ed639f84d4585d93824e7da3d85da"

SRC_URI = "gitsm://github.com/ZeroCM/zcm.git;protocol=https \
	   file://Manual-compiler-print.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "28c95fcc9dc4c9e4e05fdc62bb0cc2ad5a3c94a9"

S = "${WORKDIR}/git"

# It is a waf project so inherit waf class
inherit waf pkgconfig

INSANE_SKIP_${PN} += " ldflags"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

DEPENDS = "zeromq"
RDEPENDS_${PN} += " bash"

FILES_${PN} += "${datadir}/*"
FILES_${PN} += "${libdir}/zcm.*"
FILES_${PN}-dev = "${libdir}/* ${includedir}/*"

EXTRA_OECONF += "--use-zmq --use-ipc --use-inproc --use-udpm"

TOOLCHAIN_HOST_TASK_append += " zcm-dev"
