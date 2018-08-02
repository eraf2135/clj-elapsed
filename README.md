# clj-elapsed

Computes the number of seconds elapsed between two moments in time

The input to the program will be supplied on the standard input stream, as two lines representing the start and end points of the required time interval in the ISO 8601 format “YYYY-MM-DDTHH:MM:SS”, with four- digit years and without fractions of a second. For example:

      2018-06-25T21:53:35
      2018-06-25T22:53:36

## Usage

    lein run
    
Or you can build an executable jar using:

    lein uberjar
    
Then run with:

    java -jar ./target/clj-elapsed.jar
