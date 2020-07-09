/*
 * @JT Kelly 
 * <p> Referee
 * <p> Project 3
 * <p> 
 */

/*
	The rules to the dice game Big Pig

	Number of Players: 2 + 
	Game Duration: <30 mins
	Players Aged: 6 +

	You will need: 2 dice and paper to score on.

	Play begins with the selection of a target game score.
	The users then decide which single die value will be
	the "Pig" value that enhances scoring.

	To Play: The players take turns to roll both dice, 
	they can roll as many times as they want in one turn.
	
	A player scores the sum of the two dice thrown and 
	gradually reaches a higher score as they continue to roll.

	If a single number "Pig" value is thrown on either die, the score 
	for that whole turn is lost. However a double "Pig" value counts as 25.
	The first player to reach the target score wins unless a player scores more 
	subsequently in the same round. This means that everyone in 
	the game must have the same number of turns.
	
	The game may NOT end in a tie. 

 */

import java.util.Scanner;

public class Referee
{
	// central method to start and manage game play
	private final char _YES = 'Y';
	private int maxScore = -1;

	public void start()
	{
		Scanner kb = new Scanner(System.in);
		GamePlayer.getValues();
		maxScore = GamePlayer.returnScore();
		do
		{
			GamePlayer player1 = new GamePlayer();
			GamePlayer player2 = new GamePlayer();
			while (player1.currentTotal() != maxScore || player2.currentTotal() != maxScore)
			{
				System.out.println("\nPlayer 1");
				takeTurn(kb, player1);
				System.out.println("\nPlayer 2");
				takeTurn(kb, player2);
				if (player1.currentTotal() >= maxScore && player1.currentTotal() > player2.currentTotal())
				{
					System.out.println("Player 1 wins!");
					break;
				} else if (player2.currentTotal() >= maxScore && player2.currentTotal() > player1.currentTotal())
				{
					System.out.println("Player 2 wins!");
					break;
				}

				if (player1.currentTotal() == player2.currentTotal())
				{
					do
					{
						takeTurn(kb, player1);
						takeTurn(kb, player2);
					} while (player1.currentTotal() == player2.currentTotal());
				}
			}
			System.out.println("Do you want to play again? (Y/N)");
		} while (yesResponse(kb) == true);
		
		System.out.println("Goodbye");
	}

	//
	// Returns the initial max score (loops until a value between 1 <= score <=
	// 100 is entered)
	//
	public int getInitialMax(Scanner kb)
	{
		while (maxScore > 100 || maxScore < 0)
		{
			System.out.println("What score would you like to play to? (100 max)");
			maxScore = kb.nextInt();
			// kb.nextLine();
		}
		return maxScore;
	}

	//
	// Returns the initial max score (loops until a value between 1 <= score <=
	// 100 is entered)
	//
	public int getPigValue(Scanner kb)
	{
		System.out.println("What will be the 'pig' value? (1-6)");
		int pigValue = kb.nextInt();
		// kb.nextLine();
		return pigValue;

	}

	//
	// method for managing a single session of rolling dice
	//
	private void takeTurn(Scanner kb, GamePlayer player)
	{
		String response;
		boolean keepRolling = true;

		do
		{
			// Roll the dice
			player.rollDice();

			// Report the result
			System.out.println(player.lastRoll() + " scored " + player.evaluate() + " points.");

			// Did the player pig out?
			if (player.piggedOut())
			{
				System.out.println("You pigged out this turn.");
			} else
			{
				//
				// Roll again; see if the user wants to roll again to add to
				// total or pass and keep current points
				//
				System.out.println("Your current round score is " + player.currentRound() + " points. (total: "
						+ player.currentTotal() + ") Keep rolling? Respond (Y/N) only.");

				if (!yesResponse(kb))
				{
					keepRolling = false;
					int roundScore = player.doneRolling();
					System.out.printf("Your total for the round was %d and your total score is %d.\r\n", roundScore,
							player.currentTotal());
				}
			}

		} while (!player.piggedOut() && keepRolling);
	}

	//
	// Returns true if the user enters a 'y' or 'Y'
	//
	public boolean yesResponse(Scanner kb)
	{
		return kb.nextLine().toUpperCase().charAt(0) == _YES;
	}
}
