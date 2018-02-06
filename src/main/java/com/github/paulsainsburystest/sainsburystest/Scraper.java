package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
   */
  public List<Map<String,Object>> scrape(String categoryUrl)
  {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  /**
   * Scrape an item's page for attributes.
   * @param url The url to scrape from.
   * @return A hash map of attribute names and their associated attribute.
   */
  public Map<String, Object> scrapeItemPage(String url)
  {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

}

