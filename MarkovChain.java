import java.util.*;

/**
 * MarkovChain is a class that implements a simple Markov chain model.
 * It allows users to define states and transitions between those states,
 * enabling the generation of a sequence of states based on the defined
 * probabilities of moving from one state to another.
 *
 * Features include:
 * - Adding transitions between states.
 * - Retrieving the next state based on the current state.
 * - String representation of the Markov chain's transition map.
 *
 */

public class MarkovChain {
    private Map<String, List<String>> transitions;
    private Random random;

    /**
     * Constructor for MarkovChain.
     * Initializes the transitions map and the random number generator.
     */

    public MarkovChain() {
        transitions = new HashMap<>();
        random = new Random();
    }

    /**
     * Adds a transition from one state to another.
     * If the state does not exist, it is created with an empty list of next states.
     *
     * @param state The current state from which to transition.
     * @param nextState The state to which the transition occurs.
     */

    public void addTransition(String state, String nextState) {
        transitions.putIfAbsent(state, new ArrayList<>());
        transitions.get(state).add(nextState);
    }

    /**
     * Returns the next state given the current state based on the defined transitions.
     * If no transitions are available, it returns null.
     *
     * @param state The current state.
     * @return The next state selected at random, or null if no transitions exist.
     */

    public String next(String state) {
        List<String> nextStates = transitions.get(state);
        if (nextStates == null || nextStates.isEmpty()) return null;
        return nextStates.get(random.nextInt(nextStates.size()));
    }


    /**
     * Returns a string representation of the Markov chain's transitions.
     *
     * @return A string that represents the current state transitions.
     */

    @Override
    public String toString() {
        return transitions.toString();
    }
}
