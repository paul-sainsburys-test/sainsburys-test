package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
    super(nextScraperDecorator);
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
    try
    {
      Map<String, Object> returnedValue = this.nextScraperDecorator.scrapeDecorated(categoryUrl);

      //No exceptions were thrown. Add the attributes that to say this.
      returnedValue.put(ExceptionScraperDecorator.DECORATOR_NAME, Boolean.FALSE);

      return returnedValue;
    }
    catch (Exception ex)
    {
      Map<String, Object> outerMap = new LinkedHashMap<>();

      //There was an exception.
      outerMap.put(ExceptionScraperDecorator.DECORATOR_NAME, Boolean.TRUE);

      //Put in the stack trace
      String stacktrace;
      try(StringWriter stringWriter = new StringWriter();
          PrintWriter printerWriter = new PrintWriter(stringWriter))
      {
        ex.printStackTrace(printerWriter);
        stacktrace = stringWriter.toString();
      }
      outerMap.put(ExceptionScraperDecorator.DECORATOR_STACKTRACE_NAME, stacktrace);

      //Put in an empty list. As other classes may use this.
      List<Map<String, Object>> listOfMaps = new LinkedList<>();
      outerMap.put(IScraperDecorator.SCRAPER_RESULTS_KEY_NAME, listOfMaps);

      return outerMap;
    }
  }

}
