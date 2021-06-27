@ticketbuchen
Feature: Ticketbuchung von Wien nach Salzburg
  Scenario: Kaufen eines Zugtickets. Die Kosten eines Zugtickets sollten 38.40€ betragen.
    Given Android Applikation
    When Wähle
      | Von | Nach | Datum | Uhrzeit | Anzahl | Ermaeßigung |
      | Wien | Salzburg | Montag | 09:00 | 1 | keine        |
    Then Ticket kostet 38.40€