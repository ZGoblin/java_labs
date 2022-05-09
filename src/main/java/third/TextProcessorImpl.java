package third;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class TextProcessorImpl implements TextProcessor {

    private final String wordsSplitter = " ";

    private final String regex = "^\\Sab\\S*\\d";

    @Override
    public String process(String inputSequence) {

        String[] words = inputSequence.split(wordsSplitter);

        for (int i = 0; i < words.length / 2; i++) {
            String temp = words[i];
            words[i] = words[words.length - i - 1];
            words[words.length - i - 1] = temp;
        }

        return String.join(wordsSplitter, words);
    }

    @Override
    public int regexCount(String inputSequence) {

        AtomicInteger count = new AtomicInteger();

        doWithMatchesWords(inputSequence, word -> count.getAndIncrement());

        return count.get();
    }

    @Override
    public String regexRemove(String inputSequence) {
        return String.join(wordsSplitter, proceedWithWords(inputSequence, word -> ""));
    }

    @Override
    public String regexReplace(String inputSequence) {
        return String.join(wordsSplitter, proceedWithWords(inputSequence, word -> "*****"));
    }

    @Override
    public String[] regexFind(String inputSequence) {

        List<String> words = new ArrayList<>();

        doWithMatchesWords(inputSequence, words::add);

        return words.toArray(new String[0]);
    }

    @Override
    public String getTemplate() {
        return "\\?ab*#";
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public String[] get10Examples() {
        return new String[]{
                "laba3",
                "Kabanchik13",
                "Aabad,5",
                "sabaton1999",
                "kabinet223",
                "Labyrinth12",
                "Laboratornaya3",
                "FabiaSckoda12",
                "wabco12",
                "Xabia2022"
        };
    }

    private void doWithMatchesWords(String inputSequence, Consumer<String> action) {
        proceedWithWords(
                inputSequence,
                word -> {
                    action.accept(word);
                    return "";
                });
    }

    private String[] proceedWithWords(String inputSequence, Function<String, String> action) {
        List<String> words = new ArrayList<>();

        List.of(inputSequence.split(wordsSplitter)).forEach(word -> {
            if (word.matches(regex)) {
                words.add(action.apply(word));
            } else {
                words.add(word);
            }
        });

        return words.toArray(new String[0]);
    }

}
