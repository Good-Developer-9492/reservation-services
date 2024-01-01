package com.gd.reservationservices;

import com.gd.reservationservices.config.TestContainerConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(TestContainerConfig.class)
@Transactional
@SpringBootTest
public class IntegrationTestSupport {

}
