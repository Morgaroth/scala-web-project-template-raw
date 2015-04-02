#!/usr/bin/env bash

rm -f uftest
mkdir uftest
g8 file://. --name=uftest --force
