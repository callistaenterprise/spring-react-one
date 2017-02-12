/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.callista.ml.springreactone.reactivemongodb;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * Simple configuration that registers a {@link LoggingEventListener} to demonstrate mapping behavior when streaming
 * data.
 *
 * @author Mark Paluch
 */
//@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
//@EnableReactiveMongoRepositories
//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@RequiredArgsConstructor
class ApplicationConfiguration extends AbstractReactiveMongoConfiguration {

	private final MongoProperties mongoProperties;
//
//	@Bean
//	public LoggingEventListener mongoEventListener() {
//		return new LoggingEventListener();
//	}
//
//	@Override
//	@Bean
//	@DependsOn("embeddedMongoServer")
	public MongoClient mongoClient() {
		return MongoClients.create(String.format("mongodb://localhost:%d", mongoProperties.getPort()));
	}
//
	@Override
	protected String getDatabaseName() {
		return "reactive";
	}
}
