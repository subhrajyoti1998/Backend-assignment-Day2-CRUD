package com.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.entity.NetflixRecordEntity;
import com.crud.exception.ShowNotFoundException;
import com.crud.repository.NetflixRepository;

@Service
public class CrudServiceImpl {
	
	@Autowired
	NetflixRepository repo;
	
	// Return All Available shows
	public List<NetflixRecordEntity> getAllShows() {
		return repo.findAll();
	}
	
	// Return Show by it's ID
	public NetflixRecordEntity getShowById(String id) {
		return repo.findById(id).orElseThrow(() ->
									new ShowNotFoundException("Show not found with ID : " + id));
	}
	
	// Create Records
	public void create(NetflixRecordEntity newEntity) {
		repo.save(newEntity);
		//return newEntity;
	}
	
	public void update(String id, NetflixRecordEntity entity) {
		NetflixRecordEntity record = repo.findById(id).orElseThrow(() -> 
									 new ShowNotFoundException("Show not found with ID : " + id));
		if (record != null && !record.equals(entity)) {
			record = entity.clone();
		}
		repo.save(record);
	}
	
	public void delete(String id) {
		repo.deleteById(id);
	}
}