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
    //NOTE: moved the nested class to inside the test that used it. Now known as a local class. This means it has the same context as its enclosing method.
    class InvalidInvocation implements Executable {//NOTE when refactoring from top level to nested its created as a static nested class. Static means it can'ts see the enclosing class around it that has the static descriptor.
      //NOTE: To make a nested class a pure nested class, remove the static descriptor. You can then access objects from the enclosing class. Below Analysis analysis is commented out since its accessed from above.

      //FIELDS
//    private final Analysis analysis;//NOTE redundant since the class this is being accessed from enclosing class.
      private final int value;

      //CONSTRUCTORS
      public InvalidInvocation( int value) {
//      this.analysis = analysis; //NOTE was redundant
        this.value = value;
      }

      @Override
      public void execute() throws Throwable {
        analysis.analyze(value);
        //AnalysisTest.this.analysis.analyze(value); //NOTE you don't need to refenerence the AnalysisTest.this to get at the current instance of Analysis in the instance class. The compiler sees analysis.analyze, doesn't see an instance in the nested class, then expands scropt to look outside it.
      }
    }

    assertThrows(IllegalArgumentException.class, new InvalidInvocation(value)  ); //NOTE the InvalidInvocation class was created to have a method that throws an Executable exception.

  }


}