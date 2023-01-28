# List-Adapter

This is a project for the Software Engineering course. It contains an adapter for the List interface implemented in java that Uses CLDC 1.1's Vector class as the adaptee. The implementation allows only the features of J2RE 1.4.2 to be used.

Command to compile:
`javac -d target -cp ./Matcher/junit-4.13.2.jar \-Xlint:unchecked \Src/myAdapter/*.java Src/myTest/*.java`

Command to run:
`java -cp ./Matcher/junit-4.13.2.jar:./Matcher/hamcrest-core-1.3.jar:./target myTest/TestRunner`


