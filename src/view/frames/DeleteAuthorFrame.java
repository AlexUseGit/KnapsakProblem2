package view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import viewmodel.TaskManager;

//I'm not going to serialize this class
@SuppressWarnings("serial")
public class DeleteAuthorFrame extends AbstractDecoratedInternalFrame{

	private JComboBox<String> authorsNames;

	public DeleteAuthorFrame(Desktop desktop, TaskManager manager) {
		super("Delete author", desktop, 300, 150, 250, 250, manager);
	}

	@Override
	protected void fullPanel() {
		authorsNames = new JComboBox<>(manager.getAuthorsNames());
		panel.add(new JLabel("choice author"), "wrap");
		panel.add(authorsNames);
		
		JButton deleteAuthor = new JButton("delete");
		if(authorsNames.getItemCount() == 0){
			deleteAuthor.setEnabled(false);
		}
		deleteAuthor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manager.deleteAuthor((String) authorsNames.getSelectedItem());
					authorsNames.setModel(new DefaultComboBoxModel<>(manager.getAuthorsNames()));
				} 
				catch(MySQLIntegrityConstraintViolationException exc){
					JOptionPane.showMessageDialog(null,
							"You can't delete author which already creat tasks");
				}
				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
							"Problems with database connection");
				}
			}
		});
		panel.add(deleteAuthor);
	}

}
