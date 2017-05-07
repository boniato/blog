package com.boniato.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lee on 2017. 5. 5..
 */
@ConfigurationProperties("git")
public class GitProperties {
	private Github github = new Github();

	private Security security = new Security();

	public Github getGithub() {
		return this.github;
	}

	public Security getSecurity() {
		return this.security;
	}

	public static class Github {

		private String token;

		public String getToken() {
			return this.token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}

	public static class Security {

		private List<String> admins = Collections.singletonList("boniato");

		public List<String> getAdmins() {
			return admins;
		}

		public void setAdmins(List<String> admins) {
			this.admins = admins;
		}

	}
}
