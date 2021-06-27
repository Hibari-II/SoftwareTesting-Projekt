@ticketbuchen
Feature: Ticketbuchung von Wien nach Salzburg
  Scenario: Kaufen eines Zugtickets. Die Kosten eines Zugtickets sollten 38.40€ betragen.
    Given Android Applikation
    When Wähle
      | Von | Nach |
      | Wien | Salzburg |
    Then Ticket kostet
