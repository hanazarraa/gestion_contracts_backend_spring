package com.luv2code.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.crypto.spec.SecretKeySpec;

import com.luv2code.ecommerce.dao.UserRepository;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.User;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired(required=true)
	PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    //get all users
    @GetMapping("getAll")
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
    
    
  //get User by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable (value = "id")long userId){
        return this.userRepository.findById(userId)
           .orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+userId));

    }
    //create user
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)

    public User createUser( @RequestBody User user){
    	 String encryptedPassword = passwordEncoder.encode(user.getPassword());
         user.setPassword(encryptedPassword);
         System.out.print(user.getPassword());
        return this.userRepository.save(user);
    }
    
    
    

    //login user
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public ResponseEntity<Object> loginUser(@RequestBody User user){
        //System.out.println(user.getUsername());
  	     String encryptedPassword = passwordEncoder.encode(user.getPassword());
   	  

        
        List<JSONObject> entities = new ArrayList<JSONObject>();
        ObjectMapper objectMapper = new ObjectMapper();
   	      encryptedPassword = passwordEncoder.encode(user.getPassword());
        List<User> users=this.userRepository.findByUserName(user.getUsername());
        if (!users.isEmpty()){
        	if(passwordEncoder.matches(user.getPassword(), users.get(0).getPassword())) {
        		
        		JSONObject entity = new JSONObject();
                //entity.put("message", "success");
                 //entities.add(entity);
                entity.put("user", users.get(0));


                entities.add(entity);
                System.out.print(entities);
                // return HttpStatus.ACCEPTED;
                return new ResponseEntity<Object>(entities, HttpStatus.OK);
        	} else {
        		 JSONObject entity = new JSONObject();
                 entity.put("message","invalid credentials");
                 entities.add(entity);
                // entity.put("message error","invalid credentials");
                 //entities.add(entity);
                 System.out.print(entities);
                return new ResponseEntity<Object>(entities,HttpStatus.BAD_REQUEST);

        	}          
        	

        }else{
            JSONObject entity = new JSONObject();
             entity.put("message","invalid credentials");
             entities.add(entity);
            // entity.put("message error","invalid credentials");
             //entities.add(entity);
             System.out.print(entities);
            return new ResponseEntity<Object>(entities,HttpStatus.BAD_REQUEST);

        }

       // return this.userRepository.save(user);
    }
  //update customer
    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user,@PathVariable("id") long userId){
        User existig=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("customer not found with id: "+userId));
        existig.setFirstname(user.getFirstname());
        existig.setLastname(user.getLastname());
        existig.setUsername(user.getUsername());
        return this.userRepository.save(existig);
        //return existig;
    }
    
    //delete customer
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteCustomer(@PathVariable("id")long userID){
        User existing=this.userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("user not found with id: "+userID));
        this.userRepository.delete(existing);
        return ResponseEntity.ok().build();
    }

}
