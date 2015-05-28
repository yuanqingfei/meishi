package com.meishi.test.repository.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories
public class MeishiRepositoryApplicationForTest extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "meishiTest";
	}

	@Override
	public Mongo mongo() throws Exception {
		Mongo mongo = new Mongo();
		mongo.setWriteConcern(WriteConcern.SAFE);
		return mongo;
	}

	@Override
	protected UserCredentials getUserCredentials() {
		return new UserCredentials("meishiTestUser", "654321");
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.meishi.repository";
	}
}
