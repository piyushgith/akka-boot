package com.example.akka.boot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.akka.boot.actor.TestActor;
import com.example.akka.boot.config.SpringExtension;
import com.example.akka.boot.config.SpringProps;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@SpringBootApplication
public class AkkaBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkkaBootApplication.class, args);
	}

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	void init() {
		ActorSystem system = ActorSystem.create("actor-system", ConfigFactory.load());
		SpringExtension.getInstance().get(system).initialize(context);

		ActorRef testActor = system.actorOf(SpringProps.create(system, TestActor.class));

		testActor.tell("hello world", ActorRef.noSender());
		
		testActor.tell("hello piyush", ActorRef.noSender());
	}
}
