#!/usr/bin/env bash

set -e

if [[ -z "$DATABASE_NAME" ]]; then
  echo "Error: database name not specified"
  echo "Please set the 'DATABASE_NAME' variable"
  exit
fi

# https://stackoverflow.com/a/70976611/8124214
psql -U ${POSTGRES_USER} <<-END
  CREATE DATABASE $DATABASE_NAME
    WITH
    OWNER = ${POSTGRES_USER}
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
END