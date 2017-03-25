@Smoke
Feature: Travel Request

  Scenario Outline: Add new Travel Request - validate form
    Given Login "http://msobala:Haslo20170@10.0.0.207:5555" and open home page "http://10.0.0.207:5555"
    When Add new travel request using data: Employees: "<employees>" Project: "<project>" Region: "<region>" Start date: "<start>" End date: "<end>" Origin: "<origin>" Is billable: "<billable>" Destination: "<destination>" Comments: "<comments>"
    Then I expect my request "<isCorrect>" added
    And there "<isCorrect>" my request added for each Employee: "<employeesNameOnList>" on the list with data Project: "<projectOnList>" Region: "<region>" Start: "<start>" End: "<end>" Origin: "<origin>" Billable: "<billable>" Destination: "<destination>" Comments: "<comments>"

    Examples:
      | employees       | employeesNameOnList                   | project  | projectOnList   | region  | start | end   | origin   | billable | destination | comments           | isCorrect |
      |                 |                                       |          |                 |         |       |       |          |          |             |                    | isn't     |
      | Staszak; Sobala | Staszak, Krzysztof; Sobala, Magdalena | 16TC0005 | 16TC0005 - TEST | Raleigh | today | today | {RANDOM} | Billable | Krakow      | this is my comment | is        |


  Scenario: Add new Travel Request - Go back
    Given Login "http://msobala:Haslo20170@10.0.0.207:5555" and open home page "http://10.0.0.207:5555"
    When Add new travel request and Go back without saving changes
    Then landing page should be displayed
      
    
#  Scenario: Add new Travel Request with Copy option
#    Given Login "http://msobala:Haslo20170@10.0.0.207:5555" and open home page "http://10.0.0.207:5555"
#    When Add new travel request using data: Employees: "Bech" Project: "" Region: "" Start date: "" End date: "" Origin: "" Is billable: "" Destination: "" Comments: ""
#    And copy last trip
#    Then I expect my request has data copied from last trip