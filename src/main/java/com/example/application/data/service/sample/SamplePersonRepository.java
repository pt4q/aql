package com.example.application.data.service.sample;

import com.example.application.data.entity.sample.SamplePerson;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, Integer> {

}