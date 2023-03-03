package com.demo.hospitalapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.demo.hospitalapi.domain.Patients;
import com.demo.hospitalapi.exceptions.HospitalApiException;
import com.demo.hospitalapi.repo.PatientRepository;



public class PatientService {
	
	private static final Logger LOGGER= LogManager.getLogger(PatientService.class) ;
 	private PatientRepository patientRepository ;
	
 	//@PersistenceContext(unitName = "HospitalApiPersistentUnit")
 	//private EntityManager entityManager ;
	
	public PatientService(final PatientRepository patientRepository)
	{
		this.patientRepository = patientRepository ;
	}
	
	/*
	 * Method to save an patient record inside Database
	 *  
	 * @param Patient model
	 * 
	 * @return saved Patient model
	 * 
	 * */
	@org.springframework.transaction.annotation.Transactional
	public void enterPatientRecord(final Patients patient) throws Exception
	{
		this.patientRepository.save(patient) ;
		
		LOGGER.info("Record was saved");
	}
	
	/*
	 *  Method to fetch all the patient records
	 * 
	 *  @param void
	 *  
	 *  @return List<Patient>
	 *  
	 * */
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	@Cacheable(value = "cacheAllPatientRecords")
	public List<Patients> getAllPatientRecords() {
		// TODO Auto-generated method stub
		List<Patients> patientRecordsList = new ArrayList<>() ;
		Iterable<Patients> patientIterable = this.patientRepository.findAll() ;
		patientIterable.forEach((eachRecord) -> patientRecordsList.add(eachRecord));
		LOGGER.info("All records for patients were fetched");
		if(patientRecordsList.isEmpty())
		{
			LOGGER.error("Empty record list");
			throw new NullPointerException("No DataFound") ;
		}
			
		return patientRecordsList ;
	}
	
	@org.springframework.transaction.annotation.Transactional
	@CacheEvict(cacheNames = "patients")
	public void deletePatientRecord(final String adhaarNum) throws HospitalApiException
	{
		LOGGER.info("Trying to delete a record");
		try {
			if(this.patientRepository.existsById(Long.valueOf(adhaarNum)))
			{
			this.patientRepository.deleteByPatientAdhaarCardNum(adhaarNum);
			LOGGER.info("Record was deleted successfully");
			}
			else {
				throw  new RuntimeException("Record not found") ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Record not found :: " + e.getMessage());
			throw new HospitalApiException("Delete was not possible", e.getCause()) ;
		}
					
	}
	
	@org.springframework.transaction.annotation.Transactional
	@CachePut(cacheNames = "patients" , key = "#patient.patientAdhaarCardNum")
	public void updatePatientRecord(final Patients patient) throws HospitalApiException
	{
		LOGGER.info("Trying to update a record");
		try {
			
			if(this.patientRepository.existsById(Long.valueOf(patient.getPatientAdhaarCardNum()))) 
			{
				this.patientRepository.save(patient) ;
				LOGGER.info("Record updated succesfully");
			}
			else {
				throw new RuntimeException("Record does not exist") ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Can not update data: " + e.getMessage());
			throw new HospitalApiException("Update was not possible", e.getCause()) ;
		}
	}
	
	@Cacheable(cacheNames = "patients" , key = "#adhaarCardNumber")
	@Transactional(readOnly = true)
	public Patients getSpecificPatientDetails(final String adhaarCardNumber) {
		// TODO Auto-generated method stub
		Optional<Patients> patientsRecordOptional = Optional.ofNullable(this.patientRepository.findByPatientAdhaarCardNum(adhaarCardNumber)) ;
		if(patientsRecordOptional.isPresent())
			return patientsRecordOptional.get() ;
		else {
			throw new NullPointerException("Data not found") ;
		}
	}
	
	
	
}
