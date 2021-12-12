Feature: feature to test google search functionality

  Scenario: Validate google search is working
    Given browser is open
    And user is on google search page
    And user accepts terms
    When user enters a text in search box "jordi campoy"
    And hits enters
    Then user is navigated to search results
    And user clicks on page
    Then the user showed should be "Jordi Campoy"
    Then close navigator
