
# Instead of fetching the pre-built version 1.7.2 of the intel-driver,
# fetch instead the HEAD of the master branch as it was on the 2nd February 2017
# In particular, at least this patch is needed:
# a59c0439 Enable AVC VDEnc on BXT
SRC_URI = "git://anongit.freedesktop.org/vaapi/intel-driver;tag=05d2d25c16a52d16c3f4cee14bfa4ca8f0209ba9"
S = "${WORKDIR}/git"
 
