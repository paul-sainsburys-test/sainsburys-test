package com.github.paulsainsburystest.sainsburystest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for getting an item's title from a webpage.
 * @author Paul
 */
@RunWith(Parameterized.class)
public class TitleAttributeItemScraperStrategyTest
    extends IItemAttributeScraperStrategyAbstractTest<TitleAttributeItemScraperStrategy>
{

  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
  private static final String TEST_URL1_EXPECTED_TITLE = "Sainsbury's Strawberries 400g";

  private static final String TEST_URL2 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html";
  private static final String TEST_URL2_EXPECTED_TITLE = "Sainsbury's Blueberries 400g";

  private static final String TEST_URL3 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--tangy-150g.html";
  private static final String TEST_URL3_EXPECTED_TITLE = "Sainsbury's Blackberries, Tangy 150g";

  private static final String TEST_URL4 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html";
  private static final String TEST_URL4_EXPECTED_TITLE = "Sainsbury's Mixed Berry Twin Pack 200g";

  /**
   * Parameters to initialise each test with.
   * @return A list of Object arrays
   */
  @Parameterized.Parameters
  public static List<Object[]> data()
  {
    return Arrays.asList(
      new Object[][]{
        {TEST_URL1, TEST_URL1_EXPECTED_TITLE},
        {TEST_URL2, TEST_URL2_EXPECTED_TITLE},
        {TEST_URL3, TEST_URL3_EXPECTED_TITLE},
        {TEST_URL4, TEST_URL4_EXPECTED_TITLE},
      }
    );
  }

  /** Webpage to download and analyse. */
  private final String url;

  /** The item title we expect. */
  private final String expectedTitle;

  /**
   * Constructor for this test.
   * @param url Webpage to download and analyse.
   * @param expectedTitle The item title we expect.
   * @throws NullPointerException If any parameter is null.
   */
  public TitleAttributeItemScraperStrategyTest(String url, String expectedTitle)
  {
    if (url == null)
    {
      throw new NullPointerException("url cannot be null.");
    }
    else if (expectedTitle == null)
    {
      throw new NullPointerException("expectedTitle cannot be null.");
    }

    this.url = url;
    this.expectedTitle = expectedTitle;
  }


  @Override
  public TitleAttributeItemScraperStrategy getTestingStrategy()
  {
    return new TitleAttributeItemScraperStrategy();
  }


  /**
   * Retrieve the webpage of the url and compare it with what we expect.
   */
  @Test
  public void getAttributeTitleTest()
  {
    Document jsoupDocument;
    try
    {
      jsoupDocument = Jsoup.connect(this.url).get();
    }
    catch (IOException ex)
    {
      Assert.fail("IOException was thrown when requesting a webpage ("+this.url+").");
      return;
    }

    String actualTitle = this.getTestingStrategy().getAttribute(jsoupDocument);

    Assert.assertEquals("The expected item title differs from the actual one.",
        this.expectedTitle, actualTitle);
  }


}
