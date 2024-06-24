package com.mykarsol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mykarsol.model.Record;
import com.mykarsol.repository.RecordRepository;

@Service
public class RecordService {

	 @Autowired
	    private RecordRepository recordRepository;

	    public List<Record> findAll() {
	        return recordRepository.findAll();
	    }

	    public Optional<Record> findById(Long id) {
	        return recordRepository.findById(id);
	    }

	    public Record save(com.mykarsol.model.Record record) {
	        return recordRepository.save(record);
	    }

	    public void deleteRecordById(Long id) {
	        recordRepository.deleteById(id);
	    }

		public List<Record> getAllRecords() {
			// TODO Auto-generated method stub
			 return recordRepository.findAll();
		}

		public void saveRecord(Record record) {
			 recordRepository.save(record);
			
		}

		public Optional<Record> getRecordById(Long id) {
			
			return recordRepository.findById(id);
		}

		public void updateRecord(Record record) {
			record.setTitle(record.getTitle());
		
			
		}

	

		


}
