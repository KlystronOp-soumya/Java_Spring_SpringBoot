package com.demo.batch.intfs;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;

public interface CustomJobRepository extends BatchConfigurer {

	JobRepository createJobRepository() throws Exception;
}
