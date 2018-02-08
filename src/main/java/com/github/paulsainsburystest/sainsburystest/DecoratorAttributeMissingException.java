package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.scraperdecorators.IScraperDecorator;

/**
 * This exception is thrown when a {@link IScraperDecorator} implementation
 * tries to apply an operation on the result from a {@link Scraper} instance,
 * but the result does not contain the attribute needed and the attribute was
 * expected to be present.
 *
 * @author Paul
 * @see IItemAttributeScraperStrategy#allowsForNullAttribute()
 * @see IItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document)
 */
public class DecoratorAttributeMissingException extends RuntimeException
{
  /**
   * Creates a new exception.
   */
  public DecoratorAttributeMissingException()
  {
  }


  /**
   * Creates a new exception with the specified message.
   * @param message Message about the exceptions cause.
   */
  public DecoratorAttributeMissingException(String message)
  {
    super(message);
  }


  /**
   * Creates a new exception with the specified cause.
   * @param cause The cause of this exception.
   */
  public DecoratorAttributeMissingException(Throwable cause)
  {
    super(cause);
  }

  /**
   * Creates a new exception with the specified message and cause.
   * @param message Message about the exceptions cause.
   * @param cause The cause of this exception.
   */
  public DecoratorAttributeMissingException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
