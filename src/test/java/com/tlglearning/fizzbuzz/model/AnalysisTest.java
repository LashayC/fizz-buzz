package com.tlglearning.fizzbuzz.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class AnalysisTest {
  //NOTE you need to test all 4 behaviors: fizz, buzz, fizzbuzz, and none

  static final Set<State> expectedFizz = EnumSet.of(State.FIZZ); //NOTE you can put each of the Sets up here for readability and reduce the number of times Set is instantiated.
  private Analysis analysis;

//  final Analysis analysis = new Analysis(); NOTE: You could also have written the final method like this.
  @BeforeEach
  void setUp() {
    analysis = new Analysis();
  }

  //NOTE: 1) when testing a method with mult values, you can used ParameterizedTest to avoid creating mult methods that ea just test one value. Its telling JUnit to invoke this method mult times for a given number of parameters.
  //NOTE: 2) You add a param (int value) to the method below so that JUnit can pass in those given paremeters as it repeatedly invokes it.
  //NOTE: 3) Give ParametizedTest a place to gets its values from ie @ValueSource
  //NOTE: 4) Give ValueSource its values.
  @ParameterizedTest //TIP this is a feature of JUnit5
  @ValueSource(ints = {3, 21, 999_999_999, })
  void analyze_fizz(int value) {
//    Set<State> expectedFizz = EnumSet.of(State.FIZZ);
    assertEquals(expectedFizz, analysis.analyze(value));

  }

  @ParameterizedTest
  @ValueSource (ints = {5, 10, 100_000_000, })
  void analyze_buzz(int value){
    Set<State> expectedBuzz = EnumSet.of(State.BUZZ);
    assertEquals(expectedBuzz, analysis.analyze(value));

  }

  @ParameterizedTest
  @ValueSource(ints = {0, 15, 999_999_990})
  void analyze_fizzBuzz(int value){
    Set<State> expected = EnumSet.of(State.FIZZ, State.BUZZ);
    assertEquals(expected, analysis.analyze(value));

  }

  //NOTE you can create a csv file(txt file) that contains the argument params. Makes it easier to edit outside of code
  //NOTE 1. Right click on 'resources under 'test'
  //NOTE 2. New Kotlin file
  //NOTE 3. Match file name to package then name of method you want to put the args for 'com/tlglearning/fizzbuzz/model/neither.csv'
  //3.2. You use slashes so that IntelliJ will put it into a directory. .s just indicate file names.
  //4. Add the value source set to the csv file
  //5. Next to value source for chosen method, change it to resources = <filename.csv>
  @ParameterizedTest
  @CsvFileSource(resources = "neither.csv", numLinesToSkip = 1) //NOTE the num lines to skip skips over the values header in csv
  void analyze_neither(int value){
    Set<State> expectedNeither = EnumSet.noneOf(State.class);
    assertEquals(expectedNeither, analysis.analyze(value));

  }

  @ParameterizedTest
  @ValueSource(ints = {-1, -3, -5, -15}) //NOTE this is testing for params of neg values. Checking for thrown exceptions.
  void analyze_negative(int value){

    assertThrows(IllegalArgumentException.class, () -> analysis.analyze(value)); //NOTE the anonymous class replaced with lambda. To chane, click anon function, opt+return, select lambda.  -> is the lambda operator. Tells the compiler you're using shorthand.
      //NOTE the line of analysis.analyse(..) was the only thing being executed in the previous anonymous function. Lambda recognizes this and its all thats put in the assertThrows
      //NOTE it knows that the second param in assertThrows is executable because thats what the second param of the assert expects. Then its lots of inference
      //NOTE as far as compiler knows its just in instance of something thats an executable. Lambda or anon class doesnt affect it.
      //NOTE only use lambda for instances where you're implementing a @funtionalInterface. Anonymous functions aren't limited the same way. Lambdas are great for tests.
      //NOTE Java doesn't let you have functions independent of a class. There fore you have @functionalInterfaces, which can be passed around. Then you also have lambdas to take it further.

  }

//    assertThrows(IllegalArgumentException.class, new Executable() { TIP: This is the previous anonymous class converted to a lambda above. It knows that analysis.analyze(value) only thing being executed. Ie its a functional interface that only has one method to implement.
//      @Override
//      public void execute() throws Throwable {
//        analysis.analyze(value);
//        //AnalysisTest.this.analysis.analyze(value);
//      }
//    }  );


}