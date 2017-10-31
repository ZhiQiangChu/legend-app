#Filename: mysql_backup.sh
#Author: 詹军政|zhanjz@sondon.net
#Date: 2017-08-23
#Desc: 数据库备份

#!/bin/bash
set -x
#MYSQLDUMP=`which mysqldump`
#MYSQL=`which mysql`
USER="sondonrw"
PASSWORD="sondonrw2017"
HOST="192.168.1.10"

# 备份数据库
DATA_BASES="user_app,dp_platform,dp_device"
#TABLES="dp_user dp_base_user_info,user_app dp_app_user_info,dp_platform dp_user_app,dp_device d_bind_record"
BACKUP_DIR="/home/sondon/backup/prod"
DATE=$(date +%Y%m%d)
# 创建备份文件夹
if [ ! -d "${BACKUP_DIR}" ]; then
    mkdir "${BACKUP_DIR}"
fi
OLD_IFS="$IFS"
IFS=","
arr=($DATA_BASES)
IFS="$OLD_IFS"
# Set default file permissions
umask 177
for db in ${arr[@]}
do
  echo "开始备份数据库${db}..."
  sql_file=${db}_${DATE}.sql
  mysqldump -u${USER} -p${PASSWORD} -h${HOST} ${db} > ${BACKUP_DIR}/${sql_file}
  if [ $? -eq 0 ]; then
    echo "备份数据库${db}成功!"
  else
    echo "备份数据库${db}失败!"
  fi
done
