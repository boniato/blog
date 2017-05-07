package com.boniato.github;

import lombok.RequiredArgsConstructor;
import com.boniato.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Lee on 2017. 5. 5..
 */
@Service
@RequiredArgsConstructor
public class GithubClient {

	@Autowired
	private RestTemplate restTemplate;

	private static String GIT_HUB_URL = "https://api.github.com";

	@Cacheable("github.user")
	public GithubUser getUser(String githubId) {
		return invoke(createRequestEntity(String.format(GIT_HUB_URL + "/users/%s", githubId)), GithubUser.class)
				.getBody();
	}

	private <T> ResponseEntity<T> invoke(RequestEntity<?> request, Class<T> responseType) {
		try {
			return this.restTemplate.exchange(request, responseType);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new NotFoundException("not found");
			}
			throw ex;
		}
	}

	private RequestEntity<?> createRequestEntity(String url) {
		try {
			return RequestEntity.get(new URI(url)).accept(MediaType.APPLICATION_JSON).build();
		} catch (URISyntaxException ex) {
			throw new IllegalStateException("Invalid URL " + url, ex);
		}
	}
}