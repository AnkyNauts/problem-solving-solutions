package src.main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVParserQuestion {

  /**
   * Take the array of strings that is lines of 2 column CSV of the “brand ID” and “price” as the standard input of your program.
   *
   * Then output 5 column CSV (array of lines too) that has following aggregated information for each brand ID:
   *
   * Brand ID
   * High: It’s highest price
   * Open: It’s 1st price (= price of the 1st record of the brand)
   * Close: It’s last price (= price of the last record of the brand)
   * Low: It’s lowest price
   * The resulting CSV rows must be sorted by brand ID (in the dictionary order).
   * The price’s precision (number of digits) must be kept in the output.
   *
   * Example
   * Input:
   *
   * APPX,170.00
   * AMZY,150.00
   * APPX,180.00
   * APPX,165.00
   * APPX,185.00
   * AMZY,145.00
   * Output:
   *
   * AMZY,150.00,150.00,145.00,145.00
   * APPX,185.00,170.00,185.00,165.00
   * @param args
   */
  public static void main(String[] args) {
    {
      String[] csv = {"APPX", "170.0", "APPX", "180.0", "APPX", "165.0", "APPX", "185.0", "AMZY",
          "150.0", "AMZY", "145.0"};
      Map<String, List<Double>> map = new HashMap();

      for (int i = 0; i < csv.length; i++) {
        if (i % 2 != 0) {
          List<Double> list = map.getOrDefault(csv[i - 1], new ArrayList<>());
          list.add(Double.parseDouble(csv[i]));
          map.put(csv[i - 1], list);
        } else {
          if (!map.containsKey(csv[i])) {
            List<Double> list = map.getOrDefault(csv[i], new ArrayList<>());
            map.put(csv[i], list);
          }
        }
      }
      List<String> resultList = new ArrayList<>();
      List<String> list = map.keySet().stream().sorted().collect(Collectors.toList());
      for (String element : list) {
        resultList.add(element);
        List<Double> mylist = map.get(element).stream().sorted().collect(Collectors.toList());
        resultList.add(String.valueOf(mylist.get(0)));
        resultList.add(String.valueOf(map.get(element).get(0)));
        resultList.add(String.valueOf(map.get(element).get(map.get(element).size() - 1)));
        resultList.add(String.valueOf(mylist.get(mylist.size() - 1)));

      }
      System.out.println(resultList);

    }

  }
}
