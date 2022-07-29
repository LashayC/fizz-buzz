package com.tlglearning.fizzbuzz.model;

import org.junit.jupiter.api.function.Executable;

class InvalidInvocation implements
    Executable {//NOTE nested class goes below everything else. Good for when the class only makes sense in context of current class.
  //NOTE the static on this class means that when instances of it are created, it won't be able to see the enclosing class. Remove it to stop this behavior.

  //FIELDS
  private final Analysis analysis;//NOTE this was commented out when nested in Analysis test class because it was already declared in the enclosing class. Now that its been moved up you need it again.
  private final int value;

  //CONSTRUCTORS
  public InvalidInvocation(Analysis analysis, int value) {
    this.analysis = analysis; //NOTE was redundant, now needed since class is no longer nested.
    this.value = value;
  }

  @Override
  public void execute() throws Throwable {
    analysis.analyze(value);
  }
}
