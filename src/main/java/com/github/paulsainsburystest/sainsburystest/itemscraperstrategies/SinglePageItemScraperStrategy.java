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
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
