@zugverbindung
Feature: Suche Zugverbindungen von Wien nach Salzburg
  Scenario: Eine Liste von Zugverbindungen von Wien nach Salzburg erhalten.
    Given Android Applikation
    When Suche Zugverbindung
      | Von | Nach |
      | Wien | Salzburg |
    Then Ergebnis Liste erhalten
