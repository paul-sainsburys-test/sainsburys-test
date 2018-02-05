package com.github.paulsainsburystest.sainsburystest.itemscraperstrategies;

import com.github.paulsainsburystest.sainsburystest.MalformedDocumentException;
import java.util.List;
import org.jsoup.nodes.Document;

/**
 * The interface for defining a strategy to extract item urls from a category webpage.
 * @author Paul
 */
public interface IItemScraperStrategy
{
  /**
   * Given a html {@link Document} (which is assumed to be a category page) extract
   * all of urls of the items listed.
   * @param jsoupDocument The document to extract from.
   * @return A list of all items on the page (not null).
   * @throws NullPointerException If jsoupDocument is null.
   * @throws MalformedDocumentException If the strategy cannot determine if the
   *    items should exist.
   */
  public List<String> getItemUrls(Document jsoupDocument)
      throws NullPointerException, MalformedDocumentException;

  /**
   * The name which is used to identify this scraper strategy.
   * @return A non-null string.
   */
  public String getName();

  /**
   * Determines if an instance is the same.
   * @param obj Object to compare with.
   * @return Equals with {@link #getName() }.
   */
  @Override
  public boolean equals(Object obj);

  /**
   * Calculates the hashcode of the instance.
   * @return Hashcode of {@link #getName() }.
   */
  @Override
  public int hashCode();
}