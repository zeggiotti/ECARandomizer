package eca;

/**
 * A class that represents an Elementary Cellular Automata which evolves following Wolfram's Rule 30.
 * Source: ECA - https://en.wikipedia.org/wiki/Elementary_cellular_automaton
 * 		   Wolfram's Rule 30 - https://en.wikipedia.org/wiki/Rule_30
 */
public class ECA {
	
	/**
	 * Cells of the Automata
	 */
	private final int length;
	
	/**
	 * Representation of the Automata. Each cell of the array is a cell of the Automata.
	 * Each cell has a status (0, 1 or false, true) and two neighbors (1D automata).
	 */
	private boolean[] eca;
	
	/**
	 * Number of cycles the automata has done.
	 */
	private int seed;
	
	/*
	 * Maximum number of cycles that the automata needs to evolve before starting to generate.
	 */
	private final int MAX_SEED_DIMENSION;
	
	/**
	 * Creates an automata and evolves from 0 to MAX_SEED_DIMENSION times based on seed.
	 * 
	 * @param seed Number of cycles the automata has to evolve before being ready.
	 */
	public ECA(long seed) {
		this(seed, 50, 5000);		// Default settings for the ECA.
	}
	
	/**
	 * Creates an automata and uses the current time in ms to generate the seed.
	 */
	public ECA() {
		this(System.currentTimeMillis());
	}
	
	/**
	 * Allows to create an ECA with personalized parameters.
	 * @param seed Starting evolving cycle for the Automata
	 * @param length Number of cells of the automata
	 * @param maxSeedDim Maximum number of cycles to evolve before being ready
	 */
	public ECA(long seed, int length, int maxSeedDim) {
		this.length = length;
		this.MAX_SEED_DIMENSION = maxSeedDim;
		
		this.eca = new boolean[length];			// Set to false by default.
		this.seed = Math.abs((int) seed % MAX_SEED_DIMENSION);
		
		this.eca[(int) this.length / 2] = true;
		
		this.evolve(this.seed);
	}
	
	/**
	 * Evolves the automata.
	 * @param times Number of times the automata has to evolve
	 */
	public void evolve(int times) {
		boolean[] nextEca = new boolean[length];
		for(int round = 0; round < times; round++) {
			for(int i = 0; i < this.length; i++) {
				boolean left = this.eca[(i - 1 + length) % length];
				boolean me = this.eca[i];
				boolean right = this.eca[(i + 1 + length) % length];
				nextEca[i] = ECA.computeNextState(left, me, right);
			}
			
			this.eca = nextEca;
		}
	}
	
	/**
	 * @return The state of the central cell of the ECA, which is a bit of a pseudo-random number
	 */
	public boolean getCentralBit() {
		return this.eca[(int) this.length / 2];
	}
	
	/**
	 * Computes the next state for a specific cell of the ECA following Rule 30 of an ECA.
	 * @param left State of the left neighbor
	 * @param me State of the cell
	 * @param right State of the right neighbor
	 * @return The next state the cell will have in ECA
	 */
	private static boolean computeNextState(boolean left, boolean me, boolean right) {
		if (left && me && right) 		return false;
		if (left && me && !right) 		return false;
		if (left && !me && right) 		return false;
		if (left && !me && !right) 		return true;
		if (!left && me && right) 		return true;
		if (!left && me && !right) 		return true;
		if (!left && !me && right) 		return true;
		if (!left && !me && !right) 	return false;
		else return false;
	}
	
}
