Feature: The user can login with his credentials
  Scenario: The user try to login with his valid credentials
    When the user access the home page
    Then the browser redirects the user to the login page
    When the user enters username wilsonrf@gmail.com
    And the user enters the 123456
    And push the submit button
    Then the browser redirects the user to the conversion page
  Scenario: The user try to login with invalid credentials
    When the user access the home page
    Then the browser redirects the user to the login page
    When the user enters username wilsonrf@gmail.com
    And the user enters the abc124
    And push the submit button
    Then the browser redirects the user to the login error page
