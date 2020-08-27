package com.example.akka.boot.actor;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.akka.boot.config.Actor;
import com.example.akka.boot.service.MessageService;

import akka.actor.AbstractActor;

@Actor
public class TestActor extends AbstractActor {

    @Autowired
    private MessageService messageService;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,messageService::print)
                .build();
    }
}
