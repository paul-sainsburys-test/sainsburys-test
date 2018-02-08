package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

import com.github.paulsainsburystest.sainsburystest.AbstractScraperTest;
import static com.github.paulsainsburystest.sainsburystest.AbstractScraperTest.DEFAULT_SCRAPER_STRATREGY;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import com.github.paulsainsburystest.sainsburystest.Scraper;
import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.IItemAttributeScraperStrategy;
import java.io.IOException;
import java.util.*;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the class {@link ExceptionScraperDecorator}
 * @author Paul
 */
public class ExceptionScraperDecoratorTest extends AbstractScraperDecoratorTest
{

  private static final String TEST_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

  private static final Map<String, Object> TEST_URL_EXPECTED_PASS_RESULT;
  static
  {
    //Real it's stored like Map<String,List<Map<String, Object>>>
    Map<String, Object> map = new LinkedHashMap<>();
    TEST_URL_EXPECTED_PASS_RESULT = Collections.unmodifiableMap(map);

    List<Map<String, Object>> list = new LinkedList<>();
    //ORDERING IS IMPORTANT.
    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Strawberries 400g", "by Sainsbury's strawberries", "1.75", 33));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blueberries 200g", "by Sainsbury's blueberries", "1.75", 45));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Raspberries 225g", "by Sainsbury's raspberries", "1.75", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--sweet-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blackberries, Sweet 150g", "by Sainsbury's blackberries", "1.50", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-400g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blueberries 400g", "by Sainsbury's blueberries", "3.25", 45));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries--so-organic-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blueberries, SO Organic 150g", "So Organic blueberries", "2.00", 45));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries--taste-the-difference-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Raspberries, Taste the Difference 150g", "Ttd raspberries", "2.50", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherries-350g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Cherries 400g", "by Sainsbury's Family Cherry Punnet", "2.50", 52));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackberries--tangy-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blackberries, Tangy 150g", "by Sainsbury's blackberries", "1.50", 32));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-strawberries--taste-the-difference-300g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Strawberries, Taste the Difference 300g", "Ttd strawberries", "2.50", 33));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet-200g-468015-p-44.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Cherry Punnet 200g", "Cherries", "1.50", 52));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berries-300g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Mixed Berries 300g", "by Sainsbury's mixed berries", "3.50"));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-mixed-berry-twin-pack-200g-7696255-p-44.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Mixed Berry Twin Pack 200g", "Mixed Berries", "2.75"));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-redcurrants-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Redcurrants 150g", "by Sainsbury's redcurrants", "2.50", 71));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-cherry-punnet--taste-the-difference-250g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Cherry Punnet, Taste the Difference 200g", "Cherry Punnet", "2.50", 48));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blackcurrants-150g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's Blackcurrants 150g", "Union Flag", "1.75"));

    //https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-cherry---strawberry-pack-600g.html
    list.add(AbstractScraperTest.generateImmutableMap(
        "Sainsbury's British Cherry & Strawberry Pack 600g",
        "British Cherry & Strawberry Mixed Pack", "4.00"));

    //Make the list immutable.
    list = Collections.unmodifiableList(list);

    map.put("results", list);

    map.put(ExceptionScraperDecorator.DECORATOR_NAME, Boolean.FALSE);

  }

  private static final Map<String, Object> TEST_URL_EXPECTED_FAIL_RESULT;
  static
  {
    //Real it's stored like Map<String,List<Map<String, Object>>>
    Map<String, Object> map = new LinkedHashMap<>();
    TEST_URL_EXPECTED_FAIL_RESULT = Collections.unmodifiableMap(map);

    List<Map<String, Object>> list = new LinkedList<>();
    list = Collections.unmodifiableList(list);

    map.put("results", list);

    map.put(ExceptionScraperDecorator.DECORATOR_NAME, Boolean.TRUE);
    //This is a bit hackish as we can't test the stacktrace directly
    //it needs to made blank in the returned value too.
    map.put(ExceptionScraperDecorator.DECORATOR_STACKTRACE_NAME, "");

  }

  /**
   * Test the constructor with a valid non-null parameter.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void exceptionScraperDecoratorConstructorTest() throws MalformedDocumentException, IOException
  {
    Scraper scraper = AbstractScraperTest.createDefaultScraperInstance();
    ExceptionScraperDecorator exceptionScraperDecorator = new ExceptionScraperDecorator(scraper);
  }

  /**
   * Test the constructor with a valid non-null parameter.
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   * @throws NullPointerException Always thrown.
   */
  @Test
  public void exceptionScraperDecoratorConstructorNullTest() throws MalformedDocumentException, IOException
  {
    super.expectedException.expect(NullPointerException.class);
    super.expectedException.expectMessage("nextScraperDecorator cannot be null");
    ExceptionScraperDecorator exceptionScraperDecorator = new ExceptionScraperDecorator(null);
  }


  /**
   * Test that the input includes a new key/value pair "exceptionThrown"/"false".
   * @throws MalformedDocumentException Shouldn't be thrown.
   * @throws IOException Shouldn't be thrown.
   */
  @Test
  public void scrapeDecoratedTest() throws MalformedDocumentException, IOException
  {
    Scraper scraper = AbstractScraperTest.createDefaultScraperInstance();
    ExceptionScraperDecorator exceptionScraperDecorator = new ExceptionScraperDecorator(scraper);

    Map<String, Object> actualResult = exceptionScraperDecorator.scrapeDecorated(
        ExceptionScraperDecoratorTest.TEST_URL);

    Assert.assertEquals("We expected the output to the equal but it wasn't",
        ExceptionScraperDecoratorTest.TEST_URL_EXPECTED_PASS_RESULT, actualResult);
  }


  /**
   * Test when an attribute strategy throws a {@link MalformedDocumentException}.
   * It should catch and handle it.
   * @throws IOException Shouldn't be thrown.
   * @throws MalformedDocumentException Shouldn't be thrown (as it's caught).
   * @see Scraper#scrapeItemPage(java.lang.String)
   */
  @Test
  public void scrapeDecoratedItemPageStrategyThrowsAnExceptionTest() throws IOException, MalformedDocumentException
  {
    LinkedHashSet<IItemAttributeScraperStrategy<?>> set = new LinkedHashSet<>();
    set.add(new AbstractScraperTest.MalformedAttributeItemScraperStrategy());

    Scraper scraper = new Scraper(DEFAULT_SCRAPER_STRATREGY, set);
    ExceptionScraperDecorator exceptionScraperDecorator = new ExceptionScraperDecorator(scraper);

    Map<String, Object> actualResult = exceptionScraperDecorator.scrapeDecorated(
        ExceptionScraperDecoratorTest.TEST_URL);

    //Test to make sure it's true so we don't put one in with the next statement.
    Assert.assertTrue("The returned value must have stacktrace key/value",
        actualResult.containsKey(ExceptionScraperDecorator.DECORATOR_STACKTRACE_NAME));

    String stacktrace = (String) actualResult.get(ExceptionScraperDecorator.DECORATOR_STACKTRACE_NAME);

    String expectedStacktraceString = "An exception was thrown when trying to extract an attribute from a webpage.";
    Assert.assertThat("The expected exception string in the stacktrace was not present.",
        stacktrace, containsString(expectedStacktraceString));

    //Erase the stacktrace as we cannot easily compare it.
    actualResult.put(ExceptionScraperDecorator.DECORATOR_STACKTRACE_NAME, "");

    Assert.assertEquals("The expected return value differs from what we expect.",
        ExceptionScraperDecoratorTest.TEST_URL_EXPECTED_FAIL_RESULT, actualResult);
  }


}
