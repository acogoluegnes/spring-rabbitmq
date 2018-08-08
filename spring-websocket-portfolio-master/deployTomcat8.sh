
if [ -z "$TOMCAT8_HOME1" ]; then
    echo -e "\n\nPlease set TOMCAT8_HOME1\n\n"
    exit 1
fi

if [ -z "$TOMCAT8_HOME2" ]; then
    echo -e "\n\nPlease set TOMCAT8_HOME2\n\n"
    exit 1
fi

mvn -U -DskipTests clean package

rm -rf $TOMCAT8_HOME1/webapps/spring-websocket-portfolio*
rm -rf $TOMCAT8_HOME2/webapps/spring-websocket-portfolio*

cp target/spring-websocket-portfolio.war $TOMCAT8_HOME1/webapps/
cp target/spring-websocket-portfolio.war $TOMCAT8_HOME2/webapps/

$TOMCAT8_HOME1/bin/startup.sh
$TOMCAT8_HOME2/bin/startup.sh
