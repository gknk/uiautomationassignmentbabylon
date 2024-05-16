Softwares used:
Java: jdk-15.0.1
Junit: 5.4.0
Selenium Webdriver: 3.8.0
Maven: 3.6.3
Maven surefire plugin: 3.0.0-M3

I have kept the browser driver exe at my local drive [for ex: C:\geckodriver\geckodriver.exe]. 
Before you run the test, point to the correct location where your browser driver is available.
You can change the driver path in the property file config.properties [ui-automation-tests\src\main\resources\config.properties]

How to run tests in command prompt:

1) Open a command prompt
2) Set path for java and maven
3) Go to folder "ui-automation-tests"
4) Run command mvn clean install -DskipTests
5) To execute only smoke tests, run command mvn test -Dgroups=smoke
6) To execute only full regression tests, run command mvn test -Dgroups=regression

Some observations about the application:
1) Though the UI shows a successful message while creating and deleting a Computer name, 
but in actual, there is no change in the UI. Same behavior is observed in edit operation. 
If these operations were working properly,code for validations would have been slightly 
different. I have added some code and commented them as part of the method
addNewComputerWithRequiredFields() to show the exact validations.

2) Not sure the behavior is correct while adding a computer with an existing name 

What's next:
1) I have put all the test data in the test class itself. Which can easily be separated out
and put in an external file.
2) Support for the reporting