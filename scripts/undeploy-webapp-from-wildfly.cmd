@echo off

call set-environment.cmd --nopause

call mvn ^
	-f .. ^
	wildfly:undeploy

if not "%~1" == "--nopause" (
    pause
)