package com.wind.LogsToRedis_329.controller;

import com.wind.LogsToRedis_329.serilogj.Log;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String getUsers() {
        logger.info("HTTP GET /api/users responded 200 in 50.123 ms");
        logger.error("HTTP GET /api/users responded 200 in 50.123 ms");
        return "User list";
    }
    @PostMapping
    public String saveUsers(@RequestBody JSONObject body) {
        logger.info("HTTP GET /api/users responded 200 in 50.123 ms");
        logger.error("HTTP GET /api/users responded 200 in 50.123 ms");
        return body.toString();
    }
}
