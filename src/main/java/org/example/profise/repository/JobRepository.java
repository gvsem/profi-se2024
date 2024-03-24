package org.example.profise.repository;

import org.example.profise.model.Job;
import org.example.profise.model.Vm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
