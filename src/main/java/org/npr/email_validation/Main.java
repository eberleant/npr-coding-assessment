package org.npr.email_validation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;

public class Main {
    /**
     * Main method takes in file(s), validates emails, sorts emails by domain, and outputs result to file
     * @param args string array of input filenames
     * @throws IOException if an I/O exception has occured (eg, file not found)
     */
    public static void main(String[] args) throws IOException {
        for (String inputFilename : args) {
            String inputFileContents = Files.readString(Paths.get(inputFilename), StandardCharsets.US_ASCII);
            String outputEmails = Email.filterAndSortList(inputFileContents);
            String outputFilename = inputFilename.substring(0,
                    inputFilename.lastIndexOf(File.separator) + 1) + FilenameUtils.getBaseName(inputFilename) + "_output.txt";
            Files.writeString(Paths.get(outputFilename), outputEmails);
            System.out.println("Success! Emails from " + inputFilename + " can be found in " + outputFilename + ".");
        }
    }
}
