package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Base class for testing the interface {@link IItemScraperStrategy}.
 *
 * Subclasses are required to test the validity of inputs for
 * {@link IItemScraperStrategy#getItemUrls(org.jsoup.nodes.Document) }.
 *
 * @author Paul
 * @param <STRATEGY_TYPE> The strategy being tested.
 */
public abstract class IItemScraperStrategyAbstractTest<STRATEGY_TYPE extends IItemScraperStrategy>
{

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  /**
   * Get the strategy to test, it must create a new instance everytime when called.
   * @return A non-null {@link IItemScraperStrategy} instance.
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
   * Test to see the method to get the name does not return null.
   * @see IItemScraperStrategy#getName()
   */
  @Test
  public void getNameNullTest()
  {
    STRATEGY_TYPE testStrategy = this.getTestingStrategy();
    String name = testStrategy.getName();

    Assert.assertNotNull("The name is supposed to be not null.", name);
  }


  /**
   * Test to make sure that a {@link NullPointerException} is thrown when a
   * null JSoup document is passed in.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws NullPointerException Should always be thrown.
   * @see IItemScraperStrategy#getItemUrls(org.jsoup.nodes.Document)
   */
  @Test
  public void getItemUrlsNullPointerParameter() throws MalformedDocumentException
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();

    this.expectedException.expect(NullPointerException.class);
    this.expectedException.expectMessage("The parameter cannot be null.");
    strategy.getItemUrls(null);
  }

  /**
   * Test to see if equals returns false when tested with null.
   * @see IItemScraperStrategy#equals(java.lang.Object)
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
   * @see IItemScraperStrategy#equals(java.lang.Object)
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
   * @see IItemScraperStrategy#equals(java.lang.Object)
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
   * @see IItemScraperStrategy#equals(java.lang.Object)
   */
  @Test
  public void equalsDifferentClassSameAttributeNameTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();

    NameModifiableItemScraperStrategy strategyTest =
        new NameModifiableItemScraperStrategy(strategy.getName());

    boolean equ = strategy.equals(strategyTest);
    Assert.assertTrue("The strategy when compared to an instance of another class " +
        "with the same name is supposed to be the same.", equ);
  }

  /**
   * Tests to see if equals returns false when a different strategy has a different
   * attribute name.
   * @see IItemScraperStrategy#equals(java.lang.Object)
   */
  @Test
  public void equalsDifferentClassDifferentAttributeNameTest()
  {
    STRATEGY_TYPE strategy = this.getTestingStrategy();

    NameModifiableItemScraperStrategy strategyTest =
        new NameModifiableItemScraperStrategy(strategy.getName() + "-ExtraText");

    boolean equ = strategy.equals(strategyTest);
    Assert.assertFalse("The strategy when compared to an instance of another class " +
        "with a different name is supposed to be different.", equ);
  }


  private static class NameModifiableItemScraperStrategy extends AbstractItemScraperStrategy
  {
    private final String name;

    public NameModifiableItemScraperStrategy(String name)
    {
      if (name == null)
      {
        throw new NullPointerException("name cannot be null.");
      }
      this.name = name;
    }

    @Override
    protected List<String> getItemUrlsInputNullChecked(Document jsoupDocument) throws NullPointerException, MalformedDocumentException
    {
      //It does not matter about it being empty.
      return new LinkedList<>();
    }

    @Override
    public String getName()
    {
      return this.name;
    }


  }


}
