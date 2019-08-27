package com.synergix.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.synergix.checker.LogChecker;

public class LogFile {
	private Path logFile;
	public LogFile(Path file) {
		this.logFile = file;
	}
	
	public static List<String> check(Path file) throws IOException{
		System.out.println("Checking file: " + file.getFileName());
		List<String> output = new ArrayList<>();
		try (BufferedReader bufferReader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
			String line = null;
			int lineNumber = 0;
			while ((line = bufferReader.readLine()) != null) {
				
			}
		}
		return output;
	}
	
	/*
	 for (List<String> patternList : LogChecker.conditionList) {
			
		}
		LogFile logFile = new LogFile(file);
		try {
			logFile.check(null, null);
		} catch (IOException e) {
			System.out.println("Error checking file " + logFile.toString());
		}
		
		return null;
	 */
	private static void check(List<String> patternList, Path outputFile) throws IOException {
		// we will be using explicitly charset ISO_8859_1 to avoid
		// java.nio.charset.MalformedInputException 'cause ISO_8859_1 is an
		// all-inclusive charset
		try (BufferedReader bufferReader = Files.newBufferedReader(this.logFile, StandardCharsets.ISO_8859_1)) {
			String line = null;
			int lineNumber = 0;
			boolean firstLineInFileFound = true;
			while ((line = bufferReader.readLine()) != null) {
				++lineNumber;
				boolean lineContainsAllPattern = true;
				for (String pattern : patternList) {
					if (!line.toUpperCase().contains(pattern.toUpperCase())) {
						lineContainsAllPattern = false;
						break;
					}
				}
				if (lineContainsAllPattern) {
					//output.add(formatOutputLine(line, lineNumber));
				}
			}
//			if (fileContainsPattern) {
//				//bufferedWriter.write(System.lineSeparator() + System.lineSeparator());
//			}
		}
	}

}
