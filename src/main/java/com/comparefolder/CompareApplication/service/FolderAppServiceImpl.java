package com.comparefolder.CompareApplication.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.comparefolder.CompareApplication.properties.FileInfo;
import com.comparefolder.CompareApplication.properties.StorageProperties;

@Service
public class FolderAppServiceImpl implements FolderAppService {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	private final String sourceDirectory;
	private final String targetDirectory;
	private final String compareResultDirectory;
	private final Path rootLocation = Paths.get("compareResultDirectory");

	@Autowired
	private StorageProperties storageProperties;

	public FolderAppServiceImpl(StorageProperties storageProperties) {
		this.sourceDirectory = storageProperties.getSourcelocation();
		this.targetDirectory = storageProperties.getTargetlocation();
		this.compareResultDirectory = storageProperties.getComparelocation();
	}

	// Getting Source Files Names
	@Override
	public List<String> listFilesFromSource() {
		File sourcePath = new File(sourceDirectory);
		List<String> sourcelist = new ArrayList<String>();
		for (File fileEntry : sourcePath.listFiles()) {
			sourcelist.add(fileEntry.getName());
		}
		return sourcelist;
	}

	// Getting Target Files Names
	@Override
	public List<String> listFilesFromTarget() {
		File targetPath = new File(targetDirectory);
		List<String> targetlist = new ArrayList<String>();
		for (File fileEntry : targetPath.listFiles()) {
			targetlist.add(fileEntry.getName());
		}
		return targetlist;
	}

	// Getting Common Files Names
	public Collection<String> listCommonFilesAfterCompare() {
		File sourcePath = new File(sourceDirectory);
		File targetPath = new File(targetDirectory);
		List<String> list1 = Arrays.asList(sourcePath.list());
		List<String> list2 = Arrays.asList(targetPath.list());
		Collection<String> intersection = CollectionUtils.intersection(list1, list2);
		return intersection;
	}

	// Getting Different Files Names
	@Override
	public Collection<String> listDifferenceFilesAfterCompare() {

		File sourcePath = new File(sourceDirectory);
		File targetPath = new File(targetDirectory);
		List<String> list1 = Arrays.asList(sourcePath.list());
		List<String> list2 = Arrays.asList(targetPath.list());

		Set<String> set = new LinkedHashSet<>(list1);
		set.addAll(list2);
		List<String> combinedList = new ArrayList<>(set);
		Collection<String> intersection = CollectionUtils.intersection(list1, list2);
		Collection<String> subtract2 = CollectionUtils.subtract(combinedList, intersection);
		return subtract2;
	}

	// Getting Compare Result Files Names
	@Override
	public List<FileInfo> getCompareReport() {
		
		List<FileInfo> fileInfo = new ArrayList<FileInfo>();
		File Path = new File(compareResultDirectory);
		for (File fileEntry : Path.listFiles()) {
			FileInfo objFile = new FileInfo();
			objFile.fileName = fileEntry.getName();
			try {
				objFile.fileBytes = Files.readAllBytes(Paths.get(fileEntry.getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileInfo.add(objFile);
		}
		return fileInfo;
	}

	
	// Download
}
