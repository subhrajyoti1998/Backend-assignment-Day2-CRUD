package com.crud;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.crud.dataloader.NetflixDataLoader;
import com.crud.dataloader.NetflixShowDetails;
import com.crud.entity.NetflixRecordEntity;
import com.crud.service.CrudServiceImpl;

@Transactional
@SpringBootApplication
@EnableScheduling
public class CrudApplication {

	@Autowired
	NetflixDataLoader loader;
	
	@Autowired
	CrudServiceImpl service;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
	
	@Scheduled(cron = "*/30 * * * * ?")
	public void loadData() {
		final String path = "D:/Java Programs/crud/sampleNetflixData.csv";
		List<NetflixShowDetails> csvData = loader.loadDataFromCSV(path);       // CSV Data
		List<NetflixRecordEntity> tableData = service.getAllShows();	       // Table data
		
		if (csvData.size() > tableData.size()) {
			for (NetflixShowDetails data : csvData) {
				if ( !tableData.contains(data.getShowId())) {
					NetflixRecordEntity createRecord = new NetflixRecordEntity(data.getShowId(), data.getShowType(),
													   data.getTitle(), data.getDirector(), data.getCast(),
													   data.getCountry(), data.getDateAdded(), data.getRelease_year(),
													   data.getRating(), data.getDuration(), data.getListedIn(),
													   data.getDescription());
					service.create(createRecord);
				}
			}
		} else if (csvData.size() < tableData.size()) {
			for (NetflixRecordEntity data : tableData) {
				if ( !csvData.contains(data.getShowId())) {
					service.delete(data.getShowId());
				}
			}
		} else {
			System.out.println("\nNo Changes Required in Table.\n");
		}
	}
}