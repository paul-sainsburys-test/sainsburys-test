package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the {@link AbstractItemScraperStrategy}.
 * @author Paul
 */
public class AbstractItemScraperStrategyTest extends IItemScraperStrategyAbstractTest<AbstractItemScraperStrategy>
{
  /**
   * String used to see if it has been passed back correctly.
   * It should be compared by reference instead of using
   * {@link String#equals(java.lang.Object) } as the access settings prevent it
   * from be used anywhere else.
   */
  private static final String TESTING_RETURN_STRING =
      AbstractItemScraperStrategyTest.class.getName();

  /**
   * Valid empty html document to reference when testing.
   */
  protected static final Document EMPTY_JSOUP_DOCUMENT = Jsoup.parseBodyFragment("");

  @Override
  public AbstractItemScraperStrategy getTestingStrategy()
  {
    return new EmptyItemScraperStrategy();
  }

  /**
   * Test to see if the abstract class returns a valid list.
   * @throws NullPointerException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown.
   */
  @Test
  public void getItemUrlsNullReturnTest() throws NullPointerException, MalformedDocumentException
  {
    AbstractItemScraperStrategy strategy = this.getTestingStrategy();

    List<String> list = strategy.getItemUrls(EMPTY_JSOUP_DOCUMENT);
    Assert.assertNotNull("The list cannot be null", list);

  }

  /**
   * Test to see if a string used in the strategy is returned back.
   * @throws NullPointerException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown.
   */
  @Test
  public void getItemUrlsReturnsReferencesTest() throws NullPointerException, MalformedDocumentException
  {
    AbstractItemScraperStrategy strategy = this.getTestingStrategy();

    List<String> list = strategy.getItemUrls(EMPTY_JSOUP_DOCUMENT);
    String returnedString = list.get(0);

    //Check by reference not by equals, as the string is only used in this class.
    Assert.assertTrue("", TESTING_RETURN_STRING == returnedString);
  }

  private static class EmptyItemScraperStrategy extends AbstractItemScraperStrategy
  {

    @Override
    protected List<String> getItemUrlsInputNullChecked(Document jsoupDocument)
        throws NullPointerException, MalformedDocumentException
    {
      LinkedList<String> list = new LinkedList<>();

      //Add a reference to the list that only this class has access to.
      list.add(TESTING_RETURN_STRING);

      return list;
    }

    @Override
    public String getName()
    {
      return "Empty";
    }

  }

}
