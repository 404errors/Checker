package com.synergix.checker;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.synergix.io.InputReader;
import com.synergix.model.LogFileProcessor;

public class LogChecker {
	
	private List<Path> logFiles;
	public static List<List<String>> conditionList;
	private List<String> exclusionKeyWords;
//	synchronized private 
	
	public LogChecker(List<Path> logFiles, List<String> keyWords, List<String> exclutions) {
		this.logFiles = logFiles;
		this.exclusionKeyWords = exclutions;
	}
	
	
	public void check() {
		
	}
	
	//TODO: multi threading
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Path> logFiles = InputReader.getLogFileList(scanner);
		conditionList = InputReader.getPatterns(scanner);
		scanner.close();
		String outputFolderString = Paths.get(".").toAbsolutePath().toString();
		String outputFileName = InputReader.getCurrentDateTimeString() + "-output.txt";
		Path outputFile = Paths.get(outputFolderString.substring(0, outputFolderString.length() - 1) + outputFileName);
		int numberOfProcessor = (int)Math.ceil(logFiles.size() / 20.0);
		List<LogFileProcessor> fileProcessors = new ArrayList<>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int processorCounter = 0; processorCounter < numberOfProcessor; ++processorCounter) {
			LogFileProcessor logFileProcessor = new LogFileProcessor();
			for (int fileCounter = processorCounter * 20; fileCounter < Math.min(logFiles.size(), (processorCounter +1) * 20); ++fileCounter) {
				logFileProcessor.addFile(logFiles.get(fileCounter));
			}
			fileProcessors.add(logFileProcessor);
			executorService.execute(logFileProcessor);
		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(10,  TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.out.println("Checking all files done");
		}
		// now write result to output file
		
		
		
	}

}
