@echo on
setlocal enabledelayedexpansion

REM Get script directory
set SCRIPT_DIR=%~dp0

REM Set paths
set PROJECT_PATH=%SCRIPT_DIR%
set TOMCAT_PATH=%PROJECT_PATH%\tomcat

REM Go to Maven project directory
cd /d "%PROJECT_PATH%"

REM Clean and package the Maven project
echo Running mvn clean package...
call mvn clean package

REM Get WAR file dynamically
for %%f in ("%PROJECT_PATH%\target\*.war") do set WAR_FILE=%%f

if defined WAR_FILE (
    echo Copying WAR file "!WAR_FILE!" to Tomcat webapps...
    copy /Y "!WAR_FILE!" "%TOMCAT_PATH%\webapps\"
    if ERRORLEVEL 1 (
        echo Failed to copy WAR. Exiting.
        pause
        exit /b 1
    )
) else (
    echo No WAR file found in target directory. Exiting.
    pause
    exit /b 1
)

REM Start Tomcat
echo Starting Tomcat...
cd /d "%TOMCAT_PATH%\bin"
call startup.bat

echo Deployment finished successfully.
pause

