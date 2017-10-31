##########################################################################
# Author: zhanjz@sondon.net
# Created: 2017-10-31 10:00:00
# Description: Export Mongo DB data
###########################################################################
#!/bin/bash
#############################Configuration begin##########################
# Config Mongodb infomation
MONGO_HOST=10.3.2.25
MONGO_DB_NAME=breed
MONGO_COLLECTIONS="BREED,INDICATOR"
MONGO_EXPORT_CLIENT=/usr/bin/mongoexport
# Base data dictionary
DATA_DIR=/home/sondon/data/mongodb
#############################Configuration end############################
# export date
base_date=$(date +%Y-%m-%d)

# create dictionary
mkdir -p ${DATA_DIR}/${MONGO_DB_NAME}

OLD_IFS="$IFS"
IFS=","
arr=(${MONGO_COLLECTIONS})
IFS="$OLD_IFS"

for collection in ${arr[@]}
do

    echo "Export collection ${collection} ..."
    output_file=${DATA_DIR}/${collection}-${base_date}.json
    ${MONGO_EXPORT_CLIENT}  --host ${MONGO_HOST} --db ${MONGO_DB_NAME} --collection ${collection} --type=json -o ${output_file}
    lines=$(wc -l $output_file | awk '{print $1}')
    echo "Export collection ${collection} done,total $lines lines."
done