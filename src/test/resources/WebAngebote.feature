@WebAngebote
Feature: Gehe zu weiteren Angeboten und wähle ein Einfach-Raus-Ticket
  Scenario: Kaufen ein Einfach-Raus-Ticket. Die Kosten eines Zugtickets sollten 35.00€ betragen.
    Given Website ticket bookingpage
    When I click on Einfach-Raus
    Then Ticket price should be "€ 35,00"