import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class AnagramsClient {
    
    public static void main(String[] args) {
        Map<String, List<Word>> anagrams = new HashMap<>();

        // read words and make groups of anagrams
        Scanner s = new Scanner(System.in);
        while(s.hasNext()) {
            Word word = new Word(s.next());
            String firm = word.getFirm();
            List<Word> words = anagrams.get(firm);
            if(words == null)
                anagrams.put(firm, words = new ArrayList<Word>());
            words.add(word);
        }

        // order elements of the groups with size greater than 1, and put them in a list
        List<List<Word>> list = new ArrayList<>();
        for (List<Word> group : anagrams.values())
            if(group.size() > 1) {
                Collections.sort(group);
                list.add(group);
            }

        // sort the groups in the list
        Collections.sort(list,
            new Comparator<>(){
                @Override
                public int compare(List<Word> group1, List<Word> group2) {

                    int result;

                    // if size is the same sort by alphabetic order of the first word of each group
                    if ((result = Integer.compare(group1.size(), group2.size())) == 0)
                        for(int i = 0; i < group1.size() && result == 0; i++)
                            result = -group1.get(i).compareTo(group2.get(i));

                    return -result;
                }
            }
        );

        for (List<Word> group : list)
            System.out.println(group);

        s.close();
    }
}
