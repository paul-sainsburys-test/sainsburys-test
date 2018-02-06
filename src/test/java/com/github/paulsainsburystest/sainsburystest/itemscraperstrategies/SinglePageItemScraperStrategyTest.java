package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.Globals;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test the {@link SinglePageItemScraperStrategy}.
 * @author Paul
 */
@RunWith(Parameterized.class)
public class SinglePageItemScraperStrategyTest extends IItemScraperStrategyAbstractTest<SinglePageItemScraperStrategy>
{

  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
  private static final String[] TEST_URL1_EXPECTED_URLS = new String[]
  {
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--sweet-150g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries--so-organic-150g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherries-350g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--tangy-150g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-strawberries--taste-the-difference-300g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-redcurrants-150g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet--taste-the-difference-250g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html",
    "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html"
  };

  private static final String TEST_URL2 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/bananas-grapes.html";
  private static final String[] TEST_URL2_EXPECTED_URLS = new String[]
  {
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-loose-fairtrade-bananas",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-fairtrade-bananas-x5",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-red-seedless-grapes-500g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-white-seedless-grapes-500g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-fairtrade-bananas-x8",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-eric---friends-small-bananas--fairtrade-x8",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-red---white-seedless-grapes-500g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-fairtrade-bananas--so-organic",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-black-seedless-grapes-500g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-sabel-black-grapes--taste-the-difference-400g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-white-grapes-170g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-red-grapes-170g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-grapes--so-organic-400g-(red-or-black--colour-may-vary-due-to-supply)",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-white-grapes--so-organic-400g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-grape-bag-80g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-white-seedless-grapes-1kg",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-mixed-seedless-grapes-800g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-red-seedless-grapes-1kg",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-grapes-limited-edition--taste-the-difference-400g",
    "http://www.sainsburys.co.uk/shop/gb/groceries/bananas-grapes/sainsburys-black-grapes-700g"
  };

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
        {TEST_URL1, TEST_URL1_EXPECTED_URLS},
        {TEST_URL2, TEST_URL2_EXPECTED_URLS},
      }
    );
  }

  /** Webpage to extract item links from. */
  private final String webpage;

  /** The item links we expect. */
  private final String[] expectedUrls;

  /**
   * Initialise this test.
   * @param webpage The webpage to extract item links from.
   * @param expectedUrls The item links we expect.
   */
  public SinglePageItemScraperStrategyTest(String webpage, String[] expectedUrls)
  {
    if (webpage == null)
    {
      throw new NullPointerException("webpage cannot be null.");
    }
    else if (expectedUrls == null)
    {
      throw new NullPointerException("expectedUrls cannot be null.");
    }

    this.webpage = webpage;

    //Assumes none of the elements are null.
    this.expectedUrls = expectedUrls;
  }

  @Override
  public SinglePageItemScraperStrategy getTestingStrategy()
  {
    return new SinglePageItemScraperStrategy();
  }

  /**
   * Test that all of the items on the category page are expected.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown.
   */
  @Test
  public void getItemUrlsTest() throws IOException, MalformedDocumentException
  {
    SinglePageItemScraperStrategy strategy = this.getTestingStrategy();

    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.webpage);

    List<String> actualList = strategy.getItemUrls(jsoupDocument);

    //We need to:
    //1) Determine that all of the strings in the actual results are the expected
    //   results.
    //2) Determine that all of the strings in the expected results are the actual
    //   results.
    //Note: The solution below also handles duplicates and ignores duplicates.

    HashSet<String> expected = new HashSet<>();
    HashSet<String> actual = new HashSet<>();
    for (String expectedStr : this.expectedUrls)
    {
      expected.add(expectedStr);
    }

    //Determine if all the actual urls are in the expected results.
    for (String actualStr : actualList)
    {
      actual.add(actualStr); //Keep track of every page we see.

      //If an actual string is not present in the expected set fail the test.
      if (expected.contains(actualStr) == false)
      {
        Assert.fail("An item that was in the actual results wasn't in the expected " +
            "results. ("+actualStr+")");
      }
    }

    //Determine if all of the expected results are in the actual results.
    //Actual size would always be less than or equals to expected, as if it was more
    //that means a url was present that was not expected. But that would have been
    //caught above.
    Assert.assertEquals("The number of items that were received was less then what " +
        "we expected.", expected.size(), actual.size());

  }

}
