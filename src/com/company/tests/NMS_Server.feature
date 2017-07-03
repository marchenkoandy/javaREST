Feature: Server tests
  Scenario Outline: Verify Server version.
    Given I initialise variables for authorization: <nmsURL>, <nmsAdminLogin>, <nmsAdminPassword>
    When I run test NMS_Server.getVersion()
    Then I should receive latest NMS version more than <nmsServerVersion>
    Examples:
      | nmsURL | nmsAdminLogin | nmsAdminPassword | nmsServerVersion |
      | "https://nms-qa2.nuancehce.com" | "DMNEQAA"| "DMNEQAA"| "5.8.108.0"|


  Scenario: Single Verify Server version.
    Given I initialise variables for authorization: "https://nms-qa2.nuancehce.com", "DMNEQAA", "DMNEQAA"
    When I run test NMS_Server.getVersion()
    Then I should receive latest NMS version more than "5.8.108.0"
