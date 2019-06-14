@echo off

set JAVA_HOME=C:\Java\jdk12
set WILDFLY_HOME=C:\Java\wildfly-16.0.0.Final

if not exist %JAVA_HOME% (
    echo ==============================
    echo  set JAVA_HOME to JDK 11 path
    echo ==============================
    pause
    exit 1
)

if not exist %WILDFLY_HOME% (
    echo =====================================
    echo  set WILDFLY_HOME to WildFly 16 path
    echo =====================================
    pause
    exit 1
)

if not "%~1" == "--nopause" (
    pause
)