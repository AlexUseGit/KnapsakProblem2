package tests.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import model.BiDirectSolver;
import model.Solution;
import model.Task;

import org.junit.Before;
import org.junit.Test;

public class BiDirectSolverTests {

	private BiDirectSolver solver;
	private Task task;

	@Before
	public void setUp() {
		Integer[][] costs = { { 8, 3, 6, 5, 4 } };
		Integer[][] weights = { { 3, 4, 7, 2, 3 }, { 4, 6, 3, 1, 5 } };
		Integer[] limits = { 13, 12 };
		task = new Task("", 5, 2, 1, true);
		for (int row = 0; row < costs.length; row++) {
			for (int col = 0; col < costs[row].length; col++) {
				task.setValue(costs[row][col], row, col);
			}
		}
		for (int row = costs.length; row < weights.length + costs.length; row++) {
			for (int col = 0; col < weights[row - costs.length].length; col++) {
				task.setValue(weights[row - costs.length][col], row, col);
			}
			task.setValue(limits[row - costs.length], row, weights[row
					- costs.length].length);
		}
		solver = new BiDirectSolver(task);
	}

	@Test
	public void byDefaultSolverNotEnd() {
		assertFalse(solver.isEnd());
	}

	@Test
	public void taskWithoutSolutions() {
		task.setValue(0, 1, 5);
		solver = new BiDirectSolver(task);

		assertFalse(solver.hasSolution());
	}

	@Test
	public void taskWithAbsoluteSolution() {
		task.setValue(100, 1, 5);
		task.setValue(100, 2, 5);
		solver = new BiDirectSolver(task);
		solve();
		Solution solution = solver.getCurrentLeaderTop();

		assertTop(26, 26, solution);
	}

	@Test
	public void taskWithOneVariableSolution() {
		task.setValue(2, 1, 5);
		task.setValue(1, 2, 5);
		solver = new BiDirectSolver(task);
		solve();
		Solution solution = solver.getCurrentLeaderTop();

		assertTop(5, 5, solution);
	}

	@Test
	public void getLeaderTopRound0() {
		Solution top = solver.getCurrentLeaderTop();

		assertTop(13, 21, top);
	}

	@Test
	public void getLeaderTopRound1() {
		solver.solve();
		Solution solution = solver.getCurrentLeaderTop();

		assertTop(13, 21, solution);
	}

	@Test
	public void getLeaderTopRound2() {
		solver.solve();
		solver.solve();
		Solution solution = solver.getCurrentLeaderTop();

		assertTop(19, 20, solution);
	}

	@Test
	public void getSolution() {
		solve();
		Solution solution = solver.getCurrentLeaderTop();

		assertTop(19, 19, solution);
	}

	private void solve() {
		while (!solver.isEnd()) {
			solver.solve();
		}
	}

	private void assertTop(int expectH, int expectV, Solution top) {
		int actualH = top.getH();
		int actualV = top.getV();

		assertEquals(expectH, actualH);
		assertEquals(expectV, actualV);
	}

}
