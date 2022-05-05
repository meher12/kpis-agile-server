# dump build stage
FROM postgres:13.1-alpine as dumper

COPY /dumpfile.sql /docker-entrypoint-initdb.d/dumpfile.sql

RUN ["sed", "-i", "s/exec \"$@\"/echo \"skipping...\"/", "/usr/local/bin/docker-entrypoint.sh"]

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=mypass
ENV PGDATA=/data

RUN ["/usr/local/bin/docker-entrypoint.sh", "postgres"]

# final build stage
FROM postgres:13.1-alpine

COPY --from=dumper /data $PGDATA