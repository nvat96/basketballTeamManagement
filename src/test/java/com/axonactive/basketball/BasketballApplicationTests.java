package com.axonactive.basketball;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BasketballApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void generatePassword(){
		String encrtypedPassWord = new BCryptPasswordEncoder().encode("1234");
		System.out.println(encrtypedPassWord);
	}

}
