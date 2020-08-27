package com.example.akka.boot.config;

import org.springframework.context.ApplicationContext;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;

public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {
	private static SpringExtension instance = new SpringExtension();

	@Override
	public SpringExt createExtension(ExtendedActorSystem system) {
		return new SpringExt();
	}

	public static SpringExtension getInstance() {
		return instance;
	}

	public static class SpringExt implements Extension {

		private static ApplicationContext applicationContext;

		public void initialize(ApplicationContext applicationContext) {
			SpringExt.applicationContext = applicationContext;
		}

		Props props(Class<? extends akka.actor.Actor> actorBeanClass) {
			return Props.create(SpringActorProducer.class, applicationContext, actorBeanClass);
		}

		Props props(Class<? extends akka.actor.Actor> actorBeanClass, Object... parameters) {
			return Props.create(SpringActorProducer.class, applicationContext, actorBeanClass, parameters);
		}
	}
}
