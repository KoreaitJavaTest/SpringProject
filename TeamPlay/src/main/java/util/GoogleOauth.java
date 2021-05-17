package util;

import org.springframework.stereotype.Component;

@Component
public class GoogleOauth implements SocialOauth {

	@Override
	public String getOauthRedirectURL() {
		return "";
	}

}
