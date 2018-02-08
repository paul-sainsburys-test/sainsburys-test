package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.io.IOException;
import java.util.Map;

/**
 * This decorator catches and handles {@link Exception}s.
 *
 * See the method JavaDoc for more info.
 *
 * @author Paul
 * @see ExceptionScraperDecorator#scrapeDecorated(java.lang.String)
 */
public class ExceptionScraperDecorator extends AbstractScraperDecorator
{
  /** Name of this decorator. */
  public static final String DECORATOR_NAME = "exceptionThrown";

  /** Attribute name to store the stacktrace if an exception is thrown. */
  public static final String DECORATOR_STACKTRACE_NAME = DECORATOR_NAME+"-Stacktrace";

  /**
   * The constructor for this class.
   * @param nextScraperDecorator The next scraper decorator to be called in this chain.
   * @throws NullPointerException If the parameter is null.
   */
  public ExceptionScraperDecorator(IScraperDecorator nextScraperDecorator)
  {
    super(null);
    throw new UnsupportedOperationException("Not supported yet.");
  }


  /**
   * This catches the exceptions if they have been thrown and records them in an
   * attribute.
   * It will always add the attribute {@link #DECORATOR_NAME} with the a boolean
   * value of whether an {@link Exception} was caught or not. (It won't catch {@link Error}s.)
   * The attribute {@link #DECORATOR_STACKTRACE_NAME} only is inserted if a exception
   * is caught, if an exception is caught the stacktrace is filled in here.
   * Also if an exception is caught fillout an empty but valid (non-null) key for:
   * {@link IScraperDecorator#SCRAPER_RESULTS_KEY_NAME}
   *
   * @param categoryUrl {@inheritDoc }
   * @return {@inheritDoc }
   * @throws MalformedDocumentException Shouldn't be thrown (as it's caught here).
   * @throws IOException Shouldn't be thrown (as it's caught here).
   */
  @Override
  public Map<String, Object> scrapeDecorated(String categoryUrl)
      throws MalformedDocumentException, IOException
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
