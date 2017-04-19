package org.maxwell.springboot.http2.client;

import java.io.IOException;

import org.maxwell.springboot.http2.controller.StudentDetails;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Http2Client {
	public static final okhttp3.MediaType HTTP2_JSON = okhttp3.MediaType.parse("application/json; charset=utf-8");
	public static final MediaType JSON = MediaType.parseMediaType("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();

	String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(HTTP2_JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	String studentDetails(int studentId, String studName) throws JsonProcessingException {
		StudentDetails studentDetails = new StudentDetails(studentId, studName);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(studentDetails);
		return jsonInString;
	}

	String run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public static void main(String[] args) throws IOException {

		/*
		 * Http2Client example = new Http2Client(); String json = example.studentDetails(1, "Jake"); // String response
		 * = example.post("http://localhost:8080/updateDetails", json); String response =
		 * example.run("http://localhost:8080/getDetails"); System.out.println(response);
		 */

		String uri = "http://localhost:8080/updateDetails";
		RestTemplate http2Template = new RestTemplate(new OkHttp3ClientHttpRequestFactory());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		StudentDetails studentDetails = new StudentDetails(1, "maxwell");

		HttpEntity<StudentDetails> entity = new HttpEntity<StudentDetails>(studentDetails, headers);
		ResponseEntity<String> response = http2Template.exchange(uri, HttpMethod.POST, entity, String.class);
		System.out.println(response.getStatusCode());

	}

}
