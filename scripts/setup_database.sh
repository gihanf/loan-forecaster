#!/bin/bash

echo "Dropping and recreating database..."
mysql -uroot -ppaymenow << EOF
drop database if exists forecaster_db;
create database forecaster_db;
EOF
