/**
 * Program that will read selected AP News Feeds.
 * 
 * Preston McIllece, Ali Saffary, & Justin Chong's Lab14
 * 
 */

import java.io.*;    
import java.net.*;   
import java.util.Scanner;  
import java.util.ArrayList;

public class NewsReader {
  
  // numeric value of each feed
  private static final int HEADLINE = 0;
  private static final int NATIONAL = 1;
  private static final int WORLD = 2;
  private static final int POLITICS = 3;
  private static final int BUSINESS = 4;
  private static final int TECHNOLOGY = 5;
  private static final int SPORTS = 6;
  private static final int ENTERTAINMENT = 7;
  private static final int HEALTH = 8;
  private static final int SCIENCE = 9;
  private static final int STRANGE = 10;
  
  // array list for storing each feed
  private  ArrayList<String> feeds = new ArrayList<String>();
  private  ArrayList<String> names = new ArrayList<String>();

  /**
   * Initializes the array list of feeds.
   */
  public NewsReader() 
  {
    feeds.add(HEADLINE, "http://hosted2.ap.org/atom/APDEFAULT/3d281c11a96b4ad082fe88aa0db04305");       // headline news
    feeds.add(NATIONAL,"http://hosted2.ap.org/atom/APDEFAULT/386c25518f464186bf7a2ac026580ce7");        // US/National
    feeds.add(WORLD, "http://hosted2.ap.org/atom/APDEFAULT/cae69a7523db45408eeb2b3a98c0c9c5");          // World
    feeds.add(POLITICS, "http://hosted2.ap.org/atom/APDEFAULT/89ae8247abe8493fae24405546e9a1aa");       // Politics
    feeds.add(BUSINESS, "http://hosted2.ap.org/atom/APDEFAULT/f70471f764144b2fab526d39972d37b3");       // Business
    feeds.add(TECHNOLOGY, "http://hosted2.ap.org/atom/APDEFAULT/495d344a0d10421e9baa8ee77029cfbd");     // Techology
    feeds.add(SPORTS, "http://hosted2.ap.org/atom/APDEFAULT/347875155d53465d95cec892aeb06419");         // Sports
    feeds.add(ENTERTAINMENT, "http://hosted2.ap.org/atom/APDEFAULT/4e67281c3f754d0696fbfdee0f3f1469");  // Entertainment
    feeds.add(HEALTH, "http://hosted2.ap.org/atom/APDEFAULT/bbd825583c8542898e6fa7d440b9febc");         // Health
    feeds.add(SCIENCE, "http://hosted2.ap.org/atom/APDEFAULT/b2f0ca3a594644ee9e50a8ec4ce2d6de");        // Science
    feeds.add(STRANGE, "http://hosted2.ap.org/atom/APDEFAULT/aa9398e6757a46fa93ed5dea7bd3729e");        // Strange
    
  names.add(HEADLINE, "Headlines");
  names.add(NATIONAL, "National");
  names.add(WORLD, "World");
  names.add(POLITICS, "Politics");
  names.add(BUSINESS, "Business");
  names.add(TECHNOLOGY, "Technology");
  names.add(SPORTS, "Sports");
  names.add(ENTERTAINMENT, "Entertainment");
  names.add(HEALTH, "Health");
  names.add(SCIENCE, "Science");
  names.add(STRANGE, "Strange");
  
  }
  

  /**
   * Prints the headline news
   */
  public void printHeadlineNews() throws Exception
  {
    printNewsFeed(0);
  }
  
  /**
   * Prints the news for a specified feed
   */
  public void printNewsFeed(int feedNumber) throws Exception
  {
    String name = feeds.get(feedNumber);
    URL website = new URL(name);
    Scanner in = new Scanner(new InputStreamReader(website.openStream()));
    
    while (in.hasNextLine())
{
    String line = in.nextLine();
    
    if (line.contains("<title type=\"text\">"))
    {
    System.out.println(removeTags(line));
    }
}
    return;
  }
  
  /**
   * Prints the news for a specified feed that contains the key
   */
  public void printNewsFeed(int feedNumber, String key) throws Exception
  {
    String name = feeds.get(feedNumber);
    URL website = new URL(name);
    Scanner in = new Scanner(new InputStreamReader(website.openStream()));
    
    int count = 0;
    
    while (in.hasNextLine())
{
    String line = in.nextLine();
    
    if (line.contains(key) && line.contains("<title type=\"text\">"))
    {
      count++;
    System.out.println(removeTags(line));
    }
}
    if (count == 0)
    {
      System.out.println("Sorry. There no news items related to " + key + " in the section of " + names.get(feedNumber));
    }
    return;
  }
  
  /**
   * Prints the news for all feeds that contain the specified key
   */
  public void printNewsFeed(String key) throws Exception 
  {
    for (int i = 0; i < feeds.size(); i++)
    {
      printNewsFeed(i, key);
    }

    return;
  }
  
   private static String removeTags(String original) 
   {
     int carrot = original.indexOf('>');
     int secondCarrot = original.indexOf('<', carrot);
     String newString = original.substring(carrot, secondCarrot);
   
     return newString;
   }
  
  public static void main(String[] args) throws Exception {
    
   NewsReader n = new NewsReader();
   n.printNewsFeed(0);
    
  }
}
