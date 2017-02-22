ref-os-iot
################

This README file contains information on setting up, building, and booting
Reference Linux* for IOT project.

Building
=======================

These build instructions are for building with Yocto Project bitbake directly.
It is also possible to use Docker for building. Instructions for Docker can be found in
Iot Reference OS Kit for Intel(r) Architecture project, in intel-iot-refkit/README.rst

Here are the basic steps, preparation::

$ mkdir -p $HOME/work
$ cd $HOME/work
$ git clone https://github.com/01org/ref-os-iot
$ cd ref-os-iot
$ git submodule update --recursive --init
$ source 5xx-init-build-env

It is possible to build for different HW by selecting which *env file is sourced. Examples for this
are the 5xx-init-build-env for a 550x/570x board or edison-init-build-env for an Edison board.

Basic steps, build::

$ bitbake full-image

Updating Repositories
=====================

You may need to pull new content from the GitHub repo as it’s updated.
Use the following commands::

$ git pull
$ git submodule update --recursive

For more information about Git submodule commands, check this link: 
https://git-scm.com/docs/git-submodule

Installing the Images
=====================

See further instructions in:
https://software.intel.com/en-us/intel-joule-getting-started
