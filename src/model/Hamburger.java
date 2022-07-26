package model;

public class Hamburger {
	// 데이터를 저장하게끔 설계하는 설계도
	private String recipe;
	private String menu;
	private int rank;
	private String id;
	private int score;
	private int e_num;
	public int s3 ;

	public Hamburger(int e_num, String menu, String recipe) {
		this.e_num = e_num;
		this.menu = menu;
		this.recipe = recipe;
	}

	
	
	
	public Hamburger(String menu, String recipe) {
		this.recipe = recipe;
		this.menu = menu;
		this.rank = rank;
		this.id = id;
		this.score = score;
	}

	public Hamburger(int rank, String id, int score) {

		this.rank = rank;
		this.id = id;
		this.score = score;
	}

	public Hamburger(int s3) {
		this.s3 = s3;
	}
	public int getSelect() {
		return s3;
	}
	public String getRecipe() {
		return recipe;
	}

	public String getMenu() {
		return menu;
	}

	public int getRank() {
		return rank;
	}

	public String getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public int getE_num() {
		return e_num;
	}


}
