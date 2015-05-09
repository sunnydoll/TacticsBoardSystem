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

public class TeamInstructionsPanel extends JPanel {

	public InsFormationPanel formPanel;
	public SpecificInstructionsPanel instPanel;
	public TeamMemoPanel memoPanel;
	public PlayerObject po;
	private TacticsBoardSystem tbs;
	
	public TeamInstructionsPanel() {
		
		setLayout(new BorderLayout());
		
		instPanel = new SpecificInstructionsPanel();
		memoPanel = new TeamMemoPanel();
		formPanel = new InsFormationPanel();
		tbs = new TacticsBoardSystem();
		
		add(formPanel, BorderLayout.EAST);
		add(instPanel, BorderLayout.WEST);		
		add(memoPanel, BorderLayout.CENTER);		
		
//		setSize(800, 500);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
		setVisible(true);
	}
}