package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.Globals;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for getting an item's price from a webpage.
 *
 * FIXME: If there is sufficent time the pattern matching could be separated into
 * a different test as it's run over many webpages, you only need 1 or 2. The expected
 * results from webpages are important to start with (test breadth) but the above
 * unnessarily increases the time to completion. (Test depth vs breadth.)
 *
 * @author Paul
 */
@RunWith(Parameterized.class)
public class UnitPriceAttributeItemScraperStrategyTest
    extends IItemAttributeScraperStrategyAbstractTest<UnitPriceAttributeItemScraperStrategy,
        BigDecimal>
{

  private static final String TEST_URL1 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
  private static final BigDecimal TEST_URL1_EXPECTED_PRICE = new BigDecimal("1.75");

  private static final String TEST_URL2 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html";
  private static final BigDecimal TEST_URL2_EXPECTED_PRICE = new BigDecimal("1.75");

  private static final String TEST_URL3 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html";
  private static final BigDecimal TEST_URL3_EXPECTED_PRICE = new BigDecimal("1.75");

  private static final String TEST_URL4 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--sweet-150g.html";
  private static final BigDecimal TEST_URL4_EXPECTED_PRICE = new BigDecimal("1.50");

  private static final String TEST_URL5 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html";
  private static final BigDecimal TEST_URL5_EXPECTED_PRICE = new BigDecimal("3.25");

  private static final String TEST_URL6 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries--so-organic-150g.html";
  private static final BigDecimal TEST_URL6_EXPECTED_PRICE = new BigDecimal("2.00");

  private static final String TEST_URL7 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html";
  private static final BigDecimal TEST_URL7_EXPECTED_PRICE = new BigDecimal("2.50");

  private static final String TEST_URL8 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherries-350g.html";
  private static final BigDecimal TEST_URL8_EXPECTED_PRICE = new BigDecimal("2.50");

  private static final String TEST_URL9 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--tangy-150g.html";
  private static final BigDecimal TEST_URL9_EXPECTED_PRICE = new BigDecimal("1.50");

  private static final String TEST_URL10 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-strawberries--taste-the-difference-300g.html";
  private static final BigDecimal TEST_URL10_EXPECTED_PRICE = new BigDecimal("2.50");

  private static final String TEST_URL11 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html";
  private static final BigDecimal TEST_URL11_EXPECTED_PRICE = new BigDecimal("1.50");

  private static final String TEST_URL12 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html";
  private static final BigDecimal TEST_URL12_EXPECTED_PRICE = new BigDecimal("3.50");

  private static final String TEST_URL13 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html";
  private static final BigDecimal TEST_URL13_EXPECTED_PRICE = new BigDecimal("2.75");

  private static final String TEST_URL14 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-redcurrants-150g.html";
  private static final BigDecimal TEST_URL14_EXPECTED_PRICE = new BigDecimal("2.50");

  private static final String TEST_URL15 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet--taste-the-difference-250g.html";
  private static final BigDecimal TEST_URL15_EXPECTED_PRICE = new BigDecimal("2.50");

  private static final String TEST_URL16 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html";
  private static final BigDecimal TEST_URL16_EXPECTED_PRICE = new BigDecimal("1.75");

  private static final String TEST_URL17 = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html";
  private static final BigDecimal TEST_URL17_EXPECTED_PRICE = new BigDecimal("4.00");


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
        {TEST_URL1, TEST_URL1_EXPECTED_PRICE},
        {TEST_URL2, TEST_URL2_EXPECTED_PRICE},
        {TEST_URL3, TEST_URL3_EXPECTED_PRICE},
        {TEST_URL4, TEST_URL4_EXPECTED_PRICE},
        {TEST_URL5, TEST_URL5_EXPECTED_PRICE},
        {TEST_URL6, TEST_URL6_EXPECTED_PRICE},
        {TEST_URL7, TEST_URL7_EXPECTED_PRICE},
        {TEST_URL8, TEST_URL8_EXPECTED_PRICE},
        {TEST_URL9, TEST_URL9_EXPECTED_PRICE},
        {TEST_URL10, TEST_URL10_EXPECTED_PRICE},
        {TEST_URL11, TEST_URL11_EXPECTED_PRICE},
        {TEST_URL12, TEST_URL12_EXPECTED_PRICE},
        {TEST_URL13, TEST_URL13_EXPECTED_PRICE},
        {TEST_URL14, TEST_URL14_EXPECTED_PRICE},
        {TEST_URL15, TEST_URL15_EXPECTED_PRICE},
        {TEST_URL16, TEST_URL16_EXPECTED_PRICE},
        {TEST_URL17, TEST_URL17_EXPECTED_PRICE}
      }
    );
  }

  /** Webpage to download and analyse. */
  private final String url;

  /** The item price we expect. */
  private final BigDecimal expectedPrice;

  /**
   * Constructor for this test.
   * @param url Webpage to download and analyse.
   * @param expectedPrice The item price we expect.
   * @throws NullPointerException If any parameter is null.
   */
  public UnitPriceAttributeItemScraperStrategyTest(String url, BigDecimal expectedPrice)
  {
    if (url == null)
    {
      throw new NullPointerException("url cannot be null.");
    }
    else if (expectedPrice == null)
    {
      throw new NullPointerException("expectedPrice cannot be null.");
    }

    this.url = url;
    this.expectedPrice = expectedPrice;
  }


  @Override
  public UnitPriceAttributeItemScraperStrategy getTestingStrategy()
  {
    return new UnitPriceAttributeItemScraperStrategy();
  }


  /**
   * Retrieve the webpage of the url and compare it with what we expect.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePriceTest() throws MalformedDocumentException, IOException
  {
    BigDecimal actualPrice = this.getAttributeFromUrlString(this.url);

    //compareTo() is used so values like 1.70 are the same as 1.7.
    Assert.assertEquals("The difference in the expected and actual item price is non-zero.",
        0, this.expectedPrice.compareTo(actualPrice));
  }


  /**
   * Test to see if an exception is thrown if the "content" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeContentIdMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    contentElement.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Id \"content\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "productContent" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeProductContentClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Elements productContents = contentElement.getElementsByClass("productContent");
    productContents.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"productContent\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }


  /**
   * Test to see if an exception is thrown if the "pdp" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePdpClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Elements pdps = productContent.getElementsByClass("pdp");
    pdps.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"pdp\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "productSummary" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeProductSummaryClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Element pdp = productContent.getElementsByClass("pdp").first();
    Elements productSummaries = pdp.getElementsByClass("productSummary");
    productSummaries.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"productSummary\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "addToTrolleytabBox" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeAddToTrolleytabBoxClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Element pdp = productContent.getElementsByClass("pdp").first();
    Element productSummary = pdp.getElementsByClass("productSummary").first();
    Elements addToTrolleytabBoxes = productSummary.getElementsByClass("addToTrolleytabBox");
    addToTrolleytabBoxes.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"addToTrolleytabBox\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "addToTrolleytabContainer" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeAddToTrolleytabContainerClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Element pdp = productContent.getElementsByClass("pdp").first();
    Element productSummary = pdp.getElementsByClass("productSummary").first();
    Element addToTrolleytabBoxes = productSummary.getElementsByClass("addToTrolleytabBox").first();
    Elements addToTrolleytabContainers = addToTrolleytabBoxes.getElementsByClass("addToTrolleytabContainer");
    addToTrolleytabContainers.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"addToTrolleytabContainer\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "pricingAndTrolleyOptions" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePricingAndTrolleyOptionsClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Element pdp = productContent.getElementsByClass("pdp").first();
    Element productSummary = pdp.getElementsByClass("productSummary").first();
    Element addToTrolleytabBoxes = productSummary.getElementsByClass("addToTrolleytabBox").first();
    Element addToTrolleytabContainer = addToTrolleytabBoxes.getElementsByClass("addToTrolleytabContainer").first();
    Elements pricingAndTrolleyOptions = addToTrolleytabContainer.getElementsByClass("pricingAndTrolleyOptions");
    pricingAndTrolleyOptions.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"pricingAndTrolleyOptions\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "pricing" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePricingClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Element pdp = productContent.getElementsByClass("pdp").first();
    Element productSummary = pdp.getElementsByClass("productSummary").first();
    Element addToTrolleytabBoxes = productSummary.getElementsByClass("addToTrolleytabBox").first();
    Element addToTrolleytabContainer = addToTrolleytabBoxes.getElementsByClass("addToTrolleytabContainer").first();
    Element pricingAndTrolleyOption = addToTrolleytabContainer.getElementsByClass("pricingAndTrolleyOptions").first();
    Elements prices = pricingAndTrolleyOption.getElementsByClass("pricing");
    prices.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"pricing\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if the "pricePerUnit" element is missing.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributePricePerUnitClassMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Element pdp = productContent.getElementsByClass("pdp").first();
    Element productSummary = pdp.getElementsByClass("productSummary").first();
    Element addToTrolleytabBoxes = productSummary.getElementsByClass("addToTrolleytabBox").first();
    Element addToTrolleytabContainer = addToTrolleytabBoxes.getElementsByClass("addToTrolleytabContainer").first();
    Element pricingAndTrolleyOption = addToTrolleytabContainer.getElementsByClass("pricingAndTrolleyOptions").first();
    Element pricing = pricingAndTrolleyOption.getElementsByClass("pricing").first();
    Elements pricePerUnits = pricing.getElementsByClass("pricePerUnit");
    pricePerUnits.remove();

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Class \"pricePerUnit\" is missing");
    this.getTestingStrategy().getAttribute(jsoupDocument);

  }

  /**
   * Test to see if an exception is thrown if extraction text is missing a pound sign at the start.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatPoundMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("4.99");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is missing a decimal place.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatOnePenceMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("£0.9");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is missing two decimal places.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatTwoPenceMissing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("£1");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is missing two decimal
   * places and has a trailing decimal point.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatTwoPenceMissingWithTrailing() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("£5.");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is only pence (without a symbol).
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatOnlyPenceNoSymbol() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("89");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is only pence
   * (without a symbol) and with a decimal place.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatOnlyPenceNoSymbolWithDecimalPlace() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText(".51");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is only pence
   * (without a symbol), with a decimal place and pound sign.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatOnlyPenceNoSymbolWithDecimalPlaceWithPound()
      throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("£.51");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is only pence (with a symbol).
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatOnlyPenceWithSymbol() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("5p");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is empty.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatEmpty() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text is only a pound symbol.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatSinglePound() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("£");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text has trailing characters.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatTrailingText() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("£5.61$");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text has prepended characters.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatPrependText() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("8£5.61");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }

  /**
   * Test to see if an exception is thrown if extraction text has inserted characters in the middle.
   * @throws MalformedDocumentException Should always be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void getAttributeUnitFormatMiddleTextAdded() throws IOException, MalformedDocumentException
  {
    Document jsoupDocument = this.getDocumentAndSetUnitPriceText("£ 5.61");

    this.expectedException.expect(MalformedDocumentException.class);
    this.expectedException.expectMessage("Malformatted unit price cell.");
    this.getTestingStrategy().getAttribute(jsoupDocument);
  }


  /**
   * Get the webpage at the url initialised with this test and overwrite the text with
   * one specified as a parameter.
   * @param text What text should be set in the cell, e.g. "£0.50".
   * @return A {@link Document} with the replaced text.
   * @throws IOException Shouldn't be thrown.
   */
  private Document getDocumentAndSetUnitPriceText(String text) throws IOException
  {
    Document jsoupDocument = Globals.WEBPAGE_CACHE.getDocument(this.url);

    Element contentElement = jsoupDocument.getElementById("content");
    Element productContent = contentElement.getElementsByClass("productContent").first();
    Element pdp = productContent.getElementsByClass("pdp").first();
    Element productSummary = pdp.getElementsByClass("productSummary").first();
    Element addToTrolleytabBoxes = productSummary.getElementsByClass("addToTrolleytabBox").first();
    Element addToTrolleytabContainer = addToTrolleytabBoxes.getElementsByClass("addToTrolleytabContainer").first();
    Element pricingAndTrolleyOption = addToTrolleytabContainer.getElementsByClass("pricingAndTrolleyOptions").first();
    Element pricing = pricingAndTrolleyOption.getElementsByClass("pricing").first();
    Element pricePerUnit = pricing.getElementsByClass("pricePerUnit").first();

    //As there is multiple elements in this tag, find the text part and replace just that.
    for (Node e : pricePerUnit.childNodes())
    {
      if (e instanceof TextNode)
      {
        TextNode tn = (TextNode) e;
        tn.text(text);
        break;
      }
    }

    return jsoupDocument;
  }


}
