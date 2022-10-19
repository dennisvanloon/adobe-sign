package com.dvl.adobesign.controller;

import com.dvl.adobesign.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ContactController {

    private final ContractService contractService;

    public ContactController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("contracts", contractService.getContracts());
        return "index";
    }

    @PostMapping(value = "/contract", consumes = "multipart/form-data")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        contractService.storeContract(file);
        return "redirect:/";
    }
}
