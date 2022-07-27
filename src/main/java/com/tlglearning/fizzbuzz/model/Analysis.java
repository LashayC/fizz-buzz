package com.tlglearning.fizzbuzz.model;

import java.util.EnumSet;
import java.util.Set;

public class Analysis {

  private static final String ILLEGAL_VALUE_FORMAT = "Value was %,d; must be non-negative"; //NOTE this was made a constant in order to make it easier ot find in edit. Useful in large classes.

  /**
   *
   * @param value
   * @return
   * @throws IllegalArgumentException if{@code < 0}.
   */

  public Set<State> analyze(int value) throws IllegalArgumentException{//Note its important to include the exception in the declaration as well so its generated in any javadoc code documentation

    if(value < 0){
      throw new IllegalArgumentException(String.format(ILLEGAL_VALUE_FORMAT, value));//NOTE the comma in %,d says to use digit grouping. the d there means int formatted as a base 10
    }

    Set<State> result = EnumSet.noneOf(State.class);

    if (value % 3 == 0){
      result.add(State.FIZZ);
    }

    if(value % 5 == 0){
      result.add(State.BUZZ);
    }

//    if(value % 3 == 0 && value % 5 == 0){
//      result.add(State.BUZZ && State.BUZZ);
//    }

    return result;
  }
}
