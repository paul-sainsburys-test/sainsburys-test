package com.github.paulsainsburystest.sainsburystest;

import com.github.paulsainsburystest.sainsburystest.itemattributescraperstrategies.*;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.IItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.itemscraperstrategies.SinglePageItemScraperStrategy;
import com.github.paulsainsburystest.sainsburystest.scraperdecorators.IScraperDecorator;
import com.github.paulsainsburystest.sainsburystest.scraperdecorators.TotalScraperDecorator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * The main entry point to the program.
 * @author Paul
 */
public class Main
{
  /** Default category url. */
  private static final String DEFAULT_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

  //Webpage scraper strategy.
  /** Single webpage scraper. */
  private final IItemScraperStrategy scraperStrategy = new SinglePageItemScraperStrategy();

  //Item attributes scraper strategies.
  /** Title item attribute scraper strategy. */
  private final TitleAttributeItemScraperStrategy titleScraper =
      new TitleAttributeItemScraperStrategy();
  /** Description item attribute scraper strategy. */
  private final DescriptionAttributeItemScraperStrategy descriptionScraper =
      new DescriptionAttributeItemScraperStrategy();
  /** Kilocalories item attribute scraper strategy. */
  private final KilocaloriesAttributeItemScraperStrategy kilocaloriesScraper =
      new KilocaloriesAttributeItemScraperStrategy();
  /** Unit price item attribute scraper strategy. */
  private final UnitPriceAttributeItemScraperStrategy unitPriceScraper =
      new UnitPriceAttributeItemScraperStrategy();

  /** Attribute scraper strategies set. */
  private final LinkedHashSet<IItemAttributeScraperStrategy<?>> attributeStrategies = new LinkedHashSet<>
  (
    Arrays.asList(
      new IItemAttributeScraperStrategy<?>[]
      {
        this.titleScraper, this.descriptionScraper, this.kilocaloriesScraper,
        this.unitPriceScraper
      }
    )
  );

  //Webpage scraper and decorators.
  /** Webpage scraper. */
  private final Scraper scraper = new Scraper(this.scraperStrategy, this.attributeStrategies);
  /** Total price scraper decorator. */
  private final TotalScraperDecorator totalScraperDecorator =
      new TotalScraperDecorator(this.scraper);

  /**
   * The entry point to the scraper.
   * It's more flexible then just referencing one of the other methods. (Change it
   * once here and it's done.)
   */
  private final IScraperDecorator scraperEntryPoint = totalScraperDecorator;

  /**
   * The entry point of this program
   * @param args A link of a category webpage to download or help.
   * @see #printHelp()
   */
  public static void main(String[] args) throws IOException, MalformedDocumentException
  {
    Main main = new Main();
    if (args.length == 1)
    {
      String arg = args[0];
      //If the user wants help, print it.
      if (arg.equals("-h") || arg.equals("--help"))
      {
        Main.printHelp();
      }
      //Otherwise assume the string is a url
      else
      {
        main.start(arg);
      }
    }
    //No parameters or more than 1 parameter.
    else
    {
      Main.printHelp();
    }
  }

  /**
   * Download the specified webpage, extract all of the items their attributes we've
   * specified and print the results.
   * @param categoryUrl Url of a category to extract from.
   */
  public void start(String categoryUrl) throws MalformedDocumentException, IOException
  {
    //Allow for exceptions to bubble up.
    Map<String, Object> output = this.scraperEntryPoint.scrapeDecorated(categoryUrl);

    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.disableHtmlEscaping(); //Disable turning ampersands and apostrophes into unicode.
    gsonBuilder.setPrettyPrinting(); //Pretty print.

    Gson gson = gsonBuilder.create();

    String json = gson.toJson(output);
    System.out.println(json);

  }

  /** Print the help. */
  private static void printHelp()
  {
    System.out.println("Usage:");
    System.out.println("    Scrape and extract attributes from a webpage:");
    System.out.println("    java -jar <jar-file> <category url to parse>");
    System.out.println("");
    System.out.println("    View this help:");
    System.out.println("    java -jar <jar-file> (-h | --help)");
    System.out.println("");
    System.out.println("Scraper example:");
    System.out.println("    java -jar <jar-file> \""+DEFAULT_URL+"\"");
    System.out.println("");
  }

}
