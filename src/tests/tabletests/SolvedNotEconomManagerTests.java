package tests.tabletests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import view.components.tablemanagers.TableManager;
import viewmodel.MockTaskManager;
import viewmodel.TaskManager;
import viewmodel.componentsmodels.tablemodelmanagers.ITableManager;

public class SolvedNotEconomManagerTests {

	private final int VAR_COUNT = 4;
	private final int LIMITATION_COUNT = 3;
	private final int CRITERION_COUNT = 2;
	private ITableManager tableManager;
	private TaskManager manager;

	@Before
	public void setUp() {
		manager = MockTaskManager.getMockInstance();
		tableManager = new TableManager(manager);
		manager.setStartState();
	}

	@Test
	public void getColumnCount() {
		int expectColumnCount = VAR_COUNT + 2 + 1;

		createNotEconomSolvedTask();
		int actualColumnCount = tableManager.getColumnCount();

		assertEquals(expectColumnCount, actualColumnCount);
	}

	@Test
	public void getRowCount() {
		int expectRowCount = CRITERION_COUNT + LIMITATION_COUNT + 1;

		createNotEconomSolvedTask();
		int actualRowCount = tableManager.getRowCount();

		assertEquals(expectRowCount, actualRowCount);
	}

	@Test
	public void getFirstRowHeight() {
		int expectFirstRowHeight = 20;

		createNotEconomSolvedTask();
		int actualFirstRowHeight = tableManager.getRowHeight(0);

		assertEquals(expectFirstRowHeight, actualFirstRowHeight);
	}

	@Test
	public void getLastRowHeight() {
		int expectLastRowHeight = 40;

		createNotEconomSolvedTask();
		int actualLastRowHeight = tableManager.getRowHeight(tableManager
				.getRowCount() - 1);

		assertEquals(expectLastRowHeight, actualLastRowHeight);
	}

	@Test
	public void lastRowFirstColumnEditable() {
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = 0;

		createNotEconomSolvedTask();

		assertTrue(tableManager.isCellEditable(row, col));
	}

	@Test
	public void lastRowlastVarColumnEditable() {
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = VAR_COUNT - 1;

		createNotEconomSolvedTask();

		assertTrue(tableManager.isCellEditable(row, col));
	}

	@Test
	public void lastRowlastColumnNotEditable() {
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = VAR_COUNT + 1;

		createNotEconomSolvedTask();

		assertFalse(tableManager.isCellEditable(row, col));
	}

	@Test
	public void lastRowFirstColumnGetValue() {
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = 0;

		createNotEconomSolvedTask();
		Object expectValue = manager.getSolutionVariable(col) ? 1 : 0;
		Object actualValue = tableManager.getValueAt(row, col);

		assertEquals(expectValue, actualValue);
	}

	@Test
	public void lastRowLastVarColumnGetValue() {
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = VAR_COUNT - 1;

		createNotEconomSolvedTask();
		Object expectValue = manager.getSolutionVariable(col) ? 1 : 0;
		Object actualValue = tableManager.getValueAt(row, col);

		assertEquals(expectValue, actualValue);
	}

	@Test
	public void lastRowLastTypeColumnGetValue() {
		Object expectValue = null;
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = VAR_COUNT;

		createNotEconomSolvedTask();
		Object actualValue = tableManager.getValueAt(row, col);

		assertEquals(expectValue, actualValue);
	}

	@Test
	public void lastRowLastColumnGetValue() {
		Object expectValue = null;
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = VAR_COUNT + 1;

		createNotEconomSolvedTask();
		Object actualValue = tableManager.getValueAt(row, col);

		assertEquals(expectValue, actualValue);
	}

	@Test
	public void setSolutionVariable() {
		Object expectValue = 1;
		int row = LIMITATION_COUNT + CRITERION_COUNT;
		int col = 0;

		createNotEconomSolvedTask();
		tableManager.setValue(1, row, col);
		Object actualValue = tableManager.getValueAt(row, col);

		assertEquals(expectValue, actualValue);
	}

	@Test
	public void solutionRowName() {
		String expectName = "Solution";

		createNotEconomSolvedTask();
		String actualName = tableManager.getRowName(CRITERION_COUNT
				+ LIMITATION_COUNT);

		assertEquals(expectName, actualName);
	}
	
	@Test
	public void solutionColumnName() {
		String expectName = "Sum";

		createNotEconomSolvedTask();
		String actualName = tableManager.getColumnName(VAR_COUNT + 2);

		assertEquals(expectName, actualName);
	}
	
	@Test
	public void solutionRowColorUnFocus() {
		Color expectColor = Color.YELLOW;

		createNotEconomSolvedTask();
		Color actualColor = tableManager.getUnFocusRow(CRITERION_COUNT+LIMITATION_COUNT);

		assertEquals(expectColor, actualColor);
	}
	
	@Test
	public void firstRowFocusColor() {
		Color expectColor = new Color(255, 215, 0);

		createNotEconomSolvedTask();
		Color actualColor = tableManager.getFocusRow(LIMITATION_COUNT+CRITERION_COUNT);

		assertEquals(expectColor, actualColor);
	}

	private void createNotEconomSolvedTask() {
		manager.setTaskData("4", "3", "2");
		manager.createTask();
		manager.solveTask();
	}

}
