/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OverviewPanel extends JPanel {

	public OverviewFormationPanel formPanel;
	public OverviewListPanel listPanel;
	public OverviewSubstitutesPanel subPanel;
	public PlayerObject po;
	private TacticsBoardSystem tbs;
	
	public OverviewPanel() {
		//super("Actor List");		
		
		setLayout(new BorderLayout());
		
		listPanel = new OverviewListPanel();
		subPanel = new OverviewSubstitutesPanel(this);
		formPanel = new OverviewFormationPanel(subPanel);
		tbs = new TacticsBoardSystem();
//		subPanel.subList = tbs.subList;
//		subPanel.drawing();
		
		add(formPanel, BorderLayout.CENTER);
		add(listPanel, BorderLayout.WEST);		
		add(subPanel, BorderLayout.EAST);
		
		listPanel.setFormListener(new OverviewListListener() {
			public void formEventOccured(OverviewListEvent e) {
//				Get the player object which is chosen by user, and refresh Panel
				po = e.getPo();
				formPanel.po = po; 
				formPanel.drawing();
			}
		});
		
//		setSize(800, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
		setVisible(true);
	}
}