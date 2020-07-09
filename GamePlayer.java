import java.util.Scanner;

/*
 * @JT Kelly
 * <p> GamePlayer
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

// this class manages the state of the dice and the scoring
public class GamePlayer
{
	// keep track of total and round scores as well as the two dice.
	private int _totalScore = 0;
	private int _roundScore = 0;
	private Die _die1;
	private Die _die2;
	private static int _pigValue = 1;
	private static int _maxScore;
	static Referee ref = new Referee();
	static Scanner kb = new Scanner(System.in);

	// constructor to set the beginning state of the object
	// and accept a value that will be used for special scoring.
	public GamePlayer(int pigValue)
	{
		_totalScore = 0;
		_roundScore = 0;
		_die1 = new Die();
		_die2 = new Die();
	}

	public GamePlayer()
	{
		_totalScore = 0;
		_roundScore = 0;
		_die1 = new Die();
		_die2 = new Die();
	}

	public static void getValues()
	{
		_maxScore = ref.getInitialMax(kb);
		_pigValue = ref.getPigValue(kb);
	}

	public static int returnScore()
	{
		return _maxScore;
	}

	// accessor for total score
	public int currentTotal()
	{
		return _totalScore;
	}

	// accessor for this round score
	public int currentRound()
	{
		return _roundScore;
	}

	// accessor to see if the user has rolled a single "1" and loses turn
	public boolean piggedOut()
	{
		return singlePigRolled();
	}

	// mutator that simulates rolling two dice and evaluating the resulting score
	public void rollDice()
	{

		// Roll the die
		_die1.roll();
		_die2.roll();
	}

	// accessor for a formatted string of what the last roll looked like
	public String lastRoll()
	{
		return "D1 (" + _die1.faceValue() + "), D2 (" + _die2.faceValue() + ")";
	}

	// used to look once at the current roll and calculate scoring
	// for both the current roll and the accumulation of the round score
	public int evaluate()
	{
		if (singlePigRolled() == true)
		{
			_roundScore = 0;
			_totalScore += 0;
			return _roundScore;
		} else if (doublePigRolled() == true)
		{
			_roundScore = 25;
			_totalScore += 25;
			return _roundScore;
		}
		_roundScore = _die1.faceValue() + _die2.faceValue();
		_totalScore += _roundScore;
		return _roundScore;

	}

	// was just one of the rolled dice the Pig value?
	private boolean singlePigRolled()
	{
		if (_die1.faceValue() == _pigValue && _die2.faceValue() != _pigValue
				|| _die1.faceValue() != _pigValue && _die2.faceValue() == _pigValue)
		{
			return true;
		} else
		{
			return false;
		}
	}

	// were both of the rolled dice the Pig value?
	private boolean doublePigRolled()
	{
		if ((_die1.faceValue() == _pigValue) && (_die2.faceValue() == _pigValue))
		{
			return true;
		} else
		{
			return false;
		}
	}

	//
	// mutator to end a round and keep the add this round to the total
	// also returns the total value of the round and resets the round total for next
	// time
	//
	public int doneRolling()
	{
		_roundScore = 0;
		return _totalScore;
	}
}
