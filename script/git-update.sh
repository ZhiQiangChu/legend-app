#!/usr/bin/env bash

GIT_DIR="/home/sondon/workspace-git"

cd $GIT_DIR

for p in `ls`; do
    if [ -d "$GIT_DIR/$p" ]; then
        cd $GIT_DIR/$p
        git pull
        echo "$p 同步成功"
    fi
done

