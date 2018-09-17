package com.hht.cloud.webture.urlreceiver.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hht.cloud.webture.urlreceiver.model.FileProcess;
import com.hht.cloud.webture.urlreceiver.repository.FileProcessRepository;

@RestController
public class FileProcessController {
	
	@Autowired
	private FileProcessRepository repository;
	
	@GetMapping("/")
    public List<FileProcess> list() {
        return repository.findAll();
    }
	
	@PostMapping("/")
    public FileProcess addUrl(@RequestBody UrlDTO dto) {
		FileProcess fp = new FileProcess();
		fp.setCaptureUrl(dto.getUrl());
		fp.setCreateDate(new Date());
		fp.setFileType(dto.getFileType());
		FileProcess insert = repository.insert(fp);
        return insert;
    }


}
