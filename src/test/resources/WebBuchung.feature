@Webticketbuchen
Feature: Ticketbuchung von Wien nach Salzburg

  Scenario: Kaufen eines Zugtickets. Die Kosten eines Zugtickets sollten 38.40€ betragen.
    Given Website
    When Web choose location
      | Von  | Nach     |
      | Wien | Salzburg |
    Then Web Ticket costs
