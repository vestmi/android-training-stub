package com.houston_inc.training;

/**
 * Memory game which shows sequence of integers to user one by one and user
 * should provide the same sequence as an answer. This class contains the game
 * logic, like generating the integer sequences and counting user score.
 *
 * made in Pakistan.
 */
public class MemoryGame {

    private String integerSequence;

    private boolean gameFinished;
    private int score;

    private int numberOfTimesCurrentStepRepeated;
    private int numberOfTimesToRepeatRound;

    /**
     * Init a new game
     * @param numberOfIntegersToStart The number of integers to begin with.
     * @param numberOfTimesToRepeatStep How many times each step containing the same number of integers should be repeated.
     *                                   For example, if game starts with 4 integers and numberOfTimesToRepeatStep=2, the
     *                                   user is shown e.g. "4528", then "8231", then "34597", then "45602"...
     */
    public MemoryGame(int numberOfIntegersToStart, int numberOfTimesToRepeatStep) {
        this.numberOfTimesToRepeatRound = numberOfTimesToRepeatStep;
        generateNewSequence(numberOfIntegersToStart);
    }

    /**
     * Advances the game based on the answer the user provides. If the user's answer equals to the sequence of integers
     * shown to the user, the game advances to the next round. If the answer is incorrect, the game ends.
     * @param userAnswer The user's answer.
     */
    public void advance(String userAnswer) {

        if (gameFinished) {
            return;
        }

        numberOfTimesCurrentStepRepeated++;
        boolean correctAnswer = userAnswer.equals(integerSequence);

        if (correctAnswer) {
            score += Math.pow(userAnswer.length(), 3);

            int newSequenceLength = integerSequence.length();
            if (numberOfTimesCurrentStepRepeated == this.numberOfTimesToRepeatRound) {
                newSequenceLength++;
                numberOfTimesCurrentStepRepeated = 0;
            }

            generateNewSequence(newSequenceLength);

        } else {
            gameFinished = true;
        }

    }

    private void generateNewSequence(int sequenceLength) {
        this.integerSequence = "";
        for (int i = 0 ; i < sequenceLength; i++) {
            integerSequence += (int) (Math.random () * 10);
        }
    }

    /**
     * Sequence of integers which should be shown to the user.
     * @return Array of integer chars or null if the game has finished.
     */
    public char[] getSequenceToBeShownToUser() {
        if (gameFinished) {
            return null;
        } else {
            return integerSequence.toCharArray();
        }
    }

    public int getScore() {
        return score;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
}