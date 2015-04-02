#!/usr/bin/env bash

rm -fr test-proj
mkdir test-proj
g8 file://. --name=test-proj --force
cd test-proj
sbt