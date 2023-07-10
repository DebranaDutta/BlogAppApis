package com.springboot.blog.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {
	String UploadImage(String path, MultipartFile file) throws IOException;

	InputStream GetResource(String path, String fileName) throws FileNotFoundException;
}
