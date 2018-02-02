package com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies;

import com.github.paulsainsburystest.sainsburystest.ConstraintFailedException;
import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
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
   * @throws NullPointerException If jsoupDocument is null.
   * @throws ConstraintFailedException If it was going to return null but was
   *    not allowed to.
   * @throws MalformedDocumentException If the strategy cannot determine if the
   *    attribute exists or if it should exist but does not exist.
   */
  public ATTRIBUTE_RETURN_TYPE getAttribute(Document jsoupDocument)
      throws ConstraintFailedException, MalformedDocumentException;

  /**
   * Determines if an instance is the same.
   * @param obj Object to compare with.
   * @return Equals with {@link #getAttributeName() }.
   */
  @Override
  public boolean equals(Object obj);

  /**
   * Calculates the hashcode of the instance.
   * @return Hashcode of {@link #getAttributeName() }.
   */
  @Override
  public int hashCode();

}
