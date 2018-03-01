Feature: The
  Rules:
    - The user must be registered
  Scenario: The user try to login
    Given The current user and password:
      | username           | password |
      | wilsonrf@gmail.com | abc123   |
    When
    Then