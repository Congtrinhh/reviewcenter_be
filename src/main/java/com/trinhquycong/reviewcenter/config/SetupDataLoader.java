package com.trinhquycong.reviewcenter.config;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.trinhquycong.reviewcenter.entity.Category;
import com.trinhquycong.reviewcenter.entity.Role;
import com.trinhquycong.reviewcenter.repo.CategoryRepo;
import com.trinhquycong.reviewcenter.repo.RoleRepo;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;
	
	@Autowired
	private RoleRepo roleRepository;
	
	@Autowired 
	private CategoryRepo categoryRepository;
	
	@Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
 
        //Create initial things
        
//        createCategoryIfNotFound("tiếng Anh");
//        createCategoryIfNotFound("lập trình");
        
 
        alreadySetup = true;
    }
 
	@Transactional
    private void createCategoryIfNotFound(String name) {
		Category category = categoryRepository.findByName(name);
		
		if (category!=null)	return;
		
		category = new Category();
		
		category.setName(name);
		category.setCreatedAt(Calendar.getInstance().getTime());
		categoryRepository.save(category);
	}

	@Transactional
    private final Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setCreatedAt(Calendar.getInstance().getTime());
        }
        role = roleRepository.save(role);
        return role;
    }
}
