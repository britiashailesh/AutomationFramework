@office
Feature: View Office feature
  I want to use this template for my feature file

  @office
  Scenario: Verify if user is able to view office
    Given I navigated to View Agreement Terms screen
    When I click of an office in the office grid
    And I click on View Office action in the action tab
    Then I should be on the view office page

