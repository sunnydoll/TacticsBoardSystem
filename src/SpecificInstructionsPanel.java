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
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpecificInstructionsPanel extends JPanel {
	
	private JLabel generalLabel;
//	private JLabel philLabel;
	private JLabel straLabel;
	private JLabel passLabel;
	private JLabel creatLabel;
	private JLabel closLabel;
	private JLabel tackleLabel;
	private JLabel markLabel;
	private JLabel crossLabel;
	private JLabel roamLabel;
	private JLabel specificLabel;
	private JLabel deflineLabel;
//	private JLabel widthLabel;
	private JLabel tempoLabel;
//	private JLabel timeLabel;
	private JLabel focusLabel;
	private JLabel countLabel;
	private JLabel offsLabel;
	private JLabel deflineInfo;
	private JLabel tempoInfo;
	
//	public JComboBox philList;
	public JComboBox straList;
	public JComboBox passList;
	public JComboBox creatList;
	public JComboBox closList;
	public JComboBox tackleList;
	public JComboBox markList;
	public JComboBox crossList;
	public JComboBox roamList;
	public JSlider deflineSlider;
//	public JSlider widthSlider;
	public JSlider tempoSlider;
//	public JSlider timeSlider;
	public JComboBox focusList;
	public JComboBox countList;
	public JComboBox offsList;
	
//	private ChangeListener changeListener;
	
	public SpecificInstructionsPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		String[] philStrings = {"Very Regid", "Regid", "Balanced", "Fluid", "Very Fluid"};
		String[] straStrings = {"Contain", "Defensive", "Counter", "Stantard", "Control", "Attacking", "Control"};
		String[] passStrings = {"Short", "Direct"};
		String[] creatStrings = {"Expressive", "Disciplined"};
		String[] closStrings = {"Press", "Stand-off"};
		String[] tackleStrings = {"Aggressive", "Cautious"};
		String[] markStrings = {"Zonal Marking", "Man Marking"};
		String[] crossStrings = {"Float", "Drill"};
		String[] roamStrings = {"Stick to Position", "Roaming"};
		String[] focusStrings = {"Mixed", "Down Both Flanks", "Down Left Flank", "Down Right Flank", "Through The Middle"};
		String[] countStrings = {"Yes", "No"};
		String[] offsStrings = {"Yes", "No"};
		
		generalLabel = new JLabel("General");
//		philLabel = new JLabel("Philosophy");
		straLabel = new JLabel("Strategy:");
		passLabel = new JLabel("Passing Style:");
		creatLabel = new JLabel("Creativity:");
		closLabel = new JLabel("Closing Down:");
		tackleLabel = new JLabel("Tackling:");
		markLabel = new JLabel("Marking:");
		crossLabel = new JLabel("Crossing:");
		roamLabel = new JLabel("Roaming:");
		specificLabel = new JLabel("Specific");
		deflineLabel = new JLabel("Defensive Line:");
//		widthLabel = new JLabel("Width");
		tempoLabel = new JLabel("Tempo:");
//		timeLabel = new JLabel("Time Wasting");
		focusLabel = new JLabel("Focus Passing:");
		countLabel = new JLabel("Counter:");
		offsLabel = new JLabel("Play Offside:");
		
//		philList = new JComboBox<>(philStrings);
		straList = new JComboBox<>(straStrings);		
		passList = new JComboBox<>(passStrings);
		creatList = new JComboBox<>(creatStrings);
		closList = new JComboBox<>(closStrings);
		tackleList = new JComboBox<>(tackleStrings);
		markList = new JComboBox<>(markStrings);
		crossList = new JComboBox<>(crossStrings);
		roamList = new JComboBox<>(roamStrings);
		focusList = new JComboBox<>(focusStrings);
		countList = new JComboBox<>(countStrings);
		offsList = new JComboBox<>(offsStrings);
		
		ChangeListener deflineListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JSlider) {
                	int deflineValue = ((JSlider) e.getSource()).getValue();
                	if(deflineValue >= 0 && deflineValue <= 30) {
            			deflineInfo.setText("Deep");
            		}
            		else if(deflineValue > 30 && deflineValue <= 70) {
            			deflineInfo.setText("Normal");
            		}
            		else {
            			deflineInfo.setText("Push Up");
            		}
                }
            }
        };
        
        ChangeListener tempoListener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JSlider) {
                	int tempoValue = ((JSlider) e.getSource()).getValue();
                	if(tempoValue >= 0 && tempoValue <= 30) {
            			tempoInfo.setText("Slow");
            		}
            		else if(tempoValue > 30 && tempoValue <= 70) {
            			tempoInfo.setText("Normal");
            		}
            		else {
            			tempoInfo.setText("Fast");
            		}
                }
            }
        };
		
		deflineSlider = new JSlider(0, 140);
		deflineSlider.setPreferredSize(new Dimension(140, 20));
		deflineSlider.addChangeListener(deflineListener);
		tempoSlider = new JSlider(0, 140);
		tempoSlider.setPreferredSize(new Dimension(140, 20));
		tempoSlider.addChangeListener(tempoListener);
		deflineInfo = new JLabel();
		tempoInfo = new JLabel();
		if(deflineSlider.getValue() >= 0 && deflineSlider.getValue() <= 30) {
			deflineInfo.setText("Deep");
		}
		else if(deflineSlider.getValue() > 30 && deflineSlider.getValue() <= 70) {
			deflineInfo.setText("Normal");
		}
		else {
			deflineInfo.setText("Push Up");
		}
		if(tempoSlider.getValue() >= 0 && tempoSlider.getValue() <= 30) {
			tempoInfo.setText("Slow");
		}
		else if(tempoSlider.getValue() > 30 && tempoSlider.getValue() <= 70) {
			tempoInfo.setText("Normal");
		}
		else {
			tempoInfo.setText("Fast");
		}
//		timeSlider = new JSlider(0, 20);
		
		Border innerBorder = BorderFactory.createTitledBorder("Team Instructions");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();		
		
//		First Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(straLabel, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(straList, gc);
		
//		Second Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(passLabel, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(passList, gc);
		
//		Third Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(creatLabel, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(creatList, gc);
		
//		Fourth Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(closLabel, gc);
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(closList, gc);
		
//		Fifth Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(tackleLabel, gc);
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tackleList, gc);
		
//		Sixth Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(markLabel, gc);
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(markList, gc);
		
//		Seventh Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(crossLabel, gc);
		gc.gridx = 1;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(crossList, gc);
		
//		Eighth Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(roamLabel, gc);
		gc.gridx = 1;
		gc.gridy = 7;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(roamList, gc);
		
//		Ninth Row		
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(deflineLabel, gc);
		gc.gridx = 1;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(deflineSlider, gc);
		gc.weightx = 15;
		gc.gridx = 2;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(deflineInfo, gc);
		
//		Tenth Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 9;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(tempoLabel, gc);
		gc.gridx = 1;
		gc.gridy = 9;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tempoSlider, gc);
		gc.weightx = 15;
		gc.gridx = 2;
		gc.gridy = 9;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tempoInfo, gc);
		
//		Eleventh Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 10;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(focusLabel, gc);
		gc.gridx = 1;
		gc.gridy = 10;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(focusList, gc);
		
//		Twelfth Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 11;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(countLabel, gc);
		gc.gridx = 1;
		gc.gridy = 11;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(countList, gc);
		
//		Thirteen Row
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 12;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(offsLabel, gc);
		gc.gridx = 1;
		gc.gridy = 12;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(offsList, gc);
	}
}
