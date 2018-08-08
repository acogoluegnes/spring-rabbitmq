
if [ -z "$TOMCAT8_HOME1" ]; then
    echo -e "\n\nPlease set TOMCAT8_HOME1\n\n"
    exit 1
fi

if [ -z "$TOMCAT8_HOME2" ]; then
    echo -e "\n\nPlease set TOMCAT8_HOME2\n\n"
    exit 1
fi

$TOMCAT8_HOME1/bin/shutdown.sh
$TOMCAT8_HOME2/bin/shutdown.sh
