package com.company.controller;

import com.company.payload.TokenDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/documents")
public class StudentDocuments {
    @Autowired
    private RestTemplate restTemplate;

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
            String fileroot=null;
            for (JsonNode jsonNode : dataroot) {
              fileroot  = jsonNode.get("file").asText();
            }
            ResponseEntity<byte[]> response1 = restTemplate.exchange(fileroot, HttpMethod.GET, entity, byte[].class);
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

    @PostMapping("/uquvVaraqasi")
    public ResponseEntity<?> downloadStudySheet(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK){
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            ArrayList<String> arrayList=new ArrayList<>();
            for (JsonNode jsonNode : dataroot) {
                arrayList.add(jsonNode.get("file").asText());
            }
            ResponseEntity<byte[]> response1 = restTemplate.exchange(arrayList.get(0), HttpMethod.GET, entity, byte[].class);
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

        if (response.getStatusCode() == HttpStatus.OK){
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            ArrayList<String> arrayList=new ArrayList<>();
            for (JsonNode jsonNode : dataroot) {
                arrayList.add(jsonNode.get("file").asText());
            }
            ResponseEntity<byte[]> response1 = restTemplate.exchange(arrayList.get(1), HttpMethod.GET, entity, byte[].class);
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

    @PostMapping("/buyruq")
    public ResponseEntity<?> downloadDecree(@RequestBody TokenDto tokenDto) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+tokenDto.getToken()); // Set appropriate authorization headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(tokenDto.getLink(), HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK){
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode dataroot = rootNode.get("data");
            ArrayList<String> arrayList=new ArrayList<>();
            for (JsonNode jsonNode : dataroot) {
                arrayList.add(jsonNode.get("file").asText());
            }
            ResponseEntity<byte[]> response1 = restTemplate.exchange(arrayList.get(3), HttpMethod.GET, entity, byte[].class);
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

}