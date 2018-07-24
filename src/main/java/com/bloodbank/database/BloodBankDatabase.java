package com.bloodbank.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bloodbank.utils.Utils;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.vaadin.annotations.PreserveOnRefresh;

@PreserveOnRefresh
public class BloodBankDatabase implements AutoCloseable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String database = "mybloodbank";
	private String timeZone;
	private final MysqlDataSource data;
	private static BloodBankDatabase myDb;
	// private Connection connection = null;

	// private static Cipher cipher = null;

	public BloodBankDatabase() {

		data = new MysqlDataSource();

		File file = Utils.getSetupFile();
		try {
			BufferedReader bufferreader = new BufferedReader(new FileReader(file));

			String line = "";
			/*
			 * while ((line = bufferreader.readLine()) != null) { System.out.println(line);
			 * }
			 */
			line = bufferreader.readLine();

			/*
			 * RandomAccessFile fi = new
			 * RandomAccessFile(Utils.getSetupFile().getAbsolutePath(),"rw"); FileLock lock
			 * = fi.getChannel().lock(); line = fi.readLine(); lock.release(); fi.close();
			 */

			String[] token = line.split(",");
			String user = Utils.simpleDec(token[0]);
			data.setUser(user);

			String pass = Utils.simpleDec(token[1].toString());

			data.setPassword(pass);
			String server = Utils.simpleDec(token[2].toString());

			data.setServerName(server);
			String port = Utils.simpleDec(token[3].toString());

			data.setPort(Integer.parseInt(port));

			timeZone = Utils.simpleDec(token[4].toString());

			data.setDatabaseName(database);

			bufferreader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int dbInit() {
		try {

			// data.setAutoReconnect(true);
			// cipher = Cipher.getInstance("AES/GCM/NoPadding");
			// data.setServerTimezone("Europe/Bucharest");
			data.setServerTimezone(timeZone);
			data.setAutoClosePStmtStreams(true);

			// connection = data.getConnection();

			// data.getConnection().close();
			Connection conn =  data.getConnection();
			if (conn.isValid(2000))
				conn.close();
			return 1;

		} /*
			 * catch (NoSuchAlgorithmException e) { e.printStackTrace(); return -3;
			 * 
			 * }
			 */ catch (SQLException e) {
			// e.printStackTrace();
			return -1;
		} /*
			 * catch (NoSuchPaddingException e) { e.printStackTrace(); return -2; }
			 */

	}

	public static BloodBankDatabase getInstance() {
		if (myDb == null) {
			myDb = new BloodBankDatabase();
			myDb.dbInit();
		}
		return myDb;
	}

	// email to cnp change
	public int updateToken(String hash, String CNP) {

		Connection conn = null;
		int re = 0;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement("UPDATE users SET token= ? WHERE cnp= ?;");
			stmt.setString(1, hash);
			stmt.setString(2, Utils.simpleEnc(CNP));
			stmt.executeUpdate();
			// re = conn.prepareStatement("UPDATE users SET token='" + hash + "' WHERE id='"
			// + id + "';").executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;

	}

	public String getToken(String token) {

		Connection conn = null;
		String re = null;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement("select token from users  where token= ? ;");
			stmt.setString(1, token);
			result = stmt.executeQuery();
			// result = conn.prepareStatement("select token from users where token='" +
			// token + "';").executeQuery();
			if (result.next()) {
				re = result.getString(1).toString();

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;

	}

	public List<String> getFullName(String id) {
		// String query = "Select firstname,lastname from users where id='" + id + "';";
		String query = "Select firstname,lastname from users where id= ?;";
		Connection conn = null;
		List<String> li = new ArrayList<>();
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			result = stmt.executeQuery();
			// result = conn.prepareStatement(query).executeQuery();
			if (result.next()) {
				li.add(result.getString("firstname"));
				li.add(result.getString("lastname"));

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return li;
	}

	public Map<String, String> getMyInfo(String id) {
		String query = "Select birthdate,gender,country,totalblood,"
				+ "lastdonationdate,bloodtype,eligible from myinfo where id = ?;";
		Connection conn = null;
		Map<String, String> li = new LinkedHashMap<>();
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			result = stmt.executeQuery();
			// result = conn.prepareStatement(query).executeQuery();
			if (result.next()) {
				li.put("age", result.getString("birthdate"));
				li.put("gender", result.getString("gender"));
				li.put("country", result.getString("country"));
				li.put("totalblood", result.getString("totalblood"));
				li.put("lastdonationdate", result.getString("lastdonationdate"));
				li.put("bloodtype", result.getString("bloodtype"));
				li.put("eligible", result.getString("eligible"));

			} else {
				li.put("age", "N/A");
				li.put("gender", "N/A");
				li.put("country", "N/A");
				li.put("totalblood", "N/A");
				li.put("lastdonationdate", "N/A");
				li.put("bloodtype", "N/A");
				li.put("eligible", "N/A");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return li;
	}

	public String getEmail(String id) {
		String query = "Select coalesce(email,'not set') from myinfo where id= ?;";
		Connection conn = null;
		String re = "";
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			result = stmt.executeQuery();
			// result = conn.prepareStatement(query).executeQuery();
			if (result.next()) {
				re = result.getString("email");

			} else {
				return "Not Set";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;

	}

	public int updateEmail(String email, String id) {
		String update = "UPDATE myinfo SET email= ? where id = ? ;";
		Connection conn = null;
		int response = 0;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(update);
			stmt.setString(1, email);
			stmt.setInt(2, Integer.parseInt(id));
			response = stmt.executeUpdate();

			// response = conn.prepareStatement(update).executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return response;

	}

	public int updatePassword(String password, String currentPassword, String id) {
		Connection conn = null;
		int response = 0;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement("select password from users  where id= ?;");
			stmt.setInt(1, Integer.parseInt(id));
			result = stmt.executeQuery();
			// result = conn.prepareStatement("select password from users where id='" + id +
			// "';").executeQuery();

			result.next();
			String resultP = result.getString("password");
			System.out.println("db curr pass: " + resultP);
			if (result != null && stmt != null)
				stmt.close();

			if (MyHash.check(currentPassword, resultP)) {
				System.out.println("Pass the same");
				String hash = MyHash.getSaltedHash(password);
				// String update = "UPDATE users SET password='" + hash + "' where id ='" + id +
				// "';";
				String update = "UPDATE users SET password= ? where id = ?;";
				stmt = conn.prepareStatement(update);
				stmt.setString(1, hash);
				stmt.setInt(2, Integer.parseInt(id));
				response = stmt.executeUpdate();
				// response = conn.prepareStatement(update).executeUpdate();
				System.out.println("db update response: " + response);

			} else {
				// current pass is wrong
				return -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			}
		}
		return response;
	}

	public List<String> logSimpleUser(String cnp, String password) {

		String query = "Select firstname,password, id,rank from users where cnp= ?;";
		Connection conn = null;
		List<String> li = null;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, Utils.simpleEnc(cnp));
			result = stmt.executeQuery();

			// result = /* data.getConnection()
			// */conn.prepareStatement(query).executeQuery();
			if (result.next()) {
				String resultFN = result.getString("firstname");
				String resultP = result.getString("password");
				String resultI = result.getString("id");
				String resultR = result.getString("rank");
				// String[] split = resultP.split("\\.");

				/*
				 * byte[] decodedKey = Base64.getDecoder().decode(split[1]); SecretKey
				 * originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
				 */

				// if(password.equals(Decryption.decrypt(split[0], originalKey,cipher))) {
				if (MyHash.check(password, resultP)) {
					li = new ArrayList<>();
					li.add(resultFN);
					li.add(resultI);
					li.add(resultR);
					return li;
				} else {

					// pass don;t match
					li = new ArrayList<>();
					return li;
				}

			} else {
				// no user in db.
				li = new ArrayList<>();
				li.add("0");
				return li;
			}

		} catch (Exception e) {

			e.printStackTrace();
			// error
			return null;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/// change rom email to id
	public Map<String, String> getUserFullInfo(String queryString) {

		String query = "Select users.firstname,users.lastname,users.signupdate,"
				+ "myinfo.birthdate,myinfo.gender,myinfo.country,myinfo.county,"
				+ "myinfo.lastdonationdate,myinfo.bloodtype,myinfo.email,myinfo.tratament,myinfo.nationality,"
				+ "myinfo.eligible,myinfo.isupdated from users left join myinfo on users.id=myinfo.id "
				+ "where users.cnp = ?";
		Connection conn = null;
		Map<String, String> map = null;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, Utils.simpleEnc(queryString));
			result = stmt.executeQuery();
			// result = /* data.getConnection()
			// */conn.prepareStatement(query).executeQuery();
			map = new LinkedHashMap<>();
			/*
			 * if (result.next()) {
			 * 
			 * map.put("fistname", result.getString(1)); map.put("lastname",
			 * result.getString(2)); map.put("email", result.getString(3));
			 * map.put("signupdate", ) }
			 */
			if (result.next()) {
				for (int i = 1; i <= result.getFetchSize(); i++) {
					System.out.println(result.getMetaData().getColumnName(i));
					map.put(result.getMetaData().getColumnName(i), result.getString(i));

				}
			} else {

				map.put("age", "N/A");
				map.put("gender", "N/A");
				map.put("country", "N/A");
				map.put("totalblood", "N/A");
				map.put("lastdonationdate", "N/A");
				map.put("bloodtype", "N/A");
				map.put("eligible", "N/A");

			}

		} catch (Exception e) {

			e.printStackTrace();
			// error
			return null;
		} finally {
			try {
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return map;

	}

	public int isUpdatedUser(String id) {
		String query = "select isupdateduser from myinfo where id = ?";
		Connection conn = null;
		int re = 0;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();

			stmt = conn.prepareStatement(query);

			stmt.setInt(1, Integer.parseInt(id));

			result = stmt.executeQuery();
			// result = conn.prepareStatement(query).executeQuery();
			if (result.next()) {
				// result.next();
				re = Integer.parseInt(result.getString(1));
				System.out.println("return " + re);
			} else {
				return re;
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			return -1;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;
	}

	public int updateMyInfo(Map<String, String> infos) {
		String values = "";
		// TODO recheck with prepared statement
		Iterator<Entry<String, String>> entry = infos.entrySet().iterator();
		while (entry.hasNext()) {
			values += "'" + entry.next().getValue() + "',";
			System.out.println(values);
		}
		values += "1";

		String query = "INSERT into myinfo(id,birthdate,gender,email,country,county,alcohool,fumator,calatorie2ani,ultimulan,partener,"
				+ "droguri,tratament,starebuna,mediu,diseases,nationality,isupdateduser)" + "values(" + values + ");";
		// totalblood,lastdonationdate,bloodtype,eligible missing

		Connection conn = null;

		int result = 0;
		try {
			conn = data.getConnection();
			result = conn.prepareStatement(query).executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			// error
			return result;
		} finally {

			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}

		}

		return result;

	}

	public int checkCNP(BigInteger CNP) {

		String query = "select exists(select cnp from users where cnp= ?);";
		Connection conn = null;
		int re = 0;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();

			stmt = conn.prepareStatement(query);

			stmt.setString(1, Utils.simpleEnc(String.valueOf(CNP)));

			result = stmt.executeQuery();
			// result = conn.prepareStatement(query).executeQuery();
			result.next();
			re = Integer.parseInt(result.getString(1));
			System.out.println("return " + re);
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			return -1;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;

	}

	public int checkEmail(String email) {
		String query = "select exists(select email from myinfo where email= ? );";
		Connection conn = null;
		int re = 0;
		ResultSet result = null;
		PreparedStatement stmt = null;

		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			// result = conn.prepareStatement(query).executeQuery();
			result.next();
			re = Integer.parseInt(result.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;

	}

	public int getUserCount() {
		String query = "select count(id) from users;";
		Connection conn = null;
		int re = 0;
		ResultSet result = null;
		try {
			conn = data.getConnection();
			result = conn.prepareStatement(query).executeQuery();
			result.next();
			re = Integer.parseInt(result.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;

		} finally {
			try {
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;

	}

	public int isQUpdated(String id) {

		String query = "select isupdateduser from myinfo where id= ?;";
		Connection conn = null;
		int re = 0;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt =  conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			result = stmt.executeQuery();
			//result = conn.prepareStatement(query).executeQuery();
			boolean is = result.next();
			if (is)
				re = Integer.parseInt(result.getString(1));
			
			System.out.println("isQUpate database: "+re);

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;

		} finally {
			try {
				if( stmt!=null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return re;
	}

	public int registerSimpleUser(String firstName, String lastName, String cnp, String password) {
		Timestamp now = new Timestamp(new Date().getTime());
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();

			/*
			 * SecretKey key = Encryption.generateKey(); StringBuffer hash = new
			 * StringBuffer(Encryption.encrypt(password, key,cipher)); hash.append(".");
			 * hash.append(Base64.getEncoder().encodeToString(key.getEncoded()));
			 */
			String hash = MyHash.getSaltedHash(password);

			/*
			 * String query =
			 * "INSERT INTO users(firstname,lastname,cnp,password,signupdate)"
			 * 
			 * + "values('" + firstName + "','" + lastName + "','" + cnp + "','" + hash +
			 * "','" + now + "');";
			 */
			String query = "INSERT INTO users(firstname,lastname,cnp,password,signupdate)" + "values(?,?,?,?,?);";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, Utils.simpleEnc(cnp));
			stmt.setString(4, hash);
			stmt.setTimestamp(5, now);
			return stmt.executeUpdate();
			// return /* data.getConnection()
			// */conn.prepareStatement(query).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// should not be used
	public static Connection checkServer(String server, String user, String pass, int port, String timeZone) {
		MysqlDataSource source = new MysqlDataSource();
		source.setServerName(server);
		source.setUser(user);
		source.setPassword(pass);
		source.setPort(port);

		Connection conn = null;
		try {
			source.setServerTimezone(timeZone);
			conn = source.getConnection();
			conn.isValid(2000);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} /*
			 * finally { try { if(t!=null) t.close(); } catch (SQLException e) {
			 * Auto-generated catch block e.printStackTrace(); } }
			 */

	}

	public MysqlDataSource getDataSource() {
		return data;
	}

	/*
	 * public Connection getConnection() { return connection; }
	 */
	// redundant
	public int isvalid() {
		Connection conn =  null;
	
		try {
			conn =  data.getConnection();
			if (conn.isValid(2000))
				return 1;
			else
				return -1;
		} catch (SQLException e) {

			// e.printStackTrace();
			return -1;
		} finally {
			try {
				if(conn!=null)
				conn.close();	;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

	public List<String> getRememberLoginInfo(String token) {
		String query = "Select firstname, id,rank from users where token= ? ;";
		Connection conn = null;
		List<String> li = null;
		ResultSet result = null;
		PreparedStatement stmt = null;
		try {
			conn = data.getConnection();
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
			// result = /* data.getConnection()
			// */conn.prepareStatement(query).executeQuery();
			if (result.next()) {
				String resultFN = result.getString("firstname");
				String resultI = result.getString("id");
				String resultR = result.getString("rank");
				li = new ArrayList<>();
				li.add(resultFN);
				li.add(resultI);
				li.add(resultR);
			} else {

				// pass don;t match
				li = new ArrayList<>();
				return li;
			}

		} catch (Exception e) {

			e.printStackTrace();
			// error
			return null;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (result != null)
					result.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return li;

	}

	@Override
	public void close() throws Exception {

		data.setAutoClosePStmtStreams(true);
		// connection.close();
		// if(data.)
		// data.getConnection().close();
	}

	public void destroy() {
		myDb = null;
	}

}
