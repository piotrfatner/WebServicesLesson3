package controller;

import dto.User;
import dto.UserWithTimestamps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ServerSideService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
