package org.npr.email_validation;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    /**
     * Main method takes in file(s), validates emails, sorts emails by domain, and outputs result to file
     * @param args string array of input filenames
     */
    public static void main(String[] args) {
        for (String inputFilename : args) {
            LOGGER.log(Level.INFO, "Starting input from " + inputFilename);
            // read file
            String inputFileContents;
            try {
                inputFileContents = Files.readString(Paths.get(inputFilename), StandardCharsets.US_ASCII);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Could not read file " + inputFilename, e);
                continue;
            }
            // analyze contents
            String outputEmails = Email.filterAndSortList(inputFileContents);
            // write file
            String parentDir = new File(inputFilename).getParent();
            parentDir = parentDir == null ? "" : parentDir + File.separator;
            String outputFilename = parentDir + FilenameUtils.getBaseName(inputFilename) + "_output.txt";
            try {
                Files.writeString(Paths.get(outputFilename), outputEmails);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Could not write to file " + outputFilename, e);
                continue;
            }
            LOGGER.log(Level.INFO, "Completed input from " + inputFilename + "; Output to " + outputFilename);
        }
    }
}
