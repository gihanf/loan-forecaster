#!/bin/bash

echo "Dropping and recreating database..."
mysql -uroot -pbollox < dump.sql
