# Calculator Tests - Automation project
## Tech stack:
- Java 8 ([Install docs](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html))
- Maven ([Install docs](http://maven.apache.org/install.html))
- Appium ([Install docs](https://appium.io/docs/en/about-appium/intro/#appium-concepts))
- JUnit (Will be installed automatically)
- Please see pom.xml file for more details on application modules

## Features (Important)
- Tests for Native Android Calculator mobile application are written in Java using Appium.
  
- Notice that 'History' feature was added to Calculator App only from Calculator v7.2 version and it's stock app on Android OS versions from 8.0 and above.
- Tests could be run on emulators (Android SDK, Genymotion) or real devices.
- In current version, Xiaomi and Samsung calculator apps are not implemented
yet.
- By default, tests Run on default host ```http://127.0.0.1:472(1-9)```. Tests also could be run on remote/local host by passing parameters ```host``` and ```port```

## Additional info
- To run tests with History on Android OS versions below 8.0, you could install latest Calculator app version manually. 
Latest release could be downloaded [here](https://www.apkmirror.com/apk/google-inc/google-calculator/).


## How to set environment for running tests
Before executing tests there should be set next software:
- Android SDK and tools ([Install docs](https://developer.android.com/studio/index.html))
- Node.js with NPM ([Install docs](https://nodejs.org/en/download/))
- Appium server ([Install docs](https://appium.io/docs/en/about-appium/intro/#appium-concepts))

**[Important]**

All the paths for libraries should be added to ```PATH``` system environment variable and to ```~/.bash_profile``` (for Unix systems):
- ANDROID_HOME
- APPIUM_HOME
- NODE_HOME


#### Tests parameters

- ```id``` - mandatory parameter. It's **id** of device where tests are to be run.
Default value also could be set in ```config.properties``` file

- ```host``` - optional parameter. By default, appium server will automatically start on ```http://127.0.0.1``` host. 
By passing value for this parameter host for tests could be changed.

- ```port``` - optional parameter. By default, appium server will automatically start on any available port in range (4721-4730). 
By passing value for this parameter port for tests could be changed.


### Source code
Source code (git repo) is available at ```https://github.com/tatarynov/java-appium-calculator-example```


### Assumptions
- You already familiar with: Git, Java, Terminal/Command line implementation of your Operation System
- Application code will be located in (Unix): ```~/Projects``` or in (Windows) ```C:\Projects```

Use this command to download code base
```bash
cd PROJECT_DIR
git clone https://tatarynov@bitbucket.org/tatarynov/here-appium-task.git

```
Open terminal (Unix) or CMD prompt (MS Windows) and run following commands:
```bash
cd CLONED_PROJECT_DIR
git pull
```


## Run tests


```
mvn clean test -Did=12345
```
, where '12345' is id of device where tests are to be run.

OR

```
mvn clean test
```
, if default ```id``` value is set in ```config.properties``` file