Feature: Use Lufthansa side to search for flight

#  Scenario Outline: As a guest user I want to search for a flight
#    Given I am on the Lufthansa home side
#    When I fill in lufthansa side with data From: "<from>", To: "<to>"
#    And I search for flights
#    Then I see search results with available flights From: "Krakow", To: "Frankfurt"
#    And I see price for each flight
#
#    Examples:
#      | from | to  |
#      | WRO  | FRA |
#
 Scenario: As a guest user I want to search for a flight
    Given I am on the Lufthansa home side
    When I fill in lufthansa side with data From data below
      | from | to  |
      | WRO  | FRA |
    And I search for flights
    Then I see search results with available flights From: "Krakow", To: "Frankfurt"
    And I see price for each flight