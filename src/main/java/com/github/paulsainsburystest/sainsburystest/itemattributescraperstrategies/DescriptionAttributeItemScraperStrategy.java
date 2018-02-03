package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;


import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This strategy gets an item's description, if there are multiple lines then it
 * gets the first.
 * @author Paul
 */
public class DescriptionAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<String>
{
  /** Are null attributes allowed to return from this class?. */
  public static final boolean ALLOWS_FOR_NULL_ATTRIBUTE = false;

  /** This is the attributes name. */
  public static final String ATTRIBUTE_NAME = "description";

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
    Element informationElement = jsoupDocument.getElementById("information");
    if (informationElement == null)
    {
      throw new MalformedDocumentException("Id \"information\" is missing.");
    }

    ////Get the main part element if it exists.
    ////Actually ignore it as the id=mainPart is duplicated which makes the document
    ////HTML syntax invalid. It's unknown what will be returned.
    //Element mainPart = informationElement.getElementById("mainPart");

    ////Select the element closest to our target.
    //Element nextSelectionPoint = (mainPart == null ? informationElement : mainPart);

    //The first element product element is the description.
    Elements productTexts = informationElement.getElementsByClass("productText");
    Element productText = productTexts.first();
    if (productText == null)
    {
      throw new MalformedDocumentException("Class \"productText\" is missing.");
    }

    //Possible locations for description text.
    Elements paragraphs = productText.getElementsByTag("p");
    if (paragraphs.isEmpty())
    {
      throw new MalformedDocumentException("Tag \"p\" is missing.");
    }

    //In most cases the first paragraph element has the text but in one case it's not.
    //Therefore scan from the first to the last paragraph tag to find a non empty one.
    for (Element paragraph : paragraphs)
    {
      String text = paragraph.text();
      if (text.isEmpty() == false)
      {
        return text;
      }
    }

    //If they are all empty then return an empty string.
    return "";
  }


}
