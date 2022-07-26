package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class HamburgerGameDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	boolean check = false;
	Random rd = new Random();

	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "cgi_1_0418_3";
			String password = "smhrd3";
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getClose() {

		try {
			if (rs != null)
				rs.close();
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("자원반납시 생긴 오류!");
		}

	}

	public ArrayList<Hamburger> easyGame() {
		ArrayList<Hamburger> list = new ArrayList<Hamburger>();
		ArrayList<Hamburger> easyQ = new ArrayList<Hamburger>();
		try {
			getConn();

			String sql = "select * from easy";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int e_num = rs.getInt("e_num");
				String menu = rs.getString("menu");
				String recipe = rs.getString("recipe");

				Hamburger easy = new Hamburger(e_num, menu, recipe);
				list.add(easy);
			}
			int index = rd.nextInt(list.size() - 1) + 1;
			sql = "select menu, recipe from easy where e_num = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);
			rs = psmt.executeQuery();
			if (rs.next()) {
				String menu = rs.getString("menu");
				String recipe = rs.getString("recipe");

				Hamburger easyq = new Hamburger(menu, recipe);
				easyQ.add(easyq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패");
		} finally {
			getClose();
		}
		return easyQ;
	}

	public ArrayList<Hamburger> normalGame() {
		ArrayList<Hamburger> list = new ArrayList<Hamburger>();
		ArrayList<Hamburger> normalQ = new ArrayList<Hamburger>();
		try {
			getConn();
			String sql = "select * from normal";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int n_num = rs.getInt("n_num");
				String menu = rs.getString("menu");
				String recipe = rs.getString("recipe");
				// n_num 을 불러오는것 거기에 arraylist에 추가
				Hamburger normal = new Hamburger(n_num, menu, recipe);
				list.add(normal);
			}

			int index = rd.nextInt(list.size() - 1) + 1;
			sql = "select menu, recipe from normal where n_num = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String menu = rs.getString("menu");
				String recipe = rs.getString("recipe");

				Hamburger normalq = new Hamburger(menu, recipe);
				normalQ.add(normalq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패");
		} finally {
			getClose();
		}

		return normalQ;
	}

	public ArrayList<Hamburger> hardGame() {
		ArrayList<Hamburger> list = new ArrayList<Hamburger>();
		ArrayList<Hamburger> hardQ = new ArrayList<Hamburger>();
		try {
			getConn();
			String sql = "select * from hard";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int h_num = rs.getInt("h_num");
				String menu = rs.getString("menu");
				String recipe = rs.getString("recipe");

				Hamburger hard = new Hamburger(h_num, menu, recipe);
				list.add(hard);
			}
			int index = rd.nextInt(list.size() - 1) + 1;
			sql = "select menu, recipe from hard where h_num = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String menu = rs.getString("menu");
				String recipe = rs.getString("recipe");

				Hamburger hardq = new Hamburger(menu, recipe);
				hardQ.add(hardq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패");
		} finally {
			getClose();
		}

		return hardQ;
	}

	public boolean insertUser(String id, String pw) {

		try {
			getConn();

			String sql = "INSERT INTO member VALUES(?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			int cnt = psmt.executeUpdate();

			if (cnt > 0) {
				check = true;

			} else {
				check = false;
			}
		} catch (Exception e) {
			System.out.println("중복된 아이디거나 잘못된 형식입니다!");
		} finally {
			getClose();
		}
		return check;

	}

	public boolean login(User u) {
		boolean check1 = false;
		try {
			getConn();
			String sql = "SELECT ID, pw FROM member where id = ? and pw =?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, u.getId());
			psmt.setString(2, u.getPassword());

			rs = psmt.executeQuery();
			check1 = rs.next();

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return check1;
	}

	public void ingredient() {
		System.out.println("a. 토마토  b. 양상추  c. 양파  d. 버섯 ");
		System.out.println("e. 치즈  f.새우패티  g.소고기패티  h.베이컨");
		System.out.println("i. 피클 j.해시브라운 k.와사비 l.케찹");
		System.out.println("m. 렌치  n. 불고기소스 o. 할라피뇨 p. 치킨패티");
		System.out.println("마무리는 무조건 z. 정성♥ = 아래빵");
	}

	public String inName(char i) {
		if (i == 'a') {
			return "토마토";
		} else if (i == 'b') {
			return "양상추";
		} else if (i == 'c') {
			return "양파";
		} else if (i == 'd') {
			return "버섯";
		} else if (i == 'e') {
			return "치즈";
		} else if (i == 'f') {
			return "새우패티";
		} else if (i == 'g') {
			return "소고기패티";
		} else if (i == 'h') {
			return "베이컨";
		} else if (i == 'i') {
			return "피클";
		} else if (i == 'j') {
			return "해시브라운";
		} else if (i == 'k') {
			return "와사비";
		} else if (i == 'l') {
			return "케찹";
		} else if (i == 'm') {
			return "렌치";
		} else if (i == 'n') {
			return "불고기 소스";
		} else if (i == 'o') {
			return "할라피뇨";
		} else if (i == 'p') {
			return "치킨패티";
		} else {
			return "정성 ♥ (아랫빵)";
		}
	}

	public int insert(Hamburger h) {
		int row = 0;
		try {
			getConn();

			if (h.getRecipe().length() <= 6) {
				String sql = "insert into easy values (easy_enum_seq.nextval,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, h.getMenu());
				psmt.setString(2, h.getRecipe());
			} else if (h.getRecipe().length() <= 8) {
				String sql = "insert into normal values (normal_nnum_seq.nextval,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, h.getMenu());
				psmt.setString(2, h.getRecipe());
			} else {
				String sql = "insert into hard values (hard_hnum_seq.nextval,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, h.getMenu());
				psmt.setString(2, h.getRecipe());
			}
			row = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 베이스 연결 오류");
		} finally {
			// 자원을 반납하는 메소드
			getClose();
		}

		return row;
	}

	public void login(String id, String password) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Hamburger> rankTop10() {

		ArrayList<Hamburger> listTop10 = new ArrayList<Hamburger>();

		try

		{

			getConn();

			String sql = "SELECT * FROM rank_view where rownum<=10";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {

				int rank = rs.getInt("rank");
				String id = rs.getString("id");
				int score = rs.getInt("score");

				Hamburger h = new Hamburger(rank, id, score);
				listTop10.add(h);

			}

		}

		catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}

		return listTop10;

	}

	String id = "test";

	public Hamburger rankMine(String id) {

		Hamburger listMine = null;

		try {
			String sql = "SELECT * FROM rank_view WHERE id in (?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {

				int rank = rs.getInt("rank");
				id = rs.getString("id");
				int score = rs.getInt("score");

				listMine = new Hamburger(rank, id, score);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			getClose();

		}

		return listMine;
	}

	public int addScore(User u) {
		int row = 0;
		try {
			getConn();

			String sql = "insert into score values(?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u.getId());
			psmt.setInt(2, u.getSalary());

			row = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql오류");
		} finally {
			getClose();
		}
		return row;

	}

	public String getId(User u) {
		getConn();
		String id = null;
		String status = "0";
		try {
			String sql = "select id from score where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u.getId());
			rs = psmt.executeQuery();

			if (rs.next()) {
				//아이디가 있으면 상태 =  1을 돌려줌.
				status = "1";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 오류");
		} finally {
			getClose();
		}
		return status;

	}

	public int getScore(User u) {
		getConn();
		int salary = 0;
		try {
			String sql = " select score from score where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u.getId());
			rs = psmt.executeQuery();

			if (rs.next()) {
				salary = rs.getInt("score");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 오류");
		} finally {
			getClose();
		}
		return salary;

	}

	public int insertScore(User u) {
		int row = 0;
		try {
			getConn();

			String sql = " insert into score values (?,?) ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, u.getId());
			psmt.setInt(2, u.getSalary());

			row = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 베이스 연결 오류");
		} finally {
			getClose();
		}
		return row;
	}

	// 업데이트문 마무리하고 Main if문장 끝내기
	public int updateScore(User u) {
		int row = 0 ; 
		try {
			getConn();
			
			String sql = "update score set score = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,  u.getSalary());
			psmt.setString(2,  u.getId());
			row = psmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("업데이트 오류");
		}finally {
			getClose();
			
		}
		return row;
	}

}