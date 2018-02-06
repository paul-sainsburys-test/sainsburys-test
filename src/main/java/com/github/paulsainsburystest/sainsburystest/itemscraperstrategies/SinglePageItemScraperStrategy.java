package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The strategy to extract a list of items from a category page.
 * @author Paul
 */
public class SinglePageItemScraperStrategy extends AbstractItemScraperStrategy
{
  public static final String NAME = "SinglePage";

  @Override
  public String getName()
  {
    return NAME;
  }

  @Override
  protected List<String> getItemUrlsInputNullChecked(Document jsoupDocument)
      throws NullPointerException, MalformedDocumentException
  {
    Element productListerId = jsoupDocument.getElementById("productLister");
    if (productListerId == null)
    {
      throw new MalformedDocumentException("Id \"productLister\" is missing");
    }

    Element productListerClass = productListerId.getElementsByClass("productLister").first();
    if (productListerClass == null)
    {
      throw new MalformedDocumentException("Class \"productLister\" is missing");
    }

    //All of the items.
    Elements items = productListerClass.getElementsByClass("gridItem");

    LinkedList<String> list = new LinkedList<>();
    for (Element item : items)
    {
      //Avoid cross sell.
      Element productInfo = item.getElementsByClass("productInfo").first();
      if (productInfo == null)
      {
        throw new MalformedDocumentException("Class \"productInfo\" is missing");
      }

      //Avoid offer links
      Element h3 = productInfo.getElementsByTag("h3").first();
      if (h3 == null)
      {
        throw new MalformedDocumentException("Tag \"h3\" is missing");
      }

      //Get the link.
      Element a = h3.getElementsByTag("a").first();
      if (a == null)
      {
        throw new MalformedDocumentException("Tag \"a\" is missing");
      }

      //Get absolute reference.
      String url = a.absUrl("href");
      if (url == null || url.equals(""))
      {
        throw new MalformedDocumentException("Tag \"a\" has no href");
      }

      list.add(url);
    }

    return list;

  }
}
