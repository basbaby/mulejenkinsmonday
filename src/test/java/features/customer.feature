Feature: Testing the API's

Scenario: Get Subscribed customer details
Given I create a new request with http://localhost:8095/api/ service
And I add the users/registerUser/subscription endpoint to the service
And I send the GET request to the service
Then I get the 200 response code

Scenario: Register Customer 
Given I create a new request with http://localhost:8095/api/ service
And I add the users/registerUser endpoint to the service
And with the following headers
| Content-Type | application/json |
And I send the values of src/test/resources/cucumberResources/registerCustomerInput.json in the request body
And I send the POST request to the service
Then I get the 201 response code
Then I expect the values of src/test/resources/cucumberResources/registerCustomerOutput.json in the response body

Scenario: Test the scheduler part
Given I create a new request with http://localhost:8095/api/ service
And I add the synchronizeUsers endpoint to the service
And I send the GET request to the service
Then I get the 200 response code


Scenario: Get Salesforce Accounts
Given I create a new request with http://apiops-salesforce-sapi-mule1.us-e2.cloudhub.io/api/ service
And I add the accounts endpoint to the service
And with the following headers
| authorization | Bearer eyJraWQiOiJjQnNKTDh1bmJMT1N6b2x2cXk1eF9SOWxSLTBKb3k3NGRFOTJHV2hmWjJZIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULjFtMHNabkFMSThwM3F3SHBIU2ZhRm1PdXFyaVJPSVhUN3F0dE9NclcyUEkiLCJpc3MiOiJodHRwczovL2Rldi02MjMwODIub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNjEyMTc2Mjg1LCJleHAiOjE2MTIyNjI2ODUsImNpZCI6IjBvYTFsM2VmdmY3VTBwQjUzNHg3Iiwic2NwIjpbIm11bGVzY29wZSJdLCJzdWIiOiIwb2ExbDNlZnZmN1UwcEI1MzR4NyJ9.BoDcRKFBcioh159igSUC3WZD3tTqNiX7atKIS7GJAejw72Mye8bO75ElQyUb7nS6uSFrQIbv4KwruVDQp1Kj8Pn37s40Th2b1tCyd_TBUdOV3Pl5tVBoTm2QHNa3pPM8vbHp_MP12t-KsQqxTMK_Sb72Ww4YwQa1r2zGgwJjco7k5euaMdhttDvCFXSb8Ob8usfpqXjnlzRzncQXuJcQGUq-ahM6v5ZgtOGVJ_fPC7rOaVM5KIgvL62PaTz-ECuhMIwE7W08rkj8GPdQUsfcbKeMkxv4h3L1ttsZA9_d6Pdh5XTihUmFuEkCTqsDk-3NgDNSJ4bdN81V0TlIk4AABg |
| Content-Type | application/json |
And I send the GET request to the service
Then I get the 200 response code


Scenario: Create ServiceNow incident
Given I create a new request with http://apiops-servicenow-sapi-njclabs1.us-e1.cloudhub.io/api/ service
And I add the incidents endpoint to the service
And I send the values of src/test/resources/cucumberResources/serviceNowIncidentInput.json in the request body
And with the following headers
| Content-Type | application/json |
And I send the POST request to the service
Then I get the 201 response code
