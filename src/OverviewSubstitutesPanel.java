/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.Border;


public class OverviewSubstitutesPanel extends JPanel {

	private TacticsBoardSystem tbs;
	public static PlayerObject po;
	public JLabel firstLabel;
	public JLabel secondLabel;
	public JLabel thirdLabel;
	public JLabel fourthLabel;
	public JLabel fifthLabel;
	public JLabel sixthLabel;
	public JLabel seventhLabel;
//	public int[] subList;
	
	public OverviewSubstitutesPanel(final OverviewPanel op) {	
		
		tbs = new TacticsBoardSystem();
//		subList = tbs.subList;
		
		if(tbs.subNameList[0].length() == 1)
			firstLabel = new JLabel("Empty");
		else
			firstLabel = new JLabel(tbs.subNameList[0]);
		if(tbs.subNameList[1].length() == 1)
			secondLabel = new JLabel("Empty");
		else
			secondLabel = new JLabel(tbs.subNameList[1]);
		if(tbs.subNameList[2].length() == 1)
			thirdLabel = new JLabel("Empty");
		else
			thirdLabel = new JLabel(tbs.subNameList[2]);
		if(tbs.subNameList[3].length() == 1)
			fourthLabel = new JLabel("Empty");
		else
			fourthLabel = new JLabel(tbs.subNameList[3]);
		if(tbs.subNameList[4].length() == 1)
			fifthLabel = new JLabel("Empty");
		else
			fifthLabel = new JLabel(tbs.subNameList[4]);
		if(tbs.subNameList[5].length() == 1)
			sixthLabel = new JLabel("Empty");
		else
			sixthLabel = new JLabel(tbs.subNameList[5]);
		if(tbs.subNameList[6].length() == 1)
			seventhLabel = new JLabel("Empty");
		else
			seventhLabel = new JLabel(tbs.subNameList[6]);
		firstLabel.setForeground(Color.white);
		secondLabel.setForeground(Color.white);
		thirdLabel.setForeground(Color.white);
		fourthLabel.setForeground(Color.white);
		fifthLabel.setForeground(Color.white);
		sixthLabel.setForeground(Color.white);
		seventhLabel.setForeground(Color.white);
		firstLabel.setTransferHandler( new TransferHandler("text") );
		secondLabel.setTransferHandler( new TransferHandler("text") );
		thirdLabel.setTransferHandler( new TransferHandler("text") );
		fourthLabel.setTransferHandler( new TransferHandler("text") );
		fifthLabel.setTransferHandler( new TransferHandler("text") );
		sixthLabel.setTransferHandler( new TransferHandler("text") );
		seventhLabel.setTransferHandler( new TransferHandler("text") );	
		firstLabel.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY);//调用了exportAsDrag
			    }
		});
		secondLabel.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY);//调用了exportAsDrag
			    }
		});
		thirdLabel.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY);//调用了exportAsDrag
			    }
		});
		fourthLabel.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY);//调用了exportAsDrag
			    }
		});
		fifthLabel.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY);//调用了exportAsDrag
			    }
		});
		sixthLabel.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY);//调用了exportAsDrag
			    }
		});
		seventhLabel.addMouseListener( new MouseAdapter(){
			   public void mousePressed( MouseEvent e ){
			     JComponent c = (JComponent)e.getSource();
			     TransferHandler handler = c.getTransferHandler();
			     handler.exportAsDrag(c,e,TransferHandler.COPY);//调用了exportAsDrag
			    }
		});
		
		firstLabel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("java"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 5; j++) {
								if(tbs.stList[j] == num) {
									tbs.stList[j] = 0;
									tbs.stNameList[j] = " ";
									tbs.stPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.amList[j] == num) {
									tbs.amList[j] = 0;
									tbs.amNameList[j] = " ";
									tbs.amPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.mList[j] == num) {
									tbs.mList[j] = 0;
									tbs.mNameList[j] = " ";
									tbs.mPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dmList[j] == num) {
									tbs.dmList[j] = 0;
									tbs.dmNameList[j] = " ";
									tbs.dmPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dList[j] == num) {
									tbs.dList[j] = 0;
									tbs.dNameList[j] = " ";
									tbs.dPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 3; j++) {
								if(tbs.swList[j] == num) {
									tbs.swList[j] = 0;
									tbs.swNameList[j] = " ";
									tbs.swPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							if(tbs.gkList == num) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
								op.formPanel.repaint();
							}
							tbs.subList[0] = num;
							tbs.subNameList[0] = ((PlayerObject) tbs.poList.get(i)).getName();
							break;
						}
					}
				}
//				subList[0] = num;
				for(int j = 0; j < tbs.subList.length; j++) {
					if(num == tbs.subList[j] && j != 0) {
						tbs.subList[j] = 0;
						tbs.subNameList[j] = " ";
						setLabelBack(j);
						break;
					}
				}
				repaint();
			}			
		});
		secondLabel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("java"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 5; j++) {
								if(tbs.stList[j] == num) {
									tbs.stList[j] = 0;
									tbs.stNameList[j] = " ";
									tbs.stPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.amList[j] == num) {
									tbs.amList[j] = 0;
									tbs.amNameList[j] = " ";
									tbs.amPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.mList[j] == num) {
									tbs.mList[j] = 0;
									tbs.mNameList[j] = " ";
									tbs.mPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dmList[j] == num) {
									tbs.dmList[j] = 0;
									tbs.dmNameList[j] = " ";
									tbs.dmPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dList[j] == num) {
									tbs.dList[j] = 0;
									tbs.dNameList[j] = " ";
									tbs.dPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 3; j++) {
								if(tbs.swList[j] == num) {
									tbs.swList[j] = 0;
									tbs.swNameList[j] = " ";
									tbs.swPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							if(tbs.gkList == num) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
								op.formPanel.repaint();
							}
							tbs.subList[1] = num;
							tbs.subNameList[1] = ((PlayerObject) tbs.poList.get(i)).getName();
							break;
						}
					}
				}
				for(int j = 0; j < tbs.subList.length; j++) {
					if(num == tbs.subList[j] && j != 1) {
						tbs.subList[j] = 0;
						tbs.subNameList[j] = " ";
						setLabelBack(j);
						break;
					}
				}
				repaint();
			}	
		});
		thirdLabel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("java"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 5; j++) {
								if(tbs.stList[j] == num) {
									tbs.stList[j] = 0;
									tbs.stNameList[j] = " ";
									tbs.stPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.amList[j] == num) {
									tbs.amList[j] = 0;
									tbs.amNameList[j] = " ";
									tbs.amPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.mList[j] == num) {
									tbs.mList[j] = 0;
									tbs.mNameList[j] = " ";
									tbs.mPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dmList[j] == num) {
									tbs.dmList[j] = 0;
									tbs.dmNameList[j] = " ";
									tbs.dmPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dList[j] == num) {
									tbs.dList[j] = 0;
									tbs.dNameList[j] = " ";
									tbs.dPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 3; j++) {
								if(tbs.swList[j] == num) {
									tbs.swList[j] = 0;
									tbs.swNameList[j] = " ";
									tbs.swPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							if(tbs.gkList == num) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
								op.formPanel.repaint();
							}
							tbs.subList[2] = num;
							tbs.subNameList[2] = ((PlayerObject) tbs.poList.get(i)).getName();
							break;
						}
					}
				}
				for(int j = 0; j < tbs.subList.length; j++) {
					if(num == tbs.subList[j] && j != 2) {
						tbs.subList[j] = 0;
						tbs.subNameList[j] = " ";
						setLabelBack(j);
						break;
					}
				}
				repaint();
			}	
		});
		fourthLabel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("java"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 5; j++) {
								if(tbs.stList[j] == num) {
									tbs.stList[j] = 0;
									tbs.stNameList[j] = " ";
									tbs.stPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.amList[j] == num) {
									tbs.amList[j] = 0;
									tbs.amNameList[j] = " ";
									tbs.amPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.mList[j] == num) {
									tbs.mList[j] = 0;
									tbs.mNameList[j] = " ";
									tbs.mPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dmList[j] == num) {
									tbs.dmList[j] = 0;
									tbs.dmNameList[j] = " ";
									tbs.dmPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dList[j] == num) {
									tbs.dList[j] = 0;
									tbs.dNameList[j] = " ";
									tbs.dPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 3; j++) {
								if(tbs.swList[j] == num) {
									tbs.swList[j] = 0;
									tbs.swNameList[j] = " ";
									tbs.swPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							if(tbs.gkList == num) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
								op.formPanel.repaint();
							}
							tbs.subList[3] = num;
							tbs.subNameList[3] = ((PlayerObject) tbs.poList.get(i)).getName();
							break;
						}
					}
				}
				for(int j = 0; j < tbs.subList.length; j++) {
					if(num == tbs.subList[j] && j != 3) {
						tbs.subList[j] = 0;
						tbs.subNameList[j] = " ";
						setLabelBack(j);
						break;
					}
				}
				repaint();
			}			
		});
		fifthLabel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("java"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 5; j++) {
								if(tbs.stList[j] == num) {
									tbs.stList[j] = 0;
									tbs.stNameList[j] = " ";
									tbs.stPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.amList[j] == num) {
									tbs.amList[j] = 0;
									tbs.amNameList[j] = " ";
									tbs.amPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.mList[j] == num) {
									tbs.mList[j] = 0;
									tbs.mNameList[j] = " ";
									tbs.mPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dmList[j] == num) {
									tbs.dmList[j] = 0;
									tbs.dmNameList[j] = " ";
									tbs.dmPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dList[j] == num) {
									tbs.dList[j] = 0;
									tbs.dNameList[j] = " ";
									tbs.dPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 3; j++) {
								if(tbs.swList[j] == num) {
									tbs.swList[j] = 0;
									tbs.swNameList[j] = " ";
									tbs.swPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							if(tbs.gkList == num) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
								op.formPanel.repaint();
							}
							tbs.subList[4] = num;
							tbs.subNameList[4] = ((PlayerObject) tbs.poList.get(i)).getName();
							break;
						}
					}
				}
				for(int j = 0; j < tbs.subList.length; j++) {
					if(num == tbs.subList[j] && j != 4) {
						tbs.subList[j] = 0;
						tbs.subNameList[j] = " ";
						setLabelBack(j);
						break;
					}
				}
				repaint();
			}			
		});
		sixthLabel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("java"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 5; j++) {
								if(tbs.stList[j] == num) {
									tbs.stList[j] = 0;
									tbs.stNameList[j] = " ";
									tbs.stPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.amList[j] == num) {
									tbs.amList[j] = 0;
									tbs.amNameList[j] = " ";
									tbs.amPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.mList[j] == num) {
									tbs.mList[j] = 0;
									tbs.mNameList[j] = " ";
									tbs.mPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dmList[j] == num) {
									tbs.dmList[j] = 0;
									tbs.dmNameList[j] = " ";
									tbs.dmPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dList[j] == num) {
									tbs.dList[j] = 0;
									tbs.dNameList[j] = " ";
									tbs.dPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 3; j++) {
								if(tbs.swList[j] == num) {
									tbs.swList[j] = 0;
									tbs.swNameList[j] = " ";
									tbs.swPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							if(tbs.gkList == num) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
								op.formPanel.repaint();
							}
							tbs.subList[5] = num;
							tbs.subNameList[5] = ((PlayerObject) tbs.poList.get(i)).getName();
							break;
						}
					}
				}
				for(int j = 0; j < tbs.subList.length; j++) {
					if(num == tbs.subList[j] && j != 5) {
						tbs.subList[j] = 0;
						tbs.subNameList[j] = " ";
						setLabelBack(j);
						break;
					}
				}
				repaint();
			}		
		});
		seventhLabel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JLabel label = (JLabel)evt.getSource();
				String labelName = label.getName();
				int num = 0;
				String[] newValue = evt.getNewValue().toString().split(",");
				if(!newValue[0].startsWith("java"))
					label.setText(newValue[0]);
				if(tbs.poList.size() != 0) {
					for(int i = 0; i < tbs.poList.size(); i++) {							
						if(((PlayerObject) tbs.poList.get(i)).getName().equalsIgnoreCase(newValue[0].trim())) {
							num = ((PlayerObject)tbs.poList.get(i)).getNumber();
							for(int j = 0; j < 5; j++) {
								if(tbs.stList[j] == num) {
									tbs.stList[j] = 0;
									tbs.stNameList[j] = " ";
									tbs.stPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.amList[j] == num) {
									tbs.amList[j] = 0;
									tbs.amNameList[j] = " ";
									tbs.amPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.mList[j] == num) {
									tbs.mList[j] = 0;
									tbs.mNameList[j] = " ";
									tbs.mPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dmList[j] == num) {
									tbs.dmList[j] = 0;
									tbs.dmNameList[j] = " ";
									tbs.dmPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 5; j++) {
								if(tbs.dList[j] == num) {
									tbs.dList[j] = 0;
									tbs.dNameList[j] = " ";
									tbs.dPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							for(int j = 0; j < 3; j++) {
								if(tbs.swList[j] == num) {
									tbs.swList[j] = 0;
									tbs.swNameList[j] = " ";
									tbs.swPosList[j] = 0;
									op.formPanel.repaint();
									break;
								}
							}
							if(tbs.gkList == num) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
								op.formPanel.repaint();
							}
							tbs.subList[6] = num;
							tbs.subNameList[6] = ((PlayerObject) tbs.poList.get(i)).getName();
							break;
						}
					}
				}
				for(int j = 0; j < tbs.subList.length; j++) {
					if(num == tbs.subList[j] && j != 6) {
						tbs.subList[j] = 0;
						tbs.subNameList[j] = " ";
						setLabelBack(j);
						break;
					}
				}
				repaint();
			}		
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Substitutes");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		Dimension dim = getPreferredSize();
		dim.width = 200;
		setPreferredSize(dim);
		
		Box box = new Box(BoxLayout.Y_AXIS);		
		add(box);
		box.add(Box.createVerticalStrut(50));
		box.add(firstLabel);
		box.add(Box.createVerticalStrut(50));
		box.add(secondLabel);
		box.add(Box.createVerticalStrut(52));
		box.add(thirdLabel);
		box.add(Box.createVerticalStrut(53));
		box.add(fourthLabel);
		box.add(Box.createVerticalStrut(51));
		box.add(fifthLabel);
		box.add(Box.createVerticalStrut(52));
		box.add(sixthLabel);
		box.add(Box.createVerticalStrut(52));
		box.add(seventhLabel);
		
	}
	
	public void drawing() {
//		Refresh the DrawPanel
		repaint();		
	}
	
	public void paintComponent(Graphics g) {  
//		Draw substitutes
        super.paintComponent(g);  
        
        if(tbs.subNameList[0].length() == 1)
			firstLabel.setText("Empty");
		else
			firstLabel.setText(tbs.subNameList[0]);
		if(tbs.subNameList[1].length() == 1)
			secondLabel.setText("Empty");
		else
			secondLabel.setText(tbs.subNameList[1]);
		if(tbs.subNameList[2].length() == 1)
			thirdLabel.setText("Empty");
		else
			thirdLabel.setText(tbs.subNameList[2]);
		if(tbs.subNameList[3].length() == 1)
			fourthLabel.setText("Empty");
		else
			fourthLabel.setText(tbs.subNameList[3]);
		if(tbs.subNameList[4].length() == 1)
			fifthLabel.setText("Empty");
		else
			fifthLabel.setText(tbs.subNameList[4]);
		if(tbs.subNameList[5].length() == 1)
			sixthLabel.setText("Empty");
		else
			sixthLabel.setText(tbs.subNameList[5]);
		if(tbs.subNameList[6].length() == 1)
			seventhLabel.setText("Empty");
		else
			seventhLabel.setText(tbs.subNameList[6]);

        Graphics2D g2 = (Graphics2D)g;           
        
        int sLeftX =15;
		int sTopY = 30;
		int sHeight = 530;
		int sWidth = 170;
		g2.setColor(Color.BLUE.brighter());		
		g2.fillRect(sLeftX, sTopY, sWidth, sHeight);
		if(tbs.subList[0] == 0)
			g2.setColor(Color.lightGray);
		else
			g2.setColor(Color.green.darker());
		g2.fillOval(83, 50, 30, 30);
		if(tbs.subList[1] == 0)
			g2.setColor(Color.lightGray);
		else
			g2.setColor(Color.green.darker());
		g2.fillOval(83, 120, 30, 30);
		if(tbs.subList[2] == 0)
			g2.setColor(Color.lightGray);
		else
			g2.setColor(Color.green.darker());
		g2.fillOval(83, 190, 30, 30);
		if(tbs.subList[3] == 0)
			g2.setColor(Color.lightGray);
		else
			g2.setColor(Color.green.darker());
		g2.fillOval(83, 260, 30, 30);
		if(tbs.subList[4] == 0)
			g2.setColor(Color.lightGray);
		else
			g2.setColor(Color.green.darker());
		g2.fillOval(83, 330, 30, 30);
		if(tbs.subList[5] == 0)
			g2.setColor(Color.lightGray);
		else
			g2.setColor(Color.green.darker());
		g2.fillOval(83, 400, 30, 30);
		if(tbs.subList[6] == 0)
			g2.setColor(Color.lightGray);
		else
			g2.setColor(Color.green.darker());
		g2.fillOval(83, 470, 30, 30);
		
    }
	
	private void setLabelBack(int i) {
		switch(i) {
			case 0:
				firstLabel.setText("Empty");
				break;
			case 1:
				secondLabel.setText("Empty");
				break;
			case 2:
				thirdLabel.setText("Empty");
				break;
			case 3:
				fourthLabel.setText("Empty");
				break;
			case 4:
				fifthLabel.setText("Empty");
				break;
			case 5:
				sixthLabel.setText("Empty");
				break;
			case 6:
				seventhLabel.setText("Empty");
				break;
		}
	}
}
