package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.ConstraintFailedException;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import static com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.AbstractItemAttributeScraperStrategyTest.EMPTY_JSOUP_DOCUMENT;
import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * The test for when {@link AbstractItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document) }
 * rejects a null attribute and returns null.
 * @author Paul
 */
public class AbstractItemAttributeScraperStrategyRejectNullReturnNullTest
        extends AbstractItemAttributeScraperStrategyTest<AbstractItemAttributeScraperStrategy>
{

  @Override
  public AbstractItemAttributeScraperStrategy getTestingStrategy()
  {
    return new TestRejectNullReturnNullItemAttributeScraperStrategy();
  }


  @Test
  public void getAttributeRejectNullReturnNullTest() throws MalformedDocumentException
  {
    AbstractItemAttributeScraperStrategy strategy = this.getTestingStrategy();

    super.expectedException.expect(ConstraintFailedException.class);
    super.expectedException.expectMessage("Returned object is null and nulls are not allowed.");
    Object retObj = strategy.getAttribute(EMPTY_JSOUP_DOCUMENT);
    super.expectedException.reportMissingExceptionWithMessage(
        "We expected a "+ConstraintFailedException.class.getSimpleName()+ " to " +
        "be thrown but it wasn't");
  }


  public static class TestRejectNullReturnNullItemAttributeScraperStrategy
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
      return null;
    }

  }

}
