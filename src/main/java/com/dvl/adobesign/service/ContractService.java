package com.dvl.adobesign.service;

import com.dvl.adobesign.model.Contract;
import com.dvl.adobesign.repository.ContractRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract storeContract(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new IllegalStateException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Contract contract = new Contract(fileName, file.getContentType(), file.getBytes());
            return contractRepository.save(contract);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Contract getContract(String contractId) {
        return contractRepository.findById(contractId)
                .orElseThrow(() -> new IllegalStateException("Contract not found with id " + contractId));
    }

    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }
}
