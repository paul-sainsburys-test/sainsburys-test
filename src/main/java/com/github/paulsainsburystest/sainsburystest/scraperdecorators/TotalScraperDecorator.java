package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.UnitPriceAttributeItemScraperStrategy;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Implement the {@link Scraper} decorator that gives the sum of the prices
 * from all of the items (assume buy x1 from each list element).
 *
 * FIXME: The set of attribute scraper strategies may not contain an instance of
 * {@link UnitPriceAttributeItemScraperStrategy} hence we would be unable to extract
 * it. There are two options either ignore it or throw an exception.
 * Ignoring it would be bad as you've specified that you want to use the unit prices
 * by using this decorator, therefore throwing an exception would be best.
 * As this cannot be fixed/realistically resolved at runtime a runtime exception
 * would be best.
 *
 * @author Paul
 */
public class TotalScraperDecorator extends AbstractScraperDecorator
{
  /** This decorators name. */
  public static final String DECORATOR_NAME = "total";

  /**
   * The constructor for this class.
   * @param nextScraperDecorator The next scraper decorator to be called in this chain.
   * @throws NullPointerException If the parameter is null.
   */
  public TotalScraperDecorator(IScraperDecorator nextScraperDecorator)
  {
    super(nextScraperDecorator);
  }

  @Override
  public Map<String, Object> scrapeDecorated(String categoryUrl) throws MalformedDocumentException, IOException
  {
    Map<String, Object> returnedValue = this.nextScraperDecorator.scrapeDecorated(categoryUrl);

    List<Map<String,Object>> list = (List<Map<String,Object>>)
        returnedValue.get(IScraperDecorator.SCRAPER_RESULTS_KEY_NAME);

    BigDecimal result = TotalScraperDecorator.getTotalPrice(list);
    returnedValue.put(TotalScraperDecorator.DECORATOR_NAME, result);

    return returnedValue;
  }


  /**
   * Get the total price of all items in the list.
   * @param itemList The list of items to check.
   * @return A big decimal of the with the sum.
   */
  public static BigDecimal getTotalPrice(List<Map<String,Object>> itemList)
  {
    //Initially there is no price.
    //Use this instead of the zero constant as the precision to two decimal places is needed.
    BigDecimal price = new BigDecimal("0.00");

    for (Map<String,Object> itemAttributes : itemList)
    {
      //We know this parameter will be BigDecimal so it's safe to cast it
      BigDecimal itemPrice = (BigDecimal) itemAttributes.get(UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME);

      //Set the result to the price as the class is immutable.
      price = price.add(itemPrice);
    }

    return price;
  }

}
