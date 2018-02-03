package com.github.paulsainsburystest.sainsburystest;

import java.io.IOException;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * A simple webpage cache.
 *
 * It's used to store the documents requested for tests, this reduces latency costs.
 *
 * @author Paul
 */
public class SimpleWebpageCache
{
  /** Map of all urls to documents requested. */
  private final HashMap<String,Document> map = new HashMap<>();

  /**
   * Get the document for the url
   * @param url The webpage to download.
   * @return The webpage as {@link Document}.
   * @throws IOException On an error.
   */
  public Document getDocument(String url) throws IOException
  {
    if (url == null)
    {
      throw new NullPointerException("url cannot be null.");
    }

    if (this.map.containsKey(url))
    {
      Document doc = this.map.get(url);
      return doc.clone(); //Clone to prevent modification to the original.
    }
    else
    {
      Document jsoupDocument = Jsoup.connect(url).get();

      //It only gets to here if it's not thrown an ioerror.
      synchronized (this.map)
      {
        this.map.put(url, jsoupDocument);
      }

      return jsoupDocument.clone(); //Clone to prevent modification to the original.
    }

  }

}
