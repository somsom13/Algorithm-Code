class Solution {
    private static final String[] words = {"aya", "ye", "woo", "ma"};
    private static int answer = 0;

    public int solution(String[] babbling) {
        for (String word: babbling) {
            findMatchingWord(word);
        }
        return answer;
    }

    private static void findMatchingWord(String word) {
        String prevWord = "";
        while (!word.isBlank()) {
            String speakWord;
            for (int i = 0; i < words.length; i++) {
                speakWord = words[i];
                if (word.startsWith(speakWord)) {
                    if (speakWord.equals(prevWord)) {
                        return;
                    }
                    word = word.replaceFirst(speakWord, "");
                    prevWord = speakWord;
                    break;
                }
                if (i == words.length - 1) {
                    return;
                }
            }
        }
        answer++;
    }
}
