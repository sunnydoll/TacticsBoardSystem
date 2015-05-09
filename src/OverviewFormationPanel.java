/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.AttributedCharacterIterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;
import javax.swing.border.Border;


public class OverviewFormationPanel extends JPanel {
	
	public static PlayerObject po;
	private TacticsBoardSystem tbs;
//	private OverviewSubstitutesPanel subPanel;
	
	public JLabel stOne;
	public JLabel stTwo;
	public JLabel stThree;
	public JLabel stFour;
	public JLabel stFive;
	
	public JLabel amOne;
	public JLabel amTwo;
	public JLabel amThree;
	public JLabel amFour;
	public JLabel amFive;
	
	public JLabel mOne;
	public JLabel mTwo;
	public JLabel mThree;
	public JLabel mFour;
	public JLabel mFive;
	
	public JLabel dmOne;
	public JLabel dmTwo;
	public JLabel dmThree;
	public JLabel dmFour;
	public JLabel dmFive;
	
	public JLabel dOne;
	public JLabel dTwo;
	public JLabel dThree;
	public JLabel dFour;
	public JLabel dFive;
	
	public JLabel swOne;
	public JLabel swTwo;
	public JLabel swThree;
	
	public JLabel gkOne;
	
//	public int[] stList = {0, 0, 0, 0, 0};
//	public int[] amList = {0, 0, 0, 0, 0};
//	public int[] mList = {0, 0, 0, 0, 0};
//	public int[] dmList = {0, 0, 0, 0, 0};
//	public int[] dList = {0, 0, 0, 0, 0};
//	public int[] swList = {0, 0, 0};
//	public int gkList = 0;
	
	public OverviewFormationPanel(final OverviewSubstitutesPanel subPanel) {	
		
		tbs = new TacticsBoardSystem();
//		subPanel = new OverviewSubstitutesPanel();
		
		if(tbs.stNameList[0].length() == 1)
			stOne = new JLabel("Empty");
		else
			stOne = new JLabel(tbs.stNameList[0]);
		if(tbs.stNameList[1].length() == 1)
			stTwo = new JLabel("Empty");
		else
			stTwo = new JLabel(tbs.stNameList[1]);
		if(tbs.stNameList[2].length() == 1)
			stThree = new JLabel("Empty");
		else
			stThree = new JLabel(tbs.stNameList[2]);
		if(tbs.stNameList[3].length() == 1)
			stFour = new JLabel("Empty");
		else
			stFour = new JLabel(tbs.stNameList[3]);
		if(tbs.stNameList[4].length() == 1)
			stFive = new JLabel("Empty");
		else
			stFive = new JLabel(tbs.stNameList[4]);
		stOne.setForeground(Color.BLACK.brighter());
		stTwo.setForeground(Color.BLACK.brighter());
		stThree.setForeground(Color.BLACK.brighter());
		stFour.setForeground(Color.BLACK.brighter());
		stFive.setForeground(Color.BLACK.brighter());
		stOne.setTransferHandler( new TransferHandler("text") );
		stTwo.setTransferHandler( new TransferHandler("text") );
		stThree.setTransferHandler( new TransferHandler("text") );
		stFour.setTransferHandler( new TransferHandler("text") );
		stFive.setTransferHandler( new TransferHandler("text") );
		
		stOne.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
//				if(newValue[0].startsWith("Empty")) {
//					label.setText(evt.getOldValue().toString());
//					return;
//				}
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 0, "st")) {
								tbs.stList[0] = num;
								tbs.stNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[0] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.stList[0] = num;
								tbs.stNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[0] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		stTwo.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 1, "st")) {
								tbs.stList[1] = num;
								tbs.stNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[1] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.stList[1] = num;
								tbs.stNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[1] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		stThree.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 2, "st")) {
								tbs.stList[2] = num;
								tbs.stNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[2] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.stList[2] = num;
								tbs.stNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[2] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		stFour.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 3, "st")) {
								tbs.stList[3] = num;
								tbs.stNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[3] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.stList[3] = num;
								tbs.stNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[3] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		stFive.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 4, "st")) {
								tbs.stList[4] = num;
								tbs.stNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[4] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.stList[4] = num;
								tbs.stNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.stPosList[4] = ((PlayerObject) tbs.poList.get(i)).getSt();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		stOne.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		stTwo.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		stThree.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		stFour.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		stFive.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		
		if(tbs.amNameList[0].length() == 1)
			amOne = new JLabel("Empty");
		else
			amOne = new JLabel(tbs.amNameList[0]);
		if(tbs.amNameList[1].length() == 1)
			amTwo = new JLabel("Empty");
		else
			amTwo = new JLabel(tbs.amNameList[1]);
		if(tbs.amNameList[2].length() == 1)
			amThree = new JLabel("Empty");
		else
			amThree = new JLabel(tbs.amNameList[2]);
		if(tbs.amNameList[3].length() == 1)
			amFour = new JLabel("Empty");
		else
			amFour = new JLabel(tbs.amNameList[3]);
		if(tbs.amNameList[4].length() == 1)
			amFive = new JLabel("Empty");
		else
			amFive = new JLabel(tbs.amNameList[4]);
		amOne.setForeground(Color.BLACK.brighter());
		amTwo.setForeground(Color.BLACK.brighter());
		amThree.setForeground(Color.BLACK.brighter());
		amFour.setForeground(Color.BLACK.brighter());
		amFive.setForeground(Color.BLACK.brighter());
		amOne.setTransferHandler( new TransferHandler("text") );
		amTwo.setTransferHandler( new TransferHandler("text") );
		amThree.setTransferHandler( new TransferHandler("text") );
		amFour.setTransferHandler( new TransferHandler("text") );
		amFive.setTransferHandler( new TransferHandler("text") );
		amOne.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 0, "am")) {
								tbs.amList[0] = num;
								tbs.amNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[0] = p/100;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.amList[0] = num;
								tbs.amNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[0] = p/100;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		amTwo.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 1, "am")) {
								tbs.amList[1] = num;
								tbs.amNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[1] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.amList[1] = num;
								tbs.amNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[1] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		amThree.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 2, "am")) {
								tbs.amList[2] = num;
								tbs.amNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[2] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.amList[2] = num;
								tbs.amNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[2] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		amFour.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 3, "am")) {
								tbs.amList[3] = num;
								tbs.amNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[3] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.amList[3] = num;
								tbs.amNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[3] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		amFive.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 4, "am")) {
								tbs.amList[4] = num;
								tbs.amNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[4] = p%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.amList[4] = num;
								tbs.amNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getAm();
								tbs.amPosList[4] = p%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		amOne.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		amTwo.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		amThree.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		amFour.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		amFive.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		
		if(tbs.mNameList[0].length() == 1)
			mOne = new JLabel("Empty");
		else
			mOne = new JLabel(tbs.mNameList[0]);
		if(tbs.mNameList[1].length() == 1)
			mTwo = new JLabel("Empty");
		else
			mTwo = new JLabel(tbs.mNameList[1]);
		if(tbs.mNameList[2].length() == 1)
			mThree = new JLabel("Empty");
		else
			mThree = new JLabel(tbs.mNameList[2]);
		if(tbs.mNameList[3].length() == 1)
			mFour = new JLabel("Empty");
		else
			mFour = new JLabel(tbs.mNameList[3]);
		if(tbs.mNameList[4].length() == 1)
			mFive = new JLabel("Empty");
		else
			mFive = new JLabel(tbs.mNameList[4]);
		mOne.setForeground(Color.BLACK.brighter());
		mTwo.setForeground(Color.BLACK.brighter());
		mThree.setForeground(Color.BLACK.brighter());
		mFour.setForeground(Color.BLACK.brighter());
		mFive.setForeground(Color.BLACK.brighter());
		mOne.setTransferHandler( new TransferHandler("text") );
		mTwo.setTransferHandler( new TransferHandler("text") );
		mThree.setTransferHandler( new TransferHandler("text") );
		mFour.setTransferHandler( new TransferHandler("text") );
		mFive.setTransferHandler( new TransferHandler("text") );
		mOne.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 0, "m")) {
								tbs.mList[0] = num;
								tbs.mNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[0] = p/100;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.mList[0] = num;
								tbs.mNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[0] = p/100;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		mTwo.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 1, "m")) {
								tbs.mList[1] = num;
								tbs.mNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[1] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.mList[1] = num;
								tbs.mNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[1] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		mThree.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 2, "m")) {
								tbs.mList[2] = num;
								tbs.mNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[2] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.mList[2] = num;
								tbs.mNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[2] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		mFour.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 3, "m")) {
								tbs.mList[3] = num;
								tbs.mNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[3] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.mList[3] = num;
								tbs.mNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[3] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		mFive.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 4, "m")) {
								tbs.mList[4] = num;
								tbs.mNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[4] = p%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.mList[4] = num;
								tbs.mNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getM();
								tbs.mPosList[4] = p%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		mOne.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		mTwo.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		mThree.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		mFour.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		mFive.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		
		if(tbs.dmNameList[0].length() == 1)
			dmOne = new JLabel("Empty");
		else
			dmOne = new JLabel(tbs.dmNameList[0]);
		if(tbs.dmNameList[1].length() == 1)
			dmTwo = new JLabel("Empty");
		else
			dmTwo = new JLabel(tbs.dmNameList[1]);
		if(tbs.dmNameList[2].length() == 1)
			dmThree = new JLabel("Empty");
		else
			dmThree = new JLabel(tbs.dmNameList[2]);
		if(tbs.dmNameList[3].length() == 1)
			dmFour = new JLabel("Empty");
		else
			dmFour = new JLabel(tbs.dmNameList[3]);
		if(tbs.dmNameList[4].length() == 1)
			dmFive = new JLabel("Empty");
		else
			dmFive = new JLabel(tbs.dmNameList[4]);
		dmOne.setForeground(Color.BLACK.brighter());
		dmTwo.setForeground(Color.BLACK.brighter());
		dmThree.setForeground(Color.BLACK.brighter());
		dmFour.setForeground(Color.BLACK.brighter());
		dmFive.setForeground(Color.BLACK.brighter());
		dmOne.setTransferHandler( new TransferHandler("text") );
		dmTwo.setTransferHandler( new TransferHandler("text") );
		dmThree.setTransferHandler( new TransferHandler("text") );
		dmFour.setTransferHandler( new TransferHandler("text") );
		dmFive.setTransferHandler( new TransferHandler("text") );
		dmOne.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 0, "dm")) {
								tbs.dmList[0] = num;
								tbs.dmNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getWb();
								tbs.dmPosList[0] = p/100;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dmList[0] = num;
								tbs.dmNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getWb();
								tbs.dmPosList[0] = p/100;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dmTwo.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 1, "dm")) {
								tbs.dmList[1] = num;
								tbs.dmNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getDm();
								tbs.dmPosList[1] = p;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dmList[1] = num;
								tbs.dmNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getDm();
								tbs.dmPosList[1] = p;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dmThree.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 2, "dm")) {
								tbs.dmList[2] = num;
								tbs.dmNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getDm();
								tbs.dmPosList[2] = p;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dmList[2] = num;
								tbs.dmNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getDm();
								tbs.dmPosList[2] = p;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dmFour.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 3, "dm")) {
								tbs.dmList[3] = num;
								tbs.dmNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getDm();
								tbs.dmPosList[3] = p;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dmList[3] = num;
								tbs.dmNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getDm();
								tbs.dmPosList[3] = p;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dmFive.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 4, "dm")) {
								tbs.dmList[4] = num;
								tbs.dmNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getWb();
								tbs.dmPosList[4] = p%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dmList[4] = num;
								tbs.dmNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getWb();
								tbs.dmPosList[4] = p%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dmOne.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dmTwo.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dmThree.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dmFour.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dmFive.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		
		if(tbs.dNameList[0].length() == 1)
			dOne = new JLabel("Empty");
		else
			dOne = new JLabel(tbs.dNameList[0]);
		if(tbs.dNameList[1].length() == 1)
			dTwo = new JLabel("Empty");
		else
			dTwo = new JLabel(tbs.dNameList[1]);
		if(tbs.dNameList[2].length() == 1)
			dThree = new JLabel("Empty");
		else
			dThree = new JLabel(tbs.dNameList[2]);
		if(tbs.dNameList[3].length() == 1)
			dFour = new JLabel("Empty");
		else
			dFour = new JLabel(tbs.dNameList[3]);
		if(tbs.dNameList[4].length() == 1)
			dFive = new JLabel("Empty");
		else
			dFive = new JLabel(tbs.dNameList[4]);
		dOne.setForeground(Color.BLACK.brighter());
		dTwo.setForeground(Color.BLACK.brighter());
		dThree.setForeground(Color.BLACK.brighter());
		dFour.setForeground(Color.BLACK.brighter());
		dFive.setForeground(Color.BLACK.brighter());
		dOne.setTransferHandler( new TransferHandler("text") );
		dTwo.setTransferHandler( new TransferHandler("text") );
		dThree.setTransferHandler( new TransferHandler("text") );
		dFour.setTransferHandler( new TransferHandler("text") );
		dFive.setTransferHandler( new TransferHandler("text") );
		dOne.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 0, "d")) {
								tbs.dList[0] = num;
								tbs.dNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[0] = p/100;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dList[0] = num;
								tbs.dNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[0] = p/100;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dTwo.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 1, "d")) {
								tbs.dList[1] = num;
								tbs.dNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[1] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dList[1] = num;
								tbs.dNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[1] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dThree.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 2, "d")) {
								tbs.dList[2] = num;
								tbs.dNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[2] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dList[2] = num;
								tbs.dNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[2] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dFour.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 3, "d")) {
								tbs.dList[3] = num;
								tbs.dNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[3] = p/10%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dList[3] = num;
								tbs.dNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[3] = p/10%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dFive.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				int p = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Overview"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 4, "d")) {
								tbs.dList[4] = num;
								tbs.dNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[4] = p%10;
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.dList[4] = num;
								tbs.dNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
								p = ((PlayerObject) tbs.poList.get(i)).getD();
								tbs.dPosList[4] = p%10;
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		dOne.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dTwo.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dThree.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dFour.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		dFive.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		
		if(tbs.swNameList[0].length() == 1)
			swOne = new JLabel("Empty");
		else
			swOne = new JLabel(tbs.swNameList[0]);
		if(tbs.swNameList[1].length() == 1)
			swTwo = new JLabel("Empty");
		else
			swTwo = new JLabel(tbs.swNameList[1]);
		if(tbs.swNameList[2].length() == 1)
			swThree = new JLabel("Empty");
		else
			swThree = new JLabel(tbs.swNameList[2]);
		swOne.setForeground(Color.BLACK.brighter());
		swTwo.setForeground(Color.BLACK.brighter());
		swThree.setForeground(Color.BLACK.brighter());
		swOne.setTransferHandler( new TransferHandler("text") );
		swTwo.setTransferHandler( new TransferHandler("text") );
		swThree.setTransferHandler( new TransferHandler("text") );
		swOne.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 0, "sw")) {
								tbs.swList[0] = num;
								tbs.swNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.swPosList[0] = ((PlayerObject) tbs.poList.get(i)).getSw();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.swList[0] = num;
								tbs.swNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.swPosList[0] = ((PlayerObject) tbs.poList.get(i)).getSw();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		swTwo.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 1, "sw")) {
								tbs.swList[1] = num;
								tbs.swNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.swPosList[1] = ((PlayerObject) tbs.poList.get(i)).getSw();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.swList[1] = num;
								tbs.swNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.swPosList[1] = ((PlayerObject) tbs.poList.get(i)).getSw();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		swThree.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
							if(isInFormation(num, 2, "sw")) {
								tbs.swList[2] = num;
								tbs.swNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.swPosList[2] = ((PlayerObject) tbs.poList.get(i)).getSw();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.swList[2] = num;
								tbs.swNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.swPosList[2] = ((PlayerObject) tbs.poList.get(i)).getSw();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		swOne.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		swTwo.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		swThree.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		
		if(tbs.gkNameList.length() == 1)
			gkOne = new JLabel("Empty");
		else
			gkOne = new JLabel(tbs.gkNameList);
		
		gkOne.setForeground(Color.BLACK.brighter());
		gkOne.setTransferHandler( new TransferHandler("text") );
		gkOne.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				int sum = 0;
				sum = getSumPos();
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("Over"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 7; j++) {
								if(tbs.subList[j] == num) {
									tbs.subList[j] = 0;
									tbs.subNameList[j] = " ";
									if(j == 0)
										subPanel.firstLabel.setText("Empty");
									else if(j == 1)
										subPanel.secondLabel.setText("Empty");
									else if(j == 2)
										subPanel.thirdLabel.setText("Empty");
									else if(j == 3)
										subPanel.fourthLabel.setText("Empty");
									else if(j == 4)
										subPanel.fifthLabel.setText("Empty");
									else if(j == 5)
										subPanel.sixthLabel.setText("Empty");
									else if(j == 6)
										subPanel.seventhLabel.setText("Empty");
									subPanel.repaint();
									break;
								}
							}
//							System.out.println("a"+"a");
							if(isInFormation(num, 0, "gk")) {
								tbs.gkList = num;
								tbs.gkNameList = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.gkPosList = ((PlayerObject) tbs.poList.get(i)).getGk();
							}
							else if(sum > 11) {
								JOptionPane.showMessageDialog(null, "The total number of lineup cannot be greater than 11.");
								return;
							}
							else {
								tbs.gkList = num;
								tbs.gkNameList = ((PlayerObject) tbs.poList.get(i)).getName();
								tbs.gkPosList = ((PlayerObject) tbs.poList.get(i)).getGk();
							}
							break;
						}
					}
				}
				repaint();
			}			
		});
		gkOne.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY); 
			    }
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Formation");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		Dimension dim = getPreferredSize();
		dim.width = 380;
		setPreferredSize(dim);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();		
		
//		First Row
		gc.weightx = 1;
		gc.weighty = 0.4;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.SOUTH;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(32, 40, 0, 0);
		add(stOne, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(32, 0, 0, 0);
		add(stTwo, gc);
		gc.gridx = 2;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(32, 0, 0, 0);
		add(stThree, gc);
		gc.gridx = 3;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(32, 0, 0, 0);
		add(stFour, gc);
		gc.gridx = 4;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(32, 0, 0, 15);
		add(stFive, gc);
		
//		Second Row
		gc.weightx = 1;
		gc.weighty = 0;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(30, 40, 0, 0);
		add(amOne, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(30, 0, 0, 0);
		add(amTwo, gc);
		gc.gridx = 2;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(30, 0, 0, 0);
		add(amThree, gc);
		gc.gridx = 3;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(30, 0, 0, 0);
		add(amFour, gc);
		gc.gridx = 4;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(30, 0, 0, 0);
		add(amFive, gc);
		
//		Third Row
		gc.weightx = 1;
		gc.weighty = 0.6;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(20, 40, 0, 0);
		add(mOne, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(20, 0, 0, 0);
		add(mTwo, gc);
		gc.gridx = 2;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(20, 0, 0, 0);
		add(mThree, gc);
		gc.gridx = 3;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(20, 0, 0, 0);
		add(mFour, gc);
		gc.gridx = 4;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(20, 0, 0, 0);
		add(mFive, gc);
		
//		Fourth Row
		gc.weightx = 1;
		gc.weighty = 0;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(26, 40, 0, 0);
		add(dmOne, gc);
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(26, 0, 0, 0);
		add(dmTwo, gc);
		gc.gridx = 2;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(26, 0, 0, 0);
		add(dmThree, gc);
		gc.gridx = 3;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(26, 0, 0, 0);
		add(dmFour, gc);
		gc.gridx = 4;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(26, 0, 0, 0);
		add(dmFive, gc);
		
//		Fifth Row
		gc.weightx = 1;
		gc.weighty = 0.4;
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(40, 40, 0, 0);
		add(dOne, gc);
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(40, 0, 0, 0);
		add(dTwo, gc);
		gc.gridx = 2;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(40, 0, 0, 0);
		add(dThree, gc);
		gc.gridx = 3;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(40, 0, 0, 0);
		add(dFour, gc);
		gc.gridx = 4;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(40, 0, 0, 0);
		add(dFive, gc);
		
//		Sixth Row
		gc.weightx = 1;
		gc.weighty = 0;
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(36, 0, 0, 0);
		add(swOne, gc);
		gc.gridx = 2;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(36, 0, 0, 0);
		add(swTwo, gc);
		gc.gridx = 3;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(36, 0, 0, 0);
		add(swThree, gc);
		
//		Seventh Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 2;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 35, 0);
		add(gkOne, gc);
	}
	
	public void drawing() {
//		Refresh the FormationPanel
		repaint();		
	}
	
	public void paintComponent(Graphics g) {  
//		Draw Formation
        super.paintComponent(g);  
        
        if(tbs.stNameList[0].length() == 1)
			stOne.setText("Empty");
		else
			stOne.setText(tbs.stNameList[0]);
		if(tbs.stNameList[1].length() == 1)
			stTwo.setText("Empty");
		else
			stTwo.setText(tbs.stNameList[1]);
		if(tbs.stNameList[2].length() == 1)
			stThree.setText("Empty");
		else
			stThree.setText(tbs.stNameList[2]);
		if(tbs.stNameList[3].length() == 1)
			stFour.setText("Empty");
		else
			stFour.setText(tbs.stNameList[3]);
		if(tbs.stNameList[4].length() == 1)
			stFive.setText("Empty");
		else
			stFive.setText(tbs.stNameList[4]);
		
		if(tbs.amNameList[0].length() == 1)
			amOne.setText("Empty");
		else
			amOne.setText(tbs.amNameList[0]);
		if(tbs.amNameList[1].length() == 1)
			amTwo.setText("Empty");
		else
			amTwo.setText(tbs.amNameList[1]);
		if(tbs.amNameList[2].length() == 1)
			amThree.setText("Empty");
		else
			amThree.setText(tbs.amNameList[2]);
		if(tbs.amNameList[3].length() == 1)
			amFour.setText("Empty");
		else
			amFour.setText(tbs.amNameList[3]);
		if(tbs.amNameList[4].length() == 1)
			amFive.setText("Empty");
		else
			amFive.setText(tbs.amNameList[4]);
		
		if(tbs.mNameList[0].length() == 1)
			mOne.setText("Empty");
		else
			mOne.setText(tbs.mNameList[0]);
		if(tbs.mNameList[1].length() == 1)
			mTwo.setText("Empty");
		else
			mTwo.setText(tbs.mNameList[1]);
		if(tbs.mNameList[2].length() == 1)
			mThree.setText("Empty");
		else
			mThree.setText(tbs.mNameList[2]);
		if(tbs.mNameList[3].length() == 1)
			mFour.setText("Empty");
		else
			mFour.setText(tbs.mNameList[3]);
		if(tbs.mNameList[4].length() == 1)
			mFive.setText("Empty");
		else
			mFive.setText(tbs.mNameList[4]);
		
		if(tbs.dmNameList[0].length() == 1)
			dmOne.setText("Empty");
		else
			dmOne.setText(tbs.dmNameList[0]);
		if(tbs.dmNameList[1].length() == 1)
			dmTwo.setText("Empty");
		else
			dmTwo.setText(tbs.dmNameList[1]);
		if(tbs.dmNameList[2].length() == 1)
			dmThree.setText("Empty");
		else
			dmThree.setText(tbs.dmNameList[2]);
		if(tbs.dmNameList[3].length() == 1)
			dmFour.setText("Empty");
		else
			dmFour.setText(tbs.dmNameList[3]);
		if(tbs.dmNameList[4].length() == 1)
			dmFive.setText("Empty");
		else
			dmFive.setText(tbs.dmNameList[4]);
		
		if(tbs.dNameList[0].length() == 1)
			dOne.setText("Empty");
		else
			dOne.setText(tbs.dNameList[0]);
		if(tbs.dNameList[1].length() == 1)
			dTwo.setText("Empty");
		else
			dTwo.setText(tbs.dNameList[1]);
		if(tbs.dNameList[2].length() == 1)
			dThree.setText("Empty");
		else
			dThree.setText(tbs.dNameList[2]);
		if(tbs.dNameList[3].length() == 1)
			dFour.setText("Empty");
		else
			dFour.setText(tbs.dNameList[3]);
		if(tbs.dNameList[4].length() == 1)
			dFive.setText("Empty");
		else
			dFive.setText(tbs.dNameList[4]);
		
		if(tbs.swNameList[0].length() == 1)
			swOne.setText("Empty");
		else
			swOne.setText(tbs.swNameList[0]);
		if(tbs.swNameList[1].length() == 1)
			swTwo.setText("Empty");
		else
			swTwo.setText(tbs.swNameList[1]);
		if(tbs.swNameList[2].length() == 1)
			swThree.setText("Empty");
		else
			swThree.setText(tbs.swNameList[2]);
		
		if(tbs.gkNameList.length() == 1)
			gkOne.setText("Empty");
		else
			gkOne.setText(tbs.gkNameList);

        Graphics2D g2 = (Graphics2D)g;   
        
//        	Draw Ground
	        int fLeftX =15;
			int fTopY = 30;
			int fHeight = 530;
			int fWidth = 350;
			g2.setColor(Color.GREEN.darker());		
			g2.fillRect(fLeftX, fTopY, fWidth, fHeight);
			
//			Draw lines
			g2.setColor(Color.white);
	        g2.draw(new Line2D.Double(18, 30, 18, 500));
	        g2.draw(new Line2D.Double(361, 30, 361, 500));
	        g2.draw(new Line2D.Double(18, 220, 361, 220));
	        Ellipse2D middle = new Ellipse2D.Double(); 
	        middle.setFrameFromCenter(190, 220, 220, 250);
	        g2.draw(middle);
	        g2.draw(new Line2D.Double(18, 500, 361, 500));
	        GeneralPath p1 = new GeneralPath();
	        p1.moveTo(160, 30);
    		p1.curveTo(180, 50, 200, 50, 220, 30);
    		g2.draw(p1);
    		g2.draw(new Line2D.Double(68, 500, 68, 420));
    		g2.draw(new Line2D.Double(305, 500, 305, 420));
    		g2.draw(new Line2D.Double(68, 420, 305, 420));
    		GeneralPath p2 = new GeneralPath();
	        p2.moveTo(155, 420);
    		p2.curveTo(170, 390, 207, 390, 222, 420);
    		g2.draw(p2);
    		g2.draw(new Line2D.Double(128, 470, 245, 470));
    		g2.draw(new Line2D.Double(128, 470, 128, 500));
    		g2.draw(new Line2D.Double(245, 470, 245, 500));
    		g2.fillRect(187, 445, 2, 2);
	        
//    		Draw positions
//    		First Row
    		if(tbs.stPosList[0] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.stPosList[0] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.stPosList[0] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.stPosList[0] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(52, 50, 30, 30);
    		
    		if(tbs.stPosList[1] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.stPosList[1] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.stPosList[1] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.stPosList[1] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(114, 50, 30, 30);
    		
    		if(tbs.stPosList[2] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.stPosList[2] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.stPosList[2] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.stPosList[2] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(176, 50, 30, 30);
    		
    		if(tbs.stPosList[3] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.stPosList[3] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.stPosList[3] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.stPosList[3] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(238, 50, 30, 30);
    		
    		if(tbs.stPosList[4] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.stPosList[4] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.stPosList[4] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.stPosList[4] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(300, 50, 30, 30);
    		
//    		Second Row
    		if(tbs.amPosList[0] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.amPosList[0] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.amPosList[0] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.amPosList[0] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(52, 120, 30, 30);
    		
    		if(tbs.amPosList[1] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.amPosList[1] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.amPosList[1] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.amPosList[1] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(114, 120, 30, 30);
    		
    		if(tbs.amPosList[2] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.amPosList[2] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.amPosList[2] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.amPosList[2] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(176, 120, 30, 30);
    		
    		if(tbs.amPosList[3] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.amPosList[3] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.amPosList[3] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.amPosList[3] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(238, 120, 30, 30);
    		
    		if(tbs.amPosList[4] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.amPosList[4] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.amPosList[4] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.amPosList[4] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(300, 120, 30, 30);
    		
//    		Third Row
    		if(tbs.mPosList[0] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.mPosList[0] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.mPosList[0] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.mPosList[0] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(52, 190, 30, 30);
    		
    		if(tbs.mPosList[1] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.mPosList[1] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.mPosList[1] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.mPosList[1] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(114, 190, 30, 30);
    		
    		if(tbs.mPosList[2] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.mPosList[2] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.mPosList[2] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.mPosList[2] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(176, 190, 30, 30);

    		if(tbs.mPosList[3] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.mPosList[3] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.mPosList[3] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.mPosList[3] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(238, 190, 30, 30);

    		if(tbs.mPosList[4] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.mPosList[4] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.mPosList[4] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.mPosList[4] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(300, 190, 30, 30);
    		
//    		Fourth Row
    		if(tbs.dmPosList[0] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dmPosList[0] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dmPosList[0] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dmPosList[0] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(52, 270, 30, 30);
    		
    		if(tbs.dmPosList[1] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dmPosList[1] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dmPosList[1] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dmPosList[1] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(114, 270, 30, 30);

    		if(tbs.dmPosList[2] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dmPosList[2] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dmPosList[2] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dmPosList[2] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(176, 270, 30, 30);

    		if(tbs.dmPosList[3] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dmPosList[3] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dmPosList[3] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dmPosList[3] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(238, 270, 30, 30);

    		if(tbs.dmPosList[4] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dmPosList[4] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dmPosList[4] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dmPosList[4] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(300, 270, 30, 30);
    		
//    		Fifth Row
    		if(tbs.dPosList[0] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dPosList[0] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dPosList[0] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dPosList[0] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(52, 350, 30, 30);
    		
    		if(tbs.dPosList[1] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dPosList[1] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dPosList[1] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dPosList[1] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(114, 350, 30, 30);

    		if(tbs.dPosList[2] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dPosList[2] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dPosList[2] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dPosList[2] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(176, 350, 30, 30);

    		if(tbs.dPosList[3] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dPosList[3] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dPosList[3] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dPosList[3] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(238, 350, 30, 30);

    		if(tbs.dPosList[4] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.dPosList[4] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.dPosList[4] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.dPosList[4] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(300, 350, 30, 30);
    		
//    		Sixth Row
    		if(tbs.swPosList[0] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.swPosList[0] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.swPosList[0] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.swPosList[0] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(114, 425, 30, 30);
    		
    		if(tbs.swPosList[1] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.swPosList[1] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.swPosList[1] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.swPosList[1] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(176, 425, 30, 30);

    		if(tbs.swPosList[2] == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.swPosList[2] == 1)
    			g2.setColor(Color.red);
    		else if(tbs.swPosList[2] == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.swPosList[2] == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(238, 425, 30, 30);
    		
//    		Seventh Row
    		if(tbs.gkPosList == 0)
    			g2.setColor(Color.lightGray);
    		else if(tbs.gkPosList == 1)
    			g2.setColor(Color.red);
    		else if(tbs.gkPosList == 2)
    			g2.setColor(Color.yellow);
    		else if(tbs.gkPosList == 3)
    			g2.setColor(Color.green);
    		g2.fillOval(176, 470, 30, 30);
    }
	
	private int getSumPos() {
		int sum = 0;
		for(int i = 0; i < 5; i++) {
			if(tbs.stList[i] > 0)
				sum++;
		}
		for(int i = 0; i < 5; i++) {
			if(tbs.amList[i] > 0)
				sum++;
		}
		for(int i = 0; i < 5; i++) {
			if(tbs.mList[i] > 0)
				sum++;
		}
		for(int i = 0; i < 5; i++) {
			if(tbs.dmList[i] > 0)
				sum++;
		}
		for(int i = 0; i < 5; i++) {
			if(tbs.dList[i] > 0)
				sum++;
		}
		for(int i = 0; i < 3; i++) {
			if(tbs.swList[i] > 0)
				sum++;
		}
		if(tbs.gkList > 0)
			sum++;
		return sum;
	}
	
	private boolean isInFormation(int num, int po, String pos) {
		for(int i = 0; i < 5; i++) {
			if(pos == "st") {
				if(tbs.stList[i] == num && i != po) {
					tbs.stList[i] = 0;
					tbs.stNameList[i] = " ";
					tbs.stPosList[i] = 0;
					if(i == 0)
						stOne.setText("Empty");
					else if(i == 1)
						stTwo.setText("Empty");
					else if(i == 2)
						stThree.setText("Empty");
					else if(i == 3)
						stFour.setText("Empty");
					else if(i == 4)
						stFive.setText("Empty");
					return true;
				}
			}
			else {
				if(tbs.stList[i] == num) {
					tbs.stList[i] = 0;
					tbs.stNameList[i] = " ";
					tbs.stPosList[i] = 0;
					if(i == 0)
						stOne.setText("Empty");
					else if(i == 1)
						stTwo.setText("Empty");
					else if(i == 2)
						stThree.setText("Empty");
					else if(i == 3)
						stFour.setText("Empty");
					else if(i == 4)
						stFive.setText("Empty");
					return true;
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			if(pos == "am") {
				if(tbs.amList[i] == num && i != po) {
					tbs.amList[i] = 0;
					tbs.amNameList[i] = " ";
					tbs.amPosList[i] = 0;
					if(i == 0)
						amOne.setText("Empty");
					else if(i == 1)
						amTwo.setText("Empty");
					else if(i == 2)
						amThree.setText("Empty");
					else if(i == 3)
						amFour.setText("Empty");
					else if(i == 4)
						amFive.setText("Empty");
					return true;
				}
			}
			else {
				if(tbs.amList[i] == num) {
					tbs.amList[i] = 0;
					tbs.amNameList[i] = " ";
					tbs.amPosList[i] = 0;
					if(i == 0)
						amOne.setText("Empty");
					else if(i == 1)
						amTwo.setText("Empty");
					else if(i == 2)
						amThree.setText("Empty");
					else if(i == 3)
						amFour.setText("Empty");
					else if(i == 4)
						amFive.setText("Empty");
					return true;
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			if(pos == "m") {
				if(tbs.mList[i] == num && i != po) {
					tbs.mList[i] = 0;
					tbs.mNameList[i] = " ";
					tbs.mPosList[i] = 0;
					if(i == 0)
						mOne.setText("Empty");
					else if(i == 1)
						mTwo.setText("Empty");
					else if(i == 2)
						mThree.setText("Empty");
					else if(i == 3)
						mFour.setText("Empty");
					else if(i == 4)
						mFive.setText("Empty");
					return true;
				}
			}
			else {
				if(tbs.mList[i] == num) {
					tbs.mList[i] = 0;
					tbs.mNameList[i] = " ";
					tbs.mPosList[i] = 0;
					if(i == 0)
						mOne.setText("Empty");
					else if(i == 1)
						mTwo.setText("Empty");
					else if(i == 2)
						mThree.setText("Empty");
					else if(i == 3)
						mFour.setText("Empty");
					else if(i == 4)
						mFive.setText("Empty");
					return true;
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			if(pos == "dm") {
				if(tbs.dmList[i] == num && i != po) {
					tbs.dmList[i] = 0;
					tbs.dmNameList[i] = " ";
					tbs.dmPosList[i] = 0;
					if(i == 0)
						dmOne.setText("Empty");
					else if(i == 1)
						dmTwo.setText("Empty");
					else if(i == 2)
						dmThree.setText("Empty");
					else if(i == 3)
						dmFour.setText("Empty");
					else if(i == 4)
						dmFive.setText("Empty");
					return true;
				}
			}
			else {
				if(tbs.dmList[i] == num) {
					tbs.dmList[i] = 0;
					tbs.dmNameList[i] = " ";
					tbs.dmPosList[i] = 0;
					if(i == 0)
						dmOne.setText("Empty");
					else if(i == 1)
						dmTwo.setText("Empty");
					else if(i == 2)
						dmThree.setText("Empty");
					else if(i == 3)
						dmFour.setText("Empty");
					else if(i == 4)
						dmFive.setText("Empty");
					return true;
				}
			}
		}
		for(int i = 0; i < 5; i++) {
			if(pos == "d") {
				if(tbs.dList[i] == num && i != po) {
					tbs.dList[i] = 0;
					tbs.dNameList[i] = " ";
					tbs.dPosList[i] = 0;
					if(i == 0)
						dOne.setText("Empty");
					else if(i == 1)
						dTwo.setText("Empty");
					else if(i == 2)
						dThree.setText("Empty");
					else if(i == 3)
						dFour.setText("Empty");
					else if(i == 4)
						dFive.setText("Empty");
					return true;
				}
			}
			else {
				if(tbs.dList[i] == num) {
					tbs.dList[i] = 0;
					tbs.dNameList[i] = " ";
					tbs.dPosList[i] = 0;
					if(i == 0)
						dOne.setText("Empty");
					else if(i == 1)
						dTwo.setText("Empty");
					else if(i == 2)
						dThree.setText("Empty");
					else if(i == 3)
						dFour.setText("Empty");
					else if(i == 4)
						dFive.setText("Empty");
					return true;
				}
			}
		}
		for(int i = 0; i < 3; i++) {
			if(pos == "sw") {
				if(tbs.swList[i] == num && i != po) {
					tbs.swList[i] = 0;
					tbs.swNameList[i] = " ";
					tbs.swPosList[i] = 0;
					if(i == 0)
						swOne.setText("Empty");
					else if(i == 1)
						swTwo.setText("Empty");
					else if(i == 2)
						swThree.setText("Empty");
					return true;
				}
			}
			else {
				if(tbs.swList[i] == num) {
					tbs.swList[i] = 0;
					tbs.swNameList[i] = " ";
					tbs.swPosList[i] = 0;
					if(i == 0)
						swOne.setText("Empty");
					else if(i == 1)
						swTwo.setText("Empty");
					else if(i == 2)
						swThree.setText("Empty");
					return true;
				}
			}
		}
		if(pos == "gk") {
			if(tbs.gkList == num && po != 0) {
				tbs.gkList = 0;
				tbs.gkNameList = " ";
				tbs.gkPosList = 0;
				gkOne.setText("Empty");
				return true;
			}
		}
		else {
			if(tbs.gkList == num) {
				tbs.gkList = 0;
				tbs.gkNameList = " ";
				tbs.gkPosList = 0;
				gkOne.setText("Empty");
				return true;
			}
		}
		return false;
	}

}

