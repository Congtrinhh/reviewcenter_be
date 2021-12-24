package com.trinhquycong.reviewcenter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class ReviewcenterApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ReviewcenterApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReviewcenterApplication.class);
    }
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

/**

 * * * What to take away after implementing OAuth2 login
 * enum in deep
 * custom validator
 * bean bị định nghĩa sai có thể gây ra BeanDefinitionStoreException
 * spring security: hasRole("ROLE_ADMIN") = hasAuthority("ADMIN")
 * put vs patch - khi update rating
 * lọc sản phẩm: dùng jpa criteria api filter: 1: repository extends jpaspecificationexecutor; 2: create dto/search class; 3: create specs class which implements specification interface; 4: in controller, initialize specification class in step 3 and call findAll with pageable
 * cần định nghĩa bean trước khi dùng, dù đã include trong file pom. ví dụ: modelmapper 
*/