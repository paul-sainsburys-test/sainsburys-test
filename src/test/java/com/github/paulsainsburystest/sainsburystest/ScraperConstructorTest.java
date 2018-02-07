package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.SinglePageItemScraperStrategy;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * Test for the constructor of {@link Scraper}.
 * @author Paul
 * @see Scraper
 * @see Scraper#Scraper(com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy, java.util.Set)
 */
public class ScraperConstructorTest extends AbstractScraperTest
{

  //For the constructor tests we cannot actually test whether they were set.
  //So this would be determined if the other tests fail or not.

  /**
   * Test when the {@link IItemAttributeScraperStrategy} parameter is null.
   * @throws NullPointerException Always thrown.
   */
  @Test
  public void scraperConstructorItemScraperStrategyNullTest()
  {
    //An empty set is valid
    HashSet<IItemAttributeScraperStrategy<?>> attributeScraperStrategySet = new HashSet<>();

    this.expectedException.expect(NullPointerException.class);
    this.expectedException.expectMessage("itemScraperStrategy cannot be null");
    Scraper scraper = new Scraper(null, attributeScraperStrategySet);
  }

  /**
   * Test when the {@link IItemScraperStrategy} parameter is null.
   * @throws NullPointerException Always thrown.
   */
  @Test
  public void scraperConstructorItemAttributeScraperStrategiesNullTest()
  {
    IItemScraperStrategy scraperStrategy = new SinglePageItemScraperStrategy();

    this.expectedException.expect(NullPointerException.class);
    this.expectedException.expectMessage("itemAttributeScraperStrategies cannot be null");
    Scraper scraper = new Scraper(scraperStrategy, null);
  }

  /**
   * Test when both parameters are null.
   * @throws NullPointerException Always thrown.
   * @see Scraper#Scraper(IItemScraperStrategy, Set)
   */
  @Test
  public void scraperConstructorNullTest()
  {
    this.expectedException.expect(NullPointerException.class);
    //We can't predict the message, it'll be either of the above.
    Scraper scraper = new Scraper(null, null);
  }



}
