package com.fresco.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "hospitalController")
@RequestMapping(path = "/test")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@GetMapping(path = "/hospitals/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Hospital getHospital(@PathVariable("id") int id) throws Exception {
		Hospital hospital = this.hospitalService.getHospital(id);
		return hospital;
	}

	public @ResponseBody List<Hospital> getAllHospitals() throws Exception {
		return this.hospitalService.getAllHospitals();
	}

	@PostMapping(path = "/hospitals")
	public ResponseEntity<String> addHospital(@RequestBody Hospital hospital) {
		this.hospitalService.addHospital(hospital);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(path = "/hospitals")
	public ResponseEntity<String> updateHospital(@RequestBody Hospital hospital) {

		this.hospitalService.updateHospital(hospital);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/hospitals")
	public ResponseEntity<String> deleteHospital(@RequestBody Hospital hospital) {

		this.hospitalService.deleteHospital(hospital);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
