@weitereAngebote
Feature: Gehe zu weiteren Angeboten und wähle ein Einfach-Raus-Ticket
  Scenario: Kaufen ein Einfach-Raus-Ticket. Die Kosten eines Zugtickets sollten 35.00€ betragen.
    Given Android Applikation
    When weiteren Angeboten
    And Einfach Raus Ticket
    Then Ticket kostet price "€ 35.00"
