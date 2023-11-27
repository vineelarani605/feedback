package com.techwl.feedo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtilities<T> {

	private ObjectMapper mapper = null;

	public String convertToJson(T arg) throws JsonProcessingException {
		ObjectMapper mapper = getObjectMapper();
		return mapper.writeValueAsString(arg);
	}

	public ObjectMapper getObjectMapper() {
		if (mapper == null) {
			synchronized (this) {
				mapper = new ObjectMapper();
			}
		}
		return mapper;
	}
}
