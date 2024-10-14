# Assignment
Created a POM testng framework
Under the main-java directory, I have created the AutoAssign package, which contains four subpackages: base, extentReport, page, and util
In the base package, there are two classes: TestBase and TestSetup. In the TestBase class, I have created a webSetup method and some methods to generate the report.
TestSetup class contains a method that creates proper logs
Under extentReport package - There 2 classes ExtentTestFactory and ExtentTestFactoryParent
ExtentTestFactory class - provides methods for generating a timestamped report file path
ExtentTestFactoryParent - Similar to ExtentTestFactory, this class manages a separate ExtentTest instance using a ThreadLocal for thread-safe operations but does not include path or date-related utilities.
In the page package, there is a separate class for each page, where I have created methods and locators
In the util package, there is a class where I have created reusable methods

test-java directory - there are test package where I have created a test script
