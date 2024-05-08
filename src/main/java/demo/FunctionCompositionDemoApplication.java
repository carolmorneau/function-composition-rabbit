/*
 * Copyright 2015-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo;

import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

@SpringBootApplication
public class FunctionCompositionDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(FunctionCompositionDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FunctionCompositionDemoApplication.class, args);
	}

	@Bean
	public Function<Message<List<?>>, Message<List<?>>> fn1() {
		return msg -> {
			logger.info("fn1 called");
			List<?> payloadList = msg.getPayload();
			// With issue-1112, the items within the list are lost during payload conversion
			logger.info("Message<List<?>> has list with {} elements. This is an issue as there should be a number of elements equals to the configured batchSize", payloadList.size());
			return msg;
		};
	}

	@Bean
	public Function<Message<List<?>>, Message<List<?>>> fn2() {
		return msg -> {
			logger.info("fn2 called");
			List<?> payloadList = msg.getPayload();
			// With issue-1112, the items within the list are lost during payload conversion
			logger.info("Message<List<?>> has list with {} elements. This is an issue as there should be a number of elements equals to the configured batchSize", payloadList.size());
			return msg;
		};
	}

}
