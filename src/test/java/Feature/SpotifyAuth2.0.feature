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
    Given missing requiredfield payload to playlist
    When user are calling "post_createplaylist" with "post" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                | status_code |
      | Missing required field |         400 |

  @Regression
  Scenario Outline: Verify if blank payload with Create_playlist api functionality its working
    Given blank payload to playlist
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
    When user are calling with invalid id to "post_createplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                                        | status_code |
      | You cannot create a playlist for another user. |         403 |

  @Regression
  Scenario Outline: Verify if blank Userid with Create_playlist api functionality its working
    Given add playlist
    When user are calling with blank id to "post_createplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message          | status_code |
      | Invalid username |         404 |

  #Add item to playlist related functionality
  @smoketest
  Scenario: Verify if Additemto_playlist api functionality its working
    Given add itemtoplaylist
    When user are calling "post_additemstoplaylist" with "post" http request

  @Regression
  Scenario Outline: Verify Without Token add item to playlist api functionality
    Given without auth add itemtoplaylist
    When user are using without token "post_additemstoplaylist" with "post" http request
    Then user are verify invalid status code is "<status_code>"
    Then user verify message is "<message>"

    Examples: 
      | message           | status_code |
      | No token provided |         401 |

  @Regression
  Scenario Outline: Verify Expire Token wise add item to playlist api functionality
    Given without auth add itemtoplaylist
    When user are using Expire token "post_additemstoplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                  | status_code |
      | The access token expired |         401 |

  @Regression
  Scenario Outline: Verify if wrong url with add item to playlist api functionality
    Given add itemtoplaylist
    When user are calling "wrong_post_additemstoplaylist" with "post" http request
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | status_code |
      |         404 |

  @Regression
  Scenario Outline: Verify if invalid trackid with add item to playlist api functionality
    Given invalid trackid with add itemtoplaylist
    When user are calling with invalid id to "post_additemstoplaylist" with "post" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message           | status_code |
      | Invalid track uri |         400 |

  @Regression
  Scenario Outline: Verify if invalid playlist id with add item to playlist api functionality
    Given add itemtoplaylist
    When user are calling with invaliddata "post_additemstoplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Invalid playlist Id |         404 |

  @Regression
  Scenario Outline: Verify if blank playlistid with add item to playlist api functionality
    Given add itemtoplaylist
    When user are calling with blank id to "post_additemstoplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Invalid playlist Id |         404 |

  @Regression
  Scenario Outline: Verify if blank trackid with add item to playlist api functionality
    Given add blank trackid with itemtoplaylist
    When user are calling "post_additemstoplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Error parsing JSON. |         400 |

  @Regression
  Scenario Outline: Verify if without track uri with add item to playlist api functionality
    Given add without track uri with itemtoplaylist
    When user are calling "post_additemstoplaylist" with "post" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Error parsing JSON. |         400 |

  #update playlist item related functionality
  @smoketest
  Scenario: Verify if Change_playlistdetail api functionality its working
    Given update itemtoplaylist
    When user are calling "put_changeplaylistdetail" with "put" http request
    Then user verify status code is 200

  @Regression
  Scenario Outline: Verify Without Token Change_playlistdetail api functionality
    Given without auth update itemtoplaylist
    When user are using without token "put_changeplaylistdetail" with "put" http request
    Then user are verify invalid status code is "<status_code>"
    Then user verify message is "<message>"

    Examples: 
      | message           | status_code |
      | No token provided |         401 |

  @Regression
  Scenario Outline: Verify Expire Token wise Change_playlistdetail api functionality
    Given without auth update itemtoplaylist
    When user are using Expire token "put_changeplaylistdetail" with "put" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                  | status_code |
      | The access token expired |         401 |

  @Regression
  Scenario Outline: Verify Invalid playlist wise Change_playlistdetail api functionality
    Given update itemtoplaylist
    When user are calling with invaliddata "put_changeplaylistdetail" with "put" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Invalid playlist Id |         404 |

  @Regression
  Scenario Outline: Verify if blank playlistid  wise Change_playlistdetail api functionality
    Given update itemtoplaylist
    When user are calling with blank id to "put_changeplaylistdetail" with "put" http request
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | status_code |
      |         404 |

  @Regression
  Scenario Outline: Verify if missing requiredfield payload wise Change_playlistdetail api functionality
    Given empty requiredfield payload to update itemtoplaylist
    When user are calling "put_changeplaylistdetail" with "put" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                 | status_code |
      | Attribute name is empty |         400 |

  @Regression
  Scenario Outline: Verify if blank payload wise Change_playlistdetail api functionality
    Given blank payload to playlist
    When user are calling "put_changeplaylistdetail" with "put" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Error parsing JSON. |         400 |

  #delete playlist item related functionality
  @smoketest
  Scenario: Verify if remove_playlistitem api functionality its working
    Given delete itemtoplaylist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify status code is 200

  @Regression
  Scenario Outline: Verify Without Token remove_playlistitem api functionality
    Given without auth remove playlistitem
    When user are using without token "del_removeitemplaylist" with "delete" http request
    Then user are verify invalid status code is "<status_code>"
    Then user verify message is "<message>"

    Examples: 
      | message           | status_code |
      | No token provided |         401 |

  @Regression
  Scenario Outline: Verify Expire Token wise remove_playlistitem api functionality
    Given without auth remove playlistitem
    When user are using Expire token "del_removeitemplaylist" with "delete" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                  | status_code |
      | The access token expired |         401 |

  @Regression
  Scenario Outline: Verify Invalid playlist wise remove_playlistitem api functionality
    Given delete itemtoplaylist
    When user are calling with invaliddata "del_removeitemplaylist" with "delete" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Invalid playlist Id |         404 |

  @Regression
  Scenario Outline: Verify if blank playlistid  wise remove_playlistitem api functionality
    Given delete itemtoplaylist
    When user are calling with blank id to "del_removeitemplaylist" with "delete" http request
    Then user verify message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message             | status_code |
      | Invalid playlist Id |         404 |

  @Regression
  Scenario Outline: Verify if invalid trackid wise remove_playlistitem api functionality
    Given invaliddelete itemtoplaylist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify invalid status code is 400
    Then user verify invalid message is "<message>"

    Examples: 
      | message                                 |
      | JSON body contains an invalid track uri |

  @Regression
  Scenario Outline: Verify if empty requiredfield payload wise remove_playlistitem api functionality
    Given empty requiredfield payload to remove itemtoplaylist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                                  | status_code |
      | JSON body contains an invalid track uri: |         400 |

  @Regression
  Scenario Outline: Verify if without URI payload wise remove_playlistitem api functionality
    Given without URI payload to remove itemtoplaylist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message                                 | status_code |
      | JSON body contains a track without URI. |         400 |

  @Regression
  Scenario Outline: Verify if missing track payload wise remove_playlistitem api functionality
    Given missing requiredfield payload to playlist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message        | status_code |
      | Missing tracks |         400 |
      
  @Regression
  Scenario Outline: Verify if blank track payload wise remove_playlistitem api functionality
    Given blank payload to playlist
    When user are calling "del_removeitemplaylist" with "delete" http request
    Then user verify invalid message is "<message>"
    Then user are verify invalid status code is "<status_code>"

    Examples: 
      | message        | status_code |
      | Missing tracks |         400 |    
