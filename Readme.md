**Technologies Used:**
1. MuleSoft 4.3
2. Cucumber Framework
3. REST Assured
4. Extent Reports
5. Java 8 (As some of the features of Anypoint Studio(deployment using maven) not compatible with higher versions.)

**Prerequisites:**
1. Anypoint Platform 7+ is installed on your machine.
2. You must have installed git on your local machine.
3. You must have an access to [https://github.com/njclabs/apiops-anypoint-bdd-sapi/new/master/apiops-anypoint-bdd-sapi]. 
If you don't have access to this then please mail to: **megha.kumar@njclabs.com, bejoy@njclabs.com or siva@njclabs.com**.

Create git repository and write git clone [https://github.com/njclabs/apiops-anypoint-bdd-sapi.git]
This will fetch the above project and everything is done for you.

# Points to notice in this project which helped to create framework:

1. Include the below mentioned dependencies in your pom.xml to include the JARs related to CUCUMBER, REST Assured and Extent Reports:
		```
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.hamcrest</groupId>
			<artifactId>hamcrest-all</artifactId>
			<version>1.3</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.6.2</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.jayway.restassured</groupId>
			<artifactId>rest-assured</artifactId>
			<version>2.9.0</version>
			<scope>test</scope>
		</dependency>
		<!-- API, java.xml.bind module -->
		<dependency>
    			<groupId>jakarta.xml.bind</groupId>
    			<artifactId>jakarta.xml.bind-api</artifactId>
    			<version>2.3.2</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->
		<dependency>
    			<groupId>com.googlecode.json-simple</groupId>
    			<artifactId>json-simple</artifactId>
    			<version>1.1.1</version>
		</dependency>
		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>1.2.5</version>
		</dependency>
		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>1.2.5</version>
			<scope>test</scope>
		</dependency>
		<dependency>
    			<groupId>info.cukes</groupId>
    			<artifactId>cucumber-core</artifactId>
    			<version>1.2.5</version>
		</dependency>
		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-testng</artifactId>
			<version>1.2.0</version>
		</dependency>
		<dependency>
    			<groupId>info.cukes</groupId>
    			<artifactId>gherkin</artifactId>
    			<version>2.12.2</version>
    			<scope>provided</scope>
		</dependency>
  		<dependency>
        		<groupId>info.cukes</groupId>
        		<artifactId>cucumber-jvm-deps</artifactId>
        		<version>1.0.5</version>
        		<scope>provided</scope>
        	</dependency>
         	<dependency>
    			<groupId>net.masterthought</groupId>
    			<artifactId>cucumber-reporting</artifactId>
    			<version>3.11.0</version>
		</dependency>
		<dependency>
			<groupId>com.vimalselvam</groupId>
			<artifactId>cucumber-extentsreport</artifactId>
			<version>3.1.1</version>
		</dependency>
		<dependency>
			<groupId>com.aventstack</groupId>
			<artifactId>extentreports</artifactId>
			<version>4.0.9</version>
		</dependency>
		<dependency>
			<groupId>com.relevantcodes</groupId>
			<artifactId>extentreports</artifactId>
			<version>2.41.0</version>
		</dependency>
		<dependency>
			<groupId>net.masterthought</groupId>
			<artifactId>cucumber-reporting</artifactId>
			<version>4.10.0</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>```
    
2. In order to run the cucumber test cases we are having TestRunner.java file in src/test/java/runner folder.
Folder structure is as:
**src/test/java/config**: This will consists of configuration file like here extent_report-config.xml which will have extent reports format configuration.
**src/test/java/features**: This will contains all the feature files of Cucumber.
**src/test/java/runner**: Files under this will trigger the cucumber test cases.
**src/test/java/stepDefinition**: This will define the behaviour of the features mentioned in cucumber file.
**src/test/java/utils**: This will contains the helpers file.
 
3. We can skip running functional testing before deployment by writing -Dtestfile=runner.TestRunner.java at the end of maven command for example 
``` mvn -f apiops-anypoint-bdd-sapi/pom.xml clean install -Dtestfile=runner.TestRunner.java ```
 
4. In this we are also doing deployment through our maven project, for that you need to include below plugin in <plugins> tag:
 			  ```<plugin>
				<groupId>org.mule.tools.maven</groupId>
   				<artifactId>mule-maven-plugin</artifactId>
   				<version>3.3.5</version>
   				<extensions>true</extensions>

			   <configuration>
 		     		<cloudHubDeployment>
        			 	<username>${anypoint.username}</username>
         				<password>${anypoint.password}</password>         
         				<workers>1</workers>
         				<workerType>Micro</workerType>
         				<environment>Sandbox</environment>
         				<muleVersion>4.3.0</muleVersion>
         				<applicationName>${applicationName}</applicationName>
         				<objectStoreV2>true</objectStoreV2>
					<region>${cloudhub.region}</region>
      				</cloudHubDeployment>
   				</configuration>
			</plugin>```
 
Where 
anypoint.username = Username of Anypoint Platform
anypoint.password = Password of Anypoint Platform
applicationName = By what name you want your application to get deployed to cloudhub.
cloudhub.region = Region where you want your application to get deployed for example us-east-2 for us-e2

**NOTE**: applicationName should be unique before assigning this make sure application with same name doesnot exist on cloudhub.

These parameters either you can hardcode in pom.xml or you can pass it dynamically while running maven command 
like for applicationName -DapplicationName=apiops-bdd-sapi

 5. You can find the pipeline which will run build, deploy, functional test cases, and email in url:
[https://github.com/njclabs/apiops-anypoint-bdd-sapi/blob/master/Jenkinsfile] 

