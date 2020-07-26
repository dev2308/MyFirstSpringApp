package com.example.MyFirstSpringApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.Collections;
import java.util.List;


@RestController
public class Controller {

    DB db = new DB();

    @GetMapping("/hi")
    public String sayHello() {
        return "Hello Coders";
    }

    // USD TO INR

    // https://www.google.com/search?q=sachin
    // http://localhost:8090/usd_to_inr?q=5

    @GetMapping("/usd_to_inr")
    public int convertToINR(@RequestParam int q) {
        int usd = q;

        return 75 * usd;
    }

    @GetMapping("/user")
    public User getUser() {
        User user = new User(1, "Dev", 25);

        return user;

    }

    //Creates a new user - POST

    @PostMapping("/createUsers")
    public boolean createUser(@RequestBody User user) {
        return db.addUser(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return db.getUsers();

    }

    //Search a user by id
    // http://localhost:8080/search/users?id=1 -  Query Params
    @GetMapping("/search/users")
    public User searchUser(@RequestParam int id){
        return db.searchUser(id);
    }

    // http://localhost:8080/search/users/1 -  Path Params
    @GetMapping("/search/users/{id}")
    public User searchUserUsingPathParams(@PathVariable int id){
        return db.searchUser(id);
    }

    @PutMapping("/updateUser")
    public boolean updateUser(@RequestBody User user){
        return db.updateUser(user);
    }


    @GetMapping("/users/v2")
    public ResponseEntity<User> getUserV2(){
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.put("Server", Collections.singletonList("Dev-server"));
        headers.put("Anything", Collections.singletonList("Something"));

        User responseBody  = new User(5, "Vishal", 34);

        ResponseEntity<User> response = new ResponseEntity<User>(responseBody, headers, HttpStatus.ACCEPTED);

        return response;
    }


    @RequestMapping("/gitHub/users/{login}")
    public ResponseEntity<GitHubUser> get(@PathVariable("login") String login){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GitHubUser> response  = restTemplate.getForEntity(String.format("https://api.github.com/users/%s ", login), GitHubUser.class);

        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        return response;
    }

}
