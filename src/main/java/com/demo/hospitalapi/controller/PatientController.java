package com.demo.hospitalapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.hospitalapi.domain.Patients;
import com.demo.hospitalapi.exceptions.PatientRecordNotFoundException;
import com.demo.hospitalapi.exceptions.UpdateNotPossibleException;
import com.demo.hospitalapi.service.PatientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("patientController")
@RequestMapping(path = "/api")
public class PatientController {
	
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(PatientController.class) ;
	private final PatientService patientService;

	public PatientController(final PatientService patientService) {
		// TODO Auto-generated constructor stub
		this.patientService = patientService;
	}
	
	@ApiOperation(value = "Get a patient details by adhaar card number", notes = "Returns a object as per the id")
	@GetMapping(path = "/getPatientDetails" , produces = MediaType.APPLICATION_JSON_VALUE) //general authority
	public ResponseEntity<Map<String, Object>> getPatientDetails(@RequestParam(value = "adhaarCardNumber" , required = true) final String adhaarCardNumber) throws PatientRecordNotFoundException {
		Map<String, Object> responseMap ;
		try {
			responseMap = new HashMap<>() ;
			final Patients patientData = this.patientService.getSpecificPatientDetails(adhaarCardNumber) ;
				responseMap.put("data", patientData) ;
				responseMap.put("found", HttpStatus.FOUND) ;								
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage()) ;
			throw new PatientRecordNotFoundException() ;
		}
		return new ResponseEntity<Map<String,Object>>(responseMap, HttpStatus.ACCEPTED) ;
	}
	
	@GetMapping(path = "/fetchAllPatients", produces = MediaType.APPLICATION_JSON_VALUE) //authorize as admin only later
	public ResponseEntity<Map<String, List<Patients>>> getAllPatients() {
		Map<String , List<Patients>> allPatientRecordMap=null ;
		try {
			allPatientRecordMap = new HashMap<>() ;
			List<Patients> allPatientRecordsList = this.patientService.getAllPatientRecords();
			allPatientRecordMap.put("allPatientRecordsList", allPatientRecordsList) ;
						
		} catch (Exception e) { // TODO: handle exception
			LOGGER.error(e.getCause());
			return new ResponseEntity<Map<String, List<Patients>>>(allPatientRecordMap, HttpStatus.NOT_FOUND) ;
		}
		return new ResponseEntity<Map<String, List<Patients>>>(allPatientRecordMap, HttpStatus.FOUND) ;
	}

	@PostMapping(path = "/savePatientRecord", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> savePatientRecord(@RequestBody Patients patient, BindingResult result) {
		 Map<String, String> responseMap = null ;

		try {
			responseMap = new HashMap<>() ;
			this.patientService.enterPatientRecord(patient);
			responseMap.put("status", "saved");
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.CREATED) ;

		} catch (Exception e) {
			// TODO: handle exception
			
			LOGGER.error(e.getMessage());
			responseMap.put("status", "not saved");
			
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.BAD_REQUEST) ;
			
		}
		
	}
	
	@RequestMapping(path = "/deletePatientRecord/{adhaarNumber}" , produces = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.DELETE)
	public ResponseEntity<Map<String,String>> deletePatientRecord(@PathVariable(value = "adhaarNumber" , required = true) final String adhaarNum )
	{
		Map<String, String> responseMap = null ;
		try {
			 responseMap = new HashMap<>() ;
			 this.patientService.deletePatientRecord(adhaarNum) ;
			 
			 responseMap.put("status", "deleted") ;
			 
			 return new ResponseEntity<Map<String,String>>(responseMap , HttpStatus.OK) ;
			 
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Could not delete record") ;
			 responseMap.put("status", "not deleted") ;
			return new ResponseEntity<Map<String,String>>(responseMap , HttpStatus.BAD_REQUEST) ;
		}
	}
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_FORM_URLENCODED_VALUE } , path = "/upadtePatientRecord", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,String>> upadatePatientRecord(@RequestBody final Patients patients , BindingResult result) throws UpdateNotPossibleException
	{
		Map<String, String> responseMap = null ;
		try {
			 responseMap = new HashMap<>() ;
			 this.patientService.updatePatientRecord(patients) ;
			 
			 responseMap.put("status", "updated") ;
			 
			 return new ResponseEntity<Map<String,String>>(responseMap , HttpStatus.OK) ;
		}
		catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Error has occurred while record updation") ;
			throw new UpdateNotPossibleException(e.getMessage() , e.getCause()) ;
		}
	}
}
