#!/usr/bin/env python

import sys

if len(sys.argv) <= 1:
    print("%s <pn-depends.dot>" % sys.argv[0])
    print("parses pn-depends.dot file and prints out all packages and their versions")
    print("pn-depends.dot can be generated with bitbake: bitbake -g <image name>")
    sys.exit(1)

packs = {}

f = open(sys.argv[1])

for line in f.readlines():
    if line.find(" -> ") > 0:
        break
    if line.find("{") > 0:
        continue
    if not line.startswith('"'):
        continue

    p = line.split('"')[1]
    v = line.split(':')[1].split("\\n")[0]

    packs[p] = v

f.close()

for p in sorted(packs):
    print(p + " = " + packs[p])
