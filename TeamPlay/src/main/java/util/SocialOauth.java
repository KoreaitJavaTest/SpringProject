package util;

public interface SocialOauth {
	//각 소셜로그인 페이지로 Redirect 처리할 URL Bulid
	//각 사용자로 부터 로그인 요청을 받아 Social Login Server 인증용 Code 요청
	String	getOauthRedirectURL();
	
	
}
