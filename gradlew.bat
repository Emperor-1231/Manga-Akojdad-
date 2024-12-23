@if "%DEBUG%"=="" @echo off

@rem ##########################################################################
@rem  Gradle startup script for Windows
@rem ##########################################################################

@rem Check if running on Windows NT and set local scope
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.

set APP_HOME=%DIRNAME%
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Resolve java.exe
if defined JAVA_HOME (
    set JAVA_EXE=%JAVA_HOME%/bin/java.exe
) else (
    set JAVA_EXE=java.exe
)

@rem Verify if Java exists and show error if not
"%JAVA_EXE%" -version >NUL 2>&1 || (
    echo.
    echo ERROR: JAVA_HOME is not set or 'java.exe' could not be found in your PATH. 
    echo Please install Java and configure the JAVA_HOME environment variable.
    exit /b 1
)

@rem Execute Gradle
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%~n0" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
if %ERRORLEVEL% neq 0 (
    echo ERROR: Gradle execution failed. Please check your project setup.
    exit /b %ERRORLEVEL%
)

@endlocal
exit /b 0