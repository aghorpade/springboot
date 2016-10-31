package com.cardinalhealth.chh.pod.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.cardinalhealth.chh.pod.model.Tracker;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PDFController {

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET, produces = "application/zip")
	public ResponseEntity<byte[]> downloadPDFFile(@RequestParam("trackingNumbers") String trackingNumbers)
			throws IOException {
		// creating byteArray stream, make it bufforable and passing this buffor
		// to ZipOutputStream
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
		ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
		String[] trackingNumberArray;
		if (trackingNumbers != null && !trackingNumbers.isEmpty()) {
			trackingNumberArray = trackingNumbers.split(",");
		} else {
			throw new RuntimeException("Tracking Number values is missing");
		}
		//System.out.println("Start reading files and zip it");
		// simple file list, just for tests
		for (String number : trackingNumberArray) {
			Resource resource = resourceLoader.getResource("classpath:"+"/files/"+number+".pdf");
			zipOutputStream.putNextEntry(new ZipEntry(number+".pdf"));
			IOUtils.copy(resource.getInputStream(), zipOutputStream);
			zipOutputStream.closeEntry();
		}

		if (zipOutputStream != null) {
			zipOutputStream.finish();
			zipOutputStream.flush();
			IOUtils.closeQuietly(zipOutputStream);
		}
		IOUtils.closeQuietly(bufferedOutputStream);
		IOUtils.closeQuietly(byteArrayOutputStream);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"POD.zip\"");

		return ResponseEntity.ok().headers(headers).contentLength(byteArrayOutputStream.toByteArray().length)
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(byteArrayOutputStream.toByteArray());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<byte[]> submitForm(@ModelAttribute("tracker") Tracker tracker, BindingResult result, SessionStatus status) throws IOException {
		// Store the employee information in database
		// manager.createNewRecord(employeeVO);
		System.out.println("tracking numners" + tracker.getTrackingNumbers());
		// Mark Session Complete
		return downloadPDFFile(tracker.getTrackingNumbers());
		
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success(Model model) {
		return "success";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupForm(Model model) {
		Tracker tracker = new Tracker();
		model.addAttribute("tracker", tracker);
		return "addTrackers";
	}
}
