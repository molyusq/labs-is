package ua.kpi.fpm.pzks.is.labs.lisevych_zhuk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.kpi.fpm.pzks.is.labs.lisevych_zhuk.rest.EventData;

public class SqliteHandler {

	public static final String Q_SELECT_NUMS = "select distinct CardNo from events;";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static SqliteHandler instance = null;

	public static SqliteHandler getInstance() {
		if (instance == null) {
			instance = new SqliteHandler();
		}
		return instance;
	}

	private SqliteHandler() {
	}

	public String[] getCardNumbers(String db) {
		List<String> nums = new ArrayList<String>();
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + db);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Q_SELECT_NUMS);
			while (rs.next()) {
				nums.add(rs.getString(1));
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		String[] result = new String[nums.size()];
		nums.toArray(result);
		return result;
	}

	public EventData[] selectEvents(String cardNo) {
		List<EventData> data = new ArrayList<EventData>();
		data.add(new EventData("date1", "date2"));
		data.add(new EventData("date1", "date2"));
		data.add(new EventData("date1", "date2"));
		data.add(new EventData("date1", "date2"));
		EventData[] result = new EventData[data.size()];
		data.toArray(result);
		return result;
	}
}
