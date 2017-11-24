#!/bin/sh
nohup java ${vm} -jar ${project}-${bboss_version}.jar  --conf=configlicense.properties > ${project}.log &
tail -f ${project}.log

