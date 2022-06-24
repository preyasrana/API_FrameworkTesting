Feature: Verify SpotifyAuth2.0 Functionality

  #get playlist item related functionality
  @smoketest
  Scenario: Verify if Get_newreleases api functionality its working
    When user are calling "get_newrelease" with "get" http request
    Then user verify status code is 200

  @Regression
  Scenario Outline: Verify Without Token playlistitem api functionality
    When user are using without token "get_newrelease" with "get" http request
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | status_code |
      |         401 |

  @Regression
  Scenario Outline: Verify Expire Token playlistitem api functionality
    When user are using Expire token "get_newrelease" with "get" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                  | status_code |
      | The access token expired |         401 |

  @Regression
  Scenario Outline: Verify invalid url wise create playlistitem api functionality
    When user are using invalid url with "wrong_get_newrelease" with "get" http request
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | status_code |
      |         404 |

  #Create playlist item related functionality
  @smoketest
  Scenario: Verify if Create_playlist api functionality its working
    Given add playlist
    When user are calling "post_createplaylist" with "post" http request
    Then get playlistid

  @Regression
  Scenario Outline: Verify Without Token playlistitem api functionality
    Given without auth add playlist
    When user are using without token "post_createplaylist" with "post" http request
    Then user are verify invalid status code is "<status_code>"
    Then user verify message is "<message>"

    Examples: 
      | message           | status_code |
      | No token provided |         401 |

  @Regression
  Scenario Outline: Verify Expire Token playlistitem api functionality
    Given without auth add playlist
    When user are using Expire token "post_createplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                  | status_code |
      | The access token expired |         401 |

  @Regression
  Scenario Outline: Verify if missing requiredfield payload with Create_playlist api functionality its working
    Given missing requiredfield payload to add playlist
    When user are calling "post_createplaylist" with "post" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                | status_code |
      | Missing required field |         400 |

  @Regression
  Scenario Outline: Verify if blank payload with Create_playlist api functionality its working
    Given blank payload to add playlist
    When user are calling "post_createplaylist" with "post" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Error parsing JSON. |         400 |

  @Regression
  Scenario Outline: Verify if wrong url with Create_playlist api functionality its working
    Given add playlist
    When user are calling "wrong_post_createplaylist" with "post" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message           | status_code |
      | Service not found |         404 |

  @Regression
  Scenario Outline: Verify if invalid Userid with Create_playlist api functionality its working
    Given add playlist
    When user are calling with invalid userid to "post_createplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                                        | status_code |
      | You cannot create a playlist for another user. |         403 |

  @Regression
  Scenario Outline: Verify if blank Userid with Create_playlist api functionality its working
    Given add playlist
    When user are calling with blank userid to "post_createplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message          | status_code |
      | Invalid username |         404 |
      
      

  #Add item to playlist related functionality
  # @smoketest
  Scenario: Verify if Additemto_playlist api functionality its working
    Given add itemtoplaylist
    When user are calling "post_additemstoplaylist" with "post" http request

  #update playlist item related functionality
  #@smoketest
  Scenario: Verify if Change_playlistdetail api functionality its working
    Given update itemtoplaylist
    When user are calling "put_changeplaylistdetail" with "put" http request

  #delete playlist item related functionality
  #@smoketest
  Scenario: Verify if remove_playlistitem api functionality its working
    Given delete itemtoplaylist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify status code is 200

  #@Regression
  Scenario Outline: Verify if invalid trackid wise remove_playlistitem api functionality
    Given invaliddelete itemtoplaylist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify invalid status code is 400
    Then user verify invalid message is "<message>"

    Examples: 
      | message                                 |
      | JSON body contains an invalid track uri |
