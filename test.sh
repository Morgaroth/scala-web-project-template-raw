#!/usr/bin/env bash

DIR=project-test
rm -rf ${DIR}
mkdir ${DIR}
g8 file://. --name=${DIR} --force
cd ${DIR}
sbt
