package com.mauriciozanetti.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mauriciozanetti.model.Task;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

	private static final String KEY = "Task";

	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations hashOperations;

	@Autowired
	public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public Map<Object, Object> findAllTasks() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void add(Task task) {
		hashOperations.put(KEY, task.getId(), task.getName());
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}

	@Override
	public Task findTask(String id) {
		return (Task) hashOperations.get(KEY, id);
	}

}
