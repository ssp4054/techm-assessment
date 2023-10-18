Feature: UI Spec Test

  Scenario: Verify Home Page
    Given I navigate to the home page
    Then the home page should list different request types and endpoints

  Scenario: Verify Sample Request and Response
    When I select "SINGLE USER NOT FOUND"
    Then the page should display the request "/api/users/23" and response "404 {}"

  Scenario: Navigate to Support Page
    When I navigate to the support page
    Then the support page should list options for one-time & monthly support
    And the support page should provide Upgrade details
