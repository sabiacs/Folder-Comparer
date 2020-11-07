package com.comparefolder.CompareApplication.service;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import org.springframework.core.io.Resource;

import com.comparefolder.CompareApplication.properties.FileInfo;

public interface FolderAppService {

	public List<String> listFilesFromSource();

	public List<String> listFilesFromTarget();

	public Collection<String> listCommonFilesAfterCompare();

	public Collection<String> listDifferenceFilesAfterCompare();

	public List<FileInfo> getCompareReport();

}
