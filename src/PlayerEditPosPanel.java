/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class PlayerEditPosPanel extends JPanel {
	
	private JLabel posLabel;
	private JLabel levelLabel;
	private JLabel allSidesLabel1;
	private JLabel allSidesLabel2;
	private JLabel allSidesLabel3;
	private JLabel allSidesLabel4;
	private JLabel gkLabel;
	public JComboBox gkList;
	public JCheckBox gkCheck;
	private JLabel swLabel;
	public JComboBox swList;
	public JCheckBox swCheck;
	private JLabel dLabel;
	private JLabel dlLabel;
	private JLabel dcLabel;
	private JLabel drLabel;
	public JComboBox dlList;
	public JComboBox dcList;
	public JComboBox drList;
	private JLabel wbLabel;
	private JLabel wblLabel;
	private JLabel wbrLabel;
	public JComboBox wblList;
	public JComboBox wbrList;
	private JLabel dmLabel;
	public JComboBox dmList;
	public JCheckBox dmCheck;
	private JLabel mLabel;
	public JLabel mlLabel;
	public JLabel mcLabel;
	public JLabel mrLabel;
	public JComboBox mlList;
	public JComboBox mcList;
	public JComboBox mrList;
	private JLabel amLabel;
	public JLabel amlLabel;
	public JLabel amcLabel;
	public JLabel amrLabel;
	public JComboBox amlList;
	public JComboBox amcList;
	public JComboBox amrList;
	private JLabel stLabel;
	public JComboBox stList;
	public JCheckBox stCheck;
	
	public PlayerObject poFormPanel;
	private PlayerEditFormListener formListener;
	private TacticsBoardSystem tbs;
	public int i = -1;	//Denote the player if user clicks edit button in PlayerList;
						//-1 is default value which means user clicks add button.
	
	public PlayerEditPosPanel(PlayerObject poPassed) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		tbs = new TacticsBoardSystem();
		
		String[] levelStrings = {"Ineffectual", "Unconvincing", "Competent"};
		
		posLabel = new JLabel("Position");
		levelLabel = new JLabel("Level and Side");
		allSidesLabel1 = new JLabel("All Sides:");
		allSidesLabel2 = new JLabel("All Sides:");
		allSidesLabel3 = new JLabel("All Sides:");
		allSidesLabel4 = new JLabel("All Sides:");
		allSidesLabel1.setForeground(Color.blue);
		allSidesLabel2.setForeground(Color.blue);
		allSidesLabel3.setForeground(Color.blue);
		allSidesLabel4.setForeground(Color.blue);
		gkLabel = new JLabel("Goal Keeper(GK):");		
		gkList = new JComboBox<>(levelStrings);
		swLabel = new JLabel("Sweeper(SW):");
		swList = new JComboBox<>(levelStrings);
		dLabel = new JLabel("Defender(D):");
		dlLabel = new JLabel("Left Side:");
		dcLabel = new JLabel("Center:");
		drLabel = new JLabel("Right Side:");
		dlLabel.setForeground(Color.blue);
		dcLabel.setForeground(Color.blue);
		drLabel.setForeground(Color.blue);
		dlList = new JComboBox<>(levelStrings);
		dcList = new JComboBox<>(levelStrings);
		drList = new JComboBox<>(levelStrings);
		wbLabel = new JLabel("Wing Back(WB):");
		wblLabel = new JLabel("Left Side:");
		wbrLabel = new JLabel("Right Side:");
		wblLabel.setForeground(Color.blue);
		wbrLabel.setForeground(Color.blue);
		wblList = new JComboBox<>(levelStrings);
		wbrList = new JComboBox<>(levelStrings);
		dmLabel = new JLabel("Defensive Midfielder(DM):");
		dmList = new JComboBox<>(levelStrings);
		mLabel = new JLabel("Midfielder(M):");
		mlLabel = new JLabel("Left Side:");
		mcLabel = new JLabel("Center:");
		mrLabel = new JLabel("Right Side:");
		mlLabel.setForeground(Color.blue);
		mcLabel.setForeground(Color.blue);
		mrLabel.setForeground(Color.blue);
		mlList = new JComboBox<>(levelStrings);
		mcList = new JComboBox<>(levelStrings);
		mrList = new JComboBox<>(levelStrings);
		amLabel = new JLabel("Attacking Midfielder(AM):");
		amlLabel = new JLabel("Left Side:");
		amcLabel = new JLabel("Center:");
		amrLabel = new JLabel("Right Side:");
		amlLabel.setForeground(Color.blue);
		amcLabel.setForeground(Color.blue);
		amrLabel.setForeground(Color.blue);
		amlList = new JComboBox<>(levelStrings);
		amcList = new JComboBox<>(levelStrings);
		amrList = new JComboBox<>(levelStrings);
		stLabel = new JLabel("Striker(ST):");
		stList = new JComboBox<>(levelStrings);
		
		if(poPassed.getName() == null) {
//			If poPassed.getName() == null, it means user clicks add button,else user clicks edit button
			poFormPanel = new PlayerObject();
		}
		else {
//			Else, let FormPanel get the information of player which is chosen by user
			i = tbs.poList.indexOf(poPassed);
			poFormPanel = poPassed;
//			System.out.print(poFormPanel.getD());
//			System.out.print(poFormPanel.getM());
			getPosLevelString("gk", poFormPanel.getGk());
			getPosLevelString("sw", poFormPanel.getSw());
			getPosLevelString("d", poFormPanel.getD());
			getPosLevelString("wb", poFormPanel.getWb());
			getPosLevelString("dm", poFormPanel.getDm());
			getPosLevelString("m", poFormPanel.getM());
			getPosLevelString("am", poFormPanel.getAm());
			getPosLevelString("st", poFormPanel.getSt());
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
		add(posLabel, gc);
		gc.gridx = 2;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(levelLabel, gc);
		
//		Second Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(gkLabel, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(allSidesLabel1, gc);
		gc.gridx = 2;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(gkList, gc);
		
//		Third Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(swLabel, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(allSidesLabel2, gc);
		gc.gridx = 2;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(swList, gc);
		
//		Fourth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(dLabel, gc);
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dlLabel, gc);
		gc.gridx = 2;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dlList, gc);
		gc.gridx = 3;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dcLabel, gc);
		gc.gridx = 4;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dcList, gc);
		gc.gridx = 5;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(drLabel, gc);
		gc.gridx = 6;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(drList, gc);
		
//		Fifth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(wbLabel, gc);
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(wblLabel, gc);
		gc.gridx = 2;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(wblList, gc);
		gc.gridx = 3;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(wbrLabel, gc);
		gc.gridx = 4;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(wbrList, gc);
		
//		Sixth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(dmLabel, gc);
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(allSidesLabel3, gc);
		gc.gridx = 2;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dmList, gc);
		
//		Seventh Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(mLabel, gc);
		gc.gridx = 1;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(mlLabel, gc);
		gc.gridx = 2;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(mlList, gc);
		gc.gridx = 3;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(mcLabel, gc);
		gc.gridx = 4;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(mcList, gc);
		gc.gridx = 5;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(mrLabel, gc);
		gc.gridx = 6;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(mrList, gc);
		
//		Eighth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(amLabel, gc);
		gc.gridx = 1;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(amlLabel, gc);
		gc.gridx = 2;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(amlList, gc);
		gc.gridx = 3;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(amcLabel, gc);
		gc.gridx = 4;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(amcList, gc);
		gc.gridx = 5;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(amrLabel, gc);
		gc.gridx = 6;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(amrList, gc);
		
//		Ninth Row
		gc.weightx = 1;
		gc.weighty = 0.8;
		gc.gridx = 0;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(stLabel, gc);
		gc.gridx = 1;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(allSidesLabel4, gc);
		gc.gridx = 2;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(stList, gc);
	}
	
	public void setFormListener(PlayerEditFormListener listener) {
		this.formListener = listener;
	}

	private String getPosLevelString(String posString, int levelInt) {
		String levelString = "";
		switch(posString) {
			case "gk":
				levelString = getSideString(0, levelInt);
				gkList.setSelectedItem(levelString);
				break;
			case "sw":
				levelString = getSideString(0, levelInt);
				swList.setSelectedItem(levelString);
				break;
			case "d":
				levelString = getSideString(1, levelInt);
				dlList.setSelectedItem(levelString);
				levelString = getSideString(2, levelInt);
				dcList.setSelectedItem(levelString);
				levelString = getSideString(3, levelInt);
				drList.setSelectedItem(levelString);
				break;
			case "wb":
				levelString = getSideString(1, levelInt);
				wblList.setSelectedItem(levelString);
				levelString = getSideString(3, levelInt);
				wbrList.setSelectedItem(levelString);
				break;
			case "dm":
				levelString = getSideString(0, levelInt);
				dmList.setSelectedItem(levelString);
				break;
			case "m":
				levelString = getSideString(1, levelInt);
				mlList.setSelectedItem(levelString);
				levelString = getSideString(2, levelInt);
				mcList.setSelectedItem(levelString);
				levelString = getSideString(3, levelInt);
				mrList.setSelectedItem(levelString);
				break;
			case "am":
				levelString = getSideString(1, levelInt);
				amlList.setSelectedItem(levelString);
				levelString = getSideString(2, levelInt);
				amcList.setSelectedItem(levelString);
				levelString = getSideString(3, levelInt);
				amrList.setSelectedItem(levelString);
				break;
			case "st":
				levelString = getSideString(0, levelInt);
				stList.setSelectedItem(levelString);
				break;
		}
		return levelString;
	}
	
	private String getSideString(int side, int sideNum) {
		String sideString = "";
		if(side == 0) {
			if(sideNum == 1)
				sideString = "Ineffectual";
			else if(sideNum == 2)
				sideString = "Unconvincing";
			else
				sideString = "Competent";
		}
		else if(side == 1) {
			sideNum = sideNum/100;
			if(sideNum == 1)
				sideString = "Ineffectual";
			else if(sideNum == 2)
				sideString = "Unconvincing";
			else
				sideString = "Competent";
		}
		else if(side == 2) {
			sideNum = sideNum/10%10;
			if(sideNum == 1)
				sideString = "Ineffectual";
			else if(sideNum == 2)
				sideString = "Unconvincing";
			else
				sideString = "Competent";
		}
		else if(side == 3) {
			sideNum = sideNum%10;
			if(sideNum == 1)
				sideString = "Ineffectual";
			else if(sideNum == 2)
				sideString = "Unconvincing";
			else
				sideString = "Competent";
		}
		return sideString;
	}
}
