package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.SinglePageItemScraperStrategy;
import java.util.HashSet;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test for the class {@link Scraper}.
 * @author Paul
 */
public class ScraperTest
{
  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  //For the constructor tests we cannot actually test whether they were set.
  //So this would be determined in future tests (do they fail or not).

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
   */
  @Test
  public void scraperConstructorNullNullTest()
  {
    this.expectedException.expect(NullPointerException.class);
    //We can't predict the message, it'll be either of the above.
    Scraper scraper = new Scraper(null, null);
  }


}