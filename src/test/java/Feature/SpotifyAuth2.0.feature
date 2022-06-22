Feature: Verify SpotifyAuth2.0 Functionality

  @smoketest
  Scenario: Verify if Get_newreleases api functionality its working
    When user are calling "get_newrelease" with "get" http request
    Then user verify status code is 200

  @smoketest
  Scenario: Verify if Create_playlist api functionality its working
    Given add playlist
    When user are calling "post_createplaylist" with "post" http request
    Then get playlistid
    
  @smoketest
  Scenario: Verify if Additemto_playlist api functionality its working
      Given add itemtoplaylist
      When user are calling "post_additemstoplaylist" with "post" http request
      
  @smoketest
  Scenario: Verify if Change_playlistdetail api functionality its working
     Given update itemtoplaylist
     When user are calling "put_changeplaylistdetail" with "put" http request
     
  @smoketest
  Scenario: Verify if remove_playlistitem api functionality its working
     Given delete itemtoplaylist
     When user are calling "del_removeitemplaylist" with "delete" http request   
     Then user verify status code is 200
         