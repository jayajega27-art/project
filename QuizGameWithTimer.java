import java.util.*;

public class QuizGameWithTimer {
    static Scanner sc = new Scanner(System.in);
    static int score = 0;

    // Question class to store question, options, and correct answer
    static class Question {
        String question;
        String[] options;
        char answer;

        Question(String question, String[] options, char answer) {
            this.question = question;
            this.options = options;
            this.answer = answer;
        }
    }

    public static void main(String[] args) {
        // Create quiz questions
        Question[] questions = {
            new Question("What is the capital of France?",
                    new String[]{"A. Paris", "B. London", "C. Rome", "D. Berlin"}, 'A'),
            new Question("Which language runs in a web browser?",
                    new String[]{"A. Java", "B. C", "C. Python", "D. JavaScript"}, 'D'),
            new Question("Which planet is known as the Red Planet?",
                    new String[]{"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"}, 'B'),
            new Question("What is 5 + 7?",
                    new String[]{"A. 10", "B. 11", "C. 12", "D. 13"}, 'C')
        };

        System.out.println("=== Welcome to the Quiz Game ===");
        System.out.println("You have 10 seconds to answer each question.\n");

        // Loop through each question
        for (Question q : questions) {
            askQuestion(q);
        }

        // Show final score
        System.out.println("\nQuiz Over! Your score: " + score + "/" + questions.length);
    }

    public static void askQuestion(Question q) {
        System.out.println("\n" + q.question);
        for (String option : q.options) {
            System.out.println(option);
        }

        Timer timer = new Timer();
        final boolean[] timeUp = {false};

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\n⏳ Time's up! Moving to next question...");
                timeUp[0] = true;
                synchronized (sc) {
                    sc.notify(); // Wake up if waiting for input
                }
            }
        };

        timer.schedule(task, 10000); // 10 seconds timer

        String input = "";
        synchronized (sc) {
            if (sc.hasNextLine()) {
                input = sc.nextLine().trim().toUpperCase();
            }
        }
        timer.cancel();

        if (!timeUp[0]) {
            if (!input.isEmpty() && input.charAt(0) == q.answer) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + q.answer);
            }
        }
    }
}
