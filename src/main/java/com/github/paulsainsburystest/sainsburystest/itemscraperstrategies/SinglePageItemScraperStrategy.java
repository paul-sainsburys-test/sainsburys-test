package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.util.List;
import org.jsoup.nodes.Document;

/**
 * The strategy to extract a list of items from a category page.
 * @author Paul
 */
public class SinglePageItemScraperStrategy extends AbstractItemScraperStrategy
{

  @Override
  public String getName()
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  protected List<String> getItemUrlsInputNullChecked(Document jsoupDocument)
      throws NullPointerException, MalformedDocumentException
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
