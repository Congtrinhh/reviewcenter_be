package com.trinhquycong.reviewcenter.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.trinhquycong.reviewcenter.dto.LocalUser;
import com.trinhquycong.reviewcenter.dto.SocialProvider;
import com.trinhquycong.reviewcenter.dto.UserRegistrationForm;
import com.trinhquycong.reviewcenter.entity.Role;
import com.trinhquycong.reviewcenter.entity.User;
import com.trinhquycong.reviewcenter.exception.OAuth2AuthenticationProcessingException;
import com.trinhquycong.reviewcenter.exception.UserAlreadyExistAuthenticationException;
import com.trinhquycong.reviewcenter.oauth2.user.OAuth2UserInfo;
import com.trinhquycong.reviewcenter.oauth2.user.OAuth2UserInfoFactory;
import com.trinhquycong.reviewcenter.repo.RoleRepo;
import com.trinhquycong.reviewcenter.repo.UserRepo;
import com.trinhquycong.reviewcenter.util.Constants;
import com.trinhquycong.reviewcenter.util.GeneralUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private ModelMapper mapper;
	
    @Autowired
    private UserRepo userRepository;
 
    @Autowired
    private RoleRepo roleRepository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @SuppressWarnings("unchecked")
	@Override
	public User save(Map<String, Object> updates, Long id) {
    	
		// encode password
    	if (updates.containsKey("password") && ((String)updates.get("password")).length()>0 ) {
    		String plainPassword = (String) updates.get("password");
    		updates.put("password", passwordEncoder.encode(plainPassword));
    	}
    	// không được update role
    	if (updates.containsKey("roles") && ((Set<String>)updates.get("roles")).size()>0) {
    		updates.remove("roles");
    	}
    	
    	// xử lý ảnh ...
    	
		User userFromDB = userRepository.getById(id);
		mapper.map(updates, userFromDB);
		userFromDB.setUpdatedAt(Calendar.getInstance().getTime());
		
		//userFromDB = mapper.map(dto, User.class);
		return userRepository.save(userFromDB);
	}
    
    @Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return e.getMessage();
		}
		return Constants.DELETE_SUCCESSFULLY_MSG;
	}
    
	@Override
	@Transactional(value = "transactionManager")
	public User registerNewUser(UserRegistrationForm userRegistrationForm)
			throws UserAlreadyExistAuthenticationException {
		// TODO Auto-generated method stub
		if (userRegistrationForm.getUserID() != null && userRepository.existsById(userRegistrationForm.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + userRegistrationForm.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(userRegistrationForm.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + userRegistrationForm.getEmail() + " already exist");
        }
        User user = buildUser(userRegistrationForm);
        Date now = Calendar.getInstance().getTime();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
       
        user = userRepository.save(user);
        userRepository.flush();
        return user;
	}
	
	private User buildUser(final UserRegistrationForm formDTO) {
        User user = new User();
        user.setDisplayName(formDTO.getDisplayName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName(Role.ROLE_USER));
        user.setRoles(roles);
        user.setProvider(formDTO.getSocialProvider().getProviderType());
        user.setActive(true);
        user.setProviderUserId(formDTO.getProviderUserId());
        return user;
    }

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	// hàm này thực thi và trả về thông tin user cho CustomOAth2UserService
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken,
			OidcUserInfo userInfo) {
		// TODO Auto-generated method stub
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        UserRegistrationForm userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findUserByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userDetails);
        }
 
        return LocalUser.create(user, attributes, idToken, userInfo);
	}
	
	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }
	
	private UserRegistrationForm toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return UserRegistrationForm.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
    }
	
}
