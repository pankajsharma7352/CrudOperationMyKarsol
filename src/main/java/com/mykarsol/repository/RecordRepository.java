package com.mykarsol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<com.mykarsol.model.Record, Long> {

	com.mykarsol.model.Record save(com.mykarsol.model.Record record);
}