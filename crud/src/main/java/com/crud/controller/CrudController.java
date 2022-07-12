package com.crud.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crud.dataloader.NetflixDataLoader;
import com.crud.dataloader.NetflixShowDetails;
import com.crud.entity.NetflixRecordEntity;
import com.crud.service.CrudServiceImpl;

@RestController
@RequestMapping("/netflixshows")
public class CrudController {
	
	@Autowired
	CrudServiceImpl service;
	
	@Autowired
	NetflixDataLoader dataLoader;
	
	@GetMapping("/all")
	public ResponseEntity<List<NetflixRecordEntity>> getAllShows() {
		return new ResponseEntity<List<NetflixRecordEntity>>(service.getAllShows(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NetflixRecordEntity> getShowByID(@PathVariable String id) {
		return ResponseEntity.ok(service.getShowById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> postRecord(@RequestBody NetflixRecordEntity newEntity) {
		service.create(newEntity);
		return new ResponseEntity<String>("Data added Successfully.", HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> upateRecord(@PathVariable String id, @RequestBody NetflixRecordEntity entity) {
		service.update(id, entity);
		return new ResponseEntity<String>("Data updated Successfully.", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteRecord(@PathVariable String id) {
		service.delete(id);
		return new ResponseEntity<String>("Data deleted Successfully with ID : " + id, HttpStatus.OK);
	}
	
	@GetMapping("/get/{dataSource}/{id}")
	public ResponseEntity<Object> lookUpDataFromDataSource(@PathVariable String dataSource,
															@PathVariable String id) {
		if (dataSource.equalsIgnoreCase("SQL")) {
			return new ResponseEntity<Object>(service.getAllShows(), HttpStatus.OK);
		} else if (dataSource.equalsIgnoreCase("CSV")) {
			String filePath = "D:/Java Programs/crud/sampleNetflixData.csv";
			List<NetflixShowDetails> dataFromCSV = dataLoader.loadDataFromCSV(filePath);
			
			List<NetflixShowDetails> data = dataFromCSV.stream()
											.filter(e -> e != null && e.getShowId() != null
													 	&& e.getShowId().equalsIgnoreCase(id))
											.collect(Collectors.toList());
			
			return new ResponseEntity<Object>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("" + dataSource + " is not a valid Data Source", HttpStatus.BAD_REQUEST);
		}
	}
}