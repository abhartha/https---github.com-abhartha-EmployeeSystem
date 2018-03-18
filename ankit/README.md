## Synopsis

This is a employee management system.
I am using ConcurrentHashMap to store all the records of the employee in memory.
This is a practical solution because if we're getting anywhere close to 2^30 things we need to keep track of in a real-world application, 
we need a lot more RAM than we can rely on in one machine. 
The largest HashMap I've ever worked with that sat in a single JVM had a few tens of millions of entries, all very lightweight.

I assume number of employees in a company will not go over one million.

For persistent storage I am using employee.json file. This file is just used to load data in the ConcurrentHashMap in case of a system reboot. 

## Code Example

I am using Maven to build this project, since the web server and front end was out of scope for this project, this project can be run as a simple java application
with Java 8.

I am using json.simple and google.gson as a dependency to parse and read and write to the json file.
  

## Motivation

This project is done as an assessment test for DataSpark Software Engineer Role.

## Installation

Import the project as a maven project and run App.java as a simple java application.

## API Reference

All the inputs to the system are done my console input. 
I am using Scanner class to get input from system.in.

## Tests

There are separate files doing junit tests for different method blocks. I am using system-rules api for creating test cases to capture console outputs.

## Contributors

I certify that this project was done by me (Ankit Bharthan) with some syntax help from json.simple api documentation.

## Assumptions

I am working on this project with following assumptions:
1. There is no initial input file to load all the data (I am creating new file - employee.json on the first run of the system)
2. The file created in above step will be used to load data on subsequent reboot.
3. We need to add employee in top down order. i.e. An employee cannot be added if the manager is not already in the database.
4. The CEO (first employee) can be added as manager of itself. So in the first entry the manager id will be 1.
5. Employee ids are assigned in sequential order in increment of 1.
6. On deletion of an employee, the record is not deleted it is just set to inActive state as an indicator that this employee is retired or terminated. 