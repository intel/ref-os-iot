Intel(R) Ref-OS-IoT
###################

This README file contains information about building the Intel(R) Reference Operating System for the Internet of Things, or Intel(R) REF-OS-IOT for short, from the supplied source.
 
Intel(R) REF-OS-IOT is a Linux*-based OS designed to work elegantly and efficiently with the Intel(R) Joule(TM) Compute Module or Intel(R) Edison Compute Module.

INSTRUCTIONS
################
These instructions are for building with bitbake directly and assume basic knowledge of build environments and processes. For more information on bitbake see https://www.yoctoproject.org/tools-resources/projects/bitbake
NOTE: Instructions for Docker* can be found in the IoT Reference OS Kit for Intel(R) Architecture project, see https://github.com/intel/intel-iot-refkit.
NOTE: The method described below needs around 135GB of disk space.

Setup Ubuntu 16.04 host for build:
=====================================
| $ sudo apt update
| $ sudo apt-get install gawk wget git-core git diffstat unzip texinfo gcc-multilib build-essential chrpath socat libsdl1.2-dev xterm subversion xsltproc

Preparation:
=======================
| $ mkdir -p $HOME/work
| $ cd $HOME/work
| $ git clone https://github.com/01org/ref-os-iot
| $ cd ref-os-iot
| $ git submodule update --recursive --init
| $ source 5xx-init-build-env

The above example is for use with the Intel(R) Joule(TM) Compute Module (550x or 570x), but it is also possible to build for different compute modules by simply selecting which env file is sourced, e.g., for the Intel(R) Edison Compute Module the source command would be:

$ source edison-init-build-env for an Edison board.

Building:
=======================
$ bitbake full-image

You may need to pull new content from the GitHub* repo as it’s updated. To do this use the following commands:

| $ git pull
| $ git submodule update --recursive

For more information about Git submodule commands, check this link: https://git-scm.com/docs/git-submodule

For more information about Intel(R) REF-OS-IOT and how to use it with the compute modules see https://software.intel.com/en-us/iot/home

\*OTHER NAMES AND BRANDS MAY BE CLAIMED AS THE PROPERTY OF OTHERS
