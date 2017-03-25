$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("FlightSearch.feature");
formatter.feature({
  "line": 1,
  "name": "Use Lufthansa side to search for flight",
  "description": "",
  "id": "use-lufthansa-side-to-search-for-flight",
  "keyword": "Feature"
});
formatter.before({
  "duration": 6560950326,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "#  Scenario Outline: As a guest user I want to search for a flight"
    },
    {
      "line": 4,
      "value": "#    Given I am on the Lufthansa home side"
    },
    {
      "line": 5,
      "value": "#    When I fill in lufthansa side with data From: \"\u003cfrom\u003e\", To: \"\u003cto\u003e\""
    },
    {
      "line": 6,
      "value": "#    And I search for flights"
    },
    {
      "line": 7,
      "value": "#    Then I see search results with available flights From: \"Krakow\", To: \"Frankfurt\""
    },
    {
      "line": 8,
      "value": "#    And I see price for each flight"
    },
    {
      "line": 9,
      "value": "#"
    },
    {
      "line": 10,
      "value": "#    Examples:"
    },
    {
      "line": 11,
      "value": "#      | from | to  |"
    },
    {
      "line": 12,
      "value": "#      | WRO  | FRA |"
    },
    {
      "line": 13,
      "value": "#"
    }
  ],
  "line": 14,
  "name": "As a guest user I want to search for a flight",
  "description": "",
  "id": "use-lufthansa-side-to-search-for-flight;as-a-guest-user-i-want-to-search-for-a-flight",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 15,
  "name": "I am on the Lufthansa home side",
  "keyword": "Given "
});
formatter.step({
  "line": 16,
  "name": "I fill in lufthansa side with data From data below",
  "rows": [
    {
      "cells": [
        "from",
        "to"
      ],
      "line": 17
    },
    {
      "cells": [
        "WRO",
        "FRA"
      ],
      "line": 18
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "I search for flights",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "I see search results with available flights From: \"Krakow\", To: \"Frankfurt\"",
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "I see price for each flight",
  "keyword": "And "
});
formatter.match({
  "location": "SearchFlights.i_am_on_the_Lufthansa_home_side()"
});
formatter.result({
  "duration": 3086900868,
  "status": "passed"
});
formatter.match({
  "location": "SearchFlights.iFillInLufthansaSideWithDataFromDataBelow(DataTable)"
});
formatter.result({
  "duration": 2076923,
  "status": "passed"
});
formatter.match({
  "location": "SearchFlights.I_search_for_flights()"
});
formatter.result({
  "duration": 35556,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Krakow",
      "offset": 51
    },
    {
      "val": "Frankfurt",
      "offset": 65
    }
  ],
  "location": "SearchFlights.I_see_search_results_with_available_flights_From_To(String,String)"
});
formatter.result({
  "duration": 4521138,
  "status": "passed"
});
formatter.match({
  "location": "SearchFlights.iSeePriceForEachFlight()"
});
formatter.result({
  "duration": 2978292,
  "error_message": "cucumber.api.PendingException: TODO: implement me\r\n\tat lufthansa.SearchFlights.iSeePriceForEachFlight(SearchFlights.java:74)\r\n\tat ✽.And I see price for each flight(FlightSearch.feature:21)\r\n",
  "status": "pending"
});
formatter.after({
  "duration": 1046246546,
  "status": "passed"
});
});