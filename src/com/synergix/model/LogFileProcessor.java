package com.synergix.model;

import java.nio.file.Path;
import java.util.List;

public class LogFileProcessor implements Runnable{
	
	List<Path> logFiles;
	
	public void addFile(Path file) {
		this.logFiles.add(file);
	}

	@Override
	public void run() {
		for (Path filePath : this.logFiles) {
			
		}
		
	}

}
