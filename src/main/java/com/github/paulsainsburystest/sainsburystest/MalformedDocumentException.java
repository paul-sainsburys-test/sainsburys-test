package com.github.paulsainsburystest.sainsburystest;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Exception for a {@link Element} or {@link Document} that is not in the correct format.
 *
 * It's not a runtime exception as the user should handle the exception, otherwise
 * the program may not fail cleanly. (Produces stacktrace not in the desired format.)
 *
 * @author Paul
 */
public class MalformedDocumentException extends Exception
{

  /**
   * Creates a new exception.
   */
  public MalformedDocumentException()
  {
  }


  /**
   * Creates a new exception with the specified message.
   * @param message Message about the exceptions cause.
   */
  public MalformedDocumentException(String message)
  {
    super(message);
  }


  /**
   * Creates a new exception with the specified cause.
   * @param cause The cause of this exception.
   */
  public MalformedDocumentException(Throwable cause)
  {
    super(cause);
  }

  /**
   * Creates a new exception with the specified message and cause.
   * @param message Message about the exceptions cause.
   * @param cause The cause of this exception.
   */
  public MalformedDocumentException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
