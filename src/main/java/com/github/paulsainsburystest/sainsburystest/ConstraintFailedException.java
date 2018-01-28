package com.github.paulsainsburystest.sainsburystest;

/**
 * Runtime exception when one of the constraints failed.
 * @author Paul
 */
public class ConstraintFailedException extends RuntimeException
{

  /**
   * Creates a new exception.
   */
  public ConstraintFailedException()
  {
  }


  /**
   * Creates a new exception with the specified message.
   * @param message Message about the exceptions cause.
   */
  public ConstraintFailedException(String message)
  {
    super(message);
  }


  /**
   * Creates a new exception with the specified cause.
   * @param cause The cause of this exception.
   */
  public ConstraintFailedException(Throwable cause)
  {
    super(cause);
  }

  /**
   * Creates a new exception with the specified message and cause.
   * @param message Message about the exceptions cause.
   * @param cause The cause of this exception.
   */
  public ConstraintFailedException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
