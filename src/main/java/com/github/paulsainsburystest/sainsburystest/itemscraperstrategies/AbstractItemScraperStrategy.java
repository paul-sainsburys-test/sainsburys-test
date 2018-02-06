package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.util.List;
import org.jsoup.nodes.Document;

/**
 * Abstract base class that can be used to ease the implementation of
 * {@link IItemScraperStrategy}.
 *
 * @author Paul
 */
public abstract class AbstractItemScraperStrategy implements IItemScraperStrategy
{

  @Override
  public List<String> getItemUrls(Document jsoupDocument)
      throws NullPointerException, MalformedDocumentException
  {
    if (jsoupDocument == null)
    {
      throw new NullPointerException("The parameter cannot be null.");
    }

    //Do not check if the return type is null, as it should be null as part of
    //the interface contract and it's not dependent on this class's state
    //like AbstractItemAttributeScraperStrategy.
    return this.getItemUrlsInputNullChecked(jsoupDocument);
  }

  /**
   * Given a html {@link Document} (which is assumed to be a category page) extract
   * all of urls of the items listed.
   *
   * This method parameter will always be not null.
   * @param jsoupDocument The document to extract from (always will be non-null).
   * @return A list of all items on the page (not null).
   * @throws NullPointerException If jsoupDocument is null.
   * @throws MalformedDocumentException If the strategy cannot determine if the
   *    items should exist.
   */
  protected abstract List<String> getItemUrlsInputNullChecked(Document jsoupDocument)
      throws NullPointerException, MalformedDocumentException;


  @Override
  public boolean equals(Object obj)
  {
    if (obj != null && obj instanceof IItemScraperStrategy)
    {
      IItemScraperStrategy otherStrategy = (IItemScraperStrategy) obj;

      //Use an internal method as we know this will be non-null.
      return this.getName().equals(otherStrategy.getName());
    }
    else
    {
      return super.equals(obj);
    }
  }

  @Override
  public int hashCode()
  {
    return this.getName().hashCode();
  }

}
