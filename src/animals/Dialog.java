package animals;

import java.lang.management.GarbageCollectorMXBean;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dialog {

    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    private static final List<String> affirmationList = Arrays.asList("y", "yes", "yeah", "yep", "sure", "right", "affirmative", "correct", "indeed", "you bet", "exactly", "you said it");
    private static final List<String> negationList = Arrays.asList("n", "no", "no way", "nah", "nope", "negative", "i don't think so", "yeah no");
    private static final List<String> clarificationQuestions = Arrays.asList("Come on, yes or no!", "Please confirm, yes or no!", "Please simplify, yes or no!", "Sorry, i don't understand, yes or no");
    private static final List<String> farewellWays = Arrays.asList("See you soon!", "Have a nice day!", "Goodbye!");

    static boolean bye = false;
    static boolean bye_sec = false;




    public static String FILE_LOCATION_NAME = getFileLocationName();
    public static final animals.Storage_Services storage = new Storage_Services();
    static String type = "";

    public Dialog(String Type) {
        Greet();
    type = Type;
        String FILE_NAME = FILE_LOCATION_NAME + "." + type; ;
        FILE_LOCATION_NAME = FILE_NAME;
        var exist = storage.checkFile(FILE_NAME);
        Node root;
        if (exist) {
            root = storage.load(type, FILE_NAME);
        } else {
            root = new Node(Favourite_animal());
        }
        final var node = new KnowledgeTree(root);
        Repeat(node);
        ;
    }
    //Start();
    //Conversation();




    public static void Greet() {
        int hour = LocalDateTime.now().getHour();
        if (hour >= 5 && hour <= 12)
            System.out.println("Good morning");

        else if (hour > 12 && hour <= 18)
            System.out.println("Good afternoon");
        else
            System.out.println("Good evening");


    }
    public static String Favourite_animal() {
        System.out.println("I want to learn about animals.\n" +
                "Which animal do you like most?");

        String favorite_animal = scanner.nextLine();
        favorite_animal = favorite_animal.toLowerCase();
        favorite_animal = Put_article(favorite_animal);
        //  Root.setData(favorite_animal);
        //System.out.println("Wonderful! I've learned so much about animals!\n" +
               // "Let's play a game!\n");
        return favorite_animal;
    }






    public static void Conversation(String first_animal, String second_animal,KnowledgeTree node) {



        bye = false;
        bye_sec = false;


        System.out.println("Specify a fact that distinguishes " + first_animal +  " from " + second_animal + "\n");



        while (!bye) {
            String userInput = User_response().toLowerCase();


            Pattern pattern = Pattern.compile("it (can|has|is).+");

            Matcher matcher = pattern.matcher(userInput);
            //System.out.println(userInput);
            if(matcher.matches()) {


                System.out.println("Is the statement correct for " + second_animal + "?");
                while (!bye) {
                    String response = User_response().toLowerCase();
                    if (affirmationList.contains(response)) {
                        System.out.println("I learned the following facts about animals:");
                        System.out.println("The " + Delete_article(first_animal) + no_fact(userInput) +"." );
                        System.out.println("The " + Delete_article(second_animal) + yes_fact(userInput) + ".");

                        System.out.println("I can distinguish these animals by asking the question:");

                        System.out.println(Generate_question(userInput));
                        System.out.println("Nice! I've learned so much about animals!");

                        node.addAnimal(second_animal, Generate_question(userInput), true);
                        node.reset();
                        Node new_animal = node.getCurrent();
                        storage.save(node.getRoot(),type,FILE_LOCATION_NAME);

                        System.out.println("Would you like to play again?");
                        response = User_response().toLowerCase();

                        while(!bye_sec) {
                            if (negationList.contains(response)) {
                                System.out.println(farewellWays.get(rand.nextInt(3)));
                                bye = true;
                                bye_sec = true;
                            }
                            else if(affirmationList.contains(response)) {
                                Repeat(node);
                                bye = true;
                                bye_sec = true;
                            }
                            else {
                                System.out.println("yes or no");
                            }
                        }

                    } else if (negationList.contains(response)) {
                        System.out.println("I learned the following facts about animals:");
                        System.out.println("The " + Delete_article(first_animal) + yes_fact(userInput) + ".");
                        System.out.println("The " + Delete_article(second_animal) + no_fact(userInput) + ".");
                        System.out.println("I can distinguish these animals by asking the question:");
                        System.out.println(Generate_question(userInput));
                        System.out.println("Nice! I've learned so much about animals!\n" );

                        node.addAnimal(second_animal, Generate_question(userInput), false);
                        // Node new_animal = node.getCurrent();
                        node.reset();

                        storage.save(node.getRoot(),type,FILE_LOCATION_NAME);


                        //
                        System.out.println("Would you like to play again?");
                        response = User_response().toLowerCase();

                        while(!bye_sec) {
                            if (negationList.contains(response)) {
                                System.out.println(farewellWays.get(rand.nextInt(3)));
                                bye = true;
                                bye_sec = true;
                            } else if (affirmationList.contains(response)) {
                                Repeat(node);
                                bye = true;
                                bye_sec = true;
                            } else {
                                System.out.println("yes or no");
                            }
                        }
                    } else {
                        System.out.println("yes or no");
                    }
                }
            }else {
                System.out.println("The examples of a statement:\n" +
                        " - It can fly\n" +
                        " - It has horn\n" +
                        " - It is a mammal\n" +
                        "Specify a fact that distinguishes a cat from a shark.\n" +
                        "The sentence should be of the format: 'It can/has/is ...'.");
            }

        }



    }

    public static void Repeat(KnowledgeTree node) {
        System.out.println("You think of an animal, and I guess it.");
        System.out.println("Press enter when you.re ready");

        scanner.nextLine();


        Boolean correct = false;
        Boolean ok = true;

        while(correct == false) {


            if(node.getData().length() > 10)
                System.out.println(node.getData());
            else
                System.out.println("Is it " + node.getData() + "?");

            String user = User_response().toLowerCase();

            boolean check = false;
            while(check == false) {
                if (affirmationList.contains(user))
                    check = true;
                else if ((negationList.contains(user)))
                    check = true;
                else {
                    System.out.println("yes or no");
                    user = User_response().toLowerCase();
                }

            }

            if (node.isStatement()) {

                // System.out.println("here");
                if (affirmationList.contains(user)) {
                    node.next(true);
                    //   System.out.println(node.getData());

                }
                else if (negationList.contains(user)) {
                    node.next(false);

                }
                else {
                    System.out.println("yes or no");
                }



            } else {
                if (node.isStatement() == false && affirmationList.contains(user)) {

                    correct = true;

                    System.out.println("Would you like to play again?");
                  String  response = User_response().toLowerCase();
                   bye_sec = false;
                    while(!bye_sec) {
                        if (negationList.contains(response)) {
                            System.out.println(farewellWays.get(rand.nextInt(3)));

                            bye_sec = true;
                          //  break;
                        } else if (affirmationList.contains(response)) {
                            Repeat(node);

                            bye_sec = true;
                        } else {
                            System.out.println("yes or no");
                            response = User_response().toLowerCase();
                        }
                    }



                }
                if (node.isStatement() == false && negationList.contains(user)) {
                    System.out.println("I give up. What animal do you have in mind?");
                    String new_animal = scanner.nextLine();
                    new_animal = new_animal.toLowerCase();
                    new_animal = Put_article(new_animal);
                    correct = true;
                    Conversation(node.getData(),new_animal,node);


                }
            }
            // correct =true;

        }

    }



    public static String User_response() {
        String userInput = scanner.nextLine().trim();

        if (userInput.lastIndexOf(".") == userInput.length() - 1 || userInput.lastIndexOf("!") == userInput.length() - 1) {
            try {
                userInput = userInput.substring(0, userInput.length() - 1);
            }
            catch (IndexOutOfBoundsException e) {
                return "exception";
            }
        }return  userInput;
    }

    public static String Put_article(String userInput) {
        String[] userInputs = userInput.split(" ");

        String animalText = "";
        if(userInput.equalsIgnoreCase("a unicorn"))
        {
            return "a unicorn";
        }

        if(userInput.equalsIgnoreCase("an unicorn"))
        {
            return "an unicorn";
        }


        //deal with articles
        if (userInputs.length > 1 && Arrays.asList(new String[]{"a", "an", "the"}).contains(userInputs[0].toLowerCase())) {
            animalText = userInput.toLowerCase().substring(userInputs[0].length() + 1);
        } else {
            animalText = userInput.toLowerCase();
        }


        //System.out.println(animalText);

        if (Arrays.asList(new Character[]{'a', 'e', 'i', 'o','u'}).contains(animalText.toCharArray()[0]) || animalText.indexOf("xe") > -1) {
            animalText = "an " + animalText;
        } else {
            animalText = "a " + animalText;
        }
        return animalText;
    }

    public static String Delete_article(String userInput) {
        String[] userInputs = userInput.split(" ");

        String animalText = "";
        //deal with articles
        if (userInputs.length > 1 && Arrays.asList(new String[]{"a", "an", "the"}).contains(userInputs[0].toLowerCase())) {
            animalText = userInput.toLowerCase().substring(userInputs[0].length() + 1);
        } else {
            animalText = userInput.toLowerCase();
        }
        return animalText;
    }

    public static String yes_fact(String input) {

        if(input.startsWith("can", 3))
            input = " can" + input.substring(6);

        if(input.startsWith("has", 3))
            input = " has" + input.substring(6);

        if(input.startsWith("is", 3))
            input = " is" + input.substring(5);

        return input;
    }

    public static String no_fact(String input) {

        if(input.startsWith("can", 3))
            input = " can't" + input.substring(6);

        if(input.startsWith("has", 3))
            input = " doesn't have" + input.substring(6);

        if(input.startsWith("is", 3))
            input = " isn't" + input.substring(5);

        return input;
    }

    public static String Generate_question(String input) {
        if(input.startsWith("can", 3))
            input = "Can it" + input.substring(6) + "?";

        if(input.startsWith("has", 3))
            input = "Does it have" + input.substring(6) + "?";

        if(input.startsWith("is", 3))
            input = "Is it" + input.substring(5) + "?";



        return input;
    }

    private static String getFileLocationName() {

        return "C:\\Users\\DENISA\\Desktop\\Projects\\Guess the Animal\\Guess the Animal\\task\\animals.";
    }
}


