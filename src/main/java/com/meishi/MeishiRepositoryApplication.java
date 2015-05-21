package com.meishi;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories
public class MeishiRepositoryApplication extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "meishi";
	}

	@Override
	public Mongo mongo() throws Exception {
		Mongo mongo = new Mongo();
		mongo.setWriteConcern(WriteConcern.SAFE);
		return mongo;
	}

	@Override
	protected UserCredentials getUserCredentials() {
		return new UserCredentials("meishiUser", "123456");
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.meishi.repository";
	}
}
