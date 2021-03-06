package org.npr.email_validation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;

public class Main {
    public static void main(String[] args) {
        for (String filename : args) {
            // read file
            String fileContents;
            try {
                fileContents = Files.readString(Paths.get(filename), StandardCharsets.US_ASCII);
            } catch (IOException e) {
                System.err.println(filename + " not found. Error: " + e.toString());
                continue;
            }
            // filter and sort emails
            String validEmails = Stream.of(fileContents.split("\n"))
                    .filter(EmailValidator::isValid)
                    .sorted(new EmailComparator())
                    .collect(Collectors.joining("\n"));
            // write to output file
            String outputFilename = FilenameUtils.getBaseName(filename) + "_output.txt";
            try {
                Files.writeString(Paths.get(outputFilename), validEmails);
            } catch (IOException e) {
                System.err.println("Error writing to " + outputFilename + ". Error: " + e.toString());
                continue;
            }
            System.out.println("Success! Validated emails from " + filename + " can be found in " + outputFilename + ".");
        }
    }
}
