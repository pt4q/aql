package com.example.application.sample.service.sample;

import com.example.application.sample.entity.sample.SamplePerson;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, Integer> {

}