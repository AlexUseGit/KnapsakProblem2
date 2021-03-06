package tests.viewmodeltests.areasmodelstests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import viewmodel.MockTaskManager;
import viewmodel.TaskManager;
import viewmodel.areasmodels.SimpleCenterViewModel;

public class CenterViewModelTests {

	private SimpleCenterViewModel centerViewModel;
	private TaskManager manager;

	@Before
	public void setUp() {
		manager = MockTaskManager.getMockInstance();
		centerViewModel = new SimpleCenterViewModel(manager);
		manager.setStartState();
	}

	@Test
	public void byDefaultSplitForTableAndSolutionContinuoslyLayout() {
		assertTrue(centerViewModel
				.isSplitForTableAndSolutionContinuoslyLayout());
	}

	@Test
	public void byDefaultSplitForTableAndSolutionOneTouchExpandable() {
		assertTrue(centerViewModel
				.isSplitForTableAndSolutionOneTouchExpandable());
	}

	@Test
	public void byDefaultSplitForTableAndSolutionDividerSizeEqualsTo0() {
		int expectDividerSize = 0;

		int actualDividerSize = centerViewModel
				.getSplitForTableAndSolutionDivivderSize();

		assertEquals(expectDividerSize, actualDividerSize);
	}

	@Test
	public void byDefaultCenterSplitResizeWeightEqualsTo0() {
		double expectResizeSize = 0;

		double actualResizeSize = centerViewModel.getResizeWeight();

		assertEquals(expectResizeSize, actualResizeSize, 1e-10);
	}

	@Test
	public void byDefaultPanelForTableIsNotVisible() {
		assertFalse(centerViewModel.isPanelForTableVisible());
	}

	@Test
	public void whenTaskCreatedPanelForTableIsVisible() {
		createTask();

		assertTrue(centerViewModel.isPanelForTableVisible());
	}

	@Test
	public void byDefaultPanelForSolutionIsNotVisible() {
		assertFalse(centerViewModel.isPanelForSolutionVisible());
	}

	@Test
	public void whenTaskSolvedPanelForSolutionIsVisible() {
		createTask();
		manager.solveTask();

		assertTrue(centerViewModel.isPanelForTableVisible());
	}

	@Test
	public void whenTaskCreatedSplitForTableAndSolutionDividerSizeEqualsTo10() {
		int expectDividerSize = 5;

		createTask();
		manager.solveTask();
		int actualDividerSize = centerViewModel
				.getSplitForTableAndSolutionDivivderSize();

		assertEquals(expectDividerSize, actualDividerSize);
	}

	@Test
	public void whenTaskCreatedCenterSplitDividerSizeNotEqualsTo0() {
		double expectResizeWeight = 0.7;

		createTask();
		manager.solveTask();
		double actualResizeWeight = centerViewModel.getResizeWeight();

		assertEquals(expectResizeWeight, actualResizeWeight, 1e-10);
	}

	@Test
	public void whenMultiCriteriaTaskCreatedSvertksVisible() {
		createTask();
		manager.solveTask();

		assertTrue(centerViewModel.isSvertkaVisible());
	}

	@Test
	public void whenOneCriteriaTaskCreatedSvertksNotVisible() {
		createTask();
		manager.setTaskData("4", "2", "1");
		manager.createTask();

		assertFalse(centerViewModel.isSvertkaVisible());
	}

	private void createTask() {
		manager.setTaskData("4", "2", "3");
		manager.createTask();
	}

}
