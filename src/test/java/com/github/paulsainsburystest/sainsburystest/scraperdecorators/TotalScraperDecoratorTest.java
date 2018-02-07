package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.AbstractScraperTest;
import static com.github.paulsainsburystest.sainsburystest.AbstractScraperTest.DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES;
import static com.github.paulsainsburystest.sainsburystest.AbstractScraperTest.DEFAULT_SCRAPER_STRATREGY;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import com.github.paulsainsburystest.sainsburystest.Scraper;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for the class {@link TotalScraperDecorator}.
 *
 * @author Paul
 * @see TotalScraperDecorator 
 */
@RunWith(Parameterized.class)
public class TotalScraperDecoratorTest extends AbstractScraperDecoratorTest
{

  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
  private static final Map<String, Object> TEST_URL1_EXPECTED_RESULT;
  static
  {
    //Real it's stored like Map<String,List<Map<String, Object>>>
    Map<String, Object> map = new LinkedHashMap<>();
    TEST_URL1_EXPECTED_RESULT = Collections.unmodifiableMap(map);

    List<Map<String, Object>> list = new LinkedList<>();
    //ORDERING IS IMPORTANT.
    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Strawberries 400g", "by Sainsbury's strawberries", "1.75", 33));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blueberries 200g", "by Sainsbury's blueberries", "1.75", 45));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Raspberries 225g", "by Sainsbury's raspberries", "1.75", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--sweet-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blackberries, Sweet 150g", "by Sainsbury's blackberries", "1.50", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blueberries 400g", "by Sainsbury's blueberries", "3.25", 45));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries--so-organic-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blueberries, SO Organic 150g", "So Organic blueberries", "2.00", 45));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Raspberries, Taste the Difference 150g", "Ttd raspberries", "2.50", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherries-350g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Cherries 400g", "by Sainsbury's Family Cherry Punnet", "2.50", 52));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--tangy-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blackberries, Tangy 150g", "by Sainsbury's blackberries", "1.50", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-strawberries--taste-the-difference-300g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Strawberries, Taste the Difference 300g", "Ttd strawberries", "2.50", 33));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Cherry Punnet 200g", "Cherries", "1.50", 52));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Mixed Berries 300g", "by Sainsbury's mixed berries", "3.50"));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Mixed Berry Twin Pack 200g", "Mixed Berries", "2.75"));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-redcurrants-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Redcurrants 150g", "by Sainsbury's redcurrants", "2.50", 71));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet--taste-the-difference-250g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Cherry Punnet, Taste the Difference 200g", "Cherry Punnet", "2.50", 48));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blackcurrants 150g", "Union Flag", "1.75"));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's British Cherry & Strawberry Pack 600g",
        "British Cherry & Strawberry Mixed Pack", "4.00"));

    //Make the list immutable.
    list = Collections.unmodifiableList(list);

    map.put("results", list);

    map.put(TotalScraperDecorator.DECORATOR_NAME, new BigDecimal("39.50"));

  }

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
        {TEST_URL1, TEST_URL1_EXPECTED_RESULT},
      }
    );
  }

  /** What category webpage are we mining from. */
  private final String categoryWebpage;

  /** What result do you expect. */
  private final Map<String, Object> expectedResult;

  /**
   * The constructor for this test.
   * @param categoryWebpage What category webpage are we mining from.
   * @param expectedResult What result do you expect.
   * @throws NullPointerException If any of the parameters are null.
   */
  public TotalScraperDecoratorTest(String categoryWebpage, Map<String, Object> expectedResult)
  {
    if (categoryWebpage == null)
    {
      throw new NullPointerException("categoryWebpage cannot be null.");
    }
    else if (expectedResult == null)
    {
      throw new NullPointerException("expectedResult cannot be null.");
    }

    this.categoryWebpage = categoryWebpage;
    this.expectedResult = expectedResult;
  }

  /**
   * Test the constructor with a valid non-null parameter.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void totalScraperDecoratorConstructorTest() throws MalformedDocumentException, IOException
  {
    Scraper scraper = AbstractScraperTest.createDefaultScraperInstance();
    TotalScraperDecorator totalScraperDecorator = new TotalScraperDecorator(scraper);
  }

  /**
   * Test the constructor with a valid non-null parameter.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   * @throws NullPointerException Always thrown.
   */
  @Test
  public void totalScraperDecoratorConstructorNullTest() throws MalformedDocumentException, IOException
  {
    super.expectedException.expect(NullPointerException.class);
    super.expectedException.expectMessage("nextScraperDecorator cannot be null");
    TotalScraperDecorator totalScraperDecorator = new TotalScraperDecorator(null);
  }

  /**
   * Test that the input has changed and that it includes a new key/value pair "total".
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void scrapeDecoratedTest() throws MalformedDocumentException, IOException
  {
    Scraper scraper = AbstractScraperTest.createDefaultScraperInstance();
    TotalScraperDecorator totalScraperDecorator = new TotalScraperDecorator(scraper);

    Map<String, Object> actualResult = totalScraperDecorator.scrapeDecorated(this.categoryWebpage);

    Assert.assertEquals("We expected the output to the equal but it wasn't",
        this.expectedResult, actualResult);
  }


  //The class does not handle if exceptions are thrown they just bubble up the stack.

  /**
   * Test when an attribute strategy throws a {@link MalformedDocumentException}.
   * It shouldn't be caught by this class.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrapeItemPage(java.lang.String)
   */
  @Test
  public void scrapeDecoratedItemPageStrategyThrowsAnExceptionTest() throws IOException, MalformedDocumentException
  {
    LinkedHashSet<IItemAttributeScraperStrategy<?>> set = new LinkedHashSet<>();
    set.add(new AbstractScraperTest.MalformedAttributeItemScraperStrategy());

    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, set);
    TotalScraperDecorator totalScraperDecorator = new TotalScraperDecorator(scraper);

    //Expect a chained exception.
    //This exception -> the original exception.
    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("An exception was thrown when trying to extract an attribute from a webpage.");
    Map<String, Object> actualResult = totalScraperDecorator.scrapeDecorated(this.categoryWebpage);
  }

  /**
   * Test when an attribute strategy throws a {@link MalformedDocumentException},
   * see if that contains a chained {@link MalformedDocumentException}
   * from the {@link IItemAttributeScraperStrategy} implementation.
   * It shouldn't be caught by this class.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrapeItemPage(java.lang.String)
   */
  @Test
  public void scrapeDecoratedItemPageStrategyThrowsAChainedExceptionTest() throws IOException, MalformedDocumentException
  {
    LinkedHashSet<IItemAttributeScraperStrategy<?>> set = new LinkedHashSet<>();
    set.add(new AbstractScraperTest.MalformedAttributeItemScraperStrategy());

    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, set);
    TotalScraperDecorator totalScraperDecorator = new TotalScraperDecorator(scraper);

    //Expect a chained exception.
    //This exception -> the original exception.
    try
    {
      //This will throw an exception
      Map<String, Object> actualResult = totalScraperDecorator.scrapeDecorated(this.categoryWebpage);
      Assert.fail("A MalformedDocumentException was not thrown.");
    }
    catch (MalformedDocumentException ex)
    {
      //This will cause a class class cast except if it's the wrong type.
      MalformedDocumentException chainedException = (MalformedDocumentException) ex.getCause();

      //We can't predict the message.
      this.expectedException.expect(MalformedDocumentException.class);
      this.expectedException.expectMessage(AbstractScraperTest.MalformedAttributeItemScraperStrategy.EXCEPTION_MESSAGE);

      //If there was no chained exception this will throw a null pointer exception
      //which is good.
      throw chainedException;
    }
  }


  /**
   * Test when an item scraper strategy throws a {@link MalformedDocumentException}.
   * It shouldn't be caught by this class.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrape(java.lang.String)
   */
  @Test
  public void scrapeDecoratedItemExtractionStrategyThrowsAnExceptionTest()
      throws IOException, MalformedDocumentException
  {
    AbstractScraperTest.MalformedItemScraperStrategy categoryStrategy = new AbstractScraperTest.MalformedItemScraperStrategy();

    Scraper scraper = new Scraper(categoryStrategy, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);
    TotalScraperDecorator totalScraperDecorator = new TotalScraperDecorator(scraper);

    //Expect a chained exception.
    //This exception -> the original exception.
    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("An exception was thrown when trying to extract items from a webpage.");
    Map<String, Object> actualResult = totalScraperDecorator.scrapeDecorated(this.categoryWebpage);
  }

  /**
   * Test when an item scraper strategy throws a {@link MalformedDocumentException},
   * see if that contains a chained {@link MalformedDocumentException}
   * from the {@link IItemScraperStrategy} implementation.
   * It shouldn't be caught by this class.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrape(java.lang.String)
   */
  @Test
  public void scrapeDecoratedItemExtractionStrategyThrowsAChainedExceptionTest()
      throws IOException, MalformedDocumentException
  {
    AbstractScraperTest.MalformedItemScraperStrategy categoryStrategy = new AbstractScraperTest.MalformedItemScraperStrategy();

    Scraper scraper = new Scraper(categoryStrategy, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);
    TotalScraperDecorator totalScraperDecorator = new TotalScraperDecorator(scraper);

    //Expect a chained exception.
    //This exception -> the original exception.
    try
    {
      //This will throw an exception
      Map<String, Object> actualResult = totalScraperDecorator.scrapeDecorated(this.categoryWebpage);
      Assert.fail("A MalformedDocumentException was not thrown.");
    }
    catch (MalformedDocumentException ex)
    {
      //This will cause a class class cast except if it's the wrong type.
      MalformedDocumentException chainedException = (MalformedDocumentException) ex.getCause();

      this.expectedException.expect(MalformedDocumentException.class);
      this.expectedException.expectMessage(AbstractScraperTest.MalformedItemScraperStrategy.EXCEPTION_MESSAGE);

      //If there was no chained exception this will throw a null pointer exception
      //which is good.
      throw chainedException;
    }
  }

}
