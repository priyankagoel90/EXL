
Feature: GoogleMap 
  
  Scenario: Verify GoogleMap Application
  
    Given user is on GoogleMap Page
    When user enters "San Francisco,California" in search box
    Then user should be able to view the coordinates for San Francisco "37.7577627" and "-122.4726194"
    When user clicks on Directions link 
    And user search for driving directions by car from "Chico,California" to "San Francisco,California"
    Then user should be able to view two or more routes displayed in the list
    And user should be able to print route title and Distance in Miles and Travel time