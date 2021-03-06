package view.areas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import view.components.ToolBarButton;
import view.frames.AbstractInteractiveInternalFrame;
import view.frames.Desktop;
import view.frames.GenerationAreaView;
import view.frames.NewTaskCreatingView;
import view.frames.ReadView;
import view.frames.SaveView;
import viewmodel.TaskManager;
import viewmodel.areasmodels.ToolBarViewModel;

//I'm not going to serialize that class
@SuppressWarnings("serial")
class ToolBarView extends JToolBar implements BindableArea {

	private JButton save = new ToolBarButton("save", "save task into database");
	private JButton read = new ToolBarButton("read", "read task from database");
	private JButton newTask = new ToolBarButton("new", "create new task");
	private JButton solve = new ToolBarButton("solution", "solve task");
	private JButton gen = new ToolBarButton("generation",
			"generate data for task");
	private JButton econom = new ToolBarButton("economics",
			"show econom intepretation of task");
	private Desktop desktop;
	private TaskManager manager;

	private ToolBarViewModel toolBarViewModel;

	public ToolBarView(Desktop desktop, TaskManager manager) {
		this.desktop = desktop;
		this.manager = manager;
		toolBarViewModel = new ToolBarViewModel(manager);
		setBackground(new Color(205, 190, 112));
		setFloatable(false);
		addSaveTaskButton();
		
		add(read);
		read.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.setLayout(null);
				ReadView iframe = new ReadView(desktop, manager);
				desktop.addIFrame(iframe);
			}
		});
		
		addSeparator();
		addNewTaskButton();
		addSolveButton();
		addGenButton();
		add(econom);
	}

	private void addSaveTaskButton() {
		add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.setLayout(null);
				SaveView iframe = new SaveView(desktop, manager);
				desktop.addIFrame(iframe);
			}
		});
	}

	private void addGenButton() {
		add(gen);
		gen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				desktop.setLayout(null);
				AbstractInteractiveInternalFrame iframe = new GenerationAreaView(
						desktop, manager);
				desktop.addIFrame(iframe);
			}
		});
	}

	private void addSolveButton() {
		add(solve);
		solve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manager.execute();
			}
		});
	}

	private void addNewTaskButton() {
		add(newTask);
		newTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				desktop.setLayout(null);
				AbstractInteractiveInternalFrame iframe = new NewTaskCreatingView(
						desktop, manager);
				desktop.addIFrame(iframe);
			}
		});
	}

	@Override
	public void bind() {
		save.setEnabled(toolBarViewModel.isSaveEnable());
		solve.setEnabled(toolBarViewModel.isSolutionEnable());
		gen.setEnabled(toolBarViewModel.isGenButtonEnabled());
		econom.setEnabled(toolBarViewModel.isEconomButtonEnabled());
	}

}
