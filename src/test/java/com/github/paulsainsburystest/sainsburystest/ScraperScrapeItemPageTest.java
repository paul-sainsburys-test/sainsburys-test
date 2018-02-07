package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for the extracting attributes from a single webpage from {@link Scraper}.
 *
 * FIXME: This needs to test when a link error occurs, though I don't know how to
 * programmically cause and test that in Jsoup.
 *
 * @author Paul
 * @see Scraper
 * @see Scraper#scrapeItemPage(java.lang.String)
 * @see Scraper#scrapeItemPagePrivate(java.lang.String)
 */
@RunWith(Parameterized.class)
public class ScraperScrapeItemPageTest extends AbstractScraperTest
{
  //Has kcal
  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html";
  private static final Map<String, Object> TEST_URL1_EXPECTED_RESULT;
  static
  {
    //Linked as it'll iterating over this.
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    TEST_URL1_EXPECTED_RESULT = Collections.unmodifiableMap(map);

    map.put(TitleAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Sainsbury's Blueberries 400g");
    map.put(KilocaloriesAttributeItemScraperStrategy.ATTRIBUTE_NAME, 45);
    map.put(UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME, new BigDecimal("3.25"));
    map.put(DescriptionAttributeItemScraperStrategy.ATTRIBUTE_NAME, "by Sainsbury's blueberries");
  }

  //Has kcal
  private static final String TEST_URL2 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html";
  private static final Map<String, Object> TEST_URL2_EXPECTED_RESULT;
  static
  {
    //Linked as it'll iterating over this.
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    TEST_URL2_EXPECTED_RESULT = Collections.unmodifiableMap(map);

    map.put(TitleAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Sainsbury's Cherry Punnet 200g");
    map.put(KilocaloriesAttributeItemScraperStrategy.ATTRIBUTE_NAME, 52);
    map.put(UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME, new BigDecimal("1.50"));
    map.put(DescriptionAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Cherries");
  }

  //Missing kcal
  private static final String TEST_URL3 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html";
  private static final Map<String, Object> TEST_URL3_EXPECTED_RESULT;
  static
  {
    //Linked as it'll iterating over this.
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    TEST_URL3_EXPECTED_RESULT = Collections.unmodifiableMap(map);

    map.put(TitleAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Sainsbury's Blackcurrants 150g");
    map.put(UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME, new BigDecimal("1.75"));
    map.put(DescriptionAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Union Flag");
  }

  //Missing kcal
  private static final String TEST_URL4 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html";
  private static final Map<String, Object> TEST_URL4_EXPECTED_RESULT;
  static
  {
    //Linked as it'll iterating over this.
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    TEST_URL4_EXPECTED_RESULT = Collections.unmodifiableMap(map);

    map.put(TitleAttributeItemScraperStrategy.ATTRIBUTE_NAME, "Sainsbury's Mixed Berries 300g");
    map.put(UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME, new BigDecimal("3.50"));
    map.put(DescriptionAttributeItemScraperStrategy.ATTRIBUTE_NAME, "by Sainsbury's mixed berries");
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
        {TEST_URL2, TEST_URL2_EXPECTED_RESULT},
        {TEST_URL3, TEST_URL3_EXPECTED_RESULT},
        {TEST_URL4, TEST_URL4_EXPECTED_RESULT},
      }
    );
  }


  /** What single webpage do we want to scrape. */
  private final String singleWebpageToScrape;

  /** What is the expected result from scraping that one webpage. */
  private final Map<String, Object> singleWebpageToScrapeExpectedResult;

  /**
   * Constructor for this test.
   * @param singleWebpageToScrape What single webpage do we want to scrape.
   * @param singleWebpageToScrapeExpectedResult What is the expected result from
   *    scraping that one webpage.
   * @throws NullPointerException If any parameter is null.
   */
  public ScraperScrapeItemPageTest(String singleWebpageToScrape, Map<String, Object> singleWebpageToScrapeExpectedResult)
  {
    if (singleWebpageToScrape == null)
    {
      throw new NullPointerException("singleWebpageToScrape cannot be null.");
    }
    else if (singleWebpageToScrapeExpectedResult == null)
    {
      throw new NullPointerException("singleWebpageToScrapeExpectedResult cannot be null.");
    }

    this.singleWebpageToScrape = singleWebpageToScrape;
    this.singleWebpageToScrapeExpectedResult = singleWebpageToScrapeExpectedResult;
  }


  /**
   * Test when the parameter is null for the scraping of a single page.
   * @throws java.io.IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws NullPointerException Always thrown.
   * @see Scraper#scrapeItemPage(java.lang.String)
   */
  @Test
  public void scrapeItemPageNullParameterTest() throws IOException, MalformedDocumentException
  {
    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);

    this.expectedException.expect(NullPointerException.class);
    this.expectedException.expectMessage("url cannot be null");
    Map<String, Object> actualResult = scraper.scrapeItemPage(null);
  }

  /**
   * Test to see if the selected page brings back valid results.
   * This also handles if an attribute is allowed to return a null attribute and
   * does return a null attribute.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown.
   */
  @Test
  public void scrapeItemPageTest() throws IOException, MalformedDocumentException
  {
    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);

    Map<String, Object> actualResult = scraper.scrapeItemPage(this.singleWebpageToScrape);

    Assert.assertEquals("We expected the map of attributes to be the same but they weren't.",
        this.singleWebpageToScrapeExpectedResult, actualResult);
  }

  /**
   * Test when an attribute strategy throws a {@link MalformedDocumentException}.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrapeItemPage(java.lang.String)
   */
  @Test
  public void scrapeItemPageStrategyThrowsAnExceptionTest() throws IOException, MalformedDocumentException
  {
    LinkedHashSet<IItemAttributeScraperStrategy<?>> set = new LinkedHashSet<>();
    set.add(new MalformedAttributeItemScraperStrategy());

    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, set);

    //Expect a chained exception.
    //This exception -> the original exception.
    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("An exception was thrown when trying to extract an attribute from a webpage.");
    Map<String, Object> actualResult = scraper.scrapeItemPage(this.singleWebpageToScrape);
  }

  /**
   * Test when an attribute strategy throws a {@link MalformedDocumentException},
   * see if that contains a chained {@link MalformedDocumentException}
   * from the {@link IItemAttributeScraperStrategy} implementation.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Always thrown.
   * @see Scraper#scrapeItemPage(java.lang.String)
   */
  @Test
  public void scrapeItemPageStrategyThrowsAChainedExceptionTest() throws IOException, MalformedDocumentException
  {
    LinkedHashSet<IItemAttributeScraperStrategy<?>> set = new LinkedHashSet<>();
    set.add(new MalformedAttributeItemScraperStrategy());

    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, set);

    //Expect a chained exception.
    //This exception -> the original exception.
    try
    {
      //This will throw an exception
      Map<String, Object> actualResult = scraper.scrapeItemPage(this.singleWebpageToScrape);
      Assert.fail("A MalformedDocumentException was not thrown.");
    }
    catch (MalformedDocumentException ex)
    {
      //This will cause a class class cast except if it's the wrong type.
      MalformedDocumentException chainedException = (MalformedDocumentException) ex.getCause();

      //We can't predict the message.
      this.expectedException.expect(MalformedDocumentException.class);

      //If there was no chained exception this will throw a null pointer exception
      //which is good.
      throw chainedException;
    }
  }

}
