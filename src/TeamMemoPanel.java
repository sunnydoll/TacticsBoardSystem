/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;



public class TeamMemoPanel extends JPanel {
	public JTextArea memoArea;
	private JScrollPane sp;
	private JButton btnReset;
	
	public TeamMemoPanel() {
		
		Border innerBorder = BorderFactory.createTitledBorder("Formation Memo");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));		
		
		ActionListener resetListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memoArea.setText("");				
			}
		};
		
		memoArea = new JTextArea(27,27);
//		memoArea.setPreferredSize(new Dimension(300,400));
//		memoArea.setSize(300, 400);
		memoArea.setBorder(BorderFactory.createEtchedBorder());
		memoArea.setLineWrap(true);
		memoArea.setWrapStyleWord(true);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(resetListener);
		
		sp = new JScrollPane(memoArea);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		sp.setPreferredSize(new Dimension(270, 500));
		
		add(sp);
		add(btnReset);
	}
	
}
