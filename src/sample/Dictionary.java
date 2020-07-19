package sample;

import java.util.*;


public class Dictionary {
    private Map<String, String> wordAndTranslation;


    public Dictionary() {
        this.wordAndTranslation = new HashMap<>();
    }

    public boolean addWordTranslationPair(String word, String translation) {
        if (this.wordAndTranslation.containsKey(word)) {
            return false;
        }
        this.wordAndTranslation.put(word, translation);
        return true;
    }

    public String getRandomWord() {
        Random random = new Random();
        Object[] wordList = this.wordAndTranslation.keySet().toArray();
        int randomIndex = random.nextInt(wordList.length);
        return wordList[randomIndex].toString();
    }

    public String getTranslation(String word) {
        return this.wordAndTranslation.get(word);
    }

    public int getSize() {
        return this.wordAndTranslation.size();
    }

}
