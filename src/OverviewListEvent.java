/*
* Zhichao Cao
* zc77@drexel.edu
* CS530:DUI, Project
*/

import java.util.EventObject;


public class OverviewListEvent extends EventObject {
	//Store the player object information
	private String name;	//Player name
	private int number;	//Player number
	private int age;	//Player age
	private int height;	//Player height
	private int weight;	//Player weight
	private int foot;	//Player foot, 1 means left only, 2 means right only, 3 means both
	private int gk;	//Goal Keeper position
                    //1 means ineffectual, 2 means unconvincing, 3 means competent
	private int sw; //Sweeper position, number definition is same as gk
	private int d; //Defender position, 
	   			   //1 means ineffectual, 2 means unconvincing, 3 means competent
	               //Forexample 213 means left side unconvincing, center side ineffectual, right side competent 
	private int wb;	//Wing Back position,
	                //1 means all sides ineffectual, 2 means all sides unconvincing, 3 means all sides competent	    
	private int dm;	//Defensive Midfielder position, number definition is same as gk
	private int m;	//Midfielder position, number definition is same as d
	private int am;	//Attacking Midfielder position, number definition is same as d
	private int st;	//Striker position, number definition is same as gk
	private PlayerObject po;

	public OverviewListEvent(Object source) {
		super(source);		
	}
	
	public OverviewListEvent(Object source, PlayerObject po) {
		super(source);		
		
		this.po = po;
	}
	
	public OverviewListEvent(Object source, String name, int number, int age, int height, int width, int foot, int gk, int sw, int d, int wb, int dm, int m, int am, int st) {
		super(source);		

		this.name = name;
		this.number = number;
		this.age = age;
		this.height = height;
		this.weight = width;
		this.foot = foot;
		this.gk = gk;
		this.sw = sw;
		this.d = d;
		this.wb = wb;
		this.dm = dm;
		this.m = m;
		this.am = am;
		this.st = st;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public int getAge() {
		return age;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public int getFoot() {
		return foot;
	}

	public int getGk() {
		return gk;
	}

	public int getSw() {
		return sw;
	}

	public int getD() {
		return d;
	}

	public int getWb() {
		return wb;
	}

	public int getDm() {
		return dm;
	}

	public int getM() {
		return m;
	}

	public int getAm() {
		return am;
	}

	public int getSt() {
		return st;
	}

	public PlayerObject getPo() {
		return po;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setFoot(int foot) {
		this.foot = foot;
	}

	public void setGk(int gk) {
		this.gk = gk;
	}

	public void setSw(int sw) {
		this.sw = sw;
	}

	public void setD(int d) {
		this.d = d;
	}

	public void setWb(int wb) {
		this.wb = wb;
	}

	public void setDm(int dm) {
		this.dm = dm;
	}

	public void setM(int m) {
		this.m = m;
	}

	public void setAm(int am) {
		this.am = am;
	}

	public void setSt(int st) {
		this.st = st;
	}

	public void setPo(PlayerObject po) {
		this.po = po;
	}	


}
