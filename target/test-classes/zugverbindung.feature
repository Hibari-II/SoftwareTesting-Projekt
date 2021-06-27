@zugverbindung
Feature: Suche Zugverbindungen von Wien nach Salzburg
  Scenario: Eine Liste von Zugverbindungen von Wien nach Salzburg erhalten.
    Given Android Applikation
    When Suche Zugverbindung
      | Von | Nach | Datum | Uhrzeit |
      | Wien | Salzburg | Montag | 09:00 |
    Then Ergebnis Liste erhalten
