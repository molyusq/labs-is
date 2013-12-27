package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk;

import java.sql.*;

public class SQLiteJDBC {
	
	private String[][] eventsData = new String[][] {
			{"231","Інкасація"},
			{"206","Запис ресурсу"},
			{"212","Операція з гаманцем"},
			{"1", "Включення живлення"},
			{"2", "Вхід до технологічного меню"},
			{"3", "Завершення роботи"},
			{"4", "Вхід до меню інкасування"},
			{"5", "Перегляд історії"},
			{"6", "Відкриття пристрою (спрацював датчик )"},
			{"7", "Закриття пристрою (спрацював датчик )"},
			{"8", "Вилучення бункеру"},
			{"9", "Встановлення бункеру"},
			{"15", "Додана купюра"},
			{"17", "Запуск ПЗ(No версії)"},
			{"18", "Встановлена картка"},
			{"19", "Завершення роботи з карткою"},
			{"28", "Купюру повернуто"},
			{"29", "Прийнято купюру"},
			{"39", "Друк службового чеку"},
			{"42", "Обслуговування пристрою"},
			{"43", "Закінчення обслуговування"},
			{"45", "Перевірка бази даних"}
	};
	
	public static void main(String[] args) {
		SQLiteJDBC s = new SQLiteJDBC();
		//s.init("new.db3");
		//s.executeQuery("ADBK\\Base\\_adbkDB_.db3");
	}
	
	public void init(String dbname) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + dbname);
			s = c.createStatement();
			s.executeUpdate("drop table if exists ref_events");
			s.executeUpdate("create table ref_events (eventcode integer, eventname varchar(50));");
			PreparedStatement p = c.prepareStatement("insert into ref_events values(?,?);");
			for(String[] str : eventsData) {
				p.setString(Integer.parseInt(str[0]), str[1]);
				p.execute();
			}
			r = s.executeQuery("SELECT * FROM ref_events");
			while(r.next()) {
				System.out.println(r.getString(1) + " / " + r.getString(2));
			}
			r.close();
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeQuery(String filename) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + filename);
			s = c.createStatement();
			r = s.executeQuery("SELECT * FROM events");
			while(r.next()) {
				System.out.println(r.getString(3));
			}
			r.close();
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
