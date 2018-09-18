package com.hht.cloud.webture.producer.controller;

import java.util.Date;
import java.util.List;

import com.hht.cloud.webture.producer.model.ScreenshotRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hht.cloud.webture.producer.model.FileProcess;
import com.hht.cloud.webture.producer.repository.FileProcessRepository;
import com.hht.cloud.webture.producer.service.MessagePublisher;
import java.util.UUID;

@RestController
public class FileProcessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessController.class);

	@Autowired
	private FileProcessRepository repository;
	
	@Autowired
	private MessagePublisher messagePublisher;
	
	@GetMapping("/")
    public List<FileProcess> list() {
        return repository.findAll();
    }
	
	@PostMapping("/")
    public ResponseEntity<FileProcess> addUrl(@RequestBody UrlDTO dto) {
		FileProcess fp = new FileProcess();
		fp.setCaptureUrl(dto.getUrl());
		fp.setCreateDate(new Date());
		fp.setFileType(dto.getFileType());
		fp.setMessageId(UUID.randomUUID().toString());
		fp = repository.insert(fp);

		ScreenshotRequest sr = new ScreenshotRequest(fp.getMessageId(),fp.getCaptureUrl());

		boolean sentToQueue = messagePublisher.sendQueueMessage(sr);
		if(!sentToQueue) {
			repository.delete(fp);
			LOGGER.info("Message rollbacked!");
			return new ResponseEntity<FileProcess>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<FileProcess>(fp, HttpStatus.OK);
    }


}
