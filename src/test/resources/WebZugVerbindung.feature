@WebZugverbindung
Feature: Suche Zugverbindungen von Wien nach Salzburg
  Scenario: Eine Liste von Zugverbindungen von Wien nach Salzburg erhalten.
    Given Website ticket bookingpage
    When I search for a train
      | From | To |
      | Wien | Salzburg |
    Then there should be at least one result
