package com.github.paulsainsburystest.sainsburystest;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This strategy gets an item's title.
 * @author Paul
 */
public class TitleAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<String>
{
  /** Are null attributes allowed to return from this class?. */
  public static final boolean ALLOWS_FOR_NULL_ATTRIBUTE = false;

  /** This is the attributes name. */
  public static final String ATTRIBUTE_NAME = "title";

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
  protected String getAttributeInputNullChecked(Document jsoupDocument)
  {
    //Pick the (unique) element id closest to the data we want to extract.
    Element contentElement = jsoupDocument.getElementById("content");

    //It may be possible to go stright from this element to the last element tag,
    //then to h1 but it's more robust to step down each node in the tree.
    //Class tags would be more stable then the fine layout of the webpage too.

    Elements productContents = contentElement.getElementsByClass("productContent");
    Element productContent = productContents.first();

    Elements pdps = productContent.getElementsByClass("pdp");
    Element pdp = pdps.first();

    Elements productSummaries = pdp.getElementsByClass("productSummary");
    Element productSummary = productSummaries.first();

    Elements productTitleDescriptionContainers = productSummary.getElementsByClass("productTitleDescriptionContainer");
    Element productTitleDescriptionContainer = productTitleDescriptionContainers.first();

    Elements h1s = productTitleDescriptionContainer.getElementsByTag("h1");
    Element h1 = h1s.first();

    return h1.text();

  }

}
