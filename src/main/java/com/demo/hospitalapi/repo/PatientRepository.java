package com.demo.hospitalapi.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.hospitalapi.domain.Patients;
import com.demo.hospitalapi.repo.intf.CustomPatientCRUDRepository;

public interface PatientRepository extends CrudRepository<Patients, Long> , CustomPatientCRUDRepository {
	
	@Override
	Patients save(Patients patient);
	
	@Modifying
	@Query(name = "deletePatientRecord" , value = "DELETE FROM PATIENTS WHERE ADHAAR_NUMBER= :adhaarNum" ,nativeQuery = true)
	void deleteByPatientAdhaarCardNum(@Param("adhaarNum") String patientAdhaarCardNum);
	
	@Transactional(readOnly = true)
	Patients findByPatientAdhaarCardNum(String patientAdhaarCardNum);
	
	@Modifying
	@Query(name="updatePatientRecord" , value = "UPDATE PATIENTS SET ADHAAR_NUMBER= :adhaarNum , PATIENT_NAME= :patientName ,BED_NO=:bedNo ,ROOM_NO=:roomNo ,MEDICAL_HISTORY= :medHistory"
			+ " WHERE ADHAAR_NUMBER= :adhaarNum" , nativeQuery = true)
	void updateByAdhaarCardNum(@Param("adhaarNum") String patientAdhaarCardNum,@Param("patientName")   String patientName,@Param("bedNo")   String bedNo,@Param("roomNo")   String roomNo,
		@Param("medHistory")	String medicalHistory);
}
