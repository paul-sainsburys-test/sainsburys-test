package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import static com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.AbstractItemAttributeScraperStrategyTest.EMPTY_JSOUP_DOCUMENT;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * The test for when {@link AbstractItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document) }
 * allows for a null attribute and returns null.
 * @author Paul
 */
public class AbstractItemAttributeScraperStrategyAllowNullReturnNullTest
        extends AbstractItemAttributeScraperStrategyTest<AbstractItemAttributeScraperStrategy>
{

  @Override
  public AbstractItemAttributeScraperStrategy getTestingStrategy()
  {
    return new TestAllowNullReturnNullItemAttributeScraperStrategy();
  }


  @Test
  public void getAttributeAllowNullReturnNullTest() throws MalformedDocumentException
  {
    AbstractItemAttributeScraperStrategy strategy = this.getTestingStrategy();
    Object retObj = strategy.getAttribute(EMPTY_JSOUP_DOCUMENT);
    Assert.assertNull(retObj);
  }


  public static class TestAllowNullReturnNullItemAttributeScraperStrategy
      extends AbstractTestItemAttributeScraperStrategy
  {

    @Override
    public boolean allowsForNullAttribute()
    {
      return true;
    }

    @Override
    protected Object getAttributeInputNullChecked(Document jsoupDocument)
    {
      return null;
    }

  }

}
