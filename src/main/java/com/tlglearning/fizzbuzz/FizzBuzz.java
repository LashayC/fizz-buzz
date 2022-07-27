package com.tlglearning.fizzbuzz;

import com.tlglearning.fizzbuzz.model.Analysis;
import com.tlglearning.fizzbuzz.model.State;
import java.util.Set;
import org.apache.commons.cli.Options;

public class FizzBuzz {
//  Options options = new Options();

  public static final int DEFAULT_UPPER_BOUND = 100; //NOTE Every constant is a static final, but not every static final is a constant. (An array is an example of this bc you can change the contents of the array even if you can't change the reference, therefore its technically mutable.)

  public static void main(String[] args) {
    //TIP currently no args are being passed but you can add one in the command line by going to configurations and adding one in 'Program Arguments'
    try {//NOTE with a try/catch, once an error is thrown it continues to the next statements. You can put this in a loop to try again a set number of times before continuing.
      int upperBound = (args.length > 0) ? Integer.parseInt(args[0]) : DEFAULT_UPPER_BOUND;
      if (upperBound <= 0) {
        throw new IllegalArgumentException();
      }

      Analysis analysis = new Analysis();
      for (int counter = 1; counter <= upperBound; counter++) {

        Set<State> result = analysis.analyze(counter);
        System.out.println(result.isEmpty() ? counter : result);
        //      if(result.isEmpty()){
        //        System.out.println(counter);
        //      }else {
        //        System.out.println(result);
        //      }

      }
//    catch (NumberFormatException e) { //NOTE e refers to the exception object that's caught.
    } catch (IllegalArgumentException e) { //NOTE you can change this to IllegalArgExc because it extends NumFormatExc so it still works with the exception thats being thrown.

      //throw new RuntimeException(e); //NOTE this won't be used because its not super descriptive

      //NOTE use brackets below to indicate an optional field. - FizzBuzz.class, the class method here is a default method of every class so you can use it to get the fully qualified className (including base package). **You can only use getClass method on a class that is also an instance of something else.
      System.out.printf("Usage: java %1$s [upperBound]%n", FizzBuzz.class.getName());
      System.out.println("Where: upperBound is a positive integer, with a default of 100");
//      throw new RuntimeException("FizzBuz halted", e);

    } finally{//NOTE finally executes whether you throw an exception or not. The keyword can't be chained they same way catch keywords can. Its the end.
      System.out.println("Thanks for playing FizzBuzz!");
    }

  }

}
