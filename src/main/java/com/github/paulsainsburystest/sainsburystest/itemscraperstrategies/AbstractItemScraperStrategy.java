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
    throw new UnsupportedOperationException("Not supported yet.");
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
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  @Override
  public int hashCode()
  {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

}
