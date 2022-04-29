#!/bin/bash
#docker network prune
CNAME_Server="kpis-agile-server"
CNAME_Clinet="kpis-agile-ui"
CNAME_db="db-kpis-agile"
if [ "$(docker ps -qa -f name=$CNAME_Server)" || "$(docker ps -qa -f name=$CNAME_Clinet)" || "$(docker ps -qa -f name=$CNAME_db)" ]; then
    echo ":: Found container - $CNAME_Server"
    if [ "$(docker ps -q -f name=$CNAME_Server)"  || "$(docker ps -q -f name=$CNAME_Clinet)" || "$(docker ps -q -f name=$CNAME_db)"]; then
        echo ":: Stopping running container --"
        docker-compose stop;
        docker-compose up --no-recreate;
    else
      docker-compose up;    
    fi
    echo ":: Removing stopped container - $CNAME_Server"
    docker rm $CNAME_Server;
fi

