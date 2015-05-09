/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class TacticsBoardSystem 
{
	public static ArrayList poList = new ArrayList();	//Collect the Player Object
	public static int[] subList = {0, 0, 0, 0, 0, 0, 0};	//Collect the information of substitutes
	public static String[] subNameList = {" ", " ", " ", " ", " ", " ", " "};	//Collect the name of substitutes
	public static int[] stList = {0, 0, 0, 0, 0};
	public static int[] amList = {0, 0, 0, 0, 0};
	public static int[] mList = {0, 0, 0, 0, 0};
	public static int[] dmList = {0, 0, 0, 0, 0};
	public static int[] dList = {0, 0, 0, 0, 0};
	public static int[] swList = {0, 0, 0};
	public static int gkList = 0;	//Lists above are to collect the information of formation
	public static String[] stNameList = {" ", " ", " ", " ", " "};
	public static String[] amNameList = {" ", " ", " ", " ", " "};
	public static String[] mNameList = {" ", " ", " ", " ", " "};
	public static String[] dmNameList = {" ", " ", " ", " ", " "};
	public static String[] dNameList = {" ", " ", " ", " ", " "};
	public static String[] swNameList = {" ", " ", " ", " ", " "};
	public static String gkNameList = " ";	//Lists above are to collect the name of positions in formation
	public static int[] stPosList = {0, 0, 0, 0, 0};
	public static int[] amPosList = {0, 0, 0, 0, 0};
	public static int[] mPosList = {0, 0, 0, 0, 0};
	public static int[] dmPosList = {0, 0, 0, 0, 0};
	public static int[] dPosList = {0, 0, 0, 0, 0};
	public static int[] swPosList = {0, 0, 0};
	public static int gkPosList = 0;	//Lists above are to collect the position level of formation
	
	public static void main(String[] args) {		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TacticsFrame();
			}			
		});		
		
	}
}