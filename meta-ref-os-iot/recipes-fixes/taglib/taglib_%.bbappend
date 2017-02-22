
do_configure_append () {
    # revert this, done by the original recipe
    # TODO: try to understand why this is done and are there some
    # issues which might pop up with boost?
    # Also, should we explicitly configure the library without boost?
    sed -i -e "s/atomic-not-exist.hpp/atomic.hpp/" ${S}/ConfigureChecks.cmake ${S}/taglib/\toolkit/trefcounter.cpp
}