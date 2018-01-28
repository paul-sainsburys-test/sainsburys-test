package com.github.paulsainsburystest.sainsburystest;

import org.junit.Assert;

/**
 * A meta-test of the super class to check that
 * {@link IItemAttributeScraperStrategyAbstractTest#getTestingStrategyTest() }
 * fails when given a null pointer.
 *
 * @author Paul
 */
public class IItemAttributeScraperStrategyFailingNullTest
    extends IItemAttributeScraperStrategyAbstractTest<IItemAttributeScraperStrategy>
{

  @Override
  public IItemAttributeScraperStrategy getTestingStrategy()
  {
    return null;
  }

  /**
   * Test to see if returns a null strategy.
   *
   * Also override the superclass's test to prevent it from failing.
   *
   * @see IItemAttributeScraperStrategyAbstractTest#getTestingStrategyTest()
   */
  @Override
  public void getTestingStrategyTest()
  {
    IItemAttributeScraperStrategy strategy = this.getTestingStrategy();
    Assert.assertNull("The class returned a non-null strategy.", strategy);
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategyAbstractTest#allowsForNullAttributeTest()
   */
  @Override
  public void allowsForNullAttributeTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.allowsForNullAttributeTest();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategyAbstractTest#getAttributeNameNullTest()
   */
  @Override
  public void getAttributeNameNullTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.getAttributeNameNullTest();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategyAbstractTest#getAttributeNullPointerParameter()
   */
  @Override
  public void getAttributeNullPointerParameter()
  {
    super.expectedException.expect(NullPointerException.class);
    super.getAttributeNullPointerParameter();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

}
