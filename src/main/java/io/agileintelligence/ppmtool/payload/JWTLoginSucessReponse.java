package io.agileintelligence.ppmtool.payload;

import java.util.List;

import io.agileintelligence.ppmtool.services.UserDetailsImpl;

public class JWTLoginSucessReponse {
    private boolean success;
    private String token;
    UserDetailsImpl userDetails;
    
    public UserDetailsImpl getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsImpl userDetails) {
		this.userDetails = userDetails;
	}

	public JWTLoginSucessReponse(boolean success, String token,UserDetailsImpl userDetails) {
        this.success = success;
        this.token = token;
        this.userDetails = userDetails;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
  
    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                 ", userDetails='" + userDetails + '\'' +
                '}';
    }
}
