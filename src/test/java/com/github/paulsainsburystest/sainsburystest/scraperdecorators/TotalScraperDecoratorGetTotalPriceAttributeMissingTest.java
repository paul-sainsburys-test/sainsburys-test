package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.DecoratorAttributeMissingException;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.DescriptionAttributeItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.KilocaloriesAttributeItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.TitleAttributeItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.UnitPriceAttributeItemScraperStrategy;
import java.math.BigDecimal;
import java.util.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test {@link TotalScraperDecorator#getTotalPrice(java.util.List)} to see how
 * scraping is performed without {@link UnitPriceAttributeItemScraperStrategy}
 * present.
 *
 * @author Paul
 */
@RunWith(Parameterized.class)
public class TotalScraperDecoratorGetTotalPriceAttributeMissingTest
{

  private static final List<Map<String, Object>> TEST_INPUT1;
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT1 = Collections.unmodifiableList(list);
  }

  //1 input.
  private static final List<Map<String, Object>> TEST_INPUT2;
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT2 = Collections.unmodifiableList(list);
    list.add(TotalScraperDecoratorGetTotalPriceAttributeMissingTest.generateMap());
  }

  //More than 1 input - test 1
  private static final List<Map<String, Object>> TEST_INPUT3;
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT3 = Collections.unmodifiableList(list);
    list.add(TotalScraperDecoratorGetTotalPriceAttributeMissingTest.generateMap());
    list.add(TotalScraperDecoratorGetTotalPriceAttributeMissingTest.generateMap());
  }

  //More than 1 input - test 2
  private static final List<Map<String, Object>> TEST_INPUT4;
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT4 = Collections.unmodifiableList(list);
    list.add(TotalScraperDecoratorGetTotalPriceAttributeMissingTest.generateMap());
    list.add(TotalScraperDecoratorGetTotalPriceAttributeMissingTest.generateMap());
    list.add(TotalScraperDecoratorGetTotalPriceAttributeMissingTest.generateMap());
  }

  //Test if the input is null.
  private static final List<Map<String, Object>> TEST_INPUT5 = null;

  /**
   * Generates a new (item) map attributes specified (except unit price).
   * @return A map to be used as an item's attributes.
   */
  private static Map<String, Object> generateMap()
  {
    Map<String, Object> map = new LinkedHashMap<>();

    //Could use more random data, but it's sufficent.
    map.put(TitleAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Testing title");
    map.put(DescriptionAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Testing description");
    map.put(KilocaloriesAttributeItemScraperStrategy.ATTRIBUTE_NAME, 4);

    return Collections.unmodifiableMap(map);
  }

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  /**
   * Parameters to initialise each test with.
   * @return A list of Object arrays
   */
  @Parameterized.Parameters
  public static List<Object[]> data()
  {
    return Arrays.asList(
      new Object[][]
      {
        {TEST_INPUT1},
        {TEST_INPUT2},
        {TEST_INPUT3},
        {TEST_INPUT4},
        {TEST_INPUT5},
      }
    );
  }

  /** What list of item attribues at do you want to input. */
  private final List<Map<String, Object>> inputListOfItems;

  /**
   * The constructor for this test.
   * @param inputListOfItems What list of item attribues at do you want to input (can be null).
   */
  public TotalScraperDecoratorGetTotalPriceAttributeMissingTest(List<Map<String, Object>> inputListOfItems)
  {
    this.inputListOfItems = inputListOfItems;
  }

  /**
   * Test to see what happens if the unit price is missing from item attributes.
   */
  @Test
  public void getTotalPriceMissingUnitPriceTest()
  {
    //Test the null pointer input it should throw a null pointer exception
    if (this.inputListOfItems == null)
    {
      this.expectedException.expect(NullPointerException.class);
      BigDecimal actualResult = TotalScraperDecorator.getTotalPrice(this.inputListOfItems);
    }
    //The only one that shouldn't fail because there are no items for it to iterate over.
    else if (this.inputListOfItems.isEmpty())
    {
      BigDecimal actualResult = TotalScraperDecorator.getTotalPrice(this.inputListOfItems);
      Assert.assertEquals("The expected and actual total prices differ.", new BigDecimal("0.00"), actualResult);
    }
    //All others should throw an exception.
    else
    {
      String exceptionMessage = "We expected the attribute \""+UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME+"\" " +
          "to be present but it wasn't.";
      this.expectedException.expect(DecoratorAttributeMissingException.class);
      this.expectedException.expectMessage(exceptionMessage);
      BigDecimal actualResult = TotalScraperDecorator.getTotalPrice(this.inputListOfItems);
    }
  }
}
