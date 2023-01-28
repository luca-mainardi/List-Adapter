Comando per compilare:
javac -d target -cp ./Matcher/junit-4.13.2.jar \-Xlint:unchecked \Src/myAdapter/*.java Src/myTest/*.java

Comando per eseguire:
java -cp ./Matcher/junit-4.13.2.jar:./Matcher/hamcrest-core-1.3.jar:./target myTest/TestRunner
