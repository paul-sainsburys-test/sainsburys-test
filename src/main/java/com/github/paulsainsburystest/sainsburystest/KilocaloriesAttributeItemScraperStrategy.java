package com.github.paulsainsburystest.sainsburystest;

import org.jsoup.nodes.Document;

/**
 * This strategy gets an item's kilocalories per 100g if it's available.
 * @author Paul
 */
public class KilocaloriesAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<Integer>
{
  /** Are null attributes allowed to return from this class?. */
  public static final boolean ALLOWS_FOR_NULL_ATTRIBUTE = true;

  /** This is the attributes name. */
  public static final String ATTRIBUTE_NAME = "kcal_per_100g";

  @Override
  public boolean allowsForNullAttribute()
  {
    return ALLOWS_FOR_NULL_ATTRIBUTE;
  }

  @Override
  public String getAttributeName()
  {
    return ATTRIBUTE_NAME;
  }

  @Override
  protected Integer getAttributeInputNullChecked(Document jsoupDocument)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
