package com.comparefolder.CompareApplication.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comparefolder.CompareApplication.service.FolderAppService;

@RestController
public class FolderAppRestController {

	@Autowired
	FolderAppService folderAppService;

	@GetMapping("/source")
	public List<String> listFilesFromSource() {
		return this.folderAppService.listFilesFromSource();

	}
	
	@GetMapping("/target")
	public List<String> listFilesFromTarget() {
		return this.folderAppService.listFilesFromTarget();

	}
	
	@GetMapping("/compareCommon")
	public Collection<String> listCommonFilesAfterCompare() {
		return this.folderAppService.listCommonFilesAfterCompare();

	}
	
	@GetMapping("/compareDiff")
	public Collection<String> listDifferenceFilesAfterCompare() {
		return this.folderAppService.listDifferenceFilesAfterCompare();

	}
	
}
