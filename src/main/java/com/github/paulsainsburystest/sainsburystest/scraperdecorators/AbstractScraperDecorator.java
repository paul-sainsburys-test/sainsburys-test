package com.github.paulsainsburystest.sainsburystest.scraperdecorators;

/**
 * This is the base class for the {@link IScraperDecorator} interface implementation.
 * @author Paul
 */
public abstract class AbstractScraperDecorator implements IScraperDecorator
{
  /** The next decorator in the chain (non-null). */
  protected final IScraperDecorator nextScraperDecorator;

  /**
   * The constructor for this class.
   * @param nextScraperDecorator The next scraper decorator to be called in this chain.
   * @throws NullPointerException If the parameter is null.
   */
  public AbstractScraperDecorator(IScraperDecorator nextScraperDecorator)
  {
    if (nextScraperDecorator == null)
    {
      throw new NullPointerException("nextScraperDecorator cannot be null.");
    }

    this.nextScraperDecorator = nextScraperDecorator;
  }

}
