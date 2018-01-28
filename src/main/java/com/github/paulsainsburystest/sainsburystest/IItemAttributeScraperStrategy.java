package com.github.paulsainsburystest.sainsburystest;

import org.jsoup.nodes.Document;

/**
 * The interface for defining a strategy to extract attributes from a webpage.
 * @author Paul
 * @param <ATTRIBUTE_RETURN_TYPE> The attribute's return type.
 */
public interface IItemAttributeScraperStrategy<ATTRIBUTE_RETURN_TYPE>
{

  /**
   * Does the strategy allow for no attribute to be returned.
   * @return true for nulls allowed, otherwise false.
   */
  public boolean allowsForNullAttribute();

  /**
   * Get the string identifier of an item's attribute to be used in a set.
   * @return The attributes name (non-null string).
   */
  public String getAttributeName();

  /**
   * Get the attribute defined by this strategy.
   * @param jsoupDocument The webpage to extract the attribute from.
   * @return The attribute or if it's allowed null.
   * @see #allowsForNullAttribute()
   */
  public ATTRIBUTE_RETURN_TYPE getAttribute(Document jsoupDocument);

}
