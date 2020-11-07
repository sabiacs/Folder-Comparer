package com.comparefolder.CompareApplication.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import com.comparefolder.CompareApplication.service.FolderAppService;

@Controller
public class FolderAppController {

	@Autowired
	FolderAppService folderAppService;

	// Display the compare file list on page
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("message", "Welcome to Folder Compare Appliaction!");
		return "home";
	}

	@GetMapping("/sourcelist")
	public String sourceView(Model model) {
		model.addAttribute("listSourceFiles", folderAppService.listFilesFromSource());
		return "sourcelist";

	}

	@GetMapping("/targetlist")
	public String targetView(Model model) {
		model.addAttribute("listTargetFiles", folderAppService.listFilesFromTarget());
		return "targetlist";

	}

	@GetMapping("/matchlist")
	public String matchFilesView(Model model) {
		model.addAttribute("listCommonFiles", folderAppService.listCommonFilesAfterCompare());
		return "matchlist";

	}

	@GetMapping("/difflist")
	public String diffFileView(Model model) {
		model.addAttribute("listDiffFiles", folderAppService.listDifferenceFilesAfterCompare());
		return "difflist";

	}

	@GetMapping("/comparereport")
	public String compareReportView(Model model) {
		model.addAttribute("listCompareRport", folderAppService.getCompareReport());
		return "comparereport";

	}
	
	

}
