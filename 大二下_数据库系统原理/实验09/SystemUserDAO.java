package cn.edu.zucc.booklib.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.booklib.model.BeanSystemUser;
import cn.edu.zucc.booklib.util.BaseException;
import cn.edu.zucc.booklib.util.DbException;
/**
 * 用户信息表数据查询、增删改操作
 * 该类不进行任何逻辑判断
 * 
 * 
 * @author zucc
 *
 */
public class SystemUserDAO {
	public List<BeanSystemUser> loadAllUsers(Connection conn,boolean withDeletedUser)throws BaseException{
		List<BeanSystemUser> result=new ArrayList<BeanSystemUser>();
		java.sql.Statement st=null;
		java.sql.ResultSet rs=null;
		try {
			String sql="select userid,username,usertype,createDate from BeanSystemUser";
			if(!withDeletedUser)
				sql+=" where removeDate is null ";
			sql+=" order by userid";
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				BeanSystemUser u=new BeanSystemUser();
				u.setUserid(rs.getString(1));
				u.setUsername(rs.getString(2));
				u.setUsertype(rs.getString(3));
				u.setCreateDate(rs.getDate(4));
				result.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}
	public void createUser(Connection conn,BeanSystemUser user)throws BaseException{
		java.sql.PreparedStatement pst=null;
		try {
			String sql="insert into BeanSystemUser(userid,username,pwd,usertype,createDate) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUserid());
			pst.setString(2, user.getUsername());
			pst.setString(3,user.getPwd());
			pst.setString(4, user.getUsertype());
			pst.setTimestamp(5,new java.sql.Timestamp(System.currentTimeMillis()));
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
				}
		}
	}
	public void updateUser(Connection conn,BeanSystemUser user)throws BaseException{
		java.sql.PreparedStatement pst=null;
		try {
			String sql="update BeanSystemUser set username=?,pwd=?,usertype=? where userid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPwd());
			pst.setString(3, user.getUsertype());
			pst.setString(4, user.getUserid());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void deleteUser(Connection conn,String userid)throws BaseException{
		java.sql.PreparedStatement pst = null;
		try {
			String sql="update BeanSystemUser set removeDate=? where userid=?";
			pst=conn.prepareStatement(sql);
			pst.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(2, userid);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
				}
		}
	}
	public BeanSystemUser loadUser(Connection conn,String userid)throws BaseException{
		java.sql.PreparedStatement pst=null;
		java.sql.ResultSet rs=null;
		try {
			String sql="select userid,username,pwd,usertype,createDate,removeDate from BeanSystemUser where userid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			rs=pst.executeQuery();
			if(!rs.next()) return null;
			BeanSystemUser u=new BeanSystemUser();
			u.setUserid(rs.getString(1));
			u.setUsername(rs.getString(2));
			u.setPwd(rs.getString(3));
			u.setUsertype(rs.getString(4));
			u.setCreateDate(rs.getDate(5));
			u.setRemoveDate(rs.getDate(6));
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
				}
		}
		
	}
}
