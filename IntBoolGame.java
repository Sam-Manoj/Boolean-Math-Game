import java.util.Random;
import java.util.Scanner;
public class IntBoolGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        int number_of_questions = 5;
        int score = 0;
        boolean continueGame = true;

        final long TIME_LIMIT = 1 * 60 * 1000;


        while (continueGame) {
            if (score < 5) {
                System.out.println("\nLEVEL 1: Answer the questions.");
                score = playLevel1(number_of_questions, TIME_LIMIT);
            }
            if (score >= 5) {
                System.out.println("\nCongratulations! You've reached LEVEL 2.");
                score = playLevel2(number_of_questions, TIME_LIMIT);
            }

            System.out.print("\nDo you want to continue playing? (yes/no): ");
            String response = sc.next().trim().toLowerCase();
            continueGame = response.equals("yes");
        }

        System.out.println("Thank you for playing! Your final score is: " + score);
        sc.close();
    }

    public static int playLevel1(int number_of_questions, long timeLimit) {
        TIME_LIMIT=3*60*1000;
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        int score = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < number_of_questions; i++) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > timeLimit) {
                System.out.println("TIME UP! You exceeded the time limit for LEVEL 1.");
                break;
            }

            int a = rn.nextInt(10);
            int b = rn.nextInt(10);
            int choice = rn.nextInt(4);
            int correct_ans = 0;
            String ques = "";

            correct_ans = switch (choice) {
                case 0 -> {
                    ques = "What is: " + a + " OR " + b + "?";
                    yield LEVEL1.or_op(a, b);
                }
                case 1 -> {
                    ques = "What is: " + a + " AND " + b + "?";
                    yield LEVEL1.and_op(a, b);
                }
                case 2 -> {
                    ques = "What is: ~" + a + "?";
                    yield LEVEL1.bt_not_op(a);
                }
                case 3 -> {
                    ques = "What is: " + a + " EX-OR " + b + "?";
                    yield LEVEL1.exor_op(a, b);
                }
                default -> 0;
            };

            System.out.println(ques);
            System.out.print("Your answer: ");
            int user_ans = sc.nextInt();

            if (user_ans == correct_ans) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was: " + correct_ans);
            }
        }
        return score;
    }

    public static int playLevel2(int number_of_questions,long timeLimit) {
        TIME_LIMIT=5*60*1000;
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        int score = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < number_of_questions; i++) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > timeLimit) {
                System.out.println("TIME UP! You exceeded the time limit for LEVEL 2.");
                break;
            }

            int a = rn.nextInt(10);
            int b = rn.nextInt(10);
            int c = rn.nextInt(10);
            int choice = rn.nextInt(8);
            int correct_ans = 0;
            String ques = "";

            correct_ans = switch (choice) {
                case 0 -> {
                    ques = "What is: " + a + " | " + b + " | " + c + "?";
                    yield LEVEL2.or_op(a, b, c);
                }
                case 1 -> {
                    ques = "What is: " + a + " & " + b + " & " + c + "?";
                    yield LEVEL2.and_op(a, b, c);
                }
                case 2 -> {
                    ques = "What is: (" + a + " & " + b + ") | " + c + "?";
                    yield LEVEL2.r(a, b, c);
                }
                case 3 -> {
                    ques = "What is: (" + a + " | " + b + ") & " + c + "?";
                    yield LEVEL2.r2(a, b, c);
                }
                case 4 -> {
                    ques = "What is: " + a + " ^ " + b + " | " + c + "?";
                    yield LEVEL2.exor_op(a, b, c);
                }
                case 5 -> {
                    ques = "What is: " + a + " ^ (" + b + " & " + c + ")?";
                    yield LEVEL2.ex_and(a, b, c);
                }
                case 6 -> {
                    ques = "What is: (" + a + " & " + b + ") ^ " + c + "?";
                    yield LEVEL2.and_ex(a, b, c);
                }
                case 7 -> {
                    ques = "What is: (" + a + " | " + b + ") ^ " + c + "?";
                    yield LEVEL2.or_ex(a, b, c);
                }
                default -> 0;
            };

            System.out.println(ques);
            System.out.print("Your answer: ");
            int user_ans = sc.nextInt();

            if (user_ans == correct_ans) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was: " + correct_ans);
            }
        }
        return score;
    }
}




