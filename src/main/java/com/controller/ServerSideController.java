package com.controller;

import com.dto.User;
import com.dto.UserWithTimestamps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.service.ServerSideService;

@RestController
public class ServerSideController {
    @Autowired
    ServerSideService serverSideService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> login(@RequestBody User user){
        return serverSideService.login(user);
    }

    @RequestMapping(value = "/performRequest", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> performRequestMethod(@RequestParam String uuid){
        return serverSideService.performRequestMethod(uuid);
    }


}
