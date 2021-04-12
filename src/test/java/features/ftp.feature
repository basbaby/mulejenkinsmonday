Feature: Testing the FTP file

Scenario: Test the FTP scheduler
Given I get the expected outputFile from the location /var/lib/jenkins/workspace/apios-devops-automation-framework/cucumber-API-Framework/src/expectedOutFile.json path
And I get Activemq endpoint tcp://64.227.37.97:61616 and queue name FunctionalAutomation and actual outputFile location /var/lib/jenkins/workspace/apios-devops-automation-framework/cucumber-API-Framework/src/DqueueDatafiletest path with extention json
Then I need to compare the two files where to match the content of the data by making assertion as true value
