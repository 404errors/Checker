package com.synergix.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * processing unit
 * 1 log file processor will process a chunk of log files one by one.
 * Save the output in output
 * @author Kevin
 *
 */
public class LogFileProcessor implements Runnable{
	
	List<Path> logFiles = new ArrayList<>();
	static List<String> output;
	
	public void addFile(Path file) {
		this.logFiles.add(file);
	}

	@Override
	public void run() {
		for (Path filePath : this.logFiles) {
			LogFile _logFile = LogFile.initAndCheck(filePath);
			this.output.addAll(_logFile.getOutput());
		}
		
	}

}
