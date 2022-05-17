#!/bin/bash

CNAME_db="db-kpis-agile"
if [ "$(docker ps -qa -f name=$CNAME_db)" ]; then
    echo ":: Found container - $CNAME_db"
   
        echo ":: No recreate service db, backend and frontend service- "
        #docker-compose run --service-ports db;
        #docker-compose stop;
        docker-compose up --no-recreate;
        #docker-compose run --service-ports kpis-backend;
        #docker-compose  stop;
        #docker-compose -f docker-composetwo.yml up;
else
      echo ":: Up all service -"
      docker-compose up;
  
    
fi