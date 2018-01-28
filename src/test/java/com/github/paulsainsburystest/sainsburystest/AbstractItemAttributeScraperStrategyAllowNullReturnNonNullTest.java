package com.github.paulsainsburystest.sainsburystest;

import static com.github.paulsainsburystest.sainsburystest.AbstractItemAttributeScraperStrategyTest.EMPTY_JSOUP_DOCUMENT;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * The test for when {@link AbstractItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document) }
 * allows for a null attribute and does not return null.
 * @author Paul
 */
public class AbstractItemAttributeScraperStrategyAllowNullReturnNonNullTest
        extends AbstractItemAttributeScraperStrategyTest<AbstractItemAttributeScraperStrategy>
{

  @Override
  public AbstractItemAttributeScraperStrategy getTestingStrategy()
  {
    return new TestAllowNullReturnNonNullItemAttributeScraperStrategy();
  }


  @Test
  public void getAttributeAllowNullReturnNonNullTest()
  {
    AbstractItemAttributeScraperStrategy strategy = this.getTestingStrategy();
    Object retObj = strategy.getAttribute(EMPTY_JSOUP_DOCUMENT);
    Assert.assertNotNull(retObj);
  }


  public static class TestAllowNullReturnNonNullItemAttributeScraperStrategy
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
      return 1;
    }

  }

}
