MailPackageDelivery
===================

Application Mail Package delivery is builded as `jar` file. It is running JVM environment. Dedicated version is Java 1.8+. It must be installed in your OS.

Instructions for running
-----------------------

 Check:

> java -version 

Start:

> java -jar \<path-to-jar-file\>MailPackageDelivery.jar

or

> java -jar \<path-to-jar-file\>MailPackageDelivery.jar \<fully-qualified-path\>\<name\>.txt

Program is not error prone either on in file, either in case of input errors. It should issue error and exit its work.

Format of input is the same in command line as well as in file:

\<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator\>\<space\>\<postal code: fixed 5 digits\>

Output Every minute print out output as simple formatted text of values entered yet inside in format:

\<postal code: fixed 5 digits\>\<space\>\<total weight: fixed 3 decimal places, . (dot) as decimal separator\>


Instructions for buiding
------------------------

Program is developed in Eclipse IDE, for compilation you need only `maven`

Use:

> mvn clean compile assembly:single

to generate 'jar' file

Source code
-----------

Look on https://github.com/hariprasad108/MailPackageDelivery

