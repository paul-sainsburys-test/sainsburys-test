package com.github.paulsainsburystest.sainsburystest;

import org.jsoup.nodes.Document;

/**
 * Default baseclass for item attribute scraper strategies.
 *
 * @author Paul
 * @param <ATTRIBUTE_RETURN_TYPE> The attribute's return type.
 */
public abstract class AbstractItemAttributeScraperStrategy<ATTRIBUTE_RETURN_TYPE>
    implements IItemAttributeScraperStrategy<ATTRIBUTE_RETURN_TYPE>
{

  @Override
  public ATTRIBUTE_RETURN_TYPE getAttribute(Document jsoupDocument) throws ConstraintFailedException
  {
    if (jsoupDocument == null)
    {
      throw new NullPointerException("jsoupDocument cannot be null.");
    }

    ATTRIBUTE_RETURN_TYPE retObj = this.getAttributeInputNullChecked(jsoupDocument);

    if (retObj == null && this.allowsForNullAttribute() == false)
    {
      throw new ConstraintFailedException("Returned object is null and nulls are not allowed.");
    }

    return retObj;
  }


  /**
   * Get the attribute from a {@link Document} which is not null.
   * @param jsoupDocument The webpage, parameter will be non-null.
   * @return The attribute or if it's allowed null.
   * @see AbstractItemAttributeScraperStrategy#getAttribute(org.jsoup.nodes.Document)
   */
  protected abstract ATTRIBUTE_RETURN_TYPE getAttributeInputNullChecked(Document jsoupDocument);

  @Override
  public boolean equals(Object obj)
  {
    //Use an internal method as we know this will be non-null.
    return this.getAttributeName().equals(obj);
  }

  @Override
  public int hashCode()
  {
    return this.getAttributeName().hashCode();
  }



}
