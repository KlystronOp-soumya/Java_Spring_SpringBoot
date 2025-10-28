package com.demo.documentapi.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.documentapi.dto.DocumentResponse;

@RestController("documentController")
@RequestMapping("/api/v1")
public class DocumentController {

	/**
     * Endpoint 1: Get Document Metadata (JSON)
     * This returns the document details and a dynamically generated download link.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getDocumentDetails(@PathVariable String id) {
        // --- Mock Data ---
        // In a real app, you would fetch this data from a database based on the 'id'
        String description = "This is a sample PDF document for " + id;
        String code = "DOC-" + id;
        String type = "PDF";
        List<String> tags = Arrays.asList("sample", "pdf", "java");
        // --- End Mock Data ---

        // Dynamically build the download URL
        // This links to our second endpoint: /api/documents/{id}/download
        String downloadUrl = ServletUriComponentsBuilder  
                .fromCurrentContextPath() // Gets the base URL (e.g., http://localhost:8080)
                .path("/api/v1/")
                .path(id)
                .path("/download")
                .toUriString();

        // Create the response object
        DocumentResponse response = new DocumentResponse(
                description,
                code,
                type,
                tags,
                downloadUrl
        );

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint 2: Download the PDF file
     * This serves the actual file bytes.
     * @throws NoResourceFoundException 
     */
    @GetMapping(path = "/{id}/download", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<Resource> downloadDocument(@PathVariable String id) throws Exception {
        // In a real app, you'd fetch the file based on the 'id'.
        // For this example, we'll serve a static file from the 'resources' folder.
        // **IMPORTANT:** You must add a file named 'sample.pdf' to 'src/main/resources/files/'
        
        String filename = "sample.pdf";
        Resource resource = new ClassPathResource("files/" + filename);

        if (!resource.exists()) {
            throw new NoResourceFoundException(HttpMethod.GET, filename);
        }

        // Set HTTP headers for the file download
        HttpHeaders headers = new HttpHeaders();
        
        // This tells the browser to treat the response as a file to be downloaded
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        
        // Note: If you want the browser to *try* to display the PDF instead of downloading,
        // change "attachment" to "inline".
        // headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
