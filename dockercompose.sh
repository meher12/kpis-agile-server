#!/bin/bash

CNAME_db="db-kpis-agile"
if [ "$(docker ps -qa -f name=$CNAME_db)" ]; then
    echo ":: Found container - $CNAME_db"
   
        echo ":: Down service backend and frontend service- "
        #docker-compose down kpis-ui kpis-backend;
        docker-compose up -V kpis-ui kpis-backend;
else
      echo ":: Up all service -"
      docker-compose up;
  
    
fi