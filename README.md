Compile the code using
`mvn compile`.
Run the code on the command line using
`mvn exec:java`

To filter results based on the date range add the date ranges as program arguments in the format YYYY/MM/DD, adding the start date first.
`mvn exec:java -Dexec.args="2016/01/04 2016/01/06"`

Command to run tests: `mvn test` 

Notes:

LocalDateTime used in place of ZonedDateTime for Trace since we don't have timezone
information in the logs.
Timestamp format assumed to be in YYYY/MM/DD format though it is not clear from 
the data and could possibly be in YYYY/DD/MM format. 

 