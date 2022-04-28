#!/bin/bash

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

CNAME_Clinet="kpis-agile-ui"
if [ "$(docker ps -qa -f name=$CNAME_Clinet)" ]; then
    echo ":: Found container - $CNAME_Clinet"
    if [ "$(docker ps -q -f name=$CNAME_Clinet)" ]; then
        echo ":: Stopping running container - $CNAME_Clinet"
        docker stop $CNAME_Clinet;
    fi
    echo ":: Removing stopped container - $CNAME_Clinet"
    docker rm $CNAME_Clinet;
fi

CNAME_db="db"
if [ "$(docker ps -qa -f name=$CNAME_db)" ]; then
    echo ":: Found container - $CNAME_db"
    if [ "$(docker ps -q -f name=$CNAME_db)" ]; then
        echo ":: Stopping running container - $CNAME_db"
        docker stop $CNAME_db;
    fi
    echo ":: Removing stopped container - $CNAME_db"
    docker rm $CNAME_db;
fi