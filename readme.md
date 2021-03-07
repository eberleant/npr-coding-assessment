# NPR Coding Assessment: Email Validation
## Summary
This program takes in one or more filenames, validates each line as an email address, sorts by domain, and saves the output (excluding any invalid email addresses) into a separate file.

## JavaDocs
JavaDocs can be viewed from the `docs` folder inside the project directory. You can start browsing from `docs/index.html`.

## Dependencies
* JDK 13
* Maven 3

## Compile
In project directory, run `mvn package`

## Run
In project directory, run `mvn exec:java -Dexec.mainClass=org.npr.email_validation.Main -Dexec.args="filename.txt"`. Replace `filename.txt` with the path of the input file. If the path is relative, it should be relative to the project directory. The output file is based on the input filename; in this case, the output file would be `filename_output.txt`. You can also give a list of space-separated filenames, like `-Dexec.args="first.txt second.txt"`. The output files would then be called `first_output.txt` and `second_output.txt`.

## Test
In project directory, run `mvn test`

## Assumptions and Limitations
There are several assumptions made regarding the email format. (If I was coding an actual product, I would probably copy a regular expression from StackOverflow rather than writing my own, since that would be more likely to handle all the weird cases.) A description of the syntax of a valid email address can be found [here](https://en.wikipedia.org/wiki/Email_address#Syntax). However, the following valid email address formats are not handled by this program:

* comments (`(comment)abc@abc.com`, `abc@(comment)abc.com`, etc)
* quoted local part (`"This is a valid.. email"@abc.com`)
* IP address domain (`jsmith@[192.168.2.1]` or `jsmith@[IPv6:2001:db8::1]`)
* "non-deliverable" domains (`x@example.com` is accepted normally)
* each label in a domain may have any length > 0 (in reality, they must be less than 63 characters)

Additionally, duplicate emails are allowed, and duplicate domains may be outputted in any order.