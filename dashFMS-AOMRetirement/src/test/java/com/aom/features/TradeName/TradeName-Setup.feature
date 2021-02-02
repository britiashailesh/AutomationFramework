Feature: Manage, Approve and View TradeName for an agreement

  @ManageTradeName @Regression
  Scenario Outline: Manage Trade Name for an agreement based on CRM Trigger
    Given I enter stipulation details "<AddendumName>","<Stipulation Details>","<Deal Type>","<Deal Sub Type>"
    And I navigate to Manage Agreement Terms screen
    When I enter details and submit for approval "<Applicable To>"
    Then Trade Name shall be updated

    Examples: 
      | AddendumName      | Stipulation Details                                                                               | Applicable To | Deal Type | Deal Sub Type |
      | Manage Trade Name | Manage Trade Name:Franchisee Information:DBA:Agreement:Term Start/ Office Open Date:Term End Date | Agreement     |      5269 |          5284 |
      | Manage Trade Name | Manage Trade Name:Franchisee Information:DBA:Office:Term Start/ Office Open Date:Term End Date    | Office        |      5269 |          5284 |

  @ViewAgreementTradeName @Regression
  Scenario: View Trade Name for an agreement
    Given I navigate to View Agreement Terms screen
    Then I should be able to see the Trade Name details
