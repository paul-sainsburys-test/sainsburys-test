package com.github.paulsainsburystest.sainsburystest;


import org.jsoup.nodes.Document;

/**
 * This strategy gets an item's description, if there are multiple lines then it
 * gets the first.
 * @author Paul
 */
public class DescriptionAttributeItemScraperStrategy extends AbstractItemAttributeScraperStrategy<String>
{
  /** Are null attributes allowed to return from this class?. */
  public static final boolean ALLOWS_FOR_NULL_ATTRIBUTE = false;

  /** This is the attributes name. */
  public static final String ATTRIBUTE_NAME = "description";

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
  protected String getAttributeInputNullChecked(Document jsoupDocument)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }


}
