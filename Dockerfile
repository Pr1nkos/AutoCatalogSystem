FROM postgres:latest
COPY dump.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=1212
ENV POSTGRES_DB=auto_db
ENV PORT=5432
#TODO Сделать развертывание базы данных через Doker
FROM tomcat:latest
COPY target/AutoCatalogSystem-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080