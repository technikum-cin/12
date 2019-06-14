@echo off

call set-environment.cmd --nopause

set NOPAUSE=true

call %WILDFLY_HOME%\bin\jboss-cli.bat --file=configure-wildfly-datasource.cli

if not "%~1" == "--nopause" (
    pause
)