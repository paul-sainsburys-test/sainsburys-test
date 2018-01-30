package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * An abstract test for the interface {@link IItemAttributeScraperStrategy}.
 *
 * The test subclass is responsible for testing to see if the
 * {@link IItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document) }
 * method allows null return values or not.
 *
 * @author Paul
 * @param <STRATEGY_TYPE> What strategy is being tested.
 * @see IItemAttributeScraperStrategy
 */
public abstract class IItemAttributeScraperStrategyAbstractTest<STRATEGY_TYPE extends IItemAttributeScraperStrategy>
{

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  /**
   * Get the strategy to test.
   * @return A non-null {@link IItemAttributeScraperStrategy} instance.
   */
  public abstract STRATEGY_TYPE getTestingStrategy();

  /**
   * Test to see if the test subclass actually returns a non-null strategy instance.
   */
  @Test
  public void getTestingStrategyTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    Assert.assertNotNull("The subclass returns a null strategy.", strategy);
  }

  /**
   * Test to make sure the implementation does not throw an exception.
   * @see IItemAttributeScraperStrategy#allowsForNullAttribute()
   */
  @Test
  public void allowsForNullAttributeTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    strategy.allowsForNullAttribute(); //No exception thrown.
  }

  /**
   * Test to make sure the attribute name is not null.
   * @see IItemAttributeScraperStrategy#getAttributeName()
   */
  @Test
  public void getAttributeNameNullTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    Assert.assertNotNull("The attribute name is null.", strategy.getAttributeName());
  }

  /**
   * Test to make sure that a {@link NullPointerException} is thrown when a
   * null JSoup document is passed in.
   * @see IItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document)
   */
  @Test
  public void getAttributeNullPointerParameter()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    this.expectedException.expect(NullPointerException.class);
    strategy.getAttribute(null);
    this.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }


}
