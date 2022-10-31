#!/bin/bash
docker network prune -f
CNAME_Server="akk-backend"
CNAME_Client="akk-ui-container"
CNAME_db="akk-db-container"

if [ "$(docker ps -qa -f name=$CNAME_Server)" ] ||  [ "$(docker ps -qa -f name=$CNAME_Client)" ] || [ "$(docker ps -qa -f name=$CNAME_db)" ]; then
    echo ":: Found container - $CNAME_Server"
    if [ "$(docker ps -q -f name=$CNAME_Server)" ] || [ "$(docker ps -q -f name=$CNAME_Client)" ] ||  [ "$(docker ps -q -f name=$CNAME_db)" ]; then
        echo ":: Stopping running container -"
        docker stop $CNAME_Server $CNAME_Client $CNAME_db;
    fi
    echo ":: Removing stopped container - "
    docker rm $CNAME_Server $CNAME_Client $CNAME_db;
fi