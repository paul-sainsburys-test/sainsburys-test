package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.io.IOException;
import java.util.Map;

/**
 * Implement the {@link Scraper} decorator that gives the sum of the prices
 * from all of the items (assume buy x1 from each list element).
 * @author Paul
 */
public class TotalScraperDecorator extends AbstractScraperDecorator
{
  /** This decorators name. */
  public static final String DECORATOR_NAME = "total";

  /**
   * The constructor for this class.
   * @param nextScraperDecorator The next scraper decorator to be called in this chain.
   * @throws NullPointerException If the parameter is null.
   */
  public TotalScraperDecorator(IScraperDecorator nextScraperDecorator)
  {
    super(null); //Required for it to compile.
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Map<String, Object> scrapeDecorated(String categoryUrl) throws MalformedDocumentException, IOException
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}