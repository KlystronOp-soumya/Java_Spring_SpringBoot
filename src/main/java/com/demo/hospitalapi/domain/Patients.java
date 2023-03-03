package com.demo.hospitalapi.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name = "PATIENTS")
@DynamicInsert
@DynamicUpdate

public class Patients implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@Column(name = "ADHAAR_NUMBER")
	private String patientAdhaarCardNum ;
	
	@Column(name = "PATIENT_NAME")
	private String patientName ;
	
	@Column(name = "BED_NO")
	private String bedNo ;
	
	@Column(name = "ROOM_NO")
	private String roomNo ;
	
	@Column(name = "MEDICAL_HISTORY")
	private String medicalHistory ;
	
	
	
	public Patients() {
		super();
	}
	public Patients(String patientAdhaarCardNum, String patientName, String bedNo, String roomNo,
			String medicalHistory) {
		super();
		this.patientAdhaarCardNum = patientAdhaarCardNum;
		this.patientName = patientName;
		this.bedNo = bedNo;
		this.roomNo = roomNo;
		this.medicalHistory = medicalHistory;
	}
	public String getPatientAdhaarCardNum() {
		return patientAdhaarCardNum;
	}
	public void setPatientAdhaarCardNum(String patientAdhaarCardNum) {
		this.patientAdhaarCardNum = patientAdhaarCardNum;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bedNo, id, medicalHistory, patientAdhaarCardNum, patientName, roomNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patients other = (Patients) obj;
		return Objects.equals(bedNo, other.bedNo) && Objects.equals(id, other.id)
				&& Objects.equals(medicalHistory, other.medicalHistory)
				&& Objects.equals(patientAdhaarCardNum, other.patientAdhaarCardNum)
				&& Objects.equals(patientName, other.patientName) && Objects.equals(roomNo, other.roomNo);
	}
	
	

}
