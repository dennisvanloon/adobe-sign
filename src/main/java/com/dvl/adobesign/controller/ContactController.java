package com.dvl.adobesign.controller;

import com.dvl.adobesign.config.AdobeSignConfig;
import com.dvl.adobesign.model.Contract;
import com.dvl.adobesign.service.ContractService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Controller
public class ContactController {

    private final ContractService contractService;

    private final AdobeSignConfig adobeSignConfig;

    public ContactController(ContractService contractService, AdobeSignConfig adobeSignConfig) {
        this.contractService = contractService;
        this.adobeSignConfig = adobeSignConfig;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("contracts", contractService.getContracts());
        model.addAttribute("message", adobeSignConfig.getMessage());
        return "index";
    }

    @PostMapping(value = "/contract", consumes = "multipart/form-data")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("signerOne") String signerOne, @RequestParam("signerTwo") String signerTwo) {
        contractService.storeContract(file, signerOne, signerTwo);
        return "redirect:/";
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String id) {
        Contract contract = contractService.getContract(id);

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentType(MediaType.valueOf(contract.getFileType()));
        respHeaders.setContentLength(contract.getData().length);
        respHeaders.setContentDispositionFormData("attachment", contract.getFileName());

        return new ResponseEntity<>(new InputStreamResource(new ByteArrayInputStream(contract.getData())), respHeaders, HttpStatus.OK);
    }
}
