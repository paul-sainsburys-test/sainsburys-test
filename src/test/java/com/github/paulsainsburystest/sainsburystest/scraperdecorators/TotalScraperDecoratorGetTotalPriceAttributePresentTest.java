package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

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
 * scraping is performed with {@link UnitPriceAttributeItemScraperStrategy}
 * present.
 *
 * @author Paul
 * @see TotalScraperDecorator#getTotalPrice(java.util.List)
 */
@RunWith(Parameterized.class)
public class TotalScraperDecoratorGetTotalPriceAttributePresentTest
{

  private static final List<Map<String, Object>> TEST_INPUT1;
  private static final BigDecimal TEST_INPUT1_EXPECTED_RESULT = new BigDecimal("0.00");
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT1 = Collections.unmodifiableList(list);
  }

  //1 input.
  private static final List<Map<String, Object>> TEST_INPUT2;
  private static final BigDecimal TEST_INPUT2_EXPECTED_RESULT = new BigDecimal("1.49");
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT2 = Collections.unmodifiableList(list);
    list.add(TotalScraperDecoratorGetTotalPriceAttributePresentTest.generateMap("1.49"));
  }

  //More than 1 input - test 1
  private static final List<Map<String, Object>> TEST_INPUT3;
  private static final BigDecimal TEST_INPUT3_EXPECTED_RESULT = new BigDecimal("3.01");;
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT3 = Collections.unmodifiableList(list);
    list.add(TotalScraperDecoratorGetTotalPriceAttributePresentTest.generateMap("0.51"));
    list.add(TotalScraperDecoratorGetTotalPriceAttributePresentTest.generateMap("2.50"));
  }

  //More than 1 input - test 2
  private static final List<Map<String, Object>> TEST_INPUT4;
  private static final BigDecimal TEST_INPUT4_EXPECTED_RESULT = new BigDecimal("10.50");;
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_INPUT4 = Collections.unmodifiableList(list);
    list.add(TotalScraperDecoratorGetTotalPriceAttributePresentTest.generateMap("3.00"));
    list.add(TotalScraperDecoratorGetTotalPriceAttributePresentTest.generateMap("2.50"));
    list.add(TotalScraperDecoratorGetTotalPriceAttributePresentTest.generateMap("5.00"));
  }

  //Test if the input is null.
  private static final List<Map<String, Object>> TEST_INPUT5 = null;
  private static final BigDecimal TEST_INPUT5_EXPECTED_RESULT = null;

  /**
   * Generates a new (item) map with only the unit price attribute specified.
   * It doesn't matter if the others are missing.
   * @param unitPrice The unit price in a string with two decimal places, e.g. "2.99".
   * @return A map to be used as an item's attributes.
   */
  private static Map<String, Object> generateMap(String unitPrice)
  {
    Map<String, Object> map = new LinkedHashMap<>();
    BigDecimal unitPriceBigDecimal = new BigDecimal(unitPrice);

    map.put(UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME, unitPriceBigDecimal);

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
        {TEST_INPUT1, TEST_INPUT1_EXPECTED_RESULT},
        {TEST_INPUT2, TEST_INPUT2_EXPECTED_RESULT},
        {TEST_INPUT3, TEST_INPUT3_EXPECTED_RESULT},
        {TEST_INPUT4, TEST_INPUT4_EXPECTED_RESULT},
        {TEST_INPUT5, TEST_INPUT5_EXPECTED_RESULT},
      }
    );
  }

  /** What list of item attribues at do you want to input. */
  private final List<Map<String, Object>> inputListOfItems;

  /** What result do you expect. */
  private final BigDecimal expectedResult;

  /**
   * The constructor for this test.
   * @param inputListOfItems What list of item attribues at do you want to input.
   * @param expectedResult What result do you expect.
   */
  public TotalScraperDecoratorGetTotalPriceAttributePresentTest(
      List<Map<String, Object>> inputListOfItems, BigDecimal expectedResult)
  {
    //One of the test cases is null, so nulls are allowed, but only if both are null.
    //Both must be null or non-null to pass.
    if ((inputListOfItems == null) != (expectedResult == null))
    {
      throw new NullPointerException("One parameter is null, while the other is non-null. This isn't allowed.");
    }

    this.inputListOfItems = inputListOfItems;
    this.expectedResult = expectedResult;
  }

  /**
   * Test the to see if the totaling of prices is correct.
   * @throws NullPointerException Only if the input parameter is null.
   */
  @Test
  public void getTotalPriceTest()
  {
    //Test the null pointer input it should throw a null pointer exception
    if (this.inputListOfItems == null)
    {
      this.expectedException.expect(NullPointerException.class);
      BigDecimal actualResult = TotalScraperDecorator.getTotalPrice(this.inputListOfItems);
    }
    //All other valid cases check the totals to see if they are equal.
    else
    {
      BigDecimal actualResult = TotalScraperDecorator.getTotalPrice(this.inputListOfItems);
      Assert.assertEquals("The expected and actual total prices differ.", this.expectedResult, actualResult);
    }
  }

}



