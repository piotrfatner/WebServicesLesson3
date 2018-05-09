package com.controller;

import com.dto.BookDTO;
import com.dto.User;
import com.dto.UserWithTimestamps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.service.ServerSideService;

import java.util.List;

@RestController
public class ServerSideController {
    @Autowired
    ServerSideService serverSideService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> login(@RequestBody User user) {
        return serverSideService.login(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> register(@RequestBody User user) {
        return serverSideService.register(user);
    }

    @RequestMapping(value="/getBooks/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getBooks(@PathVariable("uuid") String uuid){

        return null;
    }
    /*@RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public String greetings(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                @RequestParam("id") long id) {
        return "hello"; // http://localhost:8080/greetings?firstName=User&lastName=asd&id=1
    }*/

    /*@RequestMapping(value = "/performRequest", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> performRequestMethod(@RequestParam String uuid) {
        return serverSideService.performRequestMethod(uuid);
    }*/


}
