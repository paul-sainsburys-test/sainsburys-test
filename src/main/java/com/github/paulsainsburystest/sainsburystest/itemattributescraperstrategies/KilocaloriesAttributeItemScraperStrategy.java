package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This strategy gets an item's kilocalories per 100g if it's available.
 * @author Paul
 */
public class KilocaloriesAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<Integer>
{
  /**
   * Pattern to extract the kcal value from.
   * Match the whole string starting with a non-zero length sequence of numbers and
   * optionally ending in "kcal".
   */
  public static final Pattern KCAL_PATTERN = Pattern.compile("^(\\d+)(kcal)?$");

  /** Are null attributes allowed to return from this class?. */
  public static final boolean ALLOWS_FOR_NULL_ATTRIBUTE = true;

  /** This is the attributes name. */
  public static final String ATTRIBUTE_NAME = "kcal_per_100g";

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
  protected Integer getAttributeInputNullChecked(Document jsoupDocument)
      throws MalformedDocumentException
  {
    //Pick the (unique) element id closest to the data we want to extract.
    Element informationElement = jsoupDocument.getElementById("information");
    if (informationElement == null)
    {
      throw new MalformedDocumentException("Id \"information\" is missing.");
    }

    //There is no other tags/classes to step down onto without iterating.
    //This class seems to be uniquely identifying, so jump onto this one.
    Elements nutritionTables = informationElement.getElementsByClass("nutritionTable");
    Element nutritionTable = nutritionTables.first();

    //Does it have a nutrition table?
    if (nutritionTable == null)
    {
      return null;
    }

    //Skip the headers and go into the body.
    Elements tableBodies = nutritionTable.getElementsByTag("tbody");
    Element tableBody = tableBodies.first();
    if (tableBody == null)
    {
      throw new MalformedDocumentException("Tag \"tbody\" is missing.");
    }

    //Get the second table row.
    Elements tableRows = tableBody.getElementsByTag("tr");
    Element tableRow = tableRows.get(1);
    if (tableRow == null)
    {
      throw new MalformedDocumentException("Tag \"tr\" is missing.");
    }

    //Select the table data, making sure to ignore table header tag ("th").
    Elements tableDataMulti = tableRow.getElementsByTag("td");
    Element tableData = tableDataMulti.first();
    String data = tableData.text();

    //Most are labeled as "<number>kcal" but one is just "<number>".
    Matcher matcher = KCAL_PATTERN.matcher(data);
    if (matcher.find())
    {
      String kcalStr = matcher.group(1); //Get the numbers
      return Integer.parseInt(kcalStr);
    }
    else
    {
      throw new MalformedDocumentException("The data was found but was not in " +
          "the correct pattern (\""+data+"\")");
    }
  }

}

