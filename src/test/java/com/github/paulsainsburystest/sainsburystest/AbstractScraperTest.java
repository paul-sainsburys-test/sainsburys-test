package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.*;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.SinglePageItemScraperStrategy;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
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

    @Override
    protected Object getAttributeInputNullChecked(Document jsoupDocument) throws MalformedDocumentException
    {
      throw new MalformedDocumentException("Test exception, this is always thrown.");
    }

    @Override
    public boolean allowsForNullAttribute()
    {
      return false;
    }

    @Override
    public String getAttributeName()
    {
      return "Malformed";
    }

  }
}
