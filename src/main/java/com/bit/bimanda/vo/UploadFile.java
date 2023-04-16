package com.bit.bimanda.vo;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {            //File 처리용 dto
    private MultipartFile file;
    //private List<MultipartFile> file;

    public MultipartFile getFile() {
    	return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}