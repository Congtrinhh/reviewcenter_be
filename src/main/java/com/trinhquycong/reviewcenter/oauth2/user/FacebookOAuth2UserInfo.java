package com.trinhquycong.reviewcenter.oauth2.user;

import java.util.Map;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo {

	public FacebookOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return (String) attributes.get("id");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String) attributes.get("email");
	}

	@Override
    @SuppressWarnings("unchecked")
    public String getImageUrl() {
        if (attributes.containsKey("picture")) {
            Map<String, Object> pictureObj = (Map<String, Object>) attributes.get("picture");
            if (pictureObj.containsKey("data")) {
                Map<String, Object> dataObj = (Map<String, Object>) pictureObj.get("data");
                if (dataObj.containsKey("url")) {
                    return (String) dataObj.get("url");
                }
            }
        }
        return null;
    }

}
