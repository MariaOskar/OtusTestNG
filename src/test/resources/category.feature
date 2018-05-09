Feature: Category
  Scenario: Check T-Shirts category
    Given I browse to http://automationpractice.com
    When I focus on "WOMEN" button
    Then I see menu
    When I click on T-Shirts link
    Then I see category page
    And I see products list