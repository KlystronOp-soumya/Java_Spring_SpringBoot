package com.demo.documentapi.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DocumentErrorResponse {
	
	private String requestURLString;
	private String requestParameter;
	private LocalDate requestDate;
	private UUID traceId;
	private String statusCode;
	private String errorDescription;
	private List<String> errorFields;

}
