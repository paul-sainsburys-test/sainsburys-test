package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Abstract test class to test the different states of
 * {@link AbstractItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document) }.
 *
 * @author Paul
 * @param <STRATEGY_TYPE> What strategy is being tested.
 * @see IItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document)
 */
public abstract class AbstractItemAttributeScraperStrategyTest<STRATEGY_TYPE extends IItemAttributeScraperStrategy>
    extends IItemAttributeScraperStrategyAbstractTest<STRATEGY_TYPE, Object>
{

  /**
   * Valid empty html document to reference when testing.
   */
  protected static final Document EMPTY_JSOUP_DOCUMENT = Jsoup.parseBodyFragment("");


  /**
   * Abstract strategy class for these tests, to avoid repetition it implements
   * {@link AbstractItemAttributeScraperStrategy#getAttributeName() }.
   */
  public static abstract class AbstractTestItemAttributeScraperStrategy
      extends AbstractItemAttributeScraperStrategy<Object>
  {

    @Override
    public String getAttributeName()
    {
      return "test";
    }

  }

}
