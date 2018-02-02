package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import static com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.AbstractItemAttributeScraperStrategyTest.EMPTY_JSOUP_DOCUMENT;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * The test for when {@link AbstractItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document) }
 * rejects a null attribute and does not return null.
 * @author Paul
 */
public class AbstractItemAttributeScraperStrategyRejectNullReturnNonNullTest
        extends AbstractItemAttributeScraperStrategyTest<AbstractItemAttributeScraperStrategy>
{

  @Override
  public AbstractItemAttributeScraperStrategy getTestingStrategy()
  {
    return new TestRejectNullReturnNonNullItemAttributeScraperStrategy();
  }


  @Test
  public void getAttributeRejectNullReturnNonNullTest() throws MalformedDocumentException
  {
    AbstractItemAttributeScraperStrategy strategy = this.getTestingStrategy();
    Object retObj = strategy.getAttribute(EMPTY_JSOUP_DOCUMENT);
    Assert.assertNotNull(retObj);
  }


  public static class TestRejectNullReturnNonNullItemAttributeScraperStrategy
      extends AbstractTestItemAttributeScraperStrategy
  {

    @Override
    public boolean allowsForNullAttribute()
    {
      return false;
    }

    @Override
    protected Object getAttributeInputNullChecked(Document jsoupDocument)
    {
      return new Object();
    }

  }

}
