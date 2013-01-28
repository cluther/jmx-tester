#!/bin/sh
echo "JMXTester listening on port 9991"
cd bin
java \
-Dcom.sun.management.jmxremote.port=9991 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
JMXTester
