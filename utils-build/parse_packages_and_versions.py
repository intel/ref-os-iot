#!/usr/bin/env python

import sys

if len(sys.argv) <= 1:
    print("%s <recipes-depends.dot> [<recipes-depends.dot>]" % sys.argv[0])
    print("parses recipes-depends.dot file and prints out all packages and their versions")
    print("recipes-depends.dot can be generated with bitbake: bitbake -g <image name>")
    print("if two <recipes-depends.dot> files are given, then a diff from packages is generated")
    sys.exit(1)

def create_list(fname):
    packs = {}

    f = open(fname)

    for line in f.readlines():
        if line.find("{") > 0 or line.find("}") > 0:
            continue
        if not line.startswith('"'):
            continue
        if line.find("[") < 0:
            continue

        p = line.split('"')[1]
        if p.endswith("-native"):
            continue

        v = line.split(':')[1].split("\\n")[0]

        packs[p] = v

    f.close()
    return packs

def list_packages(packs):
    for p in sorted(packs):
        print(p + " = " + set1[p])

def diff_packages(set1, set2):
    added = {}
    removed = {}
    changed = {}

    for p in set2:
        if p in set1:
            if set1[p] != set2[p]:
                changed[p] = [set1[p],set2[p]]
        else:
            added[p] = set2[p]
    for p in set1:
        if p not in set2:
            removed[p] = set1[p]

    if len(changed) > 0:
        print("Updates:")
        for p in changed:
            print("\t%s: %s -> %s" % (p, changed[p][0], changed[p][1]))
    if len(added) > 0:
        print("Added:")
        for p in added:
            print("\t%s: %s" % (p, added[p]))
    if len(removed) > 0:
        print("Removed:")
        for p in removed:
            print("\t%s: %s" % (p, removed[p]))

if len(sys.argv) > 2:
    set1 = create_list(sys.argv[1])
    set2 = create_list(sys.argv[2])

    diff_packages(set1, set2)

elif len(sys.argv) > 1:
    set1 = create_list(sys.argv[1])

    list_packages(set1)

sys.exit(0)


"""
digraph depends {
    "libunistring-native" [label="libunistring-native\n:0.9.7-r0\nvirtual:native:/media/bcache/mig_ise-mig_os2/build/../intel-iot-refkit/openembedded-core/meta/recipes-support/libunistring/libunistring_0.9.7.bb"]
    "libunistring-native" -> "texinfo-dummy-native"
    "libunistring-native" -> "gnu-config-native"
"""
