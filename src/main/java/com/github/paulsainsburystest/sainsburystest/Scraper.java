package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * This class scrapes an item's page
 * @author Paul
 */
public class Scraper
{
  /** Strategies for scraping attributes. */
  private final Set<IItemAttributeScraperStrategy<?>> itemAttributeScraperStrategies;

  /** Strategy for scraping item pages. */
  private final IItemScraperStrategy itemScraperStrategy;

  /**
   * The constructor for this class.
   * @param itemScraperStrategy The strategy of how items will be scraped/selected.
   * @param itemAttributeScraperStrategies The set of strategies for scraping attributes.
   * @throws NullPointerException If any parameter is null.
   */
  public Scraper(IItemScraperStrategy itemScraperStrategy,
      Set<IItemAttributeScraperStrategy<?>> itemAttributeScraperStrategies)
  {
    if (itemScraperStrategy == null)
    {
      throw new NullPointerException("itemScraperStrategy cannot be null.");
    }
    else if (itemAttributeScraperStrategies == null)
    {
      throw new NullPointerException("itemAttributeScraperStrategies cannot be null.");
    }

    this.itemAttributeScraperStrategies = itemAttributeScraperStrategies;
    this.itemScraperStrategy = itemScraperStrategy;
  }

  /**
   * Scrape a category for all item attributes contained within it.
   * @param categoryUrl Url of a category to parse.
   * @return A list of mapped attributes.
   * @throws NullPointerException If categoryUrl is null.
   * @throws MalformedDocumentException If the strategy cannot determine if the
   *    attribute exists or if it should exist but does not exist.
   * @throws IOException An error occurs when requesting the webpage.
   */
  public List<Map<String,Object>> scrape(String categoryUrl)
      throws IOException, MalformedDocumentException
  {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  /**
   * Scrape an item's page for attributes.
   * @param url The url to scrape from.
   * @return A hash map of attribute names and their associated attribute.
   * @throws NullPointerException If url is null.
   * @throws MalformedDocumentException If the strategy cannot determine if the
   *    attribute exists or if it should exist but does not exist.
   * @throws IOException An error occurs when requesting the webpage.
   */
  public Map<String, Object> scrapeItemPage(String url)
      throws IOException, MalformedDocumentException
  {
    if (url == null)
    {
      throw new NullPointerException("url cannot be null.");
    }
    return this.scrapeItemPagePrivate(url);
  }

  /**
   * Scrape an item's page for attributes (assumes the parameter is not null).
   * @param url The url to scrape from (assumed non-null).
   * @return A hash map of attribute names and their associated attribute.
   * @throws MalformedDocumentException If the strategy cannot determine if the
   *    attribute exists or if it should exist but does not exist.
   * @throws IOException An error occurs when requesting the webpage.
   */
  private Map<String, Object> scrapeItemPagePrivate(String url)
      throws IOException, MalformedDocumentException
  {
    //Linked as it will be iterated over later.
    Map<String, Object> map = new LinkedHashMap<>();

    Document jsoupDocument = Jsoup.connect(url).get();

    for (IItemAttributeScraperStrategy<?> attributeStrategy : this.itemAttributeScraperStrategies)
    {
      //Assume null is fine as that's what the interface contract says.
      Object returnValue;
      try
      {
        returnValue = attributeStrategy.getAttribute(jsoupDocument);
      }
      catch (MalformedDocumentException ex)
      {
        String message = "An exception was thrown when trying to extract an attribute " +
            "from a webpage. ("+url+")";
        throw new MalformedDocumentException(message, ex);
      }

      //If there is a return value include it otherwise skip it.
      if (returnValue != null)
      {
        String attributeName = attributeStrategy.getAttributeName();
        map.put(attributeName, returnValue);
      }
    }

    return map;

  }

}

