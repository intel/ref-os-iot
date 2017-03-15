require boost-${PV}.inc
require boost.inc

SRC_URI += "\
    file://boost-CVE-2012-2677.patch \
    file://0001-boost-asio-detail-socket_types.hpp-fix-poll.h-includ.patch \
    file://0004-Use-atomic-by-default-when-BOOST_NO_CXX11_HDR_ATOMIC.patch \
    file://boost-math-disable-pch-for-gcc.patch \
    file://0002-Don-t-set-up-m32-m64-we-do-that-ourselves.patch \
"
