package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.UUID;

@RestController
public class ServerSideController {

    private String username;
    private String password;
    private Timestamp generationTimestamp;
    private Timestamp lastRequestTimestamp;
    private String uuid;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloMethod(){
        return "Hello from the other side!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> login(@RequestBody User user){
        return new ResponseEntity<String>("OK", HttpStatus.valueOf(200));
    }

    private String generateUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
