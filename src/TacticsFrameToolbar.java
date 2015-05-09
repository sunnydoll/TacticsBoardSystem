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


public class TacticsFrameToolbar extends JPanel implements ActionListener {
	
	private JButton btnSave;
	private JButton btnLoad;
	
	private OverviewListPanel formPanel;
	
	private OverviewListButtonListener buttonListener;	//Listen to the buttons in ToolbarPanel of ActionList 
	
	public TacticsFrameToolbar() {
		btnSave = new JButton("Save Tactics");
		btnLoad = new JButton("Load Tactics");
		
		btnSave.addActionListener(this);
		btnLoad.addActionListener(this);
		
		setLayout(new FlowLayout());
		
		add(btnSave);
		add(btnLoad);
		
		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}
	
	public void setButtonListener(OverviewListButtonListener listener) {
		this.buttonListener = listener;
	}

//	Button click in ToolbarPanel
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if(buttonListener != null) {
			try {
				buttonListener.textEmitted(clicked.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
