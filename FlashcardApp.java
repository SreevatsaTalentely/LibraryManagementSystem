import java.util.*;

class Flashcard {
    private String question;
    private String answer;
    private boolean isLearned;

    // Constructor
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.isLearned = false;
    }

    // Getters and setters
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isLearned() {
        return isLearned;
    }

    public void markAsLearned() {
        this.isLearned = true;
    }

    @Override
    public String toString() {
        return "Question: " + question + "\nAnswer: " + answer + (isLearned ? " [Learned]" : " [Not Learned]");
    }
}

class FlashcardApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Flashcard> flashcards = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nFlashcard App");
            System.out.println("1. Add a Flashcard");
            System.out.println("2. View Flashcards");
            System.out.println("3. Study Flashcards");
            System.out.println("4. Mark Flashcard as Learned");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addFlashcard();
                    break;
                case 2:
                    viewFlashcards();
                    break;
                case 3:
                    studyFlashcards();
                    break;
                case 4:
                    markFlashcardAsLearned();
                    break;
                case 5:
                    System.out.println("Exiting Flashcard App...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addFlashcard() {
        System.out.print("Enter the question for the flashcard: ");
        String question = scanner.nextLine();
        System.out.print("Enter the answer for the flashcard: ");
        String answer = scanner.nextLine();

        Flashcard flashcard = new Flashcard(question, answer);
        flashcards.add(flashcard);
        System.out.println("Flashcard added successfully!");
    }

    private static void viewFlashcards() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards available.");
            return;
        }

        System.out.println("\nAll Flashcards:");
        for (Flashcard flashcard : flashcards) {
            System.out.println(flashcard);
            System.out.println("=====================================");
        }
    }

    private static void studyFlashcards() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards available to study.");
            return;
        }

        System.out.println("\nStudy Mode: Answer the questions");
        for (Flashcard flashcard : flashcards) {
            if (!flashcard.isLearned()) {
                System.out.println("Question: " + flashcard.getQuestion());
                System.out.print("Your answer: ");
                String userAnswer = scanner.nextLine();

                if (userAnswer.equalsIgnoreCase(flashcard.getAnswer())) {
                    System.out.println("Correct! You got it right.");
                } else {
                    System.out.println("Incorrect. The correct answer is: " + flashcard.getAnswer());
                }
            }
        }
    }

    private static void markFlashcardAsLearned() {
        System.out.print("Enter the question of the flashcard you want to mark as learned: ");
        String question = scanner.nextLine();

        boolean found = false;
        for (Flashcard flashcard : flashcards) {
            if (flashcard.getQuestion().equalsIgnoreCase(question)) {
                flashcard.markAsLearned();
                System.out.println("Flashcard marked as learned.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Flashcard with the given question not found.");
        }
    }
}
