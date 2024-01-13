package eca;

/**
 * A class that generates pseudo-random int, long, float and double numbers, using an ECA that follows Wolfram's Rule 30.
 * Every bit of the random number is obtained from the central cell of the automata (arbitrary choice).
 */
public class RandomGenerator {
	
	/**
	 * The Elementary Cellular Automata used to generate pseudo-random numbers.
	 */
	private ECA automata;
	
	/**
	 * Creates the ECA needed to generate pseudo-random numbers.
	 * @param seed Starting evolution cycle of the automata from which the Randomizer starts.
	 */
	public RandomGenerator(long seed) {
		this.automata = new ECA(seed);
	}
	
	/**
	 * Creates the ECA needed to generate pseudo-random numbers with a seed dependent from the current time in ms.
	 */
	public RandomGenerator() {
		this.automata = new ECA();
	}
	
	/**
	 * Initiates the Randomizer with an automata given as parameter.
	 * @param eca The pre-confgured ECA used to generate pseudo-random numbers
	 */
	public RandomGenerator(ECA eca) {
		this.automata = eca;
	}
	
	/**
	 * Generates a pseudo-random positive integer number from left to right.
	 * 		random : maxGenerable = x : right - left
	 * @param left Left inclusive bound
	 * @param right Right exclusive bound
	 * @return A pseudo-random positive integer
	 */
	public int randInt(int left, int right) {
		int randomInteger = this.randInt();
		return left + (randomInteger % (right - left));
	}
	
	/**
	 * Generates a pseudo-random positive integer from 0 to bound.
	 * @param bound Exclusive right bound
	 * @return A pseudo-random positive integer
	 */
	public int randInt(int bound) {
		return this.randInt() % bound;
	}
	
	/**
	 * Generates a pseudo-random positive integer from 0 to Integer.MAX_VALUE
	 * @return A psuedo-random positive integer
	 */
	public int randInt() {
		int bits =  31;
		
		int random = 0, factor = 1;
		
		for(int i = 0; i < bits; i++) {
			if(automata.getCentralBit()) {
				random += factor;
			}
			automata.evolve(1);
			factor *= 2;
		}
		
		return random;
	}
	
	/**
	 * Generates a pseudo-random positive long integer between left (inclusive) and right (exclusive).
	 * Rand is in [left, right).
	 * @param left Left inclusive bound of the interval
	 * @param right Right exclusive bound of the interval
	 * @return A pseudo-random positive long in the interval
	 */
	public long randLong(long left, long right) {
		return (this.randLong() % (right - left)) + left;
	}
	
	/**
	 * Generates a pseudo-random positive long integer between 0 and bound.
	 * @return A pseudo-random positive long between 0 and bound
	 */
	public long randLong(long bound) {
		return this.randLong() % bound;
	}
	
	/**
	 * Generates a pseudo-random long integer from 0 to Long.MAX_VALUE
	 * @return A pseudo-random positive long
	 */
	public long randLong() {
		int bits = 63;
		
		long random = 0, factor = 1;
		
		for(int i = 0; i < bits; i++) {
			if(automata.getCentralBit())
				random += factor;
			automata.evolve(1);
			factor *= 2;
		}
		
		return random;
	}
	
	/**
	 * Generates a pseudo-random float number from 0.0 to 1.0.
	 * This kind of number has 1 bit for sign (positive), 8 bits for exponent (0)
	 * and 23 bits for fraction (that the method has to generate).
	 * @return A float number from 0.0 to 1.0
	 */
	public float randFloat() {
		int bits = 23;
		
		float random = (float)0.0, factor = (float)0.5;
		
		for(int i = 0; i < bits; i++) {
			if(automata.getCentralBit()) {
				random += factor;
			}
			automata.evolve(1);
			factor /= 2;
		}
		
		return random;
	}
	
	/**
	 * Generates a pseudo-random double number from 0.0 to 1.0.
	 * Doubles have 1 bit for sign, 11 bits for exponent (0) and 52 bits for
	 * the fraction that the method has to generate.
	 * @return A double number from 0.0 to 1.0
	 */
	public double randDouble() {
		int bits = 52;
		
		double random = 0.0, factor = 0.5;
		
		for(int i = 0; i < bits; i++) {
			if(automata.getCentralBit()) {
				random += factor;
			}
			automata.evolve(1);
			factor /= 2;
		}
		
		return random;
	}
	
}
