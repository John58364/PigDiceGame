/*
* @JT Kelly
* <p> Die 
* <p> Project 3
* <p> 
* 
*/
import java.util.Random;

// class to manage the value of a single simulated die
public class Die
{
	private int _pips = 1;
	private final int _MAX_PIPS = 6;
	private Random _randNum;
	private int _returnRoll;

	// constructor that will create a Random class and generate a random start
	// value.
	public Die()
	{
		_randNum = new Random();
	}

	// accessor to return the current value of the die.
	public int faceValue()
	{
		return _returnRoll;
	}

	// mutator to randomly change the value of the die.
	public int roll()
	{
		int range = (_MAX_PIPS);
		int rolled = _randNum.nextInt(range);
		_returnRoll = rolled + _pips;
		return _returnRoll;
	}
}
