#!/bin/bash
docker network prune
CNAME_Server="kpis-agile-server"
if [ "$(docker ps -qa -f name=$CNAME_Server)" ]; then
    echo ":: Found container - $CNAME_Server"
    if [ "$(docker ps -q -f name=$CNAME_Server)" ]; then
        echo ":: Stopping running container - $CNAME_Server"
        docker stop $CNAME_Server;
    fi
    echo ":: Removing stopped container - $CNAME_Server"
    docker rm $CNAME_Server;
fi

CNAME_Client="kpis-agile-ui"
if [ "$(docker ps -qa -f name=$CNAME_Client)" ]; then
    echo ":: Found container - $CNAME_Client"
    if [ "$(docker ps -q -f name=$CNAME_Client)" ]; then
        echo ":: Stopping running container - $CNAME_Client"
        docker stop $CNAME_Client;
    fi
    echo ":: Removing stopped container - $CNAME_Client"
    docker rm $CNAME_Client;
fi

CNAME_db="db-kpis-agile"
if [ "$(docker ps -qa -f name=$CNAME_db)" ]; then
    echo ":: Found container - $CNAME_db"
    if [ "$(docker ps -q -f name=$CNAME_db)" ]; then
        echo ":: Stopping running container - $CNAME_db"
        docker stop $CNAME_db;
    fi
    echo ":: Removing stopped container - $CNAME_db"
    docker rm $CNAME_db;
fi