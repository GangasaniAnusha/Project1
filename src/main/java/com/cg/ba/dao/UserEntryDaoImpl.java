package com.cg.ba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cg.ba.model.CustomerDetails;
import com.cg.ba.utility.AadhaarAlreadyExistsException;
import com.cg.ba.utility.Database;

public class UserEntryDaoImpl implements UserEntryDao {
	Database db = new Database();
	Connection con = db.getConnection();
	public int register(CustomerDetails cd) {
		// TODO Auto-generated method stub
		int i = 0 , accountNo = 0 , c = 0;
		PreparedStatement ps = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer_details");
			while(rs.next())
			{
				if(cd.getAadharNo().equals(rs.getString(7)))
					c++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(c == 0) {
		try {
			ps = con.prepareStatement("insert into customer_details values(account_no_seq.nextval,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1, cd.getFirstName());
			ps.setString(2, cd.getLastName());
			ps.setString(3, cd.getEmailId());
			ps.setString(4, cd.getPassword());
			ps.setLong(5, cd.getPancardNo());
			ps.setString(6, cd.getAadharNo());
			ps.setString(7, cd.getAddress());
			ps.setString(8, cd.getMobileNo());
			ps.setInt(9, cd.getBalance());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i == 1)
		{
			try {
				ps = con.prepareStatement("select * from customer_details where aadhaar_no = ?");
				ps.setString(1, cd.getAadharNo());
				ResultSet rs1 = ps.executeQuery();
				while(rs1.next())
					accountNo = rs1.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else
		{
			try {
				throw new AadhaarAlreadyExistsException();
			} catch (AadhaarAlreadyExistsException e) {
				// TODO Auto-generated catch block

			}
		}
		return accountNo;
	}
		
	public CustomerDetails login(int id, String password) {
		// TODO Auto-generated method stub
		CustomerDetails cd = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer_details");
			while(rs.next())
			{
				if(rs.getInt(1) == id && rs.getString(5).equals(password))
				{
					cd = new CustomerDetails();
					cd.setAccountNo(id);
					cd.setFirstName(rs.getString(2));
					cd.setLastName(rs.getString(3));
					cd.setEmailId(rs.getString(4));
					cd.setPancardNo(rs.getLong(6));
					cd.setAadharNo(rs.getString(7));
					cd.setAddress(rs.getString(8));
					cd.setMobileNo(rs.getString(9));
					cd.setBalance(rs.getInt(10));
				}
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cd;
	}

}
