package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This strategy gets an item's price.
 *
 * {@link BigDecimal} is used so we don't get the rounding errors of floating point
 * numbers and when outputted it can be displayed as a decimal (e.g. 0.00).
 * @author Paul
 */
public class UnitPriceAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<BigDecimal>
{
  /**
   * Pattern to extract price from.
   *
   * Match the whole string starting with a "£", then a non-zero length sequence
   * of numbers, a decimal point "." and two more digits.
   *
   * The main capture group captures the value after the "£".
   */
  public static final Pattern PRICE_PER_UNIT_PATTERN = Pattern.compile("^£((\\d+)\\.(\\d{2}))$");

  /** Are null attributes allowed to return from this class?. */
  public static final boolean ALLOWS_FOR_NULL_ATTRIBUTE = false;

  /** This is the attributes name. */
  public static final String ATTRIBUTE_NAME = "unit_price";

  @Override
  public boolean allowsForNullAttribute()
  {
    return ALLOWS_FOR_NULL_ATTRIBUTE;
  }

  @Override
  public String getAttributeName()
  {
    return ATTRIBUTE_NAME;
  }

  @Override
  protected BigDecimal getAttributeInputNullChecked(Document jsoupDocument)
      throws MalformedDocumentException
  {
    //Pick the (unique) element id closest to the data we want to extract.
    Element contentElement = jsoupDocument.getElementById("content");
    if (contentElement == null)
    {
      throw new MalformedDocumentException("Id \"content\" is missing.");
    }

    //It may be possible to go stright from this element to class "pricePerUnit",
    //but it's more robust to step down the tree.

    Elements productContents = contentElement.getElementsByClass("productContent");
    Element productContent = productContents.first();
    if (productContent == null)
    {
      throw new MalformedDocumentException("Class \"productContent\" is missing.");
    }

    Elements pdps = productContent.getElementsByClass("pdp");
    Element pdp = pdps.first();
    if (pdp == null)
    {
      throw new MalformedDocumentException("Class \"pdp\" is missing");
    }

    Elements productSummaries = pdp.getElementsByClass("productSummary");
    Element productSummary = productSummaries.first();
    if (productSummary == null)
    {
      throw new MalformedDocumentException("Class \"productSummary\" is missing");
    }

    Elements addToTrolleytabBoxes = productSummary.getElementsByClass("addToTrolleytabBox");
    Element addToTrolleytabBox = addToTrolleytabBoxes.first();
    if (addToTrolleytabBox == null)
    {
      throw new MalformedDocumentException("Class \"addToTrolleytabBox\" is missing.");
    }

    Elements addToTrolleytabContainers = addToTrolleytabBox.getElementsByClass("addToTrolleytabContainer");
    Element addToTrolleytabContainer = addToTrolleytabContainers.first();
    if (addToTrolleytabContainer == null)
    {
      throw new MalformedDocumentException("Class \"addToTrolleytabContainer\" is missing.");
    }

    Elements pricingAndTrolleyOptions = addToTrolleytabContainer.getElementsByClass("pricingAndTrolleyOptions");
    Element pricingAndTrolleyOption = pricingAndTrolleyOptions.first();
    if (pricingAndTrolleyOption == null)
    {
      throw new MalformedDocumentException("Class \"pricingAndTrolleyOptions\" is missing.");
    }

    Elements prices = pricingAndTrolleyOption.getElementsByClass("pricing");
    Element pricing = prices.first();
    if (pricing == null)
    {
      throw new MalformedDocumentException("Class \"pricing\" is missing.");
    }

    Elements pricePerUnits = pricing.getElementsByClass("pricePerUnit");
    Element pricePerUnit = pricePerUnits.first();
    if (pricePerUnit == null)
    {
      throw new MalformedDocumentException("Class \"pricePerUnit\" is missing.");
    }

    //Some sample text: "£0.50" or "£1.00"
    String data = pricePerUnit.ownText();

    Matcher matcher = PRICE_PER_UNIT_PATTERN.matcher(data);
    if (matcher.find())
    {
      String priceString = matcher.group(1); //Get all of the numbers.
      return new BigDecimal(priceString);
    }
    else
    {
      throw new MalformedDocumentException("Malformatted unit price cell. The data " +
          "was found but was not in the correct pattern (\""+data+"\")");
    }
  }

}

