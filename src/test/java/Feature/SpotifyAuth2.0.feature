Feature: Verify SpotifyAuth2.0 Functionality

  @smoketest
  Scenario: Verify if Get_newreleases api functionality its working
    When user are calling "get_newrelease" with "get" http request
    Then user verify status code is 200

  @smoketest
  Scenario: Verify if Create_playlist api functionality its working
    Given add playlist
    When user are calling "post_createplaylist" with "post" http request
