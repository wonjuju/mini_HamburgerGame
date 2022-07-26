package view;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;
import javazoom.jl.player.MP3Player;
import model.Hamburger;
import model.HamburgerGameDAO;
import model.User;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Controller c = new Controller();
		MP3Player mp3 = new MP3Player();
		// Controller c = new Controller(null);
		Hamburger h1;
		HamburgerGameDAO dao = new HamburgerGameDAO();
		// uidao.insertUser("1234", "1234");
		// uidao.login("123", "123"); // true가 반환이 되면
		String recipe = "";
		String answer_input="";
		int input = 0;
		mp3.play("C:\\Users\\SMHRD\\Desktop\\JAVA\\MusicPlayer\\player\\Modern Attempt - TrackTribe.mp3");
		System.out.println("=================================");
		System.out.println();
		System.out.println("＊Hamburger GAME＊");
		System.out.println();

		while (true) {
			System.out.println("           Y ⊙  집게리아  ⊙ Y           ");
			while (true) {
				System.out.println();
				System.out.println("=======[1]회원가입 [2]로그인 [3]종료======= ");
				System.out.println();
				input = sc.nextInt();
				if (input <= 3 && input > 0) {
					break;
				}
				System.out.println();
				System.out.println("숫자를 잘못 입력했습니다.");
				System.out.println();
			}
			if (input == 1) {
				System.out.println("");
				System.out.print("아이디 설정: ");
				String id = sc.next();
				System.out.print("비밀번호 설정: ");
				String password = sc.next();
				if (dao.insertUser(id, password) == true) {
					System.out.println();
					System.out.println("아이디가 생성되었습니다.");
					System.out.println("축하합니다!!! 저희 햄버거집에 고용되었습니다.");
					System.out.println();
					continue;
				} 

			} else if (input == 2) {
				// 로그인 
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String password = sc.next();
				User u = new User(id, password);
				if (c.login(u) == true) {
					System.out.println();
					System.out.println("로그인 성공!!!");
					System.out.println("====================");
					System.out.println();
					while (true) {
						System.out.println();
						System.out.print("어디갔다가 이제와!!!");
						System.out.println("빨리 일해! ");
						System.out.println(
								"=========================================================================================================");
						System.out.println("[1] 나만의 햄버거 레시피 만들기 [2] 출근(게임시작) [3] 랭킹확인 [4] 첫페이지로 돌아가기 [5] 퇴근(종료) ");
						System.out.println();

						int s3 = sc.nextInt();
						if (s3 == 1) {
							// 나만의 레시피 만들기를 선택한 후
							System.out.println();
							System.out.println("새로운 버거 만들어봐");
							System.out.println();
							do {
								recipe = "";
								c.ingredient();
								System.out.println();
								System.out.println("4~12개의 재료를 넣어 ");
								System.out.println("마지막은 무조건 정성  ");
								System.out.println("재료를  알파벳으로 한개씩 입력해");
								System.out.println("윗빵(번)");
								System.out.println();
								while (true) {

									char i = sc.next().charAt(0);
									if (i >= 'a' && i <= 'p' || i == 'z') {
										recipe += i;
										if (i >= 'a' && i <= 'p') {
											System.out.print(i + ". ");

											c.inName(i);
										}

										else if (i == 'z') {
											System.out.println("z. 정성♥ ");
											System.out.println();
											System.out.println(" 완료!!");
											System.out.println();
											break;
										}

									} else {
										recipe = "";
										System.out.println();
										System.out.println("으이구  알파벳 잘못 입력했잖아!");
										System.out.println();
										System.out.println("처음부터 다시해");
										System.out.println();
										System.out.println("4~12개의 재료를 넣어 ");
										System.out.println("마지막은 무조건 정성  ");
										System.out.println("재료를  알파벳으로 한개씩 입력해");
										System.out.println("윗빵(번)");
										System.out.println();
										continue;
									}

								}

							} while (recipe.length() < 4 || recipe.length() >= 14);

							// z 입력시 break;
							while (true) {
							System.out.println();
							System.out.println(" 햄버거 이름은 최대 15글자  ");
							System.out.println("※ 띄어쓰기 금지 ※");
							
							System.out.print("너가 만든 햄버거 이름은 : ");
							
							String menu = sc.next();
							if (menu.length()<16) {
								 h1 = new Hamburger(menu, recipe);
								break;
							} 
							}
							
					
							while (true) {
								System.out.println();
								System.out.println("진짜 출시 할꺼야??");
								System.out.println();
								System.out.print("넵 자신 있습니다   (1) / 죄송해요.. 다시 생각할게요    (0) >>");
								int an = sc.nextInt();
								if (an == 1) {
									c.insert(h1);
									System.out.println();
									System.out.println("Okay~ 메뉴에 추가했어");
									System.out.println();
									break;
								} else if (an == 0) {
									System.out.println();
									System.out.println("잘 생각했어 GOOD JOB!");
									System.out.println();
									break;
								} else {
									System.out.println();
									System.out.println("으이구  숫자 잘못 입력했잖아!");
									System.out.println();
								}
							}
					
						} else if (s3 == 2) {
							// 게임 진행
							String answer = null;
							// 수정
							int count = 0;
							int salary = 7000;
							System.out.println("===========================");
							System.out.println("난이도를 선택해주세요");

							System.out.println("[1] 3일차  [2] 3개월차  [3] 3년차");
							System.out.println();

							int s4 = sc.nextInt();
							int num = 0;
							while (count != 10) {
								num++;
								if (salary <= 0 && count==10) {
									count = 10;
									System.out.println();
									System.out.println("GAME OVER");
									System.out.println("너 나가!");
									System.out.println("다음 급여에서 깔거야!");
									System.out.println();
									break;

								}
								switch (s4) {
								case 1: {
									System.out.print("[" + num+"번 주문] ");
									c.easyGame();
									Thread.sleep(5000);
									cls();
									answer = c.easySol();
									count++;
									break;

								}
								case 2: {
									count++;
									System.out.print("[" + num+"번 주문] ");
									c.normalGame();
									Thread.sleep(8000);
									cls();
									answer = c.norSol();

									break;

								}
								case 3: {
									count++;
									System.out.print("[" + num+"번 주문] ");
									c.hardGame();
									Thread.sleep(10000);
									cls();
									answer = c.hardSol();
									break;
								}
								default: {
									System.out.println();
									System.out.println("으이구  숫자 잘못 입력했잖아!");
									System.out.println();
								}

								}
								System.out.println("자 다 외웠지? 이제 만들어봐! ");
								c.ingredient();
								System.out.println();
								System.out.print("버거 제조 시작 : ");
								
								// 전역변수
								answer_input = sc.next();

								salary = c.score(answer_input, s4, salary);

								

							}
							User u0 = new User(u.getId(), salary);
							if (c.getId(u0).equals("1")) {
					
								int finalScore = c.getScore(u0)+salary;
								User u1 = new User(u.getId(), finalScore);
								c.updateScore(u1);
								
								
							}else{
								
							    c.addScore(u0);
							}
	
									
								}else if (s3 == 3) {
							// 랭킹 보기

							System.out.println("☆★☆★☆★☆★☆★☆★우수 사원☆★☆★☆★☆★☆★☆★");
							System.out.println();
							System.out.println("☆RANK☆" + "\t" + "  ☆ID☆" + "\t" + "☆SALARY☆");

							c.rankTop10();

							System.out.println(".");
							System.out.println(".");
							System.out.println(".");
							System.out.println();
							System.out.println("☆★☆★☆★☆★☆★☆★나의 순위☆★☆★☆★☆★☆★☆★");

							System.out.println("☆RANK☆" + "\t" + "  ☆ID☆" + "\t" + "☆SALARY☆");

							// 임의로 test넣어놨어요
//					      String u.get = u.getId();

							c.rankMine(u.getId());

						} else if (s3 == 4) {
							break;

						} else if (s3 == 5) {
							System.out.println("| 퇴근하겟슴니다  | ");
							System.out.println("￣￣￣￣∨￣￣￣￣");
							System.out.println("　　　 ∧_,,∧");
							System.out.println("　    (  n_n  )");
							System.out.println("　　　Ｕθ Ｕ");
							System.out.println("　／￣￣｜￣￣＼");
							System.out.println("|二二二二二二二|");
							System.out.println("｜　　 　　　 ｜");
							System.out.println("찰칵       찰칵   찰");
							System.out.println("　 ∧∧└　∧∧     칵");
							System.out.println("　(　　)】 (　　)】");
							System.out.println("　/　/┘　/　/┘");
							System.out.println("ノ￣ヽ　ノ￣ヽ{ 양심은 있나요?!");
							Thread.sleep(5000);
							System.exit(0);
						} else {
							// 로그인 후 숫자 잘못 입력시 
							System.out.println();
							System.out.println("으이구 ๑ `⌃´ ๑  숫자 잘못 입력했잖아!");
							System.out.println();

						}
					
				} 
				}else {
					System.out.println();
					System.out.println("으이구 ๑ `⌃´ ๑   잘못 입력했잖아!");
				
				}}else {
					
						System.out.println("| 종료하겟슴니다  | ");
						System.out.println("￣￣￣￣∨￣￣￣￣");
						System.out.println("　　　 ∧_,,∧");
						System.out.println("　    (  n_n  )");
						System.out.println("　　　Ｕθ Ｕ");
						System.out.println("　／￣￣｜￣￣＼");
						System.out.println("|二二二二二二二|");
						System.out.println("｜　　 　　　 ｜");
						System.out.println("찰칵       찰칵   찰");
						System.out.println("　 ∧∧└　∧∧     칵");
						System.out.println("　(　　)】 (　　)】");
						System.out.println("　/　/┘　/　/┘");
						System.out.println("ノ￣ヽ　ノ￣ヽ{ 양심은 있나요?!");
						Thread.sleep(5000);
						System.exit(0);
					
					
				} 



	

	}
		}

	public static void cls() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception E) {
			System.out.println(E);
		}

	}
}
	
	