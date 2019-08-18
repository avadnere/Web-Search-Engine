import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;



class Snake {
    //Search for the hyperlinks and store into the array list
    //also append https or wwww if a link doesn't have!
    ArrayList < String > crawledRefrence = new ArrayList < String > ();
    ArrayList crawl(String url) throws IOException {
        System.out.println("Crawling Strated ................");
        Document document;
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        for (Element link: links) {
            if (!(link.attr("href").contains("https:"))) {
                if (!(link.attr("href").startsWith("//www."))) {
                    continue;
                }
                String newurl = "https:" + link.attr("href");
                crawledRefrence.add(newurl);
                System.out.println(newurl);
            } else {
                System.out.println(link.attr("href"));
                crawledRefrence.add(link.attr("href"));
            }

        }
        return crawledRefrence;
    }
}


class hashMapSort {
    //***HASH MAP SORTING REFERRED FROM GEEKS FOR GEEK**//
    // sort the hash map
    public static HashMap < String, Integer > sortByValue(HashMap < String, Integer > hm) {
        List < Map.Entry < String, Integer > > list =
            new LinkedList < Map.Entry < String, Integer > > (hm.entrySet());

        Collections.sort(list, new Comparator < Map.Entry < String, Integer > > () {
            public int compare(Map.Entry < String, Integer > o1,
                Map.Entry < String, Integer > o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap < String, Integer > temp = new LinkedHashMap < String, Integer > ();
        for (Map.Entry < String, Integer > aa: list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}


class Scraper {
    // scraps the provided url
    String scrapedDocument;

    String getScraped(String url) throws IOException {

        Document document;
        try {
            document = Jsoup.connect(url).get();
            String scrapedDocument = document.body().text();
            return scrapedDocument;
        } catch (IOException e) {
            return null;
        }

    }

}

class stopWordFilter {
    //Filter  the stop word from the scrapped document 
    String stopWordList[] = {
        " i ",
        " me ",
        " my ",
        " myself ",
        " we ",
        " our ",
        " ours ",
        " ourselves ",
        " you ",
        " your ",
        " yours ",
        " yourself ",
        " yourselves ",
        " he ",
        " him ",
        " his ",
        " himself ",
        " she ",
        " her ",
        " hers ",
        " herself ",
        " it ",
        " its ",
        " itself ",
        " they ",
        " them ",
        " their ",
        " theirs ",
        " themselves ",
        " what ",
        " which ",
        " who ",
        " whom ",
        " this ",
        " that ",
        " these ",
        " those ",
        " am ",
        " is ",
        " are ",
        " was ",
        " were ",
        " be ",
        " been ",
        " being ",
        " have ",
        " has ",
        " had ",
        " having ",
        " do ",
        " does ",
        " did ",
        " doing ",
        " a ",
        " an ",
        " the ",
        " and ",
        " but ",
        " if ",
        " or ",
        " because ",
        " as ",
        " until ",
        " while ",
        " of ",
        " at ",
        " by ",
        " for ",
        " with ",
        " about ",
        " against ",
        " between ",
        " into ",
        " through ",
        " during ",
        " before ",
        " after ",
        " above ",
        " below ",
        " to ",
        " from ",
        " up ",
        " down ",
        " in ",
        " out ",
        " on ",
        " off ",
        " over ",
        " under ",
        " again ",
        " further ",
        " then ",
        " once ",
        " here ",
        " there ",
        " when ",
        " where ",
        " why ",
        " how ",
        " all ",
        " any ",
        " both ",
        " each ",
        " few ",
        " more ",
        " most ",
        " other ",
        " some ",
        " such ",
        " no ",
        " nor ",
        " not ",
        " only ",
        " own ",
        " same ",
        " so ",
        " than ",
        " too ",
        " very ",
        " s ",
        " t ",
        " can ",
        " will ",
        " just ",
        " don ",
        " should ",
        " now "
    };

    String getStringFiltered(String scrappedArticle) {
        String filteredArticle = scrappedArticle;

        filteredArticle = filteredArticle.replaceAll("[^a-zA-Z0-9 ]", " ");
        filteredArticle = filteredArticle.toLowerCase();
        filteredArticle = filteredArticle.replaceAll("[\\p{P}\\p{S}]", "");
        filteredArticle = filteredArticle.replaceAll(",", ".");
        filteredArticle = filteredArticle.trim().replaceAll(" +", " ");
        for (int i = 0; i < stopWordList.length; i++) {
            filteredArticle = filteredArticle.replaceAll(stopWordList[i], " ");
        }
        return filteredArticle;
    }
    //filter the input string
    String getInputStringFiltered(String input) {
        String filteredArticle = input;

        filteredArticle = filteredArticle.replaceAll("[^a-zA-Z0-9 ]", " ");
        filteredArticle = filteredArticle.toLowerCase();
        filteredArticle = filteredArticle.replaceAll("[\\p{P}\\p{S}]", "");
        filteredArticle = filteredArticle.replaceAll(",", ".");
        filteredArticle = filteredArticle.trim().replaceAll(" +", " ");
        return filteredArticle;
    }

}

class tempObject {

    boolean isWord;
    int referenceCount;

    ArrayList < String > referenceList = new ArrayList < > ();
    Map < String, Integer > map = new HashMap < > ();

    void setIsWord(boolean isWord) {
        this.isWord = isWord;
        this.referenceCount++;

    }
    void setReferenceList(String reference) {
        if (map.containsKey(reference)) {
            Integer a = map.get(reference) + new Integer(1);
            map.put(reference, a);
        } else {
            map.put(reference, new Integer(1));
        }
    }
    boolean getIsWord() {
        return isWord;
    }
    int getReferenceCount() {
        return referenceCount;
    }
    Map getReferenceList() {
        return map;
    }
}
class Tries {

    boolean isWord;
    Tries[] children;
    tempObject temp;

    public Tries() {
        this.isWord = false;
        children = new Tries[36];
        this.temp = new tempObject();
    }
    public void addWord(String word, String reference) {

        word = word.replaceAll(" ", "");
        if (word.length() == 0) {

            temp.setIsWord(true);
            temp.setReferenceList(reference);
        } else {
            int treeNodeIndex = getIndex(word.charAt(0));
            try {
                if (this.children[treeNodeIndex] == null) {
                    this.children[treeNodeIndex] = new Tries();
                }
                this.children[treeNodeIndex].addWord(word.substring(1), reference);
            } catch (Exception e) {
                System.out.println("EXEPTION " + e);
            }

        }
    }

    public int getIndex(char character) {

        char A = 'A', Z = 'Z', a = 'a', z = 'z';
        int treeNodeIndex = 0;
        if (character >= a && character <= z) {
            treeNodeIndex = character - a + 10;
        } else {
            treeNodeIndex = character - 48;
        }
        return treeNodeIndex;
    }

    public tempObject searchWord(String word) {

        word = word.replaceAll(" ", "");
        if (word.length() == 0) {
            return this.temp;
        } else {
            int treeNodeIndex = getIndex(word.charAt(0));
            if (this.children[treeNodeIndex] == null) {
                return null;
            }
            return this.children[treeNodeIndex].searchWord(word.substring(1));
        }
    }
}

public class CS600 {

    public static void main(String[] args) throws IOException {
        try {
            String url = "https://editorial.rottentomatoes.com/article/renewed-and-cancelled-tv-shows-2019/";
            System.out.println("Intializing the Program with URL " + url);
            //call the snake class which crawl all the hyperlinks from base URL
            Snake snakeCrawler = new Snake();
            ArrayList < String > urls = snakeCrawler.crawl(url);
            urls.add(url);
            System.out.println("Crwaling Completed ");
            System.out.println("===================================================================");
            System.out.println("Scraping starting");
            stopWordFilter filter = new stopWordFilter();
            Scraper scraper = new Scraper();
            int size = urls.size();
            Tries tries = new Tries();

            for (int i = 0; i < size; i++) {
                System.out.println("Percentage Completed : " + (i * 100) / size);
                String scrapedDocument = scraper.getScraped(urls.get(i));
                if (scrapedDocument == null) {
                    continue;
                }
                //filter the stop words from scrapped documents
                String filteredText = filter.getStringFiltered(scrapedDocument);
                String[] articlePhrase = filteredText.split(" ");
                for (int j = 0; j < articlePhrase.length; j++) {
                    //add words from scraped documents into tries
                    tries.addWord(articlePhrase[j], urls.get(i));
                }
            };
            String a = null;
            do {
                System.out.println("Enter the text or type exit to stop");
                Scanner sc = new Scanner(System.in);
                a = sc.nextLine();
                stopWordFilter swf = new stopWordFilter();
                //filter the stop word from the input text
                a = swf.getInputStringFiltered(a);
                String[] inputText = a.split(" ");
                HashMap < String, Integer > resultMap = new HashMap < String, Integer > ();
                int r = inputText.length;
                while (r != 0) {
                    //searches the input word
                    tempObject tmp = tries.searchWord(inputText[inputText.length - r]);
                    if (tmp != null) {
                        System.out.println(tmp.isWord);
                        System.out.println(tmp.referenceCount);
                        Map < String, Integer > map = tmp.map;
                        Set set = map.entrySet();
                        Iterator iterator = set.iterator();
                        while (iterator.hasNext()) {
                            Map.Entry mentry = (Map.Entry) iterator.next();
                            if (resultMap.containsKey(mentry.getKey())) {
                                Integer frequency = resultMap.get(mentry.getKey());
                                resultMap.replace((String) mentry.getKey(), frequency + (Integer) mentry.getValue());
                            } else {
                                resultMap.put((String) mentry.getKey(), (Integer) mentry.getValue());
                            }
                        }
                    } else {
                        System.out.println("No Results found for the query ");
                    }
                    r--;
                }
                hashMapSort obj1 = new hashMapSort();
                Map < String, Integer > hm1 = obj1.sortByValue(resultMap);
                Set set = hm1.entrySet();
                Iterator iterator = set.iterator();
                System.out.println("===============RESULTS FOR QUERY " + a + "====================");
                int rank=hm1.size();
                while (iterator.hasNext()) {
                    Map.Entry rmap = (Map.Entry) iterator.next();
                    System.out.print("key is: " + rmap.getKey() + "         RANK : "+rank);
                    System.out.println("(frequency: "+rmap.getValue()+")");
                    rank--;
                }
            } while (!a.equalsIgnoreCase("exit"));
            System.out.println("===============RESULTS FOR QUERY " + a + "====================");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}