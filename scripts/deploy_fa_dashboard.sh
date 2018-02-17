#!/usr/bin/env bash

/home/admin/apache-tomcat-6.0.45/bin/shutdown.sh

rm -rf /home/admin/apache-tomcat-6.0.45/webapps/ROOT.war
rm -rf /home/admin/apache-tomcat-6.0.45/webapps/ROOT

cp ../ROOT.war /home/admin/apache-tomcat-6.0.45/webapps/ROOT.war

/home/admin/apache-tomcat-6.0.45/bin/startup.sh