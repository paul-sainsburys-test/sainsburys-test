package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import com.github.paulsainsburystest.sainsburystest.Scraper;
import java.io.IOException;
import java.util.Map;

/**
 * Decorator interface for the class {@link Scraper}.
 *
 * Scraper by default returns a List<Map<String, Object>>, this applies another
 * layer and allows methods to be applied to the results. This is done by putting
 * the above mentioned results into another map with the key "results". Other
 * information can be added by putting another key and value combination into the
 * map.
 *
 * @author Paul
 */
public interface IScraperDecorator
{

  /**
   * This decorates the results of {@link Scraper#scrape(java.lang.String) }.
   *
   * The output of the method above is stored in the key value "results",
   * this should not be modified/removed unless it's handling an exception.
   * It's expected that any error handling would be done in the first decorator
   * in the chain (last to be returned).
   *
   * Attributes can be added by creating another key and value mapped values.
   *
   * This does not check if the parameter is null as that's done in the above method.
   *
   * @param categoryUrl Url of a category to extract items and their attributes from.
   * @return Returns a map of identifying strings and mapped values.
   *
   * @throws MalformedDocumentException If any strategy cannot determine if the
   *    attribute/items exists or if it should exist but does not exist.
   * @throws IOException If a link error occurs when getting a webpage.
   */
  public Map<String, Object> scrapeDecorated(String categoryUrl)
      throws MalformedDocumentException, IOException;

}
