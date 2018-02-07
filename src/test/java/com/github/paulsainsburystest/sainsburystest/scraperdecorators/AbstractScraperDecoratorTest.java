package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.AbstractScraperTest;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import com.github.paulsainsburystest.sainsburystest.Scraper;
import java.io.IOException;
import java.util.Map;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test the class {@link AbstractScraperDecorator}
 * @author Paul
 */
public class AbstractScraperDecoratorTest
{

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  /**
   * Test to see if no exceptions are thrown when calling the constructor
   * with a non-null input.
   */
  @Test
  public void abstractScraperDecoratorConstructorTest()
  {
    Scraper scraper = AbstractScraperTest.createDefaultScraperInstance();

    //Expect no exception to be thrown here.
    EmptyScraperDecorator emptyScraperDecorator = new EmptyScraperDecorator(scraper);
  }

  /**
   * Test to see if an execption is thrown if the constructor is called with a
   * null parameter.
   * @throws NullPointerException Always thrown.
   */
  @Test
  public void abstractScraperDecoratorConstructorNullTest()
  {
    this.expectedException.expect(NullPointerException.class);
    this.expectedException.expectMessage("nextScraperDecorator cannot be null.");
    EmptyScraperDecorator emptyScraperDecorator = new EmptyScraperDecorator(null);
  }

  /**
   * An implementation of {@link IScraperDecorator} which does nothing to the
   * returned value.
   */
  public static class EmptyScraperDecorator extends AbstractScraperDecorator
  {

    public EmptyScraperDecorator(IScraperDecorator nextScraperDecorator)
    {
      super(nextScraperDecorator);
    }

    @Override
    public Map<String, Object> scrapeDecorated(String categoryUrl) throws MalformedDocumentException, IOException
    {
      //Ignore everything and pass it back as we are only testing the constructor.
      return super.nextScraperDecorator.scrapeDecorated(categoryUrl);
    }

  }

}
