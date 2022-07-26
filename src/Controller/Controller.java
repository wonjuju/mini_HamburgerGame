package Controller;

import model.HamburgerGameDAO;
import model.Hamburger;
import model.User;
import java.util.ArrayList;

public class Controller {
	String recipe = "";
	String view_recipe = "";

	Hamburger h = null;
	Hamburger h1 = null;
	HamburgerGameDAO dao = new HamburgerGameDAO();
	User u = null;

	public void insert(Hamburger h) {
		// return row ---> int

		int row = dao.insert(h);

		if (row > 0) {
			System.out.println("데이터 추가 성공");

		} else {
			System.out.println("데이터 추가 실패 ");
		}
	}

	public void ingredient() {
		dao.ingredient();
	}

	public void inName(char i) {
		System.out.println(dao.inName(i));
	}

	public boolean login(User u) {
		return dao.login(u);
	}

	public void rankTop10() {

		ArrayList<Hamburger> listTop10 = dao.rankTop10();

		for (Hamburger h : listTop10) {

			System.out.println("  "+h.getRank() +"위" + "\t"+ "\t" + h.getId() + "\t" + "\t" + h.getScore()+"원");

		}

	}

	public void rankMine(String id) {

		Hamburger listMine = dao.rankMine(id);
		if (listMine == null) {
			System.out.println("점수가 없습니다.");

		} else {

			System.out.println("  "+listMine.getRank() + "위" +  "\t"+ "\t" + listMine.getId() + "\t" + "\t" + listMine.getScore()+"원");
			      
		}

	}

	public String easyGame() {
		//
//	      ArrayList<Hamburger> list = dao.easyList();
//	      for (Hamburger easy : list) {
//	         System.out.println(easy.getE_num()+"\t"+easy.getMenu()+"\t"+easy.getRecipe());
//	      }
		ArrayList<Hamburger> easyQ = dao.easyGame();
		for (Hamburger h : easyQ) {
			System.out.println("메뉴이름 : " + h.getMenu());
			System.out.print("레시피 : ");
			for (int i = 0; i < h.getRecipe().length(); i++) {
				char a = h.getRecipe().charAt(i);
				System.out.print(dao.inName(a) + " ");
			}
			System.out.println();
			recipe = h.getRecipe();
		}
		return recipe;
	}

	public String easySol() {
		return recipe;
	}

	public String norSol() {
		return recipe;
	}

	public String hardSol() {
		return recipe;
	}

	public String normalGame() {
		ArrayList<Hamburger> normalQ = dao.normalGame();

		for (Hamburger h : normalQ) {
			System.out.println(h.getMenu());
			System.out.print("레시피 : ");
			for (int i = 0; i < h.getRecipe().length() ; i++) {
				char a = h.getRecipe().charAt(i);
				System.out.print(dao.inName(a) + " ");
			}
			System.out.println();
			recipe = h.getRecipe();
		}

		return recipe;

	}

	public String hardGame() {
		ArrayList<Hamburger> hardQ = dao.hardGame();
		for (Hamburger h : hardQ) {
			System.out.println(h.getMenu());
			System.out.print("레시피 : ");
			for (int i = 0; i < h.getRecipe().length() ; i++) {
				char a = h.getRecipe().charAt(i);
				System.out.print(dao.inName(a) + " ");
			}
			System.out.println();
			recipe = h.getRecipe();
		}

		return recipe;
	}

	public int score(String answer_input, int s3, int salary) {
		String answer = "";
		if (s3 == 1) {
			answer = easySol();
		} else if (s3 == 2) {
			answer = norSol();
		} else {
			answer = hardSol();
		}

		if (answer.equals(answer_input)) { // 1에 레시피의 정답이 들어갈거임

			if (s3 == 1) {

				answer = recipe;
				salary += 500 * 1; // 2에 난이도에 따른 차등 곱하기
			} else if (s3 == 2) {
				answer = recipe;
				salary += 500 * 2;
			} else {
				answer = recipe;
				salary += 500 * 3;
			}

			System.out.println("잘했는데 더 빨리 만들라구ㅡㅅㅡ^^");
			System.out.println("현재급여 : " +salary  );
			
		} else {
			if (s3 == 1) {
				salary -= 500 * 2; // 2에 난이도에 따른 차등 곱하기
				answer = recipe;
			} else if (s3 == 2) {
				answer = recipe;
				salary -= 500 * 3;
			} else {
				answer = recipe;
				salary -= 500 * 4;
			}

			System.out.println("너 가게 말아먹을래!  \n현재급여 :  " + salary );
		}
		return salary;
	}

	public String getId(User u) {
		String status = dao.getId(u);
		
		return status;
	}
	
	public int getScore(User u) {
		int score = dao.getScore(u);
		return score;
	}
	
	public void updateScore(User u) {
		int row = dao.updateScore(u);
		if(row >0 ) {
			System.out.println("데이터 업데이트 성공!");
		}else {
			System.out.println("데이터 업데이트 실패!");
		}
		
	}

	public void addScore(User u) {

		System.out.println(u.getId());
		int row = dao.addScore(u);

		if (row > 0) {
			
			System.out.println("아이디, 점수 데이터 추가 성공");

		} else {
			System.out.println("아이디, 점수 데이터 추가 실패");

		}
		}


}