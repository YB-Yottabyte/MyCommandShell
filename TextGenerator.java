import java.util.Scanner;

/**
 * TextGenerator is a class that uses a Markov chain to generate text based on
 * a given input text and specified order of the model.
 *
 * Features include:
 * - Building a Markov chain from the input text.
 * - Generating new text based on the Markov chain.
 *
 */

public class TextGenerator {
    private MarkovChain markovChain;

    /**
     * Constructor for TextGenerator.
     * Initializes a new MarkovChain instance.
     */

    public TextGenerator() {
        markovChain = new MarkovChain();
    }

    /**
     * Builds a Markov chain based on the input text and specified order.
     * It creates states and transitions from the input text.
     *
     * @param text The input text from which to build the Markov chain.
     * @param order The order of the Markov model (length of state).
     */

    public void buildMarkovChain(String text, int order) {
        for (int i = 0; i <= text.length() - order; i++) {
            String state = text.substring(i, i + order);
            String nextState = (i + order < text.length()) ? String.valueOf(text.charAt(i + order)) : "";
            markovChain.addTransition(state, nextState);
        }
    }

    /**
     * Generates text of specified length starting from a given state.
     *
     * @param length The total length of the generated text.
     * @param startState The initial state from which to begin text generation.
     * @return The generated text as a String.
     */

    public String generateText(int length, String startState) {
        StringBuilder generatedText = new StringBuilder(startState);
        String state = startState;

        for (int i = 0; i < length - startState.length(); i++) {
            String next = markovChain.next(state);
            if (next == null) break;
            generatedText.append(next);
            state = generatedText.substring(generatedText.length() - startState.length());
        }

        return generatedText.toString();
    }


    /**
     * Main method to run the TextGenerator application.
     * It handles user input and displays the generated text.
     *
     * @param args Command line arguments (not used).
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text for Markov model:");
        String text = scanner.nextLine();
        System.out.println("Enter the order of the model:");
        int order = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter start state:");
        String startState = scanner.nextLine();
        System.out.println("Enter the length of text to generate:");
        int length = scanner.nextInt();

        TextGenerator generator = new TextGenerator();
        generator.buildMarkovChain(text, order);
        String generatedText = generator.generateText(length, startState);
        System.out.println("Generated text: " + generatedText);

        scanner.close();
    }
}
