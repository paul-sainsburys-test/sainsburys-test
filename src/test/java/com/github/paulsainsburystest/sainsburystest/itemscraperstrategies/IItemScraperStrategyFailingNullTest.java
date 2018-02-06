package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import org.junit.Assert;
import org.junit.Ignore;

/**
 * This is a meta-test to test the {@link IItemScraperStrategyAbstractTest} test.
 *
 * @author Paul
 */
public class IItemScraperStrategyFailingNullTest extends IItemScraperStrategyAbstractTest<IItemScraperStrategy>
{

  @Override
  public IItemScraperStrategy getTestingStrategy()
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
   * @see IItemScraperStrategyAbstractTest#getTestingStrategyTest()
   */
  @Override
  public void getTestingStrategyTest()
  {
    IItemScraperStrategy strategy = this.getTestingStrategy();
    Assert.assertNull("The class returned a non-null strategy.", strategy);
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @throws NullPointerException Always thrown.
   * @see IItemScraperStrategyAbstractTest#getNameNullTest()
   */
  @Override
  public void getNameNullTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.getNameNullTest();
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws NullPointerException Always thrown.
   * @see IItemScraperStrategyAbstractTest#getItemUrlsNullPointerParameter()
   */
  @Override
  public void getItemUrlsNullPointerParameter() throws MalformedDocumentException
  {
    super.expectedException.expect(NullPointerException.class);
    super.getItemUrlsNullPointerParameter();
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @throws NullPointerException Always thrown.
   * @see IItemScraperStrategyAbstractTest#equalsNullTest()
   */
  @Override
  public void equalsNullTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsNullTest();
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @throws NullPointerException Always thrown.
   * @see IItemScraperStrategyAbstractTest#equalsDifferentClassTest()
   */
  @Override
  public void equalsDifferentClassTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsDifferentClassTest();
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @throws NullPointerException Always thrown.
   * @see IItemScraperStrategyAbstractTest#equalsSameClassTest()
   */
  @Override
  public void equalsSameClassTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsSameClassTest();
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @throws NullPointerException Always thrown.
   * @see IItemScraperStrategyAbstractTest#equalsDifferentClassDifferentAttributeNameTest()
   */
  @Override
  public void equalsDifferentClassDifferentAttributeNameTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsDifferentClassDifferentAttributeNameTest();
  }

  /**
   * Test to see if the superclass's test fails due to the null strategy.
   * @throws NullPointerException Always thrown.
   * @see IItemScraperStrategyAbstractTest#equalsDifferentClassSameAttributeNameTest()
   */
  @Override
  public void equalsDifferentClassSameAttributeNameTest()
  {
    super.expectedException.expect(NullPointerException.class);
    super.equalsDifferentClassSameAttributeNameTest();
  }


}
