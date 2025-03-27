package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExceptionExample {
    //CheckedExceptions are compile time exceptions, we must handle the exceptions either using try catch
    // or declaring throws. If we use try catch, we run the code that might throw exception inside try
    // block, and we define how we want to handle it inside catch block. Or we can declare throws keyword
    // in the method signature to indicate that when we call the method, we must handle the exception.
    // Typical checkedExceptions are:IOException，FileNotFoundException, SOLException.

    //how to handle checkedException
    public void readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        System.out.println("Reading" + filename);
    }

    public void peopleReadFile(String people, String filename) {
        try {
            readFile(filename); // calling method that throws checked exception
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        System.out.println(people + " is reading " + filename);
    }

    public void readFile(String filename, String people) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(people + "is reading" + filename);
    }

    //Unchecked exceptions are runtime exceptions. They are more common than checked exceptions, we don't
    //have to handle them using try/catch. Instead, we change the code directly to solve the programming bugs.

    //unchecked example: we can't divide by 0;

    public int divideByZero(int dividend) {
        return dividend/0;
    }

//    No, there can not be multiple finally blocks. But we can have multiple catch blocks to handle different
//    types of exceptions. Exceptions are evaluated top-down, if an exception is caught, the rest would be
//    ignored. So more specific exceptions should come first.

   // When both catch and finally return values， the value in finally block would be the final result.
    public int multipleFinally(int num1, int num2) {
        try {
            int res = num1/num2;
            return 1;
        } catch(ArithmeticException e) {
            System.out.println(e.getMessage());
            return 2;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 3;
        } finally {
            System.out.println("Finally");
            return 4;
        }
    }

}
