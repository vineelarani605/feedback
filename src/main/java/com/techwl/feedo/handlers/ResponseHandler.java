package com.techwl.feedo.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techwl.feedo.dtos.ResponseDTO;

public class ResponseHandler {

	private ObjectMapper mapper = null;

	public ResponseEntity<String> getResponse(String message, String status, String error) {
		ResponseDTO responseDTO = getResponseDTO(message, status, error);
		return getResponseEntity(responseDTO);
	}

	public ResponseEntity<String> getResponse(String message, String status, String error, String data) {
		ResponseDTO response = getResponseDTO(message, status, error);
		response.setData(data);
		return getResponseEntity(response);
	}

	private ResponseEntity<String> getResponseEntity(ResponseDTO dto) {
		HttpHeaders headers = new HttpHeaders();
		mapper = getObjectMapper();
		ResponseEntity<String> entity = null;
		try {
			String writeValueAsString = mapper.writeValueAsString(dto);
			entity = new ResponseEntity<String>(writeValueAsString, headers, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return entity;
	}

	private ResponseDTO getResponseDTO(String message, String status, String error) {
		ResponseDTO dto = new ResponseDTO();
		dto.setMessage(message);
		dto.setStatus(status);
		dto.setException(error);
		return dto;
	}

	private ObjectMapper getObjectMapper() {
		if (mapper == null) {
			return new ObjectMapper();
		}
		return mapper;
	}

}
