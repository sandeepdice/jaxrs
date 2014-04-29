/home/sandeep/softwares/apache-tomcat-8.0.5/bin/shutdown.sh
rm -rf /home/sandeep/softwares/apache-tomcat-8.0.5/webapps/RESTfulExample

mvn clean package; cp target/RESTfulExample.war ~/softwares/apache-tomcat-8.0.5/webapps/
/home/sandeep/softwares/apache-tomcat-8.0.5/bin/startup.sh
