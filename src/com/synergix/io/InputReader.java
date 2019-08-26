package com.synergix.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {
	
	/**
	 * @param scanner
	 * @return a sorted list of log files, in ascending order of last modified date time
	 */
	public static List<Path> getLogFileList(Scanner scanner) {
		System.out.println("Please enter full path to log folder, or just leave it empy to use current folder:"
				+ System.lineSeparator());
		String userInputLogFolderPath = scanner.nextLine();
		Path workingDirectory = Paths.get(".").toAbsolutePath();
		// scanner.close();
		if (userInputLogFolderPath != null && !userInputLogFolderPath.trim().isEmpty()) {
			workingDirectory = Paths.get(userInputLogFolderPath);
		}
		List<Path> listOfLogFiles = new ArrayList<>();
		try {
			Files.walkFileTree(workingDirectory, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
					if (Files.isRegularFile(file) && isTextFile(file)) {
						listOfLogFiles.add(file);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		listOfLogFiles.sort((path1, path2) -> {
			try {
				return Files.getLastModifiedTime(path1).compareTo(Files.getLastModifiedTime(path2));
			} catch (IOException e) {
				System.out.println("Cannot compare " + path1 + " and " + path2);
				return 0;
			}
		});
		return listOfLogFiles;
	}
	
	public static List<List<String>> getPatterns(Scanner scanner) {
		System.out.println("Enter string to check (AND condition separated by comma, OR condition separated by ||):");
		String pattern = scanner.nextLine();
		List<List<String>> patternList = new ArrayList<>();
		List<String> conditionList = new ArrayList<> (Arrays.asList(pattern.split(Pattern.quote("||"))));
		conditionList.removeIf(item -> item.trim().isEmpty());
		for (String condition : conditionList) {
			List<String> keyWords = Arrays.asList(condition.split(","));
			keyWords.removeIf(item -> item.trim().isEmpty());
			patternList.add(keyWords);
		}
		return patternList;
	}
	
	private static boolean notATextFile(Path filePath) {
		String fileName = filePath.getFileName().toString();
		return fileName.endsWith("rar") || fileName.endsWith("7z") || fileName.endsWith("class") || fileName.endsWith("doc");
	}
	
	private static boolean isTextFile(Path filePath) {
		return !notATextFile(filePath);
	}
	
	public static String getCurrentDateTimeString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM_HH-mm-ss");
		return dtf.format(LocalDateTime.now());
	}
}
