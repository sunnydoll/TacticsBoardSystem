/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class TacticsFrame extends JFrame implements ActionListener {

	private TacticsBoardSystem tbs;
	private TacticsFrameToolbar toolbar;
	public OverviewPanel oPanel;
	private TeamInstructionsPanel tPanel;
	public PlayerObject poListPanel;

	public TacticsFrame()
	{
	   super("Tactics Window");
		
	   setLayout(new BorderLayout());
	   
	   toolbar = new TacticsFrameToolbar();
	   oPanel = new OverviewPanel();
	   tPanel = new TeamInstructionsPanel();
	   final OverviewListPanel listPanel = oPanel.listPanel;
	   final SpecificInstructionsPanel instPanel = tPanel.instPanel;
	   final TeamMemoPanel memoPanel = tPanel.memoPanel;
	   final InsFormationPanel insFormPanel = tPanel.formPanel;
	   final OverviewFormationPanel formationPanel = oPanel.formPanel;
	   final OverviewSubstitutesPanel subPanel = oPanel.subPanel;
	   
	   add(toolbar, BorderLayout.SOUTH);
	   
	   JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.NORTH);
	   tabbedPane.setBounds(0, 0, 445, 250);

	   tabbedPane.addTab("Overview", oPanel);
	   tabbedPane.addTab("Team Instructions", tPanel);

	   add(tabbedPane, BorderLayout.CENTER);

	   setSize(1000, 700);
	   setVisible(true);
	   setResizable(false);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   listPanel.setButtonListener(new OverviewListButtonListener() {
			public void textEmitted(String text) {
//				Listen to the buttons of ListPanel
				switch(text) {
					case "Add":
						poListPanel = new PlayerObject();
						new PlayerEdit(poListPanel, oPanel).setVisible(true);
						TacticsFrame.this.setVisible(false);
						break;
					case "Edit":
//						String playerNameEdit = "";
						int playerNameEdit = -1;
						try {
//							playerNameEdit = listPanel.playerList.getSelectedValue().toString();
							playerNameEdit = listPanel.playerTable.getSelectedRow();
						}
						catch (Exception e) {						
							JOptionPane.showMessageDialog(null, "Please pick one actor in actorlist to edit, Thanks! ");
							return;
						}
//						poListPanel = (PlayerObject) listPanel.playerList.getSelectedValue();
						poListPanel = (PlayerObject) tbs.poList.get(playerNameEdit);
						new PlayerEdit(poListPanel, oPanel).setVisible(true);
						TacticsFrame.this.setVisible(false);	
						break;
					case "Delete":
//						String actorNameDel = "";
						int actorNameDel = -1;
						try {
//							actorNameDel = listPanel.playerList.getSelectedValue().toString();
							actorNameDel = listPanel.playerTable.getSelectedRow();
						}
						catch (Exception e) {						
							JOptionPane.showMessageDialog(null, "Please pick one actor in actorlist to delete, Thanks! ");
							return;
						}
						if(JOptionPane.showConfirmDialog(null, "Confirm to delete?", "Confirm",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//							Message box that lets user confirm to delete the player 
//							poListPanel = (PlayerObject) listPanel.playerTable.getSelectedValue();
//							tbs.poList.remove(tbs.poList.indexOf(poListPanel));
//							listPanel.playerModel.removeElement(poListPanel);
//							listPanel.playerList.repaint();	//Refresh player list
							int j = listPanel.playerTable.getSelectedRow();
							int oldNumber = ((PlayerObject) tbs.poList.get(j)).getNumber();
							tbs.poList.remove(j);
							listPanel.tmd.removeRow(j);
							listPanel.playerTable.repaint();	//Refresh player list
							for(int i = 0; i < 7; i++) {
								if(tbs.subList[i] == oldNumber) {
									tbs.subList[i] = 0;
									tbs.subNameList[i] = " ";
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.stList[i] == oldNumber) {
									tbs.stList[i] = 0;
									tbs.stNameList[i] = " ";
									tbs.stPosList[i] = 0;
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.amList[i] == oldNumber) {
									tbs.amList[i] = 0;
									tbs.amNameList[i] = " ";
									tbs.amPosList[i] = 0;
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.mList[i] == oldNumber) {
									tbs.mList[i] = 0;
									tbs.mNameList[i] = " ";
									tbs.mPosList[i] = 0;
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.dmList[i] == oldNumber) {
									tbs.dmList[i] = 0;
									tbs.dmNameList[i] = " ";
									tbs.dmPosList[i] = 0;
								}
							}
							for(int i = 0; i < 5; i++) {
								if(tbs.dList[i] == oldNumber) {
									tbs.dList[i] = 0;
									tbs.dNameList[i] = " ";
									tbs.dPosList[i] = 0;
								}
							}
							for(int i = 0; i < 3; i++) {
								if(tbs.swList[i] == oldNumber) {
									tbs.swList[i] = 0;
									tbs.swNameList[i] = " ";
									tbs.swPosList[i] = 0;
								}
							}
							if(tbs.gkList == oldNumber) {
								tbs.gkList = 0;
								tbs.gkNameList = " ";
								tbs.gkPosList = 0;
							}
							formationPanel.drawing();
							subPanel.drawing();
							insFormPanel.drawing();
						}
						else {
							return;
						}
						break;
				}
			}			
		});		
	   
	   toolbar.setButtonListener(new OverviewListButtonListener() {
		   public void textEmitted(String text) throws Exception {
//				Listen to the buttons of ListPanel
			   File file = null;
			   int result = 0;
			   JFileChooser fileChooser = new JFileChooser() ;
				switch(text) {
					case "Save Tactics":
//						Document doc = new Document();
						fileChooser.setDialogTitle("Choose a File") ;
						result = fileChooser.showSaveDialog(null);
						if(result==JFileChooser.APPROVE_OPTION) {
							file = fileChooser.getSelectedFile();
//							System.out.print(head);
//							System.out.print(memoPanel.memoArea.getText());
							DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
							DocumentBuilder builder = null;
							try { 
							    builder = dbf.newDocumentBuilder(); 
							}
							catch (Exception e) { } 
						    Document doc = builder.newDocument(); 
						    Element root = doc.createElement("TacticsFormation"); 
						    doc.appendChild(root);
						    for(int i = 0; i < tbs.poList.size(); i++) {
						    	PlayerObject po = ((PlayerObject) tbs.poList.get(i));
						    	Element el = doc.createElement("Player");
						    	el.setAttribute("Number", String.valueOf(po.getNumber()));
						    	root.appendChild(el);
						    	
						    	Element name = doc.createElement("Name");
						    	el.appendChild(name);
						    	Text tname = doc.createTextNode(po.getName());
						    	name.appendChild(tname);
						    	
						    	Element age = doc.createElement("Age");
						    	el.appendChild(age);
						    	Text tage = doc.createTextNode(String.valueOf(po.getAge()));
						    	age.appendChild(tage);
						    	
						    	Element height = doc.createElement("Height");
						    	el.appendChild(height);
						    	Text theight = doc.createTextNode(String.valueOf(po.getHeight()));
						    	height.appendChild(theight);
						    	
						    	Element weight = doc.createElement("Weight");
						    	el.appendChild(weight);
						    	Text tweight = doc.createTextNode(String.valueOf(po.getWeight()));
						    	weight.appendChild(tweight);
						    	
						    	Element foot = doc.createElement("Foot");
						    	el.appendChild(foot);
						    	Text tfoot = doc.createTextNode(String.valueOf(po.getFoot()));
						    	foot.appendChild(tfoot);
						    	
						    	Element gk = doc.createElement("gk");
						    	el.appendChild(gk);
						    	Text tgk = doc.createTextNode(String.valueOf(po.getGk()));
						    	gk.appendChild(tgk);
						    	
						    	Element sw = doc.createElement("sw");
						    	el.appendChild(sw);
						    	Text tsw = doc.createTextNode(String.valueOf(po.getSw()));
						    	sw.appendChild(tsw);
						    	
						    	Element d = doc.createElement("d");
						    	el.appendChild(d);
						    	Text td = doc.createTextNode(String.valueOf(po.getD()));
						    	d.appendChild(td);
						    	
						    	Element wb = doc.createElement("wb");
						    	el.appendChild(wb);
						    	Text twb = doc.createTextNode(String.valueOf(po.getWb()));
						    	wb.appendChild(twb);
						    	
						    	Element dm = doc.createElement("dm");
						    	el.appendChild(dm);
						    	Text tdm = doc.createTextNode(String.valueOf(po.getDm()));
						    	dm.appendChild(tdm);
						    	
						    	Element m = doc.createElement("m");
						    	el.appendChild(m);
						    	Text tm = doc.createTextNode(String.valueOf(po.getM()));
						    	m.appendChild(tm);
						    	
						    	Element am = doc.createElement("am");
						    	el.appendChild(am);
						    	Text tam = doc.createTextNode(String.valueOf(po.getAm()));
						    	am.appendChild(tam);
						    	
						    	Element st = doc.createElement("st");
						    	el.appendChild(st);
						    	Text tst = doc.createTextNode(String.valueOf(po.getSt()));
						    	st.appendChild(tst);						    	
						    }
						    
//						    Element form = doc.createElement("Formation");
//						    root.appendChild(form);
					    	Element subList = doc.createElement("subList");
					    	String subListStr = "";
					    	for(int i = 0; i < 7; i++) {
					    		subListStr += String.valueOf(tbs.subList[i]);
					    		if(i < 6) {
					    			subListStr += ",";
					    		}
					    	}
					    	subList.setAttribute("subList", subListStr);
					    	root.appendChild(subList);
					    	
					    	Element subNameList = doc.createElement("subNameList");
					    	String subNameListStr = "";
					    	for(int i = 0; i < 7; i++) {
					    		subNameListStr += tbs.subNameList[i];
					    		if(i < 6) {
					    			subNameListStr += ",";
					    		}
					    	}
					    	subNameList.setAttribute("subNameList", subNameListStr);
					    	root.appendChild(subNameList);
					    	
					    	Element stList = doc.createElement("stList");
					    	String stStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		stStr += String.valueOf(tbs.stList[i]);
					    		if(i < 4) {
					    			stStr += ",";
					    		}
					    	}
					    	stList.setAttribute("stList", stStr);
					    	root.appendChild(stList);					    	
					    	Element stNameList = doc.createElement("stNameList");
					    	String stNameStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		stNameStr += String.valueOf(tbs.stNameList[i]);
					    		if(i < 4) {
					    			stNameStr += ",";
					    		}
					    	}
					    	stNameList.setAttribute("stNameList", stNameStr);
					    	root.appendChild(stNameList);
					    	Element stPosList = doc.createElement("stPosList");
					    	String stPosStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		stPosStr += String.valueOf(tbs.stPosList[i]);
					    		if(i < 4) {
					    			stPosStr += ",";
					    		}
					    	}
					    	stPosList.setAttribute("stPosList", stPosStr);
					    	root.appendChild(stPosList);
					    	
					    	Element amList = doc.createElement("amList");
					    	String amStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		amStr += String.valueOf(tbs.amList[i]);
					    		if(i < 4) {
					    			amStr += ",";
					    		}
					    	}
					    	amList.setAttribute("amList", amStr);
					    	root.appendChild(amList);
					    	Element amNameList = doc.createElement("amNameList");
					    	String amNameStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		amNameStr += String.valueOf(tbs.amNameList[i]);
					    		if(i < 4) {
					    			amNameStr += ",";
					    		}
					    	}
					    	amNameList.setAttribute("amNameList", amNameStr);
					    	root.appendChild(amNameList);
					    	Element amPosList = doc.createElement("amPosList");
					    	String amPosStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		amPosStr += String.valueOf(tbs.amPosList[i]);
					    		if(i < 4) {
					    			amPosStr += ",";
					    		}
					    	}
					    	amPosList.setAttribute("amPosList", amPosStr);
					    	root.appendChild(amPosList);
					    	
					    	Element mList = doc.createElement("mList");
					    	String mStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		mStr += String.valueOf(tbs.mList[i]);
					    		if(i < 4) {
					    			mStr += ",";
					    		}
					    	}
					    	mList.setAttribute("mList", mStr);
					    	root.appendChild(mList);
					    	Element mNameList = doc.createElement("mNameList");
					    	String mNameStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		mNameStr += String.valueOf(tbs.mNameList[i]);
					    		if(i < 4) {
					    			mNameStr += ",";
					    		}
					    	}
					    	mNameList.setAttribute("mNameList", mNameStr);
					    	root.appendChild(mNameList);
					    	Element mPosList = doc.createElement("mPosList");
					    	String mPosStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		mPosStr += String.valueOf(tbs.mPosList[i]);
					    		if(i < 4) {
					    			mPosStr += ",";
					    		}
					    	}
					    	mPosList.setAttribute("mPosList", mPosStr);
					    	root.appendChild(mPosList);
					    	
					    	Element dmList = doc.createElement("dmList");
					    	String dmStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		dmStr += String.valueOf(tbs.dmList[i]);
					    		if(i < 4) {
					    			dmStr += ",";
					    		}
					    	}
					    	dmList.setAttribute("dmList", dmStr);
					    	root.appendChild(dmList);
					    	Element dmNameList = doc.createElement("dmNameList");
					    	String dmNameStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		dmNameStr += String.valueOf(tbs.dmNameList[i]);
					    		if(i < 4) {
					    			dmNameStr += ",";
					    		}
					    	}
					    	dmNameList.setAttribute("dmNameList", dmNameStr);
					    	root.appendChild(dmNameList);
					    	Element dmPosList = doc.createElement("dmPosList");
					    	String dmPosStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		dmPosStr += String.valueOf(tbs.dmPosList[i]);
					    		if(i < 4) {
					    			dmPosStr += ",";
					    		}
					    	}
					    	dmPosList.setAttribute("dmPosList", dmPosStr);
					    	root.appendChild(dmPosList);
					    	
					    	Element dList = doc.createElement("dList");
					    	String dStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		dStr += String.valueOf(tbs.dList[i]);
					    		if(i < 4) {
					    			dStr += ",";
					    		}
					    	}
					    	dList.setAttribute("dList", dStr);
					    	root.appendChild(dList);
					    	Element dNameList = doc.createElement("dNameList");
					    	String dNameStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		dNameStr += String.valueOf(tbs.dNameList[i]);
					    		if(i < 4) {
					    			dNameStr += ",";
					    		}
					    	}
					    	dNameList.setAttribute("dNameList", dNameStr);
					    	root.appendChild(dNameList);
					    	Element dPosList = doc.createElement("dPosList");
					    	String dPosStr = "";
					    	for(int i = 0; i < 5; i++) {
					    		dPosStr += String.valueOf(tbs.dPosList[i]);
					    		if(i < 4) {
					    			dPosStr += ",";
					    		}
					    	}
					    	dPosList.setAttribute("dPosList", dPosStr);
					    	root.appendChild(dPosList);
					    	
					    	Element swList = doc.createElement("swList");
					    	String swStr = "";
					    	for(int i = 0; i < 3; i++) {
					    		swStr += String.valueOf(tbs.swList[i]);
					    		if(i < 2) {
					    			swStr += ",";
					    		}
					    	}
					    	swList.setAttribute("swList", swStr);
					    	root.appendChild(swList);
					    	Element swNameList = doc.createElement("swNameList");
					    	String swNameStr = "";
					    	for(int i = 0; i < 3; i++) {
					    		swNameStr += String.valueOf(tbs.swNameList[i]);
					    		if(i < 2) {
					    			swNameStr += ",";
					    		}
					    	}
					    	swNameList.setAttribute("swNameList", swNameStr);
					    	root.appendChild(swNameList);
					    	Element swPosList = doc.createElement("swPosList");
					    	String swPosStr = "";
					    	for(int i = 0; i < 3; i++) {
					    		swPosStr += String.valueOf(tbs.swPosList[i]);
					    		if(i < 2) {
					    			swPosStr += ",";
					    		}
					    	}
					    	swPosList.setAttribute("swPosList", swPosStr);
					    	root.appendChild(swPosList);
					    	
					    	Element gkList = doc.createElement("gkList");
					    	String gkStr = String.valueOf(tbs.gkList);
					    	gkList.setAttribute("gkList", gkStr);
					    	root.appendChild(gkList);
					    	Element gkNameList = doc.createElement("gkNameList");
					    	String gkNameStr = String.valueOf(tbs.gkNameList);
					    	gkNameList.setAttribute("gkNameList", gkNameStr);
					    	root.appendChild(gkNameList);
					    	Element gkPosList = doc.createElement("gkPosList");
					    	String gkPosStr = String.valueOf(tbs.gkPosList);
					    	gkPosList.setAttribute("gkPosList", gkPosStr);
					    	root.appendChild(gkPosList);
					    	
					    	Element memoArea = doc.createElement("memoArea");
					    	memoArea.setAttribute("memoArea", memoPanel.memoArea.getText());
					    	root.appendChild(memoArea);
					    	
					    	Element straList = doc.createElement("straList");
					    	straList.setAttribute("straList", instPanel.straList.getSelectedItem().toString());
					    	root.appendChild(straList);
					    	
					    	Element passList = doc.createElement("passList");
					    	passList.setAttribute("passList", instPanel.passList.getSelectedItem().toString());
					    	root.appendChild(passList);
					    	
					    	Element creatList = doc.createElement("creatList");
					    	creatList.setAttribute("creatList", instPanel.creatList.getSelectedItem().toString());
					    	root.appendChild(creatList);
					    	
					    	Element closList = doc.createElement("closList");
					    	closList.setAttribute("closList", instPanel.closList.getSelectedItem().toString());
					    	root.appendChild(closList);
					    	
					    	Element tackleList = doc.createElement("tackleList");
					    	tackleList.setAttribute("tackleList", instPanel.tackleList.getSelectedItem().toString());
					    	root.appendChild(tackleList);
					    	
					    	Element markList = doc.createElement("markList");
					    	markList.setAttribute("markList", instPanel.markList.getSelectedItem().toString());
					    	root.appendChild(markList);
					    	
					    	Element crossList = doc.createElement("crossList");
					    	crossList.setAttribute("crossList", instPanel.crossList.getSelectedItem().toString());
					    	root.appendChild(crossList);
					    	
					    	Element roamList = doc.createElement("roamList");
					    	roamList.setAttribute("roamList", instPanel.roamList.getSelectedItem().toString());
					    	root.appendChild(roamList);
					    	
					    	Element deflineSlider = doc.createElement("deflineSlider");
					    	deflineSlider.setAttribute("deflineSlider", String.valueOf(instPanel.deflineSlider.getValue()));
					    	root.appendChild(deflineSlider);
					    	
					    	Element tempoSlider = doc.createElement("tempoSlider");
					    	tempoSlider.setAttribute("tempoSlider", String.valueOf(instPanel.tempoSlider.getValue()));
					    	root.appendChild(tempoSlider);
					    	
					    	Element focusList = doc.createElement("focusList");
					    	focusList.setAttribute("focusList", instPanel.focusList.getSelectedItem().toString());
					    	root.appendChild(focusList);
					    	
					    	Element countList = doc.createElement("countList");
					    	countList.setAttribute("countList", instPanel.countList.getSelectedItem().toString());
					    	root.appendChild(countList);
					    	
					    	Element offsList = doc.createElement("offsList");
					    	offsList.setAttribute("offsList", instPanel.offsList.getSelectedItem().toString());
					    	root.appendChild(offsList);
					    	
						    try {						    	 
						    	FileOutputStream fos = new FileOutputStream(file.getPath());
						    	OutputStreamWriter outwriter = new OutputStreamWriter(fos);
						    	// ((XmlDocument)doc).write(outwriter); //Error!
						    	callWriteXmlFile(doc, outwriter, "gb2312");
						    	outwriter.close();
						    	fos.close();
						    }
						    catch (Exception e) {						    	 
						    	e.printStackTrace();
						    }						     
						}
						break;
					case "Load Tactics":
						tbs.poList.clear();
						fileChooser.setDialogTitle("Choose a File") ;
						result = fileChooser.showSaveDialog(null);
						if(result==JFileChooser.APPROVE_OPTION) {
							file = fileChooser.getSelectedFile();
							DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
							DocumentBuilder builder = dbf.newDocumentBuilder();
							Document doc = builder.parse(file);
							Element root = doc.getDocumentElement(); // Get Root
							NodeList player = root.getElementsByTagName("Player");
							for (int i = 0; i < player.getLength(); i++) {
								Element p = (Element) player.item(i);
								PlayerObject po = new PlayerObject();
								po.setNumber(Integer.parseInt(p.getAttribute("Number")));
								NodeList list = p.getElementsByTagName("Name");
								Element e = (Element) list.item(0);
								Node t = e.getFirstChild();
								po.setName(t.getNodeValue());
								list = p.getElementsByTagName("Age");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setAge(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("Height");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setHeight(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("Weight");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setWeight(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("Foot");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setFoot(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("gk");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setGk(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("sw");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setSw(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("d");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setD(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("wb");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setWb(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("dm");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setDm(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("m");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setM(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("am");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setAm(Integer.parseInt(t.getNodeValue()));
								list = p.getElementsByTagName("st");
								e = (Element) list.item(0);
								t = e.getFirstChild();
								po.setSt(Integer.parseInt(t.getNodeValue()));
								tbs.poList.add(po);
							}
							
							NodeList form = root.getElementsByTagName("subList");
							Element f = (Element) form.item(0);
							String subListStr[] = f.getAttribute("subList").split(",");
							for(int i = 0; i < 7; i++) {
								tbs.subList[i] = Integer.parseInt(subListStr[i]);
							}
							form = root.getElementsByTagName("subNameList");
							f = (Element) form.item(0);
							String subNameListStr[] = f.getAttribute("subNameList").split(",");
							for(int i = 0; i < 7; i++) {
								tbs.subNameList[i] = subNameListStr[i];
							}
							
							form = root.getElementsByTagName("stList");
							f = (Element) form.item(0);
							String stStr[] = f.getAttribute("stList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.stList[i] = Integer.parseInt(stStr[i]);
							}
							form = root.getElementsByTagName("stNameList");
							f = (Element) form.item(0);
							String stNameStr[] = f.getAttribute("stNameList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.stNameList[i] = stNameStr[i];
							}
							form = root.getElementsByTagName("stPosList");
							f = (Element) form.item(0);
							String stPosStr[] = f.getAttribute("stPosList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.stPosList[i] = Integer.parseInt(stPosStr[i]);
							}
							
							form = root.getElementsByTagName("amList");
							f = (Element) form.item(0);
							String amStr[] = f.getAttribute("amList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.amList[i] = Integer.parseInt(amStr[i]);
							}
							form = root.getElementsByTagName("amNameList");
							f = (Element) form.item(0);
							String amNameStr[] = f.getAttribute("amNameList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.amNameList[i] = amNameStr[i];
							}
							form = root.getElementsByTagName("amPosList");
							f = (Element) form.item(0);
							String amPosStr[] = f.getAttribute("amPosList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.amPosList[i] = Integer.parseInt(amPosStr[i]);
							}
							
							form = root.getElementsByTagName("mList");
							f = (Element) form.item(0);
							String mStr[] = f.getAttribute("mList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.mList[i] = Integer.parseInt(mStr[i]);
							}
							form = root.getElementsByTagName("mNameList");
							f = (Element) form.item(0);
							String mNameStr[] = f.getAttribute("mNameList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.mNameList[i] = mNameStr[i];
							}
							form = root.getElementsByTagName("mPosList");
							f = (Element) form.item(0);
							String mPosStr[] = f.getAttribute("mPosList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.mPosList[i] = Integer.parseInt(mPosStr[i]);
							}
							
							form = root.getElementsByTagName("dmList");
							f = (Element) form.item(0);
							String dmStr[] = f.getAttribute("dmList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.dmList[i] = Integer.parseInt(dmStr[i]);
							}
							form = root.getElementsByTagName("dmNameList");
							f = (Element) form.item(0);
							String dmNameStr[] = f.getAttribute("dmNameList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.dmNameList[i] = dmNameStr[i];
							}
							form = root.getElementsByTagName("dmPosList");
							f = (Element) form.item(0);
							String dmPosStr[] = f.getAttribute("dmPosList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.dmPosList[i] = Integer.parseInt(dmPosStr[i]);
							}
							
							form = root.getElementsByTagName("dList");
							f = (Element) form.item(0);
							String dStr[] = f.getAttribute("dList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.dList[i] = Integer.parseInt(dStr[i]);
							}
							form = root.getElementsByTagName("dNameList");
							f = (Element) form.item(0);
							String dNameStr[] = f.getAttribute("dNameList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.dNameList[i] = dNameStr[i];
							}
							form = root.getElementsByTagName("dPosList");
							f = (Element) form.item(0);
							String dPosStr[] = f.getAttribute("dPosList").split(",");
							for(int i = 0; i < 5; i++) {
								tbs.dPosList[i] = Integer.parseInt(dPosStr[i]);
							}
							
							form = root.getElementsByTagName("swList");
							f = (Element) form.item(0);
							String swStr[] = f.getAttribute("swList").split(",");
							for(int i = 0; i < 3; i++) {
								tbs.swList[i] = Integer.parseInt(swStr[i]);
							}
							form = root.getElementsByTagName("swNameList");
							f = (Element) form.item(0);
							String swNameStr[] = f.getAttribute("swNameList").split(",");
							for(int i = 0; i < 3; i++) {
								tbs.swNameList[i] = swNameStr[i];
							}
							form = root.getElementsByTagName("swPosList");
							f = (Element) form.item(0);
							String swPosStr[] = f.getAttribute("swPosList").split(",");
							for(int i = 0; i < 3; i++) {
								tbs.swPosList[i] = Integer.parseInt(swPosStr[i]);
							}
							
							form = root.getElementsByTagName("gkList");
							f = (Element) form.item(0);
							String gkStr = f.getAttribute("gkList");
							tbs.gkList = Integer.parseInt(gkStr);
							form = root.getElementsByTagName("gkNameList");
							f = (Element) form.item(0);
							String gkNameStr = f.getAttribute("gkNameList");
							tbs.gkNameList = gkNameStr;
							form = root.getElementsByTagName("gkPosList");
							f = (Element) form.item(0);
							String gkPosStr = f.getAttribute("gkPosList");
							tbs.gkPosList = Integer.parseInt(gkPosStr);	
							
							if(tbs.subNameList[0].length() == 1)
								subPanel.firstLabel.setText("Empty");
							else
								subPanel.firstLabel.setText(tbs.subNameList[0]);
							if(tbs.subNameList[1].length() == 1)
								subPanel.secondLabel.setText("Empty");
							else
								subPanel.secondLabel.setText(tbs.subNameList[1]);
							if(tbs.subNameList[2].length() == 1)
								subPanel.thirdLabel.setText("Empty");
							else
								subPanel.thirdLabel.setText(tbs.subNameList[2]);
							if(tbs.subNameList[3].length() == 1)
								subPanel.fourthLabel.setText("Empty");
							else
								subPanel.fourthLabel.setText(tbs.subNameList[3]);
							if(tbs.subNameList[4].length() == 1)
								subPanel.fifthLabel.setText("Empty");
							else
								subPanel.fifthLabel.setText(tbs.subNameList[4]);
							if(tbs.subNameList[5].length() == 1)
								subPanel.sixthLabel.setText("Empty");
							else
								subPanel.sixthLabel.setText(tbs.subNameList[5]);
							if(tbs.subNameList[6].length() == 1)
								subPanel.seventhLabel.setText("Empty");
							else
								subPanel.seventhLabel.setText(tbs.subNameList[6]);
							
							if(tbs.gkNameList.length() == 1)
								formationPanel.gkOne.setText("Empty");
							else
								formationPanel.gkOne.setText(tbs.gkNameList);
							
							if(tbs.swNameList[0].length() == 1)
								formationPanel.swOne.setText("Empty");
							else
								formationPanel.swOne.setText(tbs.swNameList[0]);
							if(tbs.swNameList[1].length() == 1)
								formationPanel.swTwo.setText("Empty");
							else
								formationPanel.swTwo.setText(tbs.swNameList[1]);
							if(tbs.swNameList[2].length() == 1)
								formationPanel.swThree.setText("Empty");
							else
								formationPanel.swThree.setText(tbs.swNameList[2]);
							
							if(tbs.dNameList[0].length() == 1)
								formationPanel.dOne.setText("Empty");
							else
								formationPanel.dOne.setText(tbs.dNameList[0]);
							if(tbs.dNameList[1].length() == 1)
								formationPanel.dTwo.setText("Empty");
							else
								formationPanel.dTwo.setText(tbs.dNameList[1]);
							if(tbs.dNameList[2].length() == 1)
								formationPanel.dThree.setText("Empty");
							else
								formationPanel.dThree.setText(tbs.dNameList[2]);
							if(tbs.dNameList[3].length() == 1)
								formationPanel.dFour.setText("Empty");
							else
								formationPanel.dFour.setText(tbs.dNameList[3]);
							if(tbs.dNameList[4].length() == 1)
								formationPanel.dFive.setText("Empty");
							else
								formationPanel.dFive.setText(tbs.dNameList[4]);
							
							if(tbs.dmNameList[0].length() == 1)
								formationPanel.dmOne.setText("Empty");
							else
								formationPanel.dmOne.setText(tbs.dmNameList[0]);
							if(tbs.dmNameList[1].length() == 1)
								formationPanel.dmTwo.setText("Empty");
							else
								formationPanel.dmTwo.setText(tbs.dmNameList[1]);
							if(tbs.dmNameList[2].length() == 1)
								formationPanel.dmThree.setText("Empty");
							else
								formationPanel.dmThree.setText(tbs.dmNameList[2]);
							if(tbs.dmNameList[3].length() == 1)
								formationPanel.dmFour.setText("Empty");
							else
								formationPanel.dmFour.setText(tbs.dmNameList[3]);
							if(tbs.dmNameList[4].length() == 1)
								formationPanel.dmFive.setText("Empty");
							else
								formationPanel.dmFive.setText(tbs.dmNameList[4]);
							
							if(tbs.mNameList[0].length() == 1)
								formationPanel.mOne.setText("Empty");
							else
								formationPanel.mOne.setText(tbs.mNameList[0]);
							if(tbs.mNameList[1].length() == 1)
								formationPanel.mTwo.setText("Empty");
							else
								formationPanel.mTwo.setText(tbs.mNameList[1]);
							if(tbs.mNameList[2].length() == 1)
								formationPanel.mThree.setText("Empty");
							else
								formationPanel.mThree.setText(tbs.mNameList[2]);
							if(tbs.mNameList[3].length() == 1)
								formationPanel.mFour.setText("Empty");
							else
								formationPanel.mFour.setText(tbs.mNameList[3]);
							if(tbs.mNameList[4].length() == 1)
								formationPanel.mFive.setText("Empty");
							else
								formationPanel.mFive.setText(tbs.mNameList[4]);
							
							if(tbs.amNameList[0].length() == 1)
								formationPanel.amOne.setText("Empty");
							else
								formationPanel.amOne.setText(tbs.amNameList[0]);
							if(tbs.amNameList[1].length() == 1)
								formationPanel.amTwo.setText("Empty");
							else
								formationPanel.amTwo.setText(tbs.amNameList[1]);
							if(tbs.amNameList[2].length() == 1)
								formationPanel.amThree.setText("Empty");
							else
								formationPanel.amThree.setText(tbs.amNameList[2]);
							if(tbs.amNameList[3].length() == 1)
								formationPanel.amFour.setText("Empty");
							else
								formationPanel.amFour.setText(tbs.amNameList[3]);
							if(tbs.amNameList[4].length() == 1)
								formationPanel.amFive.setText("Empty");
							else
								formationPanel.amFive.setText(tbs.amNameList[4]);
							
							if(tbs.stNameList[0].length() == 1)
								formationPanel.stOne.setText("Empty");
							else
								formationPanel.stOne.setText(tbs.stNameList[0]);
							if(tbs.stNameList[1].length() == 1)
								formationPanel.stTwo.setText("Empty");
							else
								formationPanel.stTwo.setText(tbs.stNameList[1]);
							if(tbs.stNameList[2].length() == 1)
								formationPanel.stThree.setText("Empty");
							else
								formationPanel.stThree.setText(tbs.stNameList[2]);
							if(tbs.stNameList[3].length() == 1)
								formationPanel.stFour.setText("Empty");
							else
								formationPanel.stFour.setText(tbs.stNameList[3]);
							if(tbs.stNameList[4].length() == 1)
								formationPanel.stFive.setText("Empty");
							else
								formationPanel.stFive.setText(tbs.stNameList[4]);
							
							formationPanel.drawing();
							subPanel.drawing();
							insFormPanel.drawing();

							int count = 0;
							count = listPanel.tmd.getRowCount();
							for(int i = count - 1; i >= 0; i--) {
								listPanel.tmd.removeRow(i);
							}						
							if(tbs.poList.size() == 0) {
								listPanel.playerTable.setModel(listPanel.tmd);
							}
							else {	//If it has players added by user, it has to add them in player list
								for(int i = 0; i < tbs.poList.size(); i++) {
									String[] row = {((PlayerObject)tbs.poList.get(i)).getName() + ", Position: " + ((PlayerObject)tbs.poList.get(i)).getPositionString()};
									listPanel.tmd.addRow(row);
								}
								listPanel.playerTable.setModel(listPanel.tmd);
							}	
							
							form = root.getElementsByTagName("memoArea");
							f = (Element) form.item(0);
							memoPanel.memoArea.setText(f.getAttribute("memoArea"));
							
							form = root.getElementsByTagName("straList");
							f = (Element) form.item(0);
							instPanel.straList.setSelectedItem(f.getAttribute("straList"));
							
							form = root.getElementsByTagName("passList");
							f = (Element) form.item(0);
							instPanel.passList.setSelectedItem(f.getAttribute("passList"));
							
							form = root.getElementsByTagName("creatList");
							f = (Element) form.item(0);
							instPanel.creatList.setSelectedItem(f.getAttribute("creatList"));
							
							form = root.getElementsByTagName("closList");
							f = (Element) form.item(0);
							instPanel.closList.setSelectedItem(f.getAttribute("closList"));
							
							form = root.getElementsByTagName("tackleList");
							f = (Element) form.item(0);
							instPanel.tackleList.setSelectedItem(f.getAttribute("tackleList"));
							
							form = root.getElementsByTagName("markList");
							f = (Element) form.item(0);
							instPanel.markList.setSelectedItem(f.getAttribute("markList"));
							
							form = root.getElementsByTagName("crossList");
							f = (Element) form.item(0);
							instPanel.crossList.setSelectedItem(f.getAttribute("crossList"));
							
							form = root.getElementsByTagName("roamList");
							f = (Element) form.item(0);
							instPanel.roamList.setSelectedItem(f.getAttribute("roamList"));
							
							form = root.getElementsByTagName("deflineSlider");
							f = (Element) form.item(0);
							instPanel.deflineSlider.setValue((Integer.parseInt(f.getAttribute("deflineSlider"))));
							
							form = root.getElementsByTagName("tempoSlider");
							f = (Element) form.item(0);
							instPanel.tempoSlider.setValue((Integer.parseInt(f.getAttribute("tempoSlider"))));
							
							form = root.getElementsByTagName("focusList");
							f = (Element) form.item(0);
							instPanel.focusList.setSelectedItem(f.getAttribute("focusList"));
							
							form = root.getElementsByTagName("countList");
							f = (Element) form.item(0);
							instPanel.countList.setSelectedItem(f.getAttribute("countList"));
							
							form = root.getElementsByTagName("offsList");
							f = (Element) form.item(0);
							instPanel.offsList.setSelectedItem(f.getAttribute("offsList"));
						}
						break;
				}
			}			
	   });
	}
	
	public static void callWriteXmlFile(Document doc, Writer w, String encoding) { 
		try {
			Source source = new DOMSource(doc); 

		    Result result = new StreamResult(w); 

		    Transformer xformer = TransformerFactory.newInstance().newTransformer(); 
		    xformer.setOutputProperty(OutputKeys.ENCODING, encoding); 
		    xformer.transform(source, result); 
		} 
		catch (TransformerConfigurationException e) { 
		    e.printStackTrace(); 
		  } 
		catch (TransformerException e) { 
		    e.printStackTrace(); 
		} 
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}