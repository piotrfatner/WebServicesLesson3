package com.service;

import com.dto.User;
import com.dto.UserWithTimestamps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ServerSideService {

    private Map<String, UserWithTimestamps> userMap = new HashMap<>();
    private Map<String, UserWithTimestamps> userMapByUuid = new HashMap<>();

    public ResponseEntity<?> login(User user){
        Map<String,String> mapToReturn = new HashMap<>();
        if(userMap.containsKey(user.getUsername()) && userMap.get(user.getUsername()).getPassword().equals(user.getPassword())){
            UserWithTimestamps thisUser = userMap.get(user.getUsername());
            mapToReturn.put("uuid",thisUser.getUuid());
            mapToReturn.put("logged","Logged!");
            return new ResponseEntity(mapToReturn,HttpStatus.OK);
        }
        mapToReturn.put("uuid", "");
        mapToReturn.put("logged", "notLogged");
        return new ResponseEntity(mapToReturn, HttpStatus.valueOf(401));
    }

    public ResponseEntity<?> register(User user){
        if(!userMap.containsKey(user.getUsername())){
            UserWithTimestamps newUser = new UserWithTimestamps(user.getUsername(), user.getPassword());
            newUser.setUuid(generateUuid());
            userMap.put(newUser.getUsername(),newUser);
            Map<String,String> responseMap = new HashMap<>();
            responseMap.put("isregistered","registered!");
            return new ResponseEntity(responseMap, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> getBooks(String uuid){
        return new ResponseEntity(HttpStatus.OK);
    }

    private void refreshUserTimestamp(UserWithTimestamps user){
        user.setLastRequestTimestamp(new Timestamp(System.currentTimeMillis()));
    }

    /*public ResponseEntity<?> performRequestMethod(String uuid){
        UserWithTimestamps currentUser = uuidMap.get(uuid);
        if(currentUser!= null &&  ((System.currentTimeMillis()/1000) -currentUser.getLastRequestTimestamp().getTime()/1000)<=120){
            return new ResponseEntity<String>("OK", HttpStatus.valueOf(200));
        }else if(((System.currentTimeMillis()/1000) -currentUser.getLastRequestTimestamp().getTime()/1000)>120){
            return new ResponseEntity<String>("GONE", HttpStatus.valueOf(410));
        }

        return new ResponseEntity<String>("NOT OK", HttpStatus.valueOf(401));
    }*/


    private String generateUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
