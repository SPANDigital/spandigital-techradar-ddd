#!/bin/bash

BASE_DIR=$(dirname $0)

for i in `ls ${BASE_DIR}/data/*.json`; do
  curl -XPOST -H "Content-Type: application/json" -d @${i} http://localhost:8081/customers
done;
