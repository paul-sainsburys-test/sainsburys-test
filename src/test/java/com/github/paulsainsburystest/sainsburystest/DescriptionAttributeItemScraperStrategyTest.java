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
 * Test for getting an item's description from a webpage.
 * @author Paul
 */
@RunWith(Parameterized.class)
public class DescriptionAttributeItemScraperStrategyTest
    extends IItemAttributeScraperStrategyAbstractTest<DescriptionAttributeItemScraperStrategy>
{

  //Has mainPart and itemTypeGroup
  //First <p> blank
  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html";
  private static final String TEST_URL1_EXPECTED_DESCRIPTION = "Union Flag";

  //Has mainPart but does not have itemTypeGroup
  private static final String TEST_URL2 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html";
  private static final String TEST_URL2_EXPECTED_DESCRIPTION = "Mixed Berries";

  private static final String TEST_URL3 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html";
  private static final String TEST_URL3_EXPECTED_DESCRIPTION = "British Cherry & Strawberry Mixed Pack";

  private static final String TEST_URL4 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html";
  private static final String TEST_URL4_EXPECTED_DESCRIPTION = "Cherries";

  //Does not have main part (multi-line).
  private static final String TEST_URL5 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries--so-organic-150g.html";
  private static final String TEST_URL5_EXPECTED_DESCRIPTION = "So Organic blueberries";

  //Does not have main part (single line)
  private static final String TEST_URL6 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html";
  private static final String TEST_URL6_EXPECTED_DESCRIPTION = "by Sainsbury's blueberries";

  private static final String TEST_URL7 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
  private static final String TEST_URL7_EXPECTED_DESCRIPTION = "by Sainsbury's strawberries";

  private static final String TEST_URL8 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--sweet-150g.html";
  private static final String TEST_URL8_EXPECTED_DESCRIPTION = "by Sainsbury's blackberries";

  private static final String TEST_URL9 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html";
  private static final String TEST_URL9_EXPECTED_DESCRIPTION = "by Sainsbury's blueberries";

  private static final String TEST_URL10 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html";
  private static final String TEST_URL10_EXPECTED_DESCRIPTION = "by Sainsbury's raspberries";

  private static final String TEST_URL11 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html";
  private static final String TEST_URL11_EXPECTED_DESCRIPTION = "Ttd raspberries";

  private static final String TEST_URL12 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherries-350g.html";
  private static final String TEST_URL12_EXPECTED_DESCRIPTION = "by Sainsbury's Family Cherry Punnet";

  private static final String TEST_URL13 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet--taste-the-difference-250g.html";
  private static final String TEST_URL13_EXPECTED_DESCRIPTION = "Cherry Punnet ";

  private static final String TEST_URL14 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-redcurrants-150g.html";
  private static final String TEST_URL14_EXPECTED_DESCRIPTION = "by Sainsbury's redcurrants";

  private static final String TEST_URL15 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--tangy-150g.html";
  private static final String TEST_URL15_EXPECTED_DESCRIPTION = "by Sainsbury's blackberries";

  private static final String TEST_URL16 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-strawberries--taste-the-difference-300g.html";
  private static final String TEST_URL16_EXPECTED_DESCRIPTION = "Ttd strawberries";

  private static final String TEST_URL17 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html";
  private static final String TEST_URL17_EXPECTED_DESCRIPTION = "by Sainsbury's mixed berries";


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
        {TEST_URL1, TEST_URL1_EXPECTED_DESCRIPTION},
        {TEST_URL2, TEST_URL2_EXPECTED_DESCRIPTION},
        {TEST_URL3, TEST_URL3_EXPECTED_DESCRIPTION},
        {TEST_URL4, TEST_URL4_EXPECTED_DESCRIPTION},
        {TEST_URL5, TEST_URL5_EXPECTED_DESCRIPTION},
        {TEST_URL6, TEST_URL6_EXPECTED_DESCRIPTION},
        {TEST_URL7, TEST_URL7_EXPECTED_DESCRIPTION},
        {TEST_URL8, TEST_URL8_EXPECTED_DESCRIPTION},
        {TEST_URL9, TEST_URL9_EXPECTED_DESCRIPTION},
        {TEST_URL10, TEST_URL10_EXPECTED_DESCRIPTION},
        {TEST_URL11, TEST_URL11_EXPECTED_DESCRIPTION},
        {TEST_URL12, TEST_URL12_EXPECTED_DESCRIPTION},
        {TEST_URL13, TEST_URL13_EXPECTED_DESCRIPTION},
        {TEST_URL14, TEST_URL14_EXPECTED_DESCRIPTION},
        {TEST_URL15, TEST_URL15_EXPECTED_DESCRIPTION},
        {TEST_URL16, TEST_URL16_EXPECTED_DESCRIPTION},
        {TEST_URL17, TEST_URL17_EXPECTED_DESCRIPTION}
      }
    );
  }

  /** Webpage to download and analyse. */
  private final String url;

  /** The item description we expect. */
  private final String expectedDescription;

  /**
   * Constructor for this test.
   * @param url Webpage to download and analyse.
   * @param expectedDescription The item description we expect.
   * @throws NullPointerException If any parameter is null.
   */
  public DescriptionAttributeItemScraperStrategyTest(String url, String expectedDescription)
  {
    if (url == null)
    {
      throw new NullPointerException("url cannot be null.");
    }
    else if (expectedDescription == null)
    {
      throw new NullPointerException("expectedDescription cannot be null.");
    }

    this.url = url;
    this.expectedDescription = expectedDescription;
  }


  @Override
  public DescriptionAttributeItemScraperStrategy getTestingStrategy()
  {
    return new DescriptionAttributeItemScraperStrategy();
  }


  /**
   * Retrieve the webpage of the url and compare it with what we expect.
   */
  @Test
  public void getAttributeDescriptionTest()
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

    String actualDescription = this.getTestingStrategy().getAttribute(jsoupDocument);

    Assert.assertEquals("The expected item description differs from the actual one.",
        this.expectedDescription, actualDescription);
  }


}
