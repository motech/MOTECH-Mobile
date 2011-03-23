package org.motechproject.mobile.util;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class FileMerger {


    public static void main(String[] arguments) {
        if (arguments.length < 2) {
            return;
        }
        String directorySource = arguments[0];
        String destinationFile = arguments[1];
        List<String> fileList = getFileList(directorySource);
        mergeFiles(fileList, destinationFile, directorySource);
    }

    private static void mergeFiles(List<String> fileList, String destinationFile, String directorySource) {

        try {
            File destFile = new File(destinationFile);
            destFile.createNewFile();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(destFile)));
            Scanner scanner = new Scanner(new File(directorySource + File.separator + "Template.xml"));
            scanner.useDelimiter("\n");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.matches("</study>")) {
                    for (String fileName : fileList) {
                        String fileContent = getFileContent(directorySource + File.separator + fileName);
                        writer.write(fileContent);
                    }
                }
                writer.write(line + "\n");
            }
            writer.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileContent(String fileName) {
        StringBuilder builder = new StringBuilder();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            scanner.useDelimiter("\n");
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static List<String> getFileList
            (String
                    directorySource) {
        File directory = new File(directorySource);

        if (!directory.exists())
            return Collections.emptyList();

        List<String> fileList = Arrays.asList(directory.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.endsWith(".xml") && !name.contains("Template"))
                    return true;
                return false;
            }
        }));
        Collections.sort(fileList, String.CASE_INSENSITIVE_ORDER);
        return fileList;
    }
}
