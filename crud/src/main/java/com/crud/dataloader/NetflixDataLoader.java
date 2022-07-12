package com.crud.dataloader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NetflixDataLoader {
	public List<NetflixShowDetails> loadDataFromCSV(final String path) {
		String line = "";
		boolean firstRecord = true;
		List<NetflixShowDetails> data = new LinkedList<NetflixShowDetails>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				if (firstRecord) {
					firstRecord = false;
					continue;
				}
				
				String values[] = line.split(",(?=([^\"]|\"[^\"]*\")*$)");
				Date dateAdded = null;
				try {
					if (values[6] != null) {
						dateAdded = new SimpleDateFormat("YYYY-MM-DD").parse(values[6]);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				NetflixShowDetails nextEntry = new NetflixShowDetails(values[0], values[1],
												values[2], values[3], values[4], values[5],
												dateAdded, values[7], values[8], values[9],
												values[10], values[11]);
				data.add(nextEntry);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}