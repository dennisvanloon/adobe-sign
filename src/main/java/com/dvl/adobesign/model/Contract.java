package com.dvl.adobesign.model;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
public class Contract {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    private String signerOne;

    private String signerTwo;

    public Contract(String fileName, String fileType, byte[] data, String signerOne, String signerTwo) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.signerOne = signerOne;
        this.signerTwo = signerTwo;
    }

    public Contract() {

    }
}
