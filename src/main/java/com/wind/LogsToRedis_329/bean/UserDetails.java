package com.wind.LogsToRedis_329.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by habib on 25/07/2022.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String id;
    private String username;
    private String wallet;
    private String scw;
    private String roles; // Comma separated id, -1 for ROLE_SUPER_ADMIN
    private Boolean isSuperAdmin;
    private String appId;
    private String refreshToken;
}
