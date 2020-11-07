package com.comparefolder.CompareApplication.schedulerr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.comparefolder.CompareApplication.properties.StorageProperties;
import com.comparefolder.CompareApplication.service.FolderAppService;
import com.comparefolder.CompareApplication.service.FolderAppServiceImpl;

@Component
public class Scheduler {
	
	@Autowired
	StorageProperties storageProperties;
	
	
	//@Scheduled(fixedRate = 10000)
	@Scheduled(cron = "${storage.scheduler}")

	public void fixedRateSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = new Date();
		String strDate = sdf.format(now);
		//System.out.println("Fixed Rate scheduler:: " + strDate);
		
		//System.out.println("Storage file path: " + storageProperties.getComparelocation());
		String file = storageProperties.getComparelocation() + "/CompareData_" + strDate + ".txt";
		System.out.println(file);

		try {
			FileWriter myWriter = new FileWriter(file);
			//Collection<String> list = getCommonFiles();
			
			FolderAppService folderAppService = new FolderAppServiceImpl(storageProperties);
			
			Collection<String> list = folderAppService.listCommonFilesAfterCompare();
			for (String s : list) {
				myWriter.write("\n");
	    		  myWriter.write("File Name: " + s);
	    		  }

			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public Collection<String> getCommonFiles() {
		File sourcePath = new File(storageProperties.getSourcelocation());
		File targetPath = new File(storageProperties.getTargetlocation());
		List<String> list1 = Arrays.asList(sourcePath.list());
		List<String> list2 = Arrays.asList(targetPath.list());
		Collection<String> intersection = CollectionUtils.intersection(list1, list2);
		return intersection;
	}
}
