package util;

import org.springframework.stereotype.Component;

@Component
public class KakaoOauth implements SocialOauth {

	//카카오로그인 구현 
	@Override
	public String getOauthRedirectURL() {
		return "";
	}

}
