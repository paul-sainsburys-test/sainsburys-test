package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;


import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
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
      throws MalformedDocumentException
  {
    //Pick the (unique) element id closest to the data we want to extract.
    Element contentElement = jsoupDocument.getElementById("content");
    if (contentElement == null)
    {
      throw new MalformedDocumentException("Id \"content\" is missing.");
    }

    //It may be possible to go stright from this element to the last element tag,
    //then to h1 but it's more robust to step down each node in the tree.
    //Class tags would be more stable then the fine layout of the webpage too.

    //We make the assumption it's the only one.
    Elements productContents = contentElement.getElementsByClass("productContent");
    Element productContent = productContents.first();
    if (productContent == null)
    {
      throw new MalformedDocumentException("Class \"productContent\" is missing.");
    }

    //Again we make the assumption it's the only one in the subtree.
    Elements pdps = productContent.getElementsByClass("pdp");
    Element pdp = pdps.first();
    if (pdp == null)
    {
      throw new MalformedDocumentException("Class \"pdp\" is missing.");
    }

    //Again we make the assumption it's the only one in the subtree.
    Elements productSummaries = pdp.getElementsByClass("productSummary");
    Element productSummary = productSummaries.first();
    if (productSummary == null)
    {
      throw new MalformedDocumentException("Class \"productSummary\" is missing.");
    }

    //Once again we make the assumption it's the only one in the subtree.
    Elements productTitleDescriptionContainers = productSummary.getElementsByClass("productTitleDescriptionContainer");
    Element productTitleDescriptionContainer = productTitleDescriptionContainers.first();
    if (productTitleDescriptionContainer == null)
    {
      throw new MalformedDocumentException("Class \"productTitleDescriptionContainer\" is missing.");
    }

    //Again we make the assumption it's the only one in the subtree.
    Elements h1s = productTitleDescriptionContainer.getElementsByTag("h1");
    Element h1 = h1s.first();
    if (h1 == null)
    {
      throw new MalformedDocumentException("Tag \"h1\" is missing.");
    }

    //We also assume the text is non-empty.
    return h1.text();

  }

}
