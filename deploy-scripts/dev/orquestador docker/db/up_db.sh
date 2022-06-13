#!/bin/bash
volDB_POSTGRES="./data"

echo "---------- [CHECK VOLUMES  DB-POSTGRES]  ----------------"
[ ! -d "$volDB_POSTGRES" ] && mkdir -p "$volDB_POSTGRES" 


echo "---------- [CHECK NETWORK ]  ----------------"
if [ ! "$(docker network ls | grep lan-fact-pa)" ]; then
  echo "Creating lan-krugger network ..."
  docker network create lan-krugger
else
  echo "lan-krugger network exists."
fi

echo "------UP DBs  -------"
docker-compose up -d --remove-orphans

echo "------clean Images Docker-------"
docker image prune -f

echo "SUCCESS"