package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import org.junit.Assert;
import org.junit.Ignore;

/**
 * A meta-test of the super class to check that
 * {@link IItemAttributeScraperStrategyAbstractTest#getTestingStrategyTest() }
 * fails when given a null pointer.
 *
 * @author Paul
 */
public class IItemAttributeScraperStrategyFailingNullTest
    extends IItemAttributeScraperStrategyAbstractTest<IItemAttributeScraperStrategy, Object>
{

  @Override
  public IItemAttributeScraperStrategy getTestingStrategy()
  {
    return null;
  }

  @Ignore("This is a purposeful failing meta-test, null will always equal to null.")
  @Override
  public void getTestingStrategyDifferentTest()
  {

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
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @see IItemAttributeScraperStrategyAbstractTest#getAttributeNullPointerParameter()
   */
  @Override
  public void getAttributeNullPointerParameter() throws MalformedDocumentException
  {
    super.expectedException.expect(NullPointerException.class);
    super.getAttributeNullPointerParameter();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Override
  public void equalsDifferentClassTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsDifferentClassTest();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Override
  public void equalsNullTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsNullTest();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Override
  public void equalsSameClassTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsSameClassTest();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Override
  public void equalsDifferentClassDifferentAttributeNameTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsDifferentClassDifferentAttributeNameTest();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Override
  public void equalsDifferentClassSameAttributeNameTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsDifferentClassSameAttributeNameTest();
    super.expectedException.reportMissingExceptionWithMessage(
        "It was supposed to throw a NullPointerException but never did.");
  }

}
