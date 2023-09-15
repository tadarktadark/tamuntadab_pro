package com.tdtd.tmtd.comm;

import javax.annotation.PostConstruct;

public class GoogleCredentialsConfigurer {
	
	private String googleCredentialsPath;

    public void setGoogleCredentialsPath(String googleCredentialsPath) {
        this.googleCredentialsPath = googleCredentialsPath;
    }

    @PostConstruct
    public void init() {
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", googleCredentialsPath);
    }

}
