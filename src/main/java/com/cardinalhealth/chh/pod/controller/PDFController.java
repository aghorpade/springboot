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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PDFController {
	
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET, produces = "application/zip")
	public ResponseEntity<byte[]> downloadPDFFile(@RequestParam("trackingNumbers") String trackingNumbers)
	        throws IOException {
		 //creating byteArray stream, make it bufforable and passing this buffor to ZipOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
        String[] trackingNumberArray;
        if(trackingNumbers!=null && !trackingNumbers.isEmpty()){
        	trackingNumberArray=trackingNumbers.split(",");
        }else{
        	throw new RuntimeException("Tracking Number values is missing");
        }
        
        //simple file list, just for tests
        ArrayList<File> files = new ArrayList<>(2);
        ClassLoader classLoader = getClass().getClassLoader();
        for(String number:trackingNumberArray){
        	files.add(new File(classLoader.getResource("files/"+number+".pdf").getPath()));	
        }
        

        //packing files
        for (File file : files) {
            //new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
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
        
	    return ResponseEntity
	            .ok().headers(headers)
	            .contentLength(byteArrayOutputStream.toByteArray().length)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(byteArrayOutputStream.toByteArray());
	}

}
