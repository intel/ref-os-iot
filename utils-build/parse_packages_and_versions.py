#!/usr/bin/env python

import sys

if len(sys.argv) <= 1:
    print("%s <pn-depends.dot>" % sys.argv[0])
    print("parses recipes-depends.dot file and prints out all packages and their versions")
    print("recipes-depends.dot can be generated with bitbake: bitbake -g <image name>")
    sys.exit(1)

packs = {}

f = open(sys.argv[1])

for line in f.readlines():
    if line.find("{") > 0 or line.find("}") > 0:
        continue
    if not line.startswith('"'):
        continue
    if line.find("[") < 0:
        continue

    p = line.split('"')[1]
    v = line.split(':')[1].split("\\n")[0]

    packs[p] = v

f.close()

for p in sorted(packs):
    print(p + " = " + packs[p])


"""
digraph depends {
    "libunistring-native" [label="libunistring-native\n:0.9.7-r0\nvirtual:native:/media/bcache/mig_ise-mig_os2/build/../intel-iot-refkit/openembedded-core/meta/recipes-support/libunistring/libunistring_0.9.7.bb"]
    "libunistring-native" -> "texinfo-dummy-native"
    "libunistring-native" -> "gnu-config-native"
"""
