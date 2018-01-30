package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

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
 * Test for getting an item's kcal per 100g from a webpage.
 * @author Paul
 */
@RunWith(Parameterized.class)
public class KilocaloriesAttributeItemScraperStrategyTest
    extends IItemAttributeScraperStrategyAbstractTest<KilocaloriesAttributeItemScraperStrategy>
{

  //3 columns coloured.
  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html";
  private static final Integer TEST_URL1_EXPECTED_KCAL = 45;

  private static final String TEST_URL2 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
  private static final Integer TEST_URL2_EXPECTED_KCAL = 33;

  private static final String TEST_URL3 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--sweet-150g.html";
  private static final Integer TEST_URL3_EXPECTED_KCAL = 32;

  //100g in first column? No colour
  private static final String TEST_URL4= "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html";
  private static final Integer TEST_URL4_EXPECTED_KCAL = 45;

  //No colour without typical values
  private static final String TEST_URL5 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html";
  private static final Integer TEST_URL5_EXPECTED_KCAL = 32;

  private static final String TEST_URL6 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries--so-organic-150g.html";
  private static final Integer TEST_URL6_EXPECTED_KCAL = 45;

  private static final String TEST_URL7 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html";
  private static final Integer TEST_URL7_EXPECTED_KCAL = 32;

  //With typical values
  private static final String TEST_URL8 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherries-350g.html";
  private static final Integer TEST_URL8_EXPECTED_KCAL = 52;

  private static final String TEST_URL9 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet--taste-the-difference-250g.html";
  private static final Integer TEST_URL9_EXPECTED_KCAL = 48;

  private static final String TEST_URL10 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-redcurrants-150g.html";
  private static final Integer TEST_URL10_EXPECTED_KCAL = 71;

  //Without typical values another column
  private static final String TEST_URL11 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--tangy-150g.html";
  private static final Integer TEST_URL11_EXPECTED_KCAL = 32;

  //Without typical values another column colour
  private static final String TEST_URL12 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-strawberries--taste-the-difference-300g.html";
  private static final Integer TEST_URL12_EXPECTED_KCAL = 33;

  //Not kcal only numbers
  private static final String TEST_URL13 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html";
  private static final Integer TEST_URL13_EXPECTED_KCAL = 52;

  //No info
  private static final String TEST_URL14 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html";
  private static final Integer TEST_URL14_EXPECTED_KCAL = null;

  private static final String TEST_URL15 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html";
  private static final Integer TEST_URL15_EXPECTED_KCAL = null;

  private static final String TEST_URL16 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html";
  private static final Integer TEST_URL16_EXPECTED_KCAL = null;

  private static final String TEST_URL17 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html";
  private static final Integer TEST_URL17_EXPECTED_KCAL = null;


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
        {TEST_URL1, TEST_URL1_EXPECTED_KCAL},
        {TEST_URL2, TEST_URL2_EXPECTED_KCAL},
        {TEST_URL3, TEST_URL3_EXPECTED_KCAL},
        {TEST_URL4, TEST_URL4_EXPECTED_KCAL},
        {TEST_URL5, TEST_URL5_EXPECTED_KCAL},
        {TEST_URL6, TEST_URL6_EXPECTED_KCAL},
        {TEST_URL7, TEST_URL7_EXPECTED_KCAL},
        {TEST_URL8, TEST_URL8_EXPECTED_KCAL},
        {TEST_URL9, TEST_URL9_EXPECTED_KCAL},
        {TEST_URL10, TEST_URL10_EXPECTED_KCAL},
        {TEST_URL11, TEST_URL11_EXPECTED_KCAL},
        {TEST_URL12, TEST_URL12_EXPECTED_KCAL},
        {TEST_URL13, TEST_URL13_EXPECTED_KCAL},
        {TEST_URL14, TEST_URL14_EXPECTED_KCAL},
        {TEST_URL15, TEST_URL15_EXPECTED_KCAL},
        {TEST_URL16, TEST_URL16_EXPECTED_KCAL},
        {TEST_URL17, TEST_URL17_EXPECTED_KCAL}
      }
    );
  }

  /** Webpage to download and analyse. */
  private final String url;

  /** The item kcal value we expect. */
  private final Integer expectedKcal;

  /**
   * Constructor for this test.
   * @param url Webpage to download and analyse.
   * @param expectedKcal The item kcal value we expect (can be null).
   * @throws NullPointerException If url parameter is null.
   */
  public KilocaloriesAttributeItemScraperStrategyTest(String url, Integer expectedKcal)
  {
    if (url == null)
    {
      throw new NullPointerException("url cannot be null.");
    }

    this.url = url;
    this.expectedKcal = expectedKcal;
  }


  @Override
  public KilocaloriesAttributeItemScraperStrategy getTestingStrategy()
  {
    return new KilocaloriesAttributeItemScraperStrategy();
  }


  /**
   * Retrieve the webpage of the url and compare it with what we expect.
   */
  @Test
  public void getAttributeKcalTest()
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

    Integer actualKcal = this.getTestingStrategy().getAttribute(jsoupDocument);

    Assert.assertEquals("The expected item kcal value differs from the actual one.",
        this.expectedKcal, actualKcal);
  }


}
