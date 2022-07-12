package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crud.entity.NetflixRecordEntity;

@Repository
public interface NetflixRepository extends JpaRepository<NetflixRecordEntity, String> {
	
}