package com.github.paulsainsburystest.sainsburystest;

import static com.github.paulsainsburystest.sainsburystest.AbstractScraperTest.DEFAULT_SCRAPER_STRATREGY;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import java.io.IOException;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for the extracting items (and their attributes) from a category webpage
 * from {@link Scraper}.
 *
 * FIXME: This needs to test when a link error occurs, though I don't know how to
 * programmically cause and test that in Jsoup.
 *
 * @author Paul
 * @see Scraper
 * @see Scraper#scrape(java.lang.String)
 */
@RunWith(Parameterized.class)
public class ScraperScrapeTest extends AbstractScraperTest
{

  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
  private static final List<Map<String, Object>> TEST_URL1_EXPECTED_RESULT;
  static
  {
    List<Map<String, Object>> list = new LinkedList<>();
    TEST_URL1_EXPECTED_RESULT = Collections.unmodifiableList(list);

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
        "Sainsbury's Strawberries, Taste the Difference 300g", "Ttd strawberries", "2.50", 27));

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

  }


  /**
   * Parameters to initialise each test with.
   * @return A list of Object arrays
   */
  @Parameterized.Parameters
  public static List<Object[]> data()
  {
    return Arrays.asList(
      new Object[][]{
        {TEST_URL1, TEST_URL1_EXPECTED_RESULT},
      }
    );
  }

  /** What category webpage are we mining from. */
  private final String categoryWebpage;

  /** What result do you expect. */
  private final List<Map<String, Object>> expectedResult;

  /**
   * The constructor for this test.
   * @param categoryWebpage What category webpage are we mining from.
   * @param expectedResult What result do you expect.
   * @throws NullPointerException If any of the parameters are null.
   */
  public ScraperScrapeTest(String categoryWebpage, List<Map<String, Object>> expectedResult)
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
   * Test scraping a null string.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws NullPointerException Always thrown
   * @see Scraper#scrape(java.lang.String)
   */
  @Test
  public void scrapeNullUrlTest() throws IOException, MalformedDocumentException
  {
    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);

    super.expectedException.expect(NullPointerException.class);
    super.expectedException.expectMessage("categoryUrl cannot be null");
    List<Map<String, Object>> actualResult = scraper.scrape(null);

  }

  /**
   * Test scraping a valid category webpage.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @see Scraper#scrape(java.lang.String)
   */
  @Test
  public void scrapeTest() throws IOException, MalformedDocumentException
  {
    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);

    List<Map<String, Object>> actualResult = scraper.scrape(this.categoryWebpage);

    //This is a complex data structure, it's hard to precisely say where the difference
    //it without resorting to extra complexity here. (Which should be avoided.)
    Assert.assertEquals("We expected the returned values to be the same but they were different",
        this.expectedResult, actualResult);
  }

  /**
   * Test when an attribute strategy throws a {@link MalformedDocumentException}.
   * The exception bubbles up and out of the method without being caught.
   * Don't need to check for chained exceptions as that's done elsewhere.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrape(java.lang.String)
   */
  @Test
  public void scrapeAttributeScraperStrategyThrowsAnExceptionTest()
      throws IOException, MalformedDocumentException
  {
    LinkedHashSet<IItemAttributeScraperStrategy<?>> set = new LinkedHashSet<>();
    set.add(new MalformedAttributeItemScraperStrategy());

    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, set);

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("An exception was thrown when trying to extract an attribute from a webpage.");
    List<Map<String, Object>> actualResult = scraper.scrape(this.categoryWebpage);
  }

  /**
   * Test when an item scraper strategy throws a {@link MalformedDocumentException}.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrape(java.lang.String)
   */
  @Test
  public void scrapeItemExtractionStrategyThrowsAnExceptionTest()
      throws IOException, MalformedDocumentException
  {
    MalformedItemScraperStrategy categoryStrategy = new MalformedItemScraperStrategy();

    Scraper scraper = new Scraper(categoryStrategy, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);

    //Expect a chained exception.
    //This exception -> the original exception.
    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("An exception was thrown when trying to extract items from a webpage.");
    Map<String, Object> actualResult = scraper.scrapeItemPage(this.categoryWebpage);
  }

  /**
   * Test when an item scraper strategy throws a {@link MalformedDocumentException},
   * see if that contains a chained {@link MalformedDocumentException}
   * from the {@link IItemScraperStrategy} implementation.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrape(java.lang.String)
   */
  @Test
  public void scrapeItemExtractionStrategyThrowsAChainedExceptionTest()
      throws IOException, MalformedDocumentException
  {
    MalformedItemScraperStrategy categoryStrategy = new MalformedItemScraperStrategy();

    Scraper scraper = new Scraper(categoryStrategy, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);

    //Expect a chained exception.
    //This exception -> the original exception.
    try
    {
      //This will throw an exception
      Map<String, Object> actualResult = scraper.scrapeItemPage(this.categoryWebpage);
      Assert.fail("A MalformedDocumentException was not thrown.");
    }
    catch (MalformedDocumentException ex)
    {
      //This will cause a class class cast except if it's the wrong type.
      MalformedDocumentException chainedException = (MalformedDocumentException) ex.getCause();

      //We can't predict the message.
      this.expectedException.expect(MalformedDocumentException.class);
      this.expectedException.expectMessage(MalformedItemScraperStrategy.EXCEPTION_MESSAGE);

      //If there was no chained exception this will throw a null pointer exception
      //which is good.
      throw chainedException;
    }
  }

}
