package model;

import java.util.ArrayList;
import java.util.List;

public class Solution implements Comparable<Solution> {

	private int h;
	private int v;
	private int fix;
	private List<Integer> constVariables = new ArrayList<>();
	private boolean[] discretSolution;

	public Solution() {
	}

	public Solution(List<Integer> constVariables) {
		this.constVariables.addAll(constVariables);
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setV(int v) {
		this.v = v;
	}

	@Override
	public String toString() {
		String res = "Solution [h=" + h + ", v=" + v + ", fix = " + fix
				+ ", constants = " + constVariables + ", solution = [";
		for(int var = 0; var < discretSolution.length; var++){
			res+=discretSolution[var]+", ";
		}
		res += "]]";
		return res;
	}

	@Override
	public int compareTo(Solution that) {
		if (this.v > that.v) {
			return 1;
		} else if (this.v < that.v) {
			return -1;
		}
		return 0;
	}

	public void setFix(int fix) {
		this.fix = fix;
		constVariables.add(fix);
	}

	public int getH() {
		return h;
	}

	public int getV() {
		return v;
	}

	public int getFix() {
		return fix;
	}

	public List<Integer> getConstVariables() {
		return constVariables;
	}

	public void initializeSolution(int size) {
		discretSolution = new boolean[size];
		for (int var = 0; var < size; var++) {
			discretSolution[var] = false;
		}
	}

	public void setVariable(int var) {
		discretSolution[var] = true;
	}

	public boolean isVarTaken(int var) {
		return discretSolution[var];
	}

	public boolean[] getSolution() {
		return discretSolution;
	}
}
