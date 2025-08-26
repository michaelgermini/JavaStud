@echo off
echo ========================================
echo JavaStud Learning Platform Launcher
echo ========================================
echo.

:: Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or later and try again
    pause
    exit /b 1
)

echo ✅ Java found
echo.

:: Check if JAR file exists
if not exist "target\javastud-learning-2.0.0-jar-with-dependencies.jar" (
    echo ERROR: JAR file not found
    echo Please run build.bat first
    pause
    exit /b 1
)

echo 🚀 Starting JavaStud Learning Platform...
echo.

:: Set Java options
set JAVA_OPTS=-Xmx2g -Xms512m -Dfile.encoding=UTF-8

:: Run the application
java %JAVA_OPTS% -jar target\javastud-learning-2.0.0-jar-with-dependencies.jar

echo.
echo 👋 JavaStud application closed
pause
