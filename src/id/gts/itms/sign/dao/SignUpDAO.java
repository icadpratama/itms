package id.gts.itms.sign.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;

import id.gts.itms.common.CommonDAO;
import id.gts.itms.error.model.Error;
import id.gts.itms.sign.model.SignUp;

public class SignUpDAO extends CommonDAO implements Serializable {
	private static final long serialVersionUID = 1011928498210350794L;
	
	public boolean insertData(SignUp param) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		int hasil = 0;
		StringBuilder query = new StringBuilder()
				.append("INSERT INTO g_user(username,password,privilege,status,createdby,createddate,updatedby,updateddate) ")
				.append("VALUES (?,?,?,?,?,DEFAULT,?,DEFAULT)");
		
		try {
			conn = super.getConnection();
			ps = conn.prepareCall(query.toString());
			ps.setString(1, param.getUsername());
			ps.setString(2, param.getPassword());
			ps.setString(3, "1");
			ps.setString(4, "0");
			ps.setString(5, param.getCreatedby());
			ps.setString(6, param.getUpdatedby());
			hasil = ps.executeUpdate();
			
			if (hasil>0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Error errors = new Error();
			String message = e.getMessage();
			String type = e.getClass().getSimpleName();
			
			StringBuilder error = new StringBuilder()
					.append("INSERT INTO G_ERRORLOG (TYPE,MESSAGE,ERRORBY,ERRORDATE) ")
					.append("VALUES (?,?,?,DEFAULT) ");
			
			errors.setType(type);
			errors.setMessage(message);
			errors.setErrorby("USERS");
			conn = super.getConnection();
			ps = conn.prepareStatement(error.toString());
			ps.setString(1, errors.getType());
			ps.setString(2, errors.getMessage());
			ps.setString(3, errors.getErrorby());
			ps.executeUpdate();
			return false;
		}
	}
}
