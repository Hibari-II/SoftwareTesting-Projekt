@WebZugverbindung
Feature: Suche Zugverbindungen von Wien nach Salzburg
  Scenario: Eine Liste von Zugverbindungen von Wien nach Salzburg erhalten.
    Given Website ticket bookingpage
    When I search for a train
      | From | To |
      | Wien | Wien Floridsdorf |
    And I add a second adult person
    And I click on a train connection
    Then I should be able to put a ticket into the cart
