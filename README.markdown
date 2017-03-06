# jmx-tester

A small Java application for testing JMX management.

## Usage

1. Verify that you have a recent Java Runtime Environment ([JRE]) installed.
2. Download [jmx-tester-1.0.0.jar].
3. Run it from the command line with JMX configuration.

Here's an example of how to run it with no authentication or SSL on port 9991.

    java \
        -Dcom.sun.management.jmxremote.port=9991 \
        -Dcom.sun.management.jmxremote.ssl=false \
        -Dcom.sun.management.jmxremote.authenticate=false \
        -jar target/jmx-tester-1.0.0.jar
s
A common problem you might encounter is still being unable to
connect successfully after running it as above. This is most often
caused by server not knowing the correct name by which the JMX
client such as JConsole knows it.

To correct this problem you can define _java.rmi.server.hostname_
as is done below. The value should be a resolvable hostname or the
IP address by which the client can connect to the server.

    java \
        -Djava.rmi.server.hostname=172.17.0.1 \
        -Dcom.sun.management.jmxremote.port=9991 \
        -Dcom.sun.management.jmxremote.ssl=false \
        -Dcom.sun.management.jmxremote.authenticate=false \
        -jar target/jmx-tester-1.0.0-jar-with-dependencies.jar

## Testing

The best way to 

[JRE]: https://java.com/
[jmx-tester-1.0.0.jar]: https://github.com/cluther/jmx-tester/releases/download/v1.0.0/jmx-tester-1.0.0.jar