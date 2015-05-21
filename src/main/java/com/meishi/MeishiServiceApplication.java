package com.meishi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.meishi.service")
@Import(MeishiRepositoryApplication.class)
public class MeishiServiceApplication {

}
