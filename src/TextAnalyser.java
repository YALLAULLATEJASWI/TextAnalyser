import java.util.HashMap;

public class TextAnalyser {

    TextAnalyser(int separator) {
        separator_ = separator;
    }
    

    private void initialise() {
        int separator = 0;
        for(int i = 0; i < text_.length(); i++) {
            char item = text_.charAt(i);
            if(Character.isDigit(item)) {
                if(digits.get(item) == null)
                    digits.put(item,1);
                else
                    digits.put(item, digits.get(item) + 1);
                wordsText.append(item);
            }
            else if(Character.isLetter(item)) {
                if(alphabets.get(item) == null)
                    alphabets.put(item,1);
                else
                    alphabets.put(item, alphabets.get(item) + 1);
                wordsText.append(item);
            }
            else if(item == '.' || item == ',' || item == ';') {
                if(specialChars.get(item) == null)
                    specialChars.put(item,1);
                else
                    specialChars.put(item, specialChars.get(item) + 1);
                separator++;
            }
            else {
                if(unknowns.get(item) == null)
                    unknowns.put(item,1);
                else
                    unknowns.put(item, unknowns.get(item) + 1);
                separator++;
            }
            if(separator == separator_) {
                wordsText.append("#");
                separator = 0;
            }
        }
    }

    private void analyseWords(String[] words) {
        for(int i = 0; i < words.length; i++) {
            if(wordsMap.get(words[i]) == null)
                wordsMap.put(words[i], 1);
            else
                wordsMap.put(words[i], wordsMap.get(words[i]) + 1);
        }
    }

    public String[] findWords() {
        return wordsText.toString().split("#");
    }

    public int getWordsLength() {
        return wordsText.toString().split("#").length;
    }

    public void report() {
        int uniqueLettersCount = alphabets.size();
        int totalLettersCount = getCountMapValues(alphabets);
        int uniqueDigitCount = digits.size();
        int totalDigitCount = getCountMapValues(digits);

        System.out.println("Total number of alpha numeric characters := " + (totalLettersCount + totalDigitCount));
        System.out.println("Total number of letters := " + totalLettersCount);
        System.out.println("Total number of letters frequency w.r.t total number of alpha numeric characters := " + (totalLettersCount/(totalLettersCount+totalDigitCount)));
        System.out.println("Total number of digits := " + totalDigitCount);
        System.out.println("Total number of digits frequency w.r.t total number of alpha numeric characters := " + (totalDigitCount/(totalLettersCount+totalDigitCount)));
        System.out.println("Total number of words := " + getWordsLength());
        System.out.println("Total number of words starts with " + matchSubstring_ + " w.r.t total number of words := " + (getFrequencyDataStartsWith(wordsMap, matchSubstring_)/getWordsLength()));
        System.out.println("Total number of words ends with " + matchSubstring_ + " w.r.t total number of words := " + (getFrequencyDataEndsWith(wordsMap, matchSubstring_)/getWordsLength()));
        System.out.println("Total number of words containing " + matchSubstring_ + " in middle w.r.t total number of words := " + (getFrequencyDataMiddle(wordsMap, matchSubstring_)/getWordsLength()));
        int seqLength = wordsText.toString().split(matchSubstring_).length;
        System.out.println("Total number of sequence with " + matchSubstring_ + " := " + (seqLength == 1 ? 0 : seqLength));
    }

    private int getCountMapValues(HashMap<Character, Integer> item) {
        int count = 0;
        if(item == null)
            return count;
        for(Character a : item.keySet()) {
            count = count + item.get(a);
        }
        return count;
    }

    private int getFrequencyDataMiddle(HashMap<String, Integer> data, String matchSubstring_) {
        if (wordsMap == null)
            return 0;
        int count = 0;
        for (String key : data.keySet()) {
            while (key.length() > matchSubstring_.length()) {
                key = key.substring(1, key.length() - 1);
            }
            if (key.equals(matchSubstring_))
                count++;
        }
        return count;
    }

    private int getFrequencyDataStartsWith(HashMap<String, Integer> data, String startWithText) {
        if(data == null)
            return 0;
        int count = 0;
        for(String key : data.keySet()) {
            if(key.startsWith(startWithText)) {
                count++;
            }
        }
        return count;
    }

    private int getFrequencyDataEndsWith(HashMap<String, Integer> data, String endsWithText) {
        if(data == null)
            return 0;
        int count = 0;
        for(String key : data.keySet()) {
            if(key.endsWith(endsWithText)) {
                count++;
            }
        }
        return count;
    }

    public void generateReport(String input, String matchSubstring) {
        text_ = new StringBuilder(input);
        matchSubstring_ = matchSubstring;
        initialise();
        analyseWords(findWords());
        report();
    }

    private int separator_;
    private String matchSubstring_;
    private HashMap<Character,Integer> alphabets = new HashMap<>();
    private HashMap<Character,Integer> digits = new HashMap<>();
    private HashMap<Character,Integer> specialChars = new HashMap<>();
    private HashMap<Character,Integer> unknowns = new HashMap<>();
    private HashMap<String,Integer> wordsMap = new HashMap<>();
    private StringBuilder text_;
    private StringBuilder wordsText = new StringBuilder();
}
