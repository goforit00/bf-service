#!/usr/bin/env bash

/home/admin/apache-tomcat-6.0.45/bin/shutdown.sh

rm -rf /home/admin/apache-tomcat-6.0.45/webapps/service.war
rm -rf /home/admin/apache-tomcat-6.0.45/webapps/service

cp ../service.war /home/admin/apache-tomcat-6.0.45/webapps/service.war

/home/admin/apache-tomcat-6.0.45/bin/startup.sh