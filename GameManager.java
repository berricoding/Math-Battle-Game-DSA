import java.util.*;

public class GameManager { // Generics ensure only String data is stored safely
// Queue is used to manage questions in FIFO order (First-In-First-Out)
    private Queue<Question> questions = new LinkedList<>(); 
// Stack is used to store history in LIFO order (Last-In-First-Out)
    private Stack<String> history = new Stack<>(); 
// Set is used to prevent duplicate questions
    private Leaderboard leaderboard = new Leaderboard();

    private int damage = 10;

    // needed for round-based enemy rotation)
    private static int roundCounter = 0;

    public void startGame(Scanner sc) {
        System.out.println("Enter name (or type exit): ");
        String name = sc.nextLine();

        if (name.equalsIgnoreCase("exit")) {
            System.out.println("Goodbye!");
            System.exit(0);
        }

        int difficulty = 0;

        while (true) {
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.print("Choice: ");

            try {
                difficulty = Integer.parseInt(sc.nextLine());

                if (difficulty < 1 || difficulty > 3) {
                    throw new InvalidChoiceException("Please choose between 1 and 3 only!");
                }

                break;
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number:");
            }
        }

        Player player = new Player(name, 100);

// Create circular linked list of enemies
//used to link enemies in a loop, allowing the game to rotate between enemies continuously across rounds.
        EnemyCLL enemyList = new EnemyCLL();

        Enemy enemy1 = new Enemy("Enemy 1", 100);
        Enemy enemy2 = new Enemy("Enemy 2", 100);
        Enemy enemy3 = new Enemy("Enemy 3", 100);

        enemyList.add(enemy1);
        enemyList.add(enemy2);
        enemyList.add(enemy3);

      
        Enemy enemy = enemyList.getHead();

// rotate enemy based on roundCounter
        for (int i = 0; i < roundCounter % 3; i++) {
            enemy = enemyList.getNextEnemy(enemy);
        }

        System.out.println("Current: " + enemy.name);

// Demonstrate circular behavior
        Enemy nextEnemy = enemyList.getNextEnemy(enemy);
        System.out.println("Next enemy (circular): " + nextEnemy.name);

        loadQuestions(difficulty);

        while (player.getHp() > 0 && enemy.getHp() > 0) {

            if (questions.isEmpty()) {
                loadQuestions(difficulty);
            }

            Question q = questions.poll();

            int correct = q.ask();

            int ans = 0;

            while (true) {
                System.out.print("Answer: ");

                try {
                    ans = Integer.parseInt(sc.nextLine());
                    break; 

                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number:");
                }
            }

            history.push("Correct: " + correct + " Your: " + ans);

            if (ans == correct) {
                enemy.takeDamage(damage);
                player.addScore(10);
                System.out.println("Correct!");
            } else {
                player.takeDamage(damage);
                System.out.println("Wrong!");
            }

            System.out.println(name + " HP: " + player.getHp());
            System.out.println("Enemy HP: " + enemy.getHp());
        }

        System.out.println(player.getHp() > 0 ? "YOU WIN!" : "ENEMY WINS!");

        leaderboard.add(new Player(player.getName(), player.getHp()));
        leaderboard.display();
        leaderboard.displayReverse();

        System.out.println("\nHistory:");
        showHistoryRecursive(history);

      
        roundCounter++;
    }

    private void loadQuestions(int d) {
//HashSet
//A HashSet ensures that only unique questions are added, improving gameplay variety.
        Set<String> used = new HashSet<>();
        int limit = (d == 3) ? 5 : 10; 

        while (used.size() < limit) {
            MathQuestion mq = new MathQuestion(d);

            if (!used.contains(mq.getQuestion())) {
                questions.add(mq);
                used.add(mq.getQuestion());
            }
        }
    }
//Recursion
//used to show the history
    private void showHistoryRecursive(Stack<String> stack) {
         Stack<String> temp = new Stack<>();
    temp.addAll(stack);
     showHelper(temp);
}

    private void showHelper(Stack<String> stack) {
        if (stack.isEmpty()) return;

    String top = stack.pop();
    showHelper(stack);
    System.out.println(top);
}
}