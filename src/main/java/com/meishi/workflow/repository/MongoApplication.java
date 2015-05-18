package com.meishi.workflow.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan
@EnableMongoRepositories
public class MongoApplication extends AbstractMongoConfiguration {

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

}
