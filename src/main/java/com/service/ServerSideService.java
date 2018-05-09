package com.service;

import com.dto.BookDTO;
import com.dto.User;
import com.dto.UserWithTimestamps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ServerSideService {

    private Map<String, UserWithTimestamps> userMapByUserName = new HashMap<>(); // String is username
    private Map<String, UserWithTimestamps> userMapByUuid = new HashMap<>(); // String is uuid
    Map<Long,BookDTO> books = generateBooks();

    public ResponseEntity<?> register(User user){
        if(!userMapByUserName.containsKey(user.getUsername())){
            UserWithTimestamps newUser = new UserWithTimestamps(user.getUsername(), user.getPassword());
            newUser.setUuid(generateUuid());

            userMapByUserName.put(newUser.getUsername(),newUser);
            userMapByUuid.put(newUser.getUuid(), newUser);

            Map<String,String> responseMap = new HashMap<>();
            responseMap.put("isregistered","registered!");
            return new ResponseEntity(responseMap, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> login(User user){
        Map<String,String> mapToReturn = new HashMap<>();
        if(userMapByUserName.containsKey(user.getUsername()) && userMapByUserName.get(user.getUsername()).getPassword().equals(user.getPassword())){
            UserWithTimestamps thisUser = userMapByUserName.get(user.getUsername());
            refreshUuidMap(thisUser);
            refreshUserTimestamp(thisUser);

            mapToReturn.put("uuid",thisUser.getUuid());
            mapToReturn.put("logged","Logged!");
            return new ResponseEntity(mapToReturn,HttpStatus.OK);
        }
        mapToReturn.put("uuid", "");
        mapToReturn.put("logged", "notLogged");
        return new ResponseEntity(mapToReturn, HttpStatus.valueOf(401));
    }

    public ResponseEntity<?> getBooks(String uuid){
        UserWithTimestamps user = userMapByUuid.get(uuid);
        if(user!=null && checkIfTimestampIsStillValid(user.getLastRequestTimestamp())){
            return new ResponseEntity(getBookList(),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> deleteBook(String uuid,long bookId){
        UserWithTimestamps user = userMapByUuid.get(uuid);
        if(user!=null && checkIfTimestampIsStillValid(user.getLastRequestTimestamp())){
            books.remove(bookId);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    private void refreshUserTimestamp(UserWithTimestamps user){
        user.setLastRequestTimestamp(new Timestamp(System.currentTimeMillis()));
    }

    private boolean checkIfTimestampIsStillValid(Timestamp timestamp){
        if((System.currentTimeMillis()/1000) -(timestamp.getTime()/1000)<=15){
            return true;
        }
        return false;
    }

    private void refreshUuidMap(UserWithTimestamps user){
        userMapByUuid.remove(user.getUuid());
        user.setUuid(generateUuid());
        userMapByUuid.put(user.getUuid(),user);
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

    private Map<Long,BookDTO> generateBooks(){
        Map<Long,BookDTO> mapWithBooks = new HashMap<>();
        for (int i =0;i<7;i++){
            BookDTO bookToInsert = new BookDTO();
            bookToInsert.setId((long) i);
            bookToInsert.setTitle("Book"+i);
            bookToInsert.setAuthor("Author"+i);
            mapWithBooks.put(bookToInsert.getId(), bookToInsert);
        }
        return mapWithBooks;
    }

    private List<BookDTO> getBookList(){
        List<BookDTO> bookList = new ArrayList<>();
        for (Long key : books.keySet()) {
            bookList.add(books.get(key));
        }
        return bookList;
    }
}
