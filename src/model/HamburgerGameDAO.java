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
			System.out.println("�ڿ��ݳ��� ���� ����!");
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
			System.out.println("�����ͺ��̽� ���� ����");
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
				// n_num �� �ҷ����°� �ű⿡ arraylist�� �߰�
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
			System.out.println("�����ͺ��̽� ���� ����");
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
			System.out.println("�����ͺ��̽� ���� ����");
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
			System.out.println("�ߺ��� ���̵�ų� �߸��� �����Դϴ�!");
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
		System.out.println("a. �丶��  b. �����  c. ����  d. ���� ");
		System.out.println("e. ġ��  f.������Ƽ  g.�Ұ����Ƽ  h.������");
		System.out.println("i. ��Ŭ j.�ؽú��� k.�ͻ�� l.����");
		System.out.println("m. ��ġ  n. �Ұ��ҽ� o. �Ҷ��Ǵ� p. ġŲ��Ƽ");
		System.out.println("�������� ������ z. ������ = �Ʒ���");
	}

	public String inName(char i) {
		if (i == 'a') {
			return "�丶��";
		} else if (i == 'b') {
			return "�����";
		} else if (i == 'c') {
			return "����";
		} else if (i == 'd') {
			return "����";
		} else if (i == 'e') {
			return "ġ��";
		} else if (i == 'f') {
			return "������Ƽ";
		} else if (i == 'g') {
			return "�Ұ����Ƽ";
		} else if (i == 'h') {
			return "������";
		} else if (i == 'i') {
			return "��Ŭ";
		} else if (i == 'j') {
			return "�ؽú���";
		} else if (i == 'k') {
			return "�ͻ��";
		} else if (i == 'l') {
			return "����";
		} else if (i == 'm') {
			return "��ġ";
		} else if (i == 'n') {
			return "�Ұ�� �ҽ�";
		} else if (i == 'o') {
			return "�Ҷ��Ǵ�";
		} else if (i == 'p') {
			return "ġŲ��Ƽ";
		} else {
			return "���� �� (�Ʒ���)";
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
			System.out.println("������ ���̽� ���� ����");
		} finally {
			// �ڿ��� �ݳ��ϴ� �޼ҵ�
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
			System.out.println("�����ͺ��̽� ���� ����");
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
			System.out.println("sql����");
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
				//���̵� ������ ���� =  1�� ������.
				status = "1";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�����ͺ��̽� ���� ����");
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
			System.out.println("�����ͺ��̽� ���� ����");
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
			System.out.println("������ ���̽� ���� ����");
		} finally {
			getClose();
		}
		return row;
	}

	// ������Ʈ�� �������ϰ� Main if���� ������
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
			System.out.println("������Ʈ ����");
		}finally {
			getClose();
			
		}
		return row;
	}

}