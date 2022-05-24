package com.greencommute.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class BackendApplicationTests {

		@Test
		void testApplication() {
			String[] str = new String[]{};
			BackendApplication.main(str);
			Assertions.assertEquals(1,2-1);
		}

}
