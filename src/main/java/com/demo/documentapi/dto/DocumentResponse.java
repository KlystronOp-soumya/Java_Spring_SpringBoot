package com.demo.documentapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {

	private String description;
    private String code;
    private String type;
    private List<String> tags;
    private String downloadLink;
}
