package com.hht.cloud.webture.urlreceiver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hht.cloud.webture.urlreceiver.model.FileProcess;

@Repository
public interface FileProcessRepository extends MongoRepository<FileProcess, String> {
	FileProcess findByMessageId(String messageId);
}