Time to play! The program prompts the user to think of an animal and tries to guess it. If it fails, the program asks for the user's help. The new knowledge is stored in a binary tree and kept in memory for the current game session. We will use the JSON format to store the accumulated knowledge.

![6ED9696C-D756-4AA3-8100-E60CD9368D59](https://user-images.githubusercontent.com/73117226/196727764-b09265f1-268c-45a0-8a4a-a078f169424f.jpeg)


Examples
The greater-than symbol followed by a space > represents the user input. Note that it's not part of the input.

Example 1

Good morning!

I want to learn about animals.
Which animal do you like most?
> cat
Wonderful!
I've learned so much about animals!
Let's play a game!
You think of an animal, and I guess it.
Press enter when you're ready.
> Is it a cat?
> No
I give up. What animal do you have in mind?
> a dog
Enter a statement which can help me distinguish a cat from a dog.
> It can climb trees.
Is that fact correct for a dog?
> No
I remember the following facts about animals:
- The cat can climb trees.
- The dog can't climb trees.

I've learned so much about animals!
Would you like to play again?
> No

Have a nice day!
Example 2

If a knowledge base already exists, the program doesn't ask the user about the favorite animal: it offers to play a game instead.

Hi Night Owl!

I know a lot about animals.
Let's play a game!
You think of an animal, and I guess it.
Press enter when you're ready.
>
Can it climb trees?
> Sure!
Come on, yes or no?
> Yeah
Is it a cat?
> Nope
I give up. What animal do you have in mind?
> Lynx
Enter a statement that can help me distinguish a cat from a lynx.
> It has tassels on its ears
Is the fact correct for a lynx?
> yea
I remember the following facts about animals:
- The cat hasn't tassels on its ears.
- The lynx has tassels on its ears.

I've learned so much about animals!
Would you like to play again?
> No

Good night!
