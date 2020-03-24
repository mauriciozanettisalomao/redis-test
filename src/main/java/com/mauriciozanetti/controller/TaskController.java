package com.mauriciozanetti.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mauriciozanetti.model.Task;
import com.mauriciozanetti.repository.RedisRepository;

@Controller
@RequestMapping("/")
public class TaskController {
	    
	    @Autowired
	    private RedisRepository redisRepository;

	    @RequestMapping("/")
	    public String index() {
	        return "index";
	    }

	    @RequestMapping("/keys")
	    public @ResponseBody Map<Object, Object> keys() {
	        return redisRepository.findAllTasks();
	    }

	    @RequestMapping("/values")
	    public @ResponseBody Map<String, String> findAll() {
	        Map<Object, Object> aa = redisRepository.findAllTasks();
	        Map<String, String> map = new HashMap<String, String>();
	        for(Map.Entry<Object, Object> entry : aa.entrySet()){
	            String key = (String) entry.getKey();
	            map.put(key, aa.get(key).toString());
	        }
	        return map;
	    }

	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public ResponseEntity<String> add(
	        @RequestParam String key,
	        @RequestParam String value) {
	        
	    	Task task = new Task(key, value);
	        
	        redisRepository.add(task);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    public ResponseEntity<String> delete(@RequestParam String key) {
	        redisRepository.delete(key);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	}