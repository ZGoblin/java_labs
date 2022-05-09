package third;

import common.Common;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        TextProcessor textProcessor = new TextProcessorImpl();

        Common.introduceMyself();

        Common.print("""
                A sequence of words is entered. The sequence in which the words are in reverse order is displayed.
                Example: input sequence "abc def ghi", one - "ghi def abc"
                """);

        String inText = Common.input("Type text");
        Common.print("Processed text: %s", textProcessor.process(inText));

        Common.print("Template: %s", textProcessor.getTemplate());
        Common.print("Regex: %s", textProcessor.getRegex());
        Common.print("Examples: %s", Arrays.toString(textProcessor.get10Examples()));

        String inSecondText = Common.input("Type text");
        Common.print("Count of words matched with regex: %s", textProcessor.regexCount(inSecondText));
        Common.print("Words: %s", Arrays.toString(textProcessor.regexFind(inSecondText)));
        Common.print("Delete matched words: %s", textProcessor.regexRemove(inSecondText));
        Common.print("Replace matched words: %s", textProcessor.regexReplace(inSecondText));

    }

}
