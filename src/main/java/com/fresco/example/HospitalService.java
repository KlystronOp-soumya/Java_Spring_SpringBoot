package com.fresco.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "hospitalService")
@EnableTransactionManagement
public class HospitalService {

	@Autowired
	private HospitalRepo hospitalRepository;

	@Transactional
	public List<Hospital> getAllHospitals() {
		return (List<Hospital>) this.hospitalRepository.findAll();
	}

	@Transactional
	public Hospital getHospital(int id) {
		Optional<Hospital> hospital = this.hospitalRepository.findById(id);
		if (hospital.isPresent())
			return hospital.get();
		return null;
	}

	@Transactional
	public void addHospital(Hospital hospital) {
		this.hospitalRepository.save(hospital);
	}

	@Transactional
	public void updateHospital(Hospital hospital) {
		this.hospitalRepository.save(hospital);
	}

	@Transactional
	public void deleteHospital(Hospital hospital) {
		this.hospitalRepository.delete(hospital);
	}
}
