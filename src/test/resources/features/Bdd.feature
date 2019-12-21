Feature: Firstsearch
  This feature search an item and add it to shopping cart for comparing

  Scenario Outline: Search something in falabella con examples
    Given i am on falabella main page
    When i search for "<item>"
    And i add it the first result to the cart
    Then i go to checkout
    And the price should be the same that in item's description

    Examples:
      | item     |
      | pendrive |


  Scenario: Search something in falabella
    Given i am on falabella main page
    When i search for "6803347"
    And i add it to the cart
    Then i go to checkout
    And the price should be the same that in item's description