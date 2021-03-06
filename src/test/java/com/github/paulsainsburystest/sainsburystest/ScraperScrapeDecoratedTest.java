package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.scraperdecorators.IScraperDecorator;
import java.io.IOException;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test the {@link IScraperDecorator} implementation in {@link Scraper}
 * @author Paul
 * @see Scraper#scrapeDecorated(java.lang.String)
 */
@RunWith(Parameterized.class)
public class ScraperScrapeDecoratedTest extends AbstractScraperTest
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
  private final Map<String, Object> expectedResult;

  /**
   * The constructor for this test.
   * @param categoryWebpage What category webpage are we mining from.
   * @param expectedResult What result do you expect.
   * @throws NullPointerException If any of the parameters are null.
   */
  public ScraperScrapeDecoratedTest(String categoryWebpage, Map<String, Object> expectedResult)
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
   * Test that the scrapers output has been changed to reflect the decorator.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void scrapeDecoratedTest() throws MalformedDocumentException, IOException
  {
    Scraper scraper = AbstractScraperTest.createDefaultScraperInstance();

    Map<String, Object> actualResult = scraper.scrapeDecorated(this.categoryWebpage);

    Assert.assertEquals("We expected the output to the equal but it wasn't",
        this.expectedResult, actualResult);
  }

}
