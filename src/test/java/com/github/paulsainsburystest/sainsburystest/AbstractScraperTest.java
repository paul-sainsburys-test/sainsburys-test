package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.*;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.AbstractItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.SinglePageItemScraperStrategy;
import java.math.BigDecimal;
import java.util.*;
import org.jsoup.nodes.Document;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Base class containing duplicate code used to test the class {@link Scraper}.
 * @author Paul
 * @see Scraper
 */
public abstract class AbstractScraperTest
{
  public static final IItemScraperStrategy DEFAULT_SCRAPER_STRATREGY = new SinglePageItemScraperStrategy();

  public static final Set<IItemAttributeScraperStrategy<?>> DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES;
  static
  {
    //Linked as it'll iterating over this.
    LinkedHashSet<IItemAttributeScraperStrategy<?>> set = new LinkedHashSet<>();
    DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES = Collections.unmodifiableSet(set);

    //Different order to the instances above.
    set.add(new TitleAttributeItemScraperStrategy());
    set.add(new DescriptionAttributeItemScraperStrategy());
    set.add(new KilocaloriesAttributeItemScraperStrategy());
    set.add(new UnitPriceAttributeItemScraperStrategy());
  }

  /**
   * Create a {@link Scraper} instance with the default parameters for this test.
   * @return A {@link Scraper} instance.
   */
  public static Scraper createDefaultScraperInstance()
  {
    return new Scraper(DEFAULT_SCRAPER_STRATREGY, DEFAULT_ATTRIBUTE_SCRAPER_STRATEGIES);
  }

  /**
   * Generates a map with the inputted parameters.
   * A non-null parameter means it's not present in the map, while a null
   * parameter means it's missing.
   * @param title The title of item (not null).
   * @param description The description of the item (not null).
   * @param pricePerUnit The price per unit (not null).
   * @param kilocaloriesPer100g kilocalories per 100g (can be null).
   * @return A map used for testing common parameters.
   * @throws NullPointerException If any parameter specified non null is null.
   */
  public static Map<String, Object> generateMap(String title, String description,
      String pricePerUnit, Integer kilocaloriesPer100g)
  {
    if (title == null)
    {
      throw new NullPointerException("title cannot be null.");
    }
    else if (description == null)
    {
      throw new NullPointerException("description cannot be null.");
    }
    else if (pricePerUnit == null)
    {
      throw new NullPointerException("pricePerUnit cannot be null.");
    }

    //Linked as it'll iterating over this.
    LinkedHashMap<String, Object> map = new LinkedHashMap<>();

    map.put(TitleAttributeItemScraperStrategy.ATTRIBUTE_NAME, title);
    //Having a string reduces the amount of space neede when declaring it.
    map.put(UnitPriceAttributeItemScraperStrategy.ATTRIBUTE_NAME, new BigDecimal(pricePerUnit));
    map.put(DescriptionAttributeItemScraperStrategy.ATTRIBUTE_NAME, description);

    if (kilocaloriesPer100g != null)
    {
      map.put(KilocaloriesAttributeItemScraperStrategy.ATTRIBUTE_NAME, kilocaloriesPer100g);
    }

    return map;
  }

  /**
   * Generates a map with the inputted parameters.
   * @param title The title of item (not null).
   * @param description The description of the item (not null).
   * @param pricePerUnit The price per unit (not null).
   * @return A map used for testing common parameters.
   * @throws NullPointerException If any parameter is null.
   */
  public static Map<String, Object> generateMap(String title, String description,
      String pricePerUnit)
  {
    return AbstractScraperTest.generateMap(title, description, pricePerUnit, null);
  }

  /**
   * Generates a immutable map with the inputted parameters.
   * A non-null parameter means it's not present in the map, while a null
   * parameter means it's missing.
   * @param title The title of item (not null).
   * @param description The description of the item (not null).
   * @param pricePerUnit The price per unit (not null).
   * @param kilocaloriesPer100g kilocalories per 100g (can be null).
   * @return A map used for testing common parameters.
   * @throws NullPointerException If any parameter specified non null is null.
   */
  public static Map<String, Object> generateImmutableMap(String title, String description,
      String pricePerUnit, Integer kilocaloriesPer100g)
  {
    Map<String, Object> map = AbstractScraperTest.generateMap(title, description, pricePerUnit, kilocaloriesPer100g);
    return Collections.unmodifiableMap(map);
  }

  /**
   * Generates a immutable map with the inputted parameters.
   * @param title The title of item (not null).
   * @param description The description of the item (not null).
   * @param pricePerUnit The price per unit (not null).
   * @return A map used for testing common parameters.
   * @throws NullPointerException If any parameter is null.
   */
  public static Map<String, Object> generateImmutableMap(String title, String description,
      String pricePerUnit)
  {
    Map<String, Object> map = AbstractScraperTest.generateMap(title, description, pricePerUnit);
    return Collections.unmodifiableMap(map);
  }

  @Rule
  public final ExpectedException expectedException = ExpectedException.none();

  /**
   * This class always throws a {@link MalformedDocumentException} when
   * {@link MalformedAttributeItemScraperStrategy#getAttributeInputNullChecked(org.jsoup.nodes.Document) }
   * is called.
   * @see IItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document)
   */
  public static class MalformedAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<Object>
  {
    public static final String EXCEPTION_MESSAGE =
        "Test exception (ItemAttributeScraperStrategy), this is always thrown.";

    @Override
    protected Object getAttributeInputNullChecked(Document jsoupDocument) throws MalformedDocumentException
    {
      throw new MalformedDocumentException(MalformedAttributeItemScraperStrategy.EXCEPTION_MESSAGE);
    }

    @Override
    public boolean allowsForNullAttribute()
    {
      return false;
    }

    @Override
    public String getAttributeName()
    {
      return "Malformed-ItemAttributeScraperStrategy";
    }

  }

  /**
   * This class always throws a {@link MalformedDocumentException} when
   * {@link MalformedAttributeItemScraperStrategy#getAttributeInputNullChecked(org.jsoup.nodes.Document) }
   * is called.
   * @see IItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document)
   */
  public static class MalformedItemScraperStrategy extends AbstractItemScraperStrategy
  {
    public static final String EXCEPTION_MESSAGE =
        "Test exception (ItemScraperStrategy), this is always thrown.";

    @Override
    protected List<String> getItemUrlsInputNullChecked(Document jsoupDocument) throws NullPointerException, MalformedDocumentException
    {
      throw new MalformedDocumentException(MalformedItemScraperStrategy.EXCEPTION_MESSAGE);
    }

    @Override
    public String getName()
    {
      return "Malformed-ItemScraperStrategy";
    }

  }
}
