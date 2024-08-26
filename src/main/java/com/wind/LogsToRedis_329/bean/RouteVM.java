package com.wind.LogsToRedis_329.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by habib on 25/07/2022.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteVM {
    private String uri;
    private String path;
    private String serviceId;
    private JSONObject serviceTestCallResponse;
    private Boolean serverAlive = Boolean.FALSE;
}
