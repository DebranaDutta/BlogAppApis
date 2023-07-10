package com.springboot.blog.Services.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.blog.Services.FileService;
import com.springboot.blog.Utils.RandomIdGenerator;

@Component
public class FileServiceImpl implements FileService {

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {

		// File Name
		String name = file.getOriginalFilename();

		// Random name generate file
		String randomId = RandomIdGenerator.generateUUID();
		String fileName1 = randomId.concat(name).substring(name.lastIndexOf("."));

		// Full Path
		String filePath = path + File.separator + fileName1;

		// Create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// File Copy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;
	}

	@Override
	public InputStream GetResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

}
