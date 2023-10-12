package com.company.controller;

import com.company.entity.AttachmentEntity;
import com.company.payload.TokenDto;
import com.company.repository.AttachmentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
//@CrossOrigin(origins = "*")
public class StudentDocuments {
    @Autowired
    private RestTemplate restTemplate;

    private final Hashids hashids;

    @Value("${upload.server}")
    private String serverPath;
    
    @Autowired
    private AttachmentRepository attachmentRepository;

    public StudentDocuments() {
        this.hashids = new Hashids(getClass().getName(),6);
    }

    @PostMapping("/uquvVaraqasi")
    public ResponseEntity<?> downloadstudySheet(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            JsonNode jsonNode = dataroot.get(0);
            String file = jsonNode.get("file").asText();
            ResponseEntity<byte[]> response1 = restTemplate.exchange(file, HttpMethod.GET, entity, byte[].class);
            if (response1.getStatusCode().is2xxSuccessful()){
                byte[] fileBytes = response1.getBody();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "converted.pdf");
                // PDF faylni ResponseEntity orqali qaytarish
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            }else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
            }
        }else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
        }
    }


    @PostMapping("/malumotnoma")
    public ResponseEntity<?> downloadReference(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            JsonNode jsonNode = dataroot.get(3);
            String file = jsonNode.get("file").asText();
            ResponseEntity<byte[]> response1 = restTemplate.exchange(file, HttpMethod.GET, entity, byte[].class);
            if (response1.getStatusCode().is2xxSuccessful()){
                byte[] fileBytes = response1.getBody();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "converted.pdf");
                // PDF faylni ResponseEntity orqali qaytarish
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            }else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
            }
        }else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
        }
    }


    @PostMapping("/ReytingDaftari")
    public ResponseEntity<?> downloadRatingBook(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            JsonNode jsonNode = dataroot.get(1);
            String file = jsonNode.get("file").asText();
            ResponseEntity<byte[]> response1 = restTemplate.exchange(file, HttpMethod.GET, entity, byte[].class);
            if (response1.getStatusCode().is2xxSuccessful()){
                byte[] fileBytes = response1.getBody();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "converted.pdf");
                // PDF faylni ResponseEntity orqali qaytarish
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            }else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
            }
        }else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
        }
    }


    @PostMapping("/shartnoma")
    public ResponseEntity<?> downloadContract(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            JsonNode jsonNode = dataroot.get(2);
            String file = jsonNode.get("file").asText();
            ResponseEntity<byte[]> response1 = restTemplate.exchange(file, HttpMethod.GET, entity, byte[].class);
            if (response1.getStatusCode().is2xxSuccessful()){
                byte[] fileBytes = response1.getBody();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "converted.pdf");
                // PDF faylni ResponseEntity orqali qaytarish
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            }else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
            }
        }else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
        }
    }


    @PostMapping("/buyruq")
    public ResponseEntity<?> downloadCommand(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            JsonNode jsonNode = dataroot.get(4);
            String file = jsonNode.get("file").asText();
            ResponseEntity<byte[]> response1 = restTemplate.exchange(file, HttpMethod.GET, entity, byte[].class);
            if (response1.getStatusCode().is2xxSuccessful()){
                byte[] fileBytes = response1.getBody();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "converted.pdf");
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            }else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
            }
        }else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
        }
    }


    @PostMapping("/malumotnoma-save")
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> downloadsaveDatabese(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            JsonNode jsonNode = dataroot.get(3);
            String name = jsonNode.get("name").asText();
            String file = jsonNode.get("file").asText();
            ResponseEntity<byte[]> response1 = restTemplate.exchange(file, HttpMethod.GET, entity, byte[].class);
            if (response1.getStatusCode().is2xxSuccessful()){
                byte[] fileBytes = response1.getBody();
                AttachmentEntity attachment=new AttachmentEntity();
                attachment.setFileSize((long) fileBytes.length);
                attachment.setExtension("pdf");
                attachment.setContentType("application/pdf");
                attachment.setOrginalName(name);
                AttachmentEntity attachmentEntity = attachmentRepository.save(attachment);
                attachment.setHashId(hashids.encode(attachmentEntity.getId()));
                attachmentRepository.save(attachment);

                Path path = Path.of(this.serverPath+"/"+attachment.getHashId()+"."+attachment.getExtension());
                Files.write(path, fileBytes);
                File file1 = new File(path.toFile().toString());
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file1));


                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\""+ UriEncoder.encode(attachmentEntity.getOrginalName()))
                        .contentType(MediaType.parseMediaType(attachmentEntity.getContentType()))
                        .contentLength(attachmentEntity.getFileSize())
                        .body(new FileUrlResource(String.valueOf(path)));
            }else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
            }
        }else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to upload files from the source server");
        }
    }


}
