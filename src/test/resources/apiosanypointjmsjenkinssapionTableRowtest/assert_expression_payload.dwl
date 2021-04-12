%dw 2.0
import * from dw::test::Asserts
---
payload must equalTo({
  "EmpId": 5,
  "Country": "UK",
  "FirstName": "Sunny",
  "City": "London",
  "Mobile": "9032898380",
  "PostCode": "E62BT",
  "Email": "xyz.@gmail.com",
  "LastName": "Madishetty"
})