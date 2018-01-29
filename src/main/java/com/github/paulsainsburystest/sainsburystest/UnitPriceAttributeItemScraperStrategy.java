package com.github.paulsainsburystest.sainsburystest;

import java.math.BigDecimal;
import org.jsoup.nodes.Document;

/**
 * This strategy gets an item's price.
 *
 * {@link BigDecimal} is used so we don't get the rounding errors of floating point
 * numbers and when outputted it can be displayed as a decimal (e.g. 0.00).
 * @author Paul
 */
public class UnitPriceAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<BigDecimal>
{

  /** Are null attributes allowed to return from this class?. */
  public static final boolean ALLOWS_FOR_NULL_ATTRIBUTE = false;

  /** This is the attributes name. */
  public static final String ATTRIBUTE_NAME = "unit_price";

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
  protected BigDecimal getAttributeInputNullChecked(Document jsoupDocument)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}

