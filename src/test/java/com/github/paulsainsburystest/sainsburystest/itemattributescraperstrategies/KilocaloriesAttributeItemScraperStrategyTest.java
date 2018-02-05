package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.Globals;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for getting an item's kcal per 100g from a webpage.
 *
 * FIXME: If there is sufficent time the pattern matching could be separated into
 * a different test as it's run over many webpages, you only need 1 or 2. The expected
 * results from webpages are important to start with (test breadth) but the above
 * unnessarily increases the time to completion. (Test depth vs breadth.)
 *
 * @author Paul
 */
@RunWith(Parameterized.class)
public class KilocaloriesAttributeItemScraperStrategyTest
    extends IItemAttributeScraperStrategyAbstractTest<KilocaloriesAttributeItemScraperStrategy,
        Integer>
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
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeKcalTest()  throws MalformedDocumentException, IOException
  {
    Integer actualKcal = this.getAttributeFromUrlString(this.url);

    Assert.assertEquals("The expected item kcal value differs from the actual one.",
        this.expectedKcal, actualKcal);
  }

  /**
   * Test to see if an exception is thrown if the "information" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeInformationIdMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element informationElement = jsoupDocument.getElementById("information");
    informationElement.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Id \"information\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "tbody" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeTbodyTagMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element informationElement = jsoupDocument.getElementById("information");
    Elements tbodies = informationElement.getElementsByTag("tbody");

    Assume.assumeFalse("This page does not have a table so we cannot test it.", tbodies.isEmpty());
    tbodies.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Tag \"tbody\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "tr" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeTrTagMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element informationElement = jsoupDocument.getElementById("information");
    Elements tbodies = informationElement.getElementsByTag("tbody");

    Assume.assumeFalse("This page does not have a table so we cannot test it.", tbodies.isEmpty());

    Element tbody = tbodies.first();
    Elements tableRows = tbody.getElementsByTag("tr");
    tableRows.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Tag \"tr\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "td" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeTdTagMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element informationElement = jsoupDocument.getElementById("information");
    Elements tbodies = informationElement.getElementsByTag("tbody");

    Assume.assumeFalse("This page does not have a table so we cannot test it.", tbodies.isEmpty());

    Element tbody = tbodies.first();
    Element tableRow = tbody.getElementsByTag("tr").get(1);
    Elements tableData = tableRow.getElementsByTag("td");
    tableData.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Tag \"td\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the extraction cell is empty.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePatternEmpty() throws IOException, MalformedDocumentException
  {
    //Correct format "^<numbers>$" or "^<numbers>kcal$"
    Document jsoupDocument = this.getDocumentAndSetKcalText("");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted kilocalories cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if the extraction cell has random text.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePatternRandomText() throws IOException, MalformedDocumentException
  {
    //Correct format "^<numbers>$" or "^<numbers>kcal$"
    Document jsoupDocument = this.getDocumentAndSetKcalText("test");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted kilocalories cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if the extraction cell starts with a
   * non number (doesn't end with "kcal").
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePatternStartNonNumbersNoKcal() throws IOException, MalformedDocumentException
  {
    //Correct format "^<numbers>$" or "^<numbers>kcal$"
    Document jsoupDocument = this.getDocumentAndSetKcalText("^54");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted kilocalories cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if the extraction cell starts with a
   * non number (ends with "kcal").
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePatternStartNonNumbersWithKcal() throws IOException, MalformedDocumentException
  {
    //Correct format "^<numbers>$" or "^<numbers>kcal$"
    Document jsoupDocument = this.getDocumentAndSetKcalText("a54kcal");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted kilocalories cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if the extraction cell has a non number
   * in the middle/end (doesn't end with "kcal").
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePatternMiddleEndNonNumbersNoKcal() throws IOException, MalformedDocumentException
  {
    //Correct format "^<numbers>$" or "^<numbers>kcal$"
    Document jsoupDocument = this.getDocumentAndSetKcalText("54$");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted kilocalories cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if the extraction cell has another character
   * at the end (ends with "kcal").
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePatternEndWithKcal() throws IOException, MalformedDocumentException
  {
    //Correct format "^<numbers>$" or "^<numbers>kcal$"
    Document jsoupDocument = this.getDocumentAndSetKcalText("54kcal6");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted kilocalories cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if the extraction cell has a non number
   * in the middle (ends with "kcal").
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePatternMiddleNonNumbersWithKcal() throws IOException, MalformedDocumentException
  {
    //Correct format "^<numbers>$" or "^<numbers>kcal$"
    Document jsoupDocument = this.getDocumentAndSetKcalText("54dkcal");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted kilocalories cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }


  /**
   * Get the webpage at the url initialised with this test and overwrite the text with
   * one specified as a parameter.
   * @param text What text should be set in the cell.
   * @return A {@link Document} with the replaced text.
   * @throws IOException Shouldn't be thrown.
   */
  private Document getDocumentAndSetKcalText(String text) throws IOException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element informationElement = jsoupDocument.getElementById("information");
    Elements tbodies = informationElement.getElementsByTag("tbody");

    Assume.assumeFalse("This page does not have a table so we cannot test it.", tbodies.isEmpty());

    Element tbody = tbodies.first();
    Element tableRow = tbody.getElementsByTag("tr").get(1);
    Element tableData = tableRow.getElementsByTag("td").first();
    tableData.text(text);

    return jsoupDocument;
  }


}
