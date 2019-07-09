package com.goyarce.api;

// WORKING VERSION

import com.mongodb.Block;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.SslSettings.Builder;
import com.mongodb.connection.netty.NettyStreamFactoryFactory;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PreDestroy;

@SpringBootApplication(scanBasePackages = {"com.goyarce"})
@EnableMongoRepositories(basePackages = {"com.goyarce.api.repositories.mongo"})
@EnableJpaRepositories(basePackages = {"com.goyarce.api.repositories.mysql"})
@EnableSwagger2
public class ApiApplication {
	private NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	@Bean
	public MongoClientSettingsBuilderCustomizer sslCustomizer() {

		Block<Builder> sslSettingsBlock = new Block<SslSettings.Builder>() {
			@Override
			public void apply(Builder t) {
				t.applySettings(SslSettings.builder()
						.enabled(true)
						.invalidHostNameAllowed(true)
						.build());
			}
		};

		return clientSettingsBuilder -> clientSettingsBuilder
				.applyToSslSettings(sslSettingsBlock)
				.streamFactoryFactory(NettyStreamFactoryFactory.builder()
						.eventLoopGroup(eventLoopGroup).build());
	}

	@PreDestroy
	public void shutDownEventLoopGroup() {
		eventLoopGroup.shutdownGracefully();
	}

}
