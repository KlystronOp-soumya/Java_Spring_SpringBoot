package com.demo.resttemplatedemo;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service(value = "topStoriesService")
public class TopStoriesService {

	private static RestTemplate restTemplate;

	public TopStoriesService(RestTemplateBuilder restTemplateBuilder) {
		// TODO Auto-generated constructor stub
		restTemplate = restTemplateBuilder.build();
	}

	@Async("threadPoolTaskExecutor")
	public String getTopStories() {
		JSONObject topStories;
		JSONArray resultJsonArray;
		ResponseEntity<Map> nyResponseEntity;
		String topStoriesResult = null;
		HttpHeaders headers;
		HttpEntity<String> entity;
		try {
			headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_PLAIN);
			entity = new HttpEntity<String>(headers);

			topStories = new JSONObject();
			nyResponseEntity = restTemplate.exchange(getRequestUrl(), HttpMethod.GET, entity, Map.class);

			if (nyResponseEntity.getStatusCode() == HttpStatus.OK)

			{
				topStories = new JSONObject(nyResponseEntity.getBody());
				resultJsonArray = topStories.getJSONArray("results");
				System.out.println(topStories.toString(4));
				topStoriesResult = topStories.toString();
			} else {
				throw new ResourceAccessException(
						"The resource requested can not be retrieved" + nyResponseEntity.getStatusCodeValue());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return topStoriesResult;
	}

	protected String getRequestUrl() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(Constants.NY_TIMES_ENDPOINT.getValue());
		stringBuffer.append("api-key=");
		stringBuffer.append(Constants.NY_TIMES_API_KEY.getValue());

		return stringBuffer.toString();

	}

}
