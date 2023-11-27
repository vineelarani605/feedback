package com.techwl.feedo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techwl.feedo.constants.FeedoConstants;
import com.techwl.feedo.daos.UserSurveyDAO;
import com.techwl.feedo.handlers.ResponseHandler;
import com.techwl.feedo.handlers.UserSurveyRequest;
import com.techwl.feedo.jpa.repositories.SurveyRepository;
import com.techwl.feedo.user.entities.SurveyDetails;
import com.techwl.feedo.user.entities.UserSurveyDetails;

@RestController
@RequestMapping(path = "/survey", consumes = "application/json", produces = "application/json")
public class SurveyController {

	@Autowired
	SurveyRepository surveyRepository;

	@Autowired
	ResponseHandler resHandler;

	@Autowired
	UserSurveyDAO userSurveyDAO;

	@PostMapping(path = "create")
	public ResponseEntity<String> create(@RequestBody UserSurveyRequest surveyRequest) {

		if (checkSurveryByTitle(surveyRequest)) {
			return resHandler.getResponse(FeedoConstants.DUPLICATE_SURVEY_TITLE, FeedoConstants.FAILURE, null);
		}
		userSurveyDAO.createUserSurvey(surveyRequest);
		return resHandler.getResponse(FeedoConstants.SURVEY_CREATTION_SUCCESSFUL, FeedoConstants.SUCCESS, null);
	}

	@PostMapping(path = "edit")
	public ResponseEntity<String> edit(@RequestBody SurveyDetails survey) {
		surveyRepository.save(survey);
		return resHandler.getResponse(FeedoConstants.SURVEY_EDIT_SUCCESSFUL, FeedoConstants.SUCCESS, null);
	}

	@GetMapping(path = "load")
	public List<UserSurveyDetails> loadSurvey(int userId) {
		return userSurveyDAO.loadUserSurveys(userId);
	}

	private boolean checkSurveryByTitle(UserSurveyRequest survey) {
		return surveyRepository.findByTitleAndActive(survey.getTitle(), Boolean.TRUE) != null;

	}

}
