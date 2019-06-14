@echo off

call set-environment.cmd --nopause

call mvn ^
	-f .. ^
	-P deploy-webapp-to-wildfly ^
	clean ^
	install

if not "%~1" == "--nopause" (
    pause
)