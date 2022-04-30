#!/bin/bash
#docker network prune
CNAME_Server="kpis-agile-server"
CNAME_Client="kpis-agile-ui"
CNAME_db="db-kpis-agile"

if [ "$(docker ps -qa -f name=$CNAME_Server)" ] ||  [ "$(docker ps -qa -f name=$CNAME_Client)" ] || [ "$(docker ps -qa -f name=$CNAME_db)" ]; then
    echo ":: Found container - $CNAME_Server"
    if [ "$(docker ps -q -f name=$CNAME_Server)" ] || [ "$(docker ps -q -f name=$CNAME_Client)" ] ||  [ "$(docker ps -q -f name=$CNAME_db)" ]; then
        echo ":: Stopping running container -"
        docker stop $CNAME_Server $CNAME_Client $CNAME_db;
    fi
    echo ":: Removing stopped container - "
    docker rm $CNAME_Server $CNAME_Client $CNAME_db;
fi