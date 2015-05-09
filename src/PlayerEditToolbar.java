/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class PlayerEditToolbar extends JPanel implements ActionListener {
	
	private JButton btnOK;
	private JButton btnCancel;	
	
	private PlayerEditFormListener formListener;
	private PlayerEditButtonListener buttonListener;	//Listen to the buttons in ToolbarPanel of ActionEdit 
	
	public PlayerEditToolbar() {
		btnOK = new JButton("Save");
		btnCancel = new JButton("Cancel");
		
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
		
		setLayout(new FlowLayout());
		
		add(btnOK);
		add(btnCancel);
		
		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}
	
	public void setStringListener(PlayerEditButtonListener listener) {
		this.buttonListener = listener;
	}

//	Button click in ToolbarPanel
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();		
		if(buttonListener != null) {
			buttonListener.textEmitted(clicked.getText());
		}
	}
	
	public void setFormListener(PlayerEditFormListener listener) {
		this.formListener = listener;
	}
}

