package com.immfly.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.immfly.model.Flight;

@Repository
public class FlightRepository {
	
	public static final String FLIGHT_KEY = "FLIGHT";
	 
    private HashOperations<String, String, List<Flight>> hashOperations;
 
    private RedisTemplate<String, Object> redisTemplate;
 
    public FlightRepository(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }
 
    public void save(List<Flight> flights, String tailNumber) {
        hashOperations.put(FLIGHT_KEY, tailNumber, flights);
    }
 
    public Optional<List<Flight>> findByTailNumber(String tailNumber) {
        return Optional.ofNullable((List<Flight>) hashOperations.get(FLIGHT_KEY, tailNumber));
    }

}
