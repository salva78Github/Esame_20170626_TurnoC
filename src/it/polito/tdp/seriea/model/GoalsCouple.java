package it.polito.tdp.seriea.model;

public class GoalsCouple implements Comparable<GoalsCouple> {

	private final Goal goal1;
	private final Goal goal2;
	private final int numberOfResult;

	/**
	 * @param goal1
	 * @param goal2
	 * @param numberOfResult
	 */
	public GoalsCouple(Goal goal1, Goal goal2, int numberOfResult) {
		super();
		this.goal1 = goal1;
		this.goal2 = goal2;
		this.numberOfResult = numberOfResult;
	}

	/**
	 * @return the goal1
	 */
	public Goal getGoal1() {
		return goal1;
	}

	/**
	 * @return the goal2
	 */
	public Goal getGoal2() {
		return goal2;
	}

	/**
	 * @return the numberOfResult
	 */
	public int getNumberOfResult() {
		return numberOfResult;
	}

	@Override
	public int compareTo(GoalsCouple o) {
		// TODO Auto-generated method stub
		return o.getNumberOfResult() - getNumberOfResult();
	}

}
