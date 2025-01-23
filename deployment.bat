@echo off

REM Nom de votre application
SET "APP_NAME=Boulangerie"

REM Chemin vers le répertoire webapps de Tomcat
SET "TOMCAT_WEBAPPS=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"

REM Définition des répertoires
SET "TEMP_DIR=temp_%APP_NAME%"
SET "CLASSES_DIR=%TEMP_DIR%\WEB-INF\classes"
SET "VIEWS_DIR=%TEMP_DIR%\views"
SET "LIB=%TEMP_DIR%\WEB-INF\lib"
SET "WAR_NAME=%APP_NAME%.war"

javac -parameters -d %CLASSES_DIR% -sourcepath src src\annotation\*.java src\connection\*.java src\database\*.java src\exception\*.java src\generator\*.java src\model\*.java src\servlet\*.java src\utils\*.java src\util\*.java -cp lib\*

if errorlevel 1 (
    echo Compilation failed.
    pause
    exit /b 1
)

REM Création du répertoire temporaire
mkdir %TEMP_DIR%

REM Copie des vues
xcopy /s /i web %VIEWS_DIR%

REM Copie des xml
xcopy /s /i xml %TEMP_DIR%\WEB-INF

REM Copie des lib
xcopy /s /i lib %LIB%

REM Copie du fichier application.properties
if exist src\application.properties (
    xcopy src\application.properties %CLASSES_DIR% /Y
) else (
    echo application.properties introuvable.
    pause
    exit /b 1
)

REM Création du fichier WAR
cd %TEMP_DIR%
jar -cvf %WAR_NAME% *

REM Déploiement du WAR dans Tomcat
copy %WAR_NAME% "%TOMCAT_WEBAPPS%\%APP_NAME%.war"

REM Nettoyage du répertoire temporaire
cd ..
rmdir /s /q %TEMP_DIR%

@REM pause