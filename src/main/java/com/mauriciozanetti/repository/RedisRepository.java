package com.mauriciozanetti.repository;

import java.util.Map;

import com.mauriciozanetti.model.Task;

public interface RedisRepository {

	Map<Object, Object> findAllTasks();

	void add(Task task);

	void delete(String id);

	Task findTask(String id);

}
