#!/bin/bash

echo "Hello there"
echo `whoami`

#mysql -h "localhost" -u "root" -p "paymenow" < filename.sql

mysql -uroot -ppaymenow << EOF
drop database forecaster_db;
EOF
