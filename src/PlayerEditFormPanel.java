/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class PlayerEditFormPanel extends JPanel {
	
	private JLabel nameLabel;
	private JLabel numLabel;
	private JLabel ageLabel;
	private JLabel heightLabel;
	private JLabel weightLabel;
	private JLabel footLabel;
	public JTextField nameField;
	public JTextField numField;
	public JTextField ageField;
	public JTextField heightField;
	public JTextField weightField;
	public JComboBox footList;
	
	public PlayerObject poFormPanel;
	private PlayerEditFormListener formListener;
	private TacticsBoardSystem tbs;
	public int i = -1;	//Denote the player if user clicks edit button in PlayerList;
						//-1 is default value which means user clicks add button.
	
	public PlayerEditFormPanel(PlayerObject poPassed) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		tbs = new TacticsBoardSystem();
		
		String[] footStrings = {"Left Only", "Right Only", "Both"};
		
		nameLabel = new JLabel("Actor Name:");
		nameField = new JTextField(10);
		numLabel = new JLabel("Number:");
		numField = new JTextField(5);
		ageLabel = new JLabel("Age:");
		ageField = new JTextField(5);
		heightLabel = new JLabel("Height(cm):");
		heightField = new JTextField(5);
		weightLabel = new JLabel("Weight(kg):");
		weightField = new JTextField(5);
		footLabel = new JLabel("Foot:");
		footList = new JComboBox<>(footStrings);
		
		String levelString = "";
		
		if(poPassed.getName() == null) {
//			If poPassed.getName() == null, it means user clicks add button,else user clicks edit button
			poFormPanel = new PlayerObject();
		}
		else {
//			Else, let FormPanel get the information of player which is chosen by user
			i = tbs.poList.indexOf(poPassed);
			poFormPanel = poPassed;
			nameField.setText(poFormPanel.getName());
			ageField.setText(String.valueOf(poFormPanel.getAge()));
			numField.setText(String.valueOf(poFormPanel.getNumber()));
			heightField.setText(String.valueOf(poFormPanel.getHeight()));
			weightField.setText(String.valueOf(poFormPanel.getWeight()));
			footList.setSelectedItem(poFormPanel.getFoot());
		}
		
		Border innerBorder = BorderFactory.createTitledBorder("Actor List");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();		
		
//		First Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);
		
//		Second Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(numLabel, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(numField, gc);
		
//		Third Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(ageLabel, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageField, gc);
		
//		Fourth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(heightLabel, gc);
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(heightField, gc);
		
//		Fifth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(weightLabel, gc);
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(weightField, gc);
		
//		Sixth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(footLabel, gc);
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(footList, gc);
		
	}
	
	public void setFormListener(PlayerEditFormListener listener) {
		this.formListener = listener;
	}
	
}