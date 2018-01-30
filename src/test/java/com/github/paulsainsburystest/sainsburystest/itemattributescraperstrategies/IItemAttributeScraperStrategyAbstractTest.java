package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import org.jsoup.nodes.Document;
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
   * Get the strategy to test, it must create a new instance everytime when called.
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
   * Test to see if the test returns a different instance when called again.
   */
  @Test
  public void getTestingStrategyDifferentTest()
  {
    STRATEGY_TYPE strategy1 = this.getTestingStrategy();
    STRATEGY_TYPE strategy2 = this.getTestingStrategy();
    Assert.assertFalse("When called twice different strategy instances are supposed " +
        "to be instantiated", strategy1 == strategy2);
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

  /**
   * Test to see if equals returns false when tested with null.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Test
  public void equalsNullTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    boolean equal = strategy.equals(null);
    Assert.assertFalse("The strategy when compared to null is supposed to be different.", equal);
  }

  /**
   * Test to see if equals returns false when tested with a different class not
   * in the inheritance hierarchy.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Test
  public void equalsDifferentClassTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    Object compare = new Object();
    Assert.assertFalse("The strategy when compared to a different class is supposed " +
        "to be different.", strategy.equals(compare));
  }

  /**
   * Test to see if equals returns true if compared with another instance of itself.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Test
  public void equalsSameClassTest()
  {
    STRATEGY_TYPE strategy1 = this.getTestingStrategy();
    STRATEGY_TYPE strategy2 = this.getTestingStrategy();
    boolean equ = strategy1.equals(strategy2);
    Assert.assertTrue("The strategy when compared to another instance of itself is supposed " +
        "to be the same.", equ);
  }

  /**
   * Tests to see if equals returns true when a different strategy has the same
   * attribute name.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Test
  public void equalsDifferentClassSameAttributeNameTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    NameModifiableItemAttributeScraperStrategy strategyTest =
        new NameModifiableItemAttributeScraperStrategy(strategy.getAttributeName());

    boolean equ = strategy.equals(strategyTest);
    Assert.assertTrue("The strategy when compared to an instance of another class " +
        "with the same name is supposed to be the same.", equ);
  }

  /**
   * Tests to see if equals returns false when a different strategy has a different
   * attribute name.
   * @see IItemAttributeScraperStrategy#equals(java.lang.Object)
   */
  @Test
  public void equalsDifferentClassDifferentAttributeNameTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();
    NameModifiableItemAttributeScraperStrategy strategyTest =
        new NameModifiableItemAttributeScraperStrategy(strategy.getAttributeName() + "-ExtraText");

    boolean equ = strategy.equals(strategyTest);
    Assert.assertFalse("The strategy when compared to an instance of another class " +
        "with a different name is supposed to be different.", equ);
  }


  private static class NameModifiableItemAttributeScraperStrategy extends AbstractItemAttributeScraperStrategy<Object>
  {
    private final String attributeName;

    public NameModifiableItemAttributeScraperStrategy(String attributeName)
    {
      if (attributeName == null)
      {
        throw new NullPointerException("attributeName cannot be null.");
      }
      this.attributeName = attributeName;
    }

    @Override
    protected Object getAttributeInputNullChecked(Document jsoupDocument)
    {
      //It does not matter what is returns as it won't be checked.
      return new Object();
    }

    @Override
    public boolean allowsForNullAttribute()
    {
      //It does not matter what is returns as it won't be checked.
      return false;
    }

    @Override
    public String getAttributeName()
    {
      return this.attributeName;
    }

  }

}
