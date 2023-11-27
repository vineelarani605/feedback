package com.techwl.feedo.daos;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techwl.feedo.handlers.UserSurveyRequest;
import com.techwl.feedo.jpa.repositories.SurveyRepository;
import com.techwl.feedo.jpa.repositories.UserRepository;
import com.techwl.feedo.jpa.repositories.UserSurveysRepository;
import com.techwl.feedo.user.entities.SurveyDetails;
import com.techwl.feedo.user.entities.UserDetails;
import com.techwl.feedo.user.entities.UserSurveyDetails;

@Service
public class UserSurveyDAO {

	@Autowired
	UserSurveysRepository userSurveyRepository;

	@Autowired
	SurveyRepository surveyRepository;

	@Autowired
	UserRepository userRepository;

	public List<UserSurveyDetails> loadUserSurveys(int userId) {
		List<UserSurveyDetails> userSurveysList = userSurveyRepository.findSuveysByUserId(userId);
		return userSurveysList;
	}

	@Transactional(rollbackOn = { SQLException.class, Exception.class })
	public void createUserSurvey(UserSurveyRequest requestDetails) {

		java.util.Date today = new java.util.Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(today);
		calender.set(Calendar.DATE, 10);

		SurveyDetails survey = new SurveyDetails();		
		survey.setActive(Boolean.TRUE);
		survey.setStartDate(new Date(today.getTime()));
		survey.setEndDate(new Date(calender.getTimeInMillis()));
		survey.setTitle(requestDetails.getTitle());

		SurveyDetails save = surveyRepository.save(survey);
		List<UserDetails> findAllById = userRepository.findAllById(List.of(1, 2, 3, 4, 5));
		
		List<UserSurveyDetails> userSurveyDetailsList = new ArrayList<>();
		for (UserDetails user : findAllById) {
			UserSurveyDetails userSurveyDetails = new UserSurveyDetails();
			userSurveyDetails.setSurveyId(save.getId());
			userSurveyDetails.setUserId(user.getId());
			userSurveyDetails.setCompleted(false);
			userSurveyDetailsList.add(userSurveyDetails);
		}
		userSurveyRepository.saveAll(userSurveyDetailsList);

	}

}
