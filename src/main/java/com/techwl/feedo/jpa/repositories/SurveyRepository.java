package com.techwl.feedo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techwl.feedo.user.entities.SurveyDetails;

public interface SurveyRepository extends JpaRepository<SurveyDetails, Integer>{

	public SurveyDetails findByTitle(String title);	
	public SurveyDetails findByTitleAndActive(String title, boolean active);
	public List<SurveyDetails> findAllByActive(boolean active);
}
