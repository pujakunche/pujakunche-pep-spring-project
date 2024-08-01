package com.example.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepositoryImplementation<Message, Integer>{

}
