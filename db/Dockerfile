FROM postgres:13.1-alpine

COPY init.sql /docker-entrypoint-initdb.d/

RUN ["/usr/local/bin/docker-entrypoint.sh", "postgres"]