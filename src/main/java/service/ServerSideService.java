package service;

import dto.User;
import dto.UserWithTimestamps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ServerSideService {

    private Map<String, UserWithTimestamps> uuidMap= new HashMap<>();

    public ResponseEntity<?> login(User user){
        String newUuid = generateUuid();
        System.out.println(newUuid);
        String newUuidToMap= newUuid.replaceFirst("..","44");
        System.out.println(newUuid);
        UserWithTimestamps newUser = new UserWithTimestamps(user.getUsername(), user.getPassword());
        uuidMap.put(newUuidToMap, newUser);
        return new ResponseEntity<String>(newUuid, HttpStatus.valueOf(200));
    }

    public ResponseEntity<?> performRequestMethod(String uuid){
        UserWithTimestamps currentUser = uuidMap.get(uuid);
        if(currentUser!= null &&  ((System.currentTimeMillis()/1000) -currentUser.getLastRequestTimestamp().getTime()/1000)<=120){
            return new ResponseEntity<String>("OK", HttpStatus.valueOf(200));
        }else if(((System.currentTimeMillis()/1000) -currentUser.getLastRequestTimestamp().getTime()/1000)>120){
            return new ResponseEntity<String>("GONE", HttpStatus.valueOf(410));
        }

        return new ResponseEntity<String>("NOT OK", HttpStatus.valueOf(401));
    }


    private String generateUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
