/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class OverviewListPanel extends JPanel implements ActionListener {
	
//	public JList playerList;
//	public DefaultListModel playerModel;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDel;
	private TacticsBoardSystem tbs;
	
	private JScrollPane sp;
	public DefaultTableModel tmd;
	public JTable playerTable;	
//	private JLabel testDrag;

	private OverviewListButtonListener buttonListener;	//Listen to the buttons in ListPanel 
	private OverviewListListener formListener;
	
	public OverviewListPanel() {
		
		tbs = new TacticsBoardSystem();
		
		btnAdd = new JButton("Add");
		btnEdit = new JButton("Edit");
		btnDel = new JButton("Delete");
		
		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDel.addActionListener(this);		
		
//		testDrag = new JLabel("Try");
//		testDrag.setTransferHandler( new TransferHandler("text") );

//		playerList = new JList<>();
//		playerModel = new DefaultListModel();
//		if(tbs.poList.size() == 0) {
//			playerList.setModel(playerModel);
//		}
//		else {	//If it has players added by user, it has to add them in player list
//			for(int i = 0; i < tbs.poList.size(); i++) {
//				playerModel.addElement(tbs.poList.get(i));
//			}
//			playerList.setModel(playerModel);
//		}
//		playerList.setPreferredSize(new Dimension(250, 450));
//		playerList.setBorder(BorderFactory.createEtchedBorder());
//		playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		

		String[] column = {"Name","Position"};
		tmd = new DefaultTableModel();
		tmd.addColumn("Name and Position");
		playerTable = new JTable(){
			public boolean isCellEditable(int rowIndex, int ColIndex){
				return false;
			}
		};
		if(tbs.poList.size() == 0) {
			playerTable.setModel(tmd);
		}
		else {	//If it has players added by user, it has to add them in player list
			for(int i = 0; i < tbs.poList.size(); i++) {
				String[] row = {((PlayerObject)tbs.poList.get(i)).getName() + ", Position: " + ((PlayerObject)tbs.poList.get(i)).getPositionString()};
				tmd.addRow(row);
			}
			playerTable.setModel(tmd);
		}
		playerTable.setPreferredSize(new Dimension(350, 450));
		playerTable.setBorder(BorderFactory.createEtchedBorder());
		playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerTable.setDragEnabled(true);
//		playerTable.setDropMode(DropMode.ON);
//		playerTable.setTransferHandler( new TransferHandler("row") );
		sp = new JScrollPane(playerTable);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		sp.setPreferredSize(new Dimension(350, 475));
		
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("Player List");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		add(sp);
		add(btnAdd);
		add(btnEdit);
		add(btnDel);
		
//		add(testDrag);
		
//		btnAdd.addActionListener(new ActionListener() {			
//			public void actionPerformed(ActionEvent e) {
//				PlayerObject po = (PlayerObject) playerList.getSelectedValue();
//				OverviewListEvent ev = new OverviewListEvent(this, po);
//				//Get the player object chosen by user, pass it to the listevent class, 
//				//then Tactics frame can get it.
//				if(formListener != null) {
//					formListener.formEventOccured(ev);
//				}
//			}
//		});
		
	}	
	
	public void setButtonListener(OverviewListButtonListener listener) {
		this.buttonListener = listener;
	}

	public void setFormListener(OverviewListListener listener) {
		this.formListener = listener;
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if(buttonListener != null)
			try {
				buttonListener.textEmitted(clicked.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
	}
}
