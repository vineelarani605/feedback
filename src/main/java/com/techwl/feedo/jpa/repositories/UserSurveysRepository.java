package com.techwl.feedo.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techwl.feedo.user.entities.UserSurveyDetails;

public interface UserSurveysRepository extends JpaRepository<UserSurveyDetails, Integer>{

	@Query(value = "SELECT U FROM UserSurveyDetails U WHERE userId = :userId AND completed = 0")
	public List<UserSurveyDetails> findSuveysByUserId(int userId);
}
