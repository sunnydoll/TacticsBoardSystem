/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class PlayerEdit extends JFrame {

	private PlayerEditPosPanel posPanel;
	private PlayerEditToolbar toolbar;
	private PlayerEditFormPanel formPanel;
	private PlayerObject aoFormPanel;
	private TacticsBoardSystem tbs;
	
	public PlayerEdit(PlayerObject poPassed, final OverviewPanel oPanel) {
		super("Edit Player");
		
		setLayout(new BorderLayout());
		
		posPanel = new PlayerEditPosPanel(poPassed);
		toolbar = new PlayerEditToolbar();
		formPanel = new PlayerEditFormPanel(poPassed);
		
		add(posPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.SOUTH);
		add(formPanel, BorderLayout.WEST);
		
		toolbar.setStringListener(new PlayerEditButtonListener() {
			public void textEmitted(String text) {
//				Listen to the buttons of the ToolbarPanel
				switch(text) {
					case "Cancel":
						aoFormPanel = new PlayerObject();
						new TacticsFrame().setVisible(true);
		                PlayerEdit.this.setVisible(false);
		                break;				
					case "Save":
						String name = formPanel.nameField.getText();
						int height = 0;
						int weight = 0;
						int number = 0;
						int age = 0;
						int foot = 0;
						String footString = formPanel.footList.getSelectedItem().toString();
						int gk = 0;
						int sw = 0;
						int d = 0;
						int wb = 0;
						int dm = 0;
						int m = 0;
						int am = 0;
						int st = 0;
						int oldNumber = 0;
						if(formPanel.nameField.getText() == null || formPanel.nameField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please enter name for the your player, thanks! ");
							return;
						}
						try {
							number = Integer.parseInt(formPanel.numField.getText());
							age = Integer.parseInt(formPanel.ageField.getText());
						} 
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Please enter integer in both number and age textfield, Thanks! ");
							return;
						}
						try {
							height = Integer.parseInt(formPanel.heightField.getText());
							weight = Integer.parseInt(formPanel.weightField.getText());
						} 
						catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Please enter integer in both width and height textfield, Thanks! ");
							return;
						}						
						switch(footString) {
							case "Left Only":
								foot = 1;
								break;
							case "Right Only":
								foot = 2;
								break;
							case "Both":
								foot = 3;
								break;
						}
						gk = getPosLevelNumber("gk");
						sw = getPosLevelNumber("sw");
						d = getPosLevelNumber("d");
						wb = getPosLevelNumber("wb");
						dm = getPosLevelNumber("dm");
						m = getPosLevelNumber("m");
						am = getPosLevelNumber("am");
						st = getPosLevelNumber("st");						
						
						tbs = new TacticsBoardSystem();
						if(formPanel.i == -1) {
//							if i == -1, it means user clicks add button, then just add new player object to list
							if(tbs.poList.size() != 0) {
								for(int i = 0; i < tbs.poList.size(); i++) {		
									if(((PlayerObject) tbs.poList.get(i)).getNumber() == number) {
										JOptionPane.showMessageDialog(null, ((PlayerObject) tbs.poList.get(i)).getName() + " has already taken the same number.");
										return;
									}									
								}
							}
							aoFormPanel = new PlayerObject(name, number, age, height, weight, foot, gk, sw, d, wb, dm, m, am, st);
							tbs.poList.add(aoFormPanel);
						}
						else {
//							else, user clicks edit button, then modify the player object chosen by user in the list
							oldNumber = ((PlayerObject) tbs.poList.get(formPanel.i)).getNumber();
							if(tbs.poList.size() != 0) {
								for(int i = 0; i < tbs.poList.size(); i++) {		
									if(((PlayerObject) tbs.poList.get(i)).getNumber() == number && i != formPanel.i) {
										JOptionPane.showMessageDialog(null, ((PlayerObject) tbs.poList.get(i)).getName() + " has already taken the same number.");
										return;
									}									
								}
							}
							for(int i = 0; i < 7; i++) {
								if(tbs.subList[i] == oldNumber) {
									tbs.subList[i] = number;
									tbs.subNameList[i] = name;
									oPanel.subPanel.repaint();
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.stList[i] == oldNumber) {
									tbs.stList[i] = number;
									tbs.stNameList[i] = name;
									tbs.stPosList[i] = st;
									oPanel.formPanel.repaint();
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.amList[i] == oldNumber) {
									tbs.amList[i] = number;
									tbs.amNameList[i] = name;
									if(i == 0) 
										tbs.amPosList[i] = am/100;
									else if(i == 4)
										tbs.amPosList[i] = am%10;
									else
										tbs.amPosList[i] = am/10%10;
									oPanel.formPanel.repaint();
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.mList[i] == oldNumber) {
									tbs.mList[i] = number;
									tbs.mNameList[i] = name;
									if(i == 0) 
										tbs.mPosList[i] = m/100;
									else if(i == 4)
										tbs.mPosList[i] = m%10;
									else
										tbs.mPosList[i] = m/10%10;
									oPanel.formPanel.repaint();
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.dmList[i] == oldNumber) {
									tbs.dmList[i] = number;
									tbs.dmNameList[i] = name;
									if(i == 0) 
										tbs.dmPosList[i] = wb/100;
									else if(i == 4)
										tbs.dmPosList[i] = wb/10%10;
									else
										tbs.dmPosList[i] = dm;
									oPanel.formPanel.repaint();
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.dList[i] == oldNumber) {
									tbs.dList[i] = number;
									tbs.dNameList[i] = name;
									if(i == 0) 
										tbs.dPosList[i] = d/100;
									else if(i == 4)
										tbs.dPosList[i] = d%10;
									else
										tbs.dPosList[i] = d/10%10;
									oPanel.formPanel.repaint();
								}
							}
							for(int i = 0; i < 3; i++) {
								if(tbs.swList[i] == oldNumber) {
									tbs.swList[i] = number;
									tbs.swNameList[i] = name;
									tbs.swPosList[i] = sw;
									oPanel.formPanel.repaint();
								}
							}
							if(tbs.gkList == oldNumber) {
								tbs.gkList = number;
								tbs.gkNameList = name;
								tbs.gkPosList = gk;
							}
							aoFormPanel = new PlayerObject(name, number, age, height, weight, foot, gk, sw, d, wb, dm, m, am, st);
							tbs.poList.set(formPanel.i, aoFormPanel);
						}
						new TacticsFrame().setVisible(true);
		                PlayerEdit.this.setVisible(false);
		                break;
				}
			}
			
		});
		
//		formPanel.setFormListener(new PlayerEditFormListener() {
//			public void formEventOccured(PlayerEditFormEvent e) {
//				aoFormPanel = e.getPo();
//				drawPanel.ao = aoFormPanel; 
//				drawPanel.drawing();
//			}
//		});
		
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private int getPosLevelNumber(String posString) {
		String levelString = "";
		int lSide = 100;
		int cSide = 10;
		int rSide = 1;
		int posLevelNumber = 1;
		switch(posString) {
			case "gk":
				levelString = posPanel.gkList.getSelectedItem().toString();
				posLevelNumber = getSideNumber(0, levelString);
				break;
			case "sw":
				levelString = posPanel.swList.getSelectedItem().toString();
				posLevelNumber = getSideNumber(0, levelString);
				break;
			case "d":
				levelString = posPanel.dlList.getSelectedItem().toString();
				lSide = getSideNumber(1, levelString);
				levelString = posPanel.dcList.getSelectedItem().toString();
				cSide = getSideNumber(2, levelString);
				levelString = posPanel.drList.getSelectedItem().toString();
				rSide = getSideNumber(3, levelString);
				posLevelNumber = lSide + cSide + rSide;
				break;
			case "wb":
				levelString = posPanel.wblList.getSelectedItem().toString();				
				lSide = getSideNumber(1, levelString);
				cSide = 10;
				levelString = posPanel.wbrList.getSelectedItem().toString();
				rSide = getSideNumber(3, levelString);
				posLevelNumber = lSide + cSide + rSide;
				break;
			case "dm":
				levelString = posPanel.dmList.getSelectedItem().toString();
				posLevelNumber = getSideNumber(0, levelString);
				break;
			case "m":
				levelString = posPanel.mlList.getSelectedItem().toString();
				lSide = getSideNumber(1, levelString);
				levelString = posPanel.mcList.getSelectedItem().toString();
				cSide = getSideNumber(2, levelString);
				levelString = posPanel.mrList.getSelectedItem().toString();
				rSide = getSideNumber(3, levelString);
				posLevelNumber = lSide + cSide + rSide;
				break;
			case "am":
				levelString = posPanel.amlList.getSelectedItem().toString();
				lSide = getSideNumber(1, levelString);
				levelString = posPanel.amcList.getSelectedItem().toString();
				cSide = getSideNumber(2, levelString);
				levelString = posPanel.amrList.getSelectedItem().toString();
				rSide = getSideNumber(3, levelString);
				posLevelNumber = lSide + cSide + rSide;
				break;
			case "st":
				levelString = posPanel.stList.getSelectedItem().toString();
				posLevelNumber = getSideNumber(0, levelString);
				break;
		}
		return posLevelNumber;
	}
	
	private int getSideNumber(int side, String level) {
		int sideNum = 0;
		if(side == 0) {
			if(level == "Ineffectual")
				sideNum = 1;
			else if(level == "Unconvincing")
				sideNum = 2;
			else
				sideNum = 3;
		}
		else if(side == 1) {
			if(level == "Ineffectual")
				sideNum = 100;
			else if(level == "Unconvincing")
				sideNum = 200;
			else
				sideNum = 300;
		}
		else if(side == 2) {
			if(level == "Ineffectual")
				sideNum = 10;
			else if(level == "Unconvincing")
				sideNum = 20;
			else
				sideNum = 30;
		}
		else if(side == 3) {
			if(level == "Ineffectual")
				sideNum = 1;
			else if(level == "Unconvincing")
				sideNum = 2;
			else
				sideNum = 3;
		}
		return sideNum;
	}
}