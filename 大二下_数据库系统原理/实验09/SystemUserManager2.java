package cn.edu.zucc.booklib.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.edu.zucc.booklib.dao.SystemUserDAO;
import cn.edu.zucc.booklib.model.BeanSystemUser;
import cn.edu.zucc.booklib.util.BaseException;
import cn.edu.zucc.booklib.util.BusinessException;
import cn.edu.zucc.booklib.util.DBUtil;
import cn.edu.zucc.booklib.util.DbException;

public class SystemUserManager2 {
	public static BeanSystemUser currentUser=null;
	private SystemUserDAO dao = new SystemUserDAO();
	public List<BeanSystemUser> loadAllUsers(boolean withDeletedUser)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			return dao.loadAllUsers(conn, withDeletedUser);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
	public void createUser(BeanSystemUser user)throws BaseException{
		if(user.getUserid()==null || "".equals(user.getUserid()) || user.getUserid().length()>20){
			throw new BusinessException("登陆账号必须是1-20个字");
		}
		if(user.getUsername()==null || "".equals(user.getUsername()) || user.getUsername().length()>50){
			throw new BusinessException("账号名称必须是1-50个字");
		}
		if(!"管理员".equals(user.getUsertype()) && "借阅员".equals(user.getUsertype())){
			throw new BusinessException("用户类别 必须是借阅员或管理员");
		}
		
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			BeanSystemUser existsUser = dao.loadUser(conn, user.getUserid());
			if(existsUser!=null) throw new BusinessException("登陆账号已经存在");
			if(user.getPwd()==null || user.getPwd().equals(""))
				user.setPwd(user.getUserid());
			dao.createUser(conn, user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
	public void changeUserPwd(String userid,String oldPwd,String newPwd)throws BaseException{
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>16) throw new BusinessException("必须为1-16个字符");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			BeanSystemUser user = dao.loadUser(conn, userid);
			if(user==null) throw new BusinessException("用户不存在");
			if(!oldPwd.equals(user.getPwd())) throw new BusinessException("原始密码错误");
			user.setPwd(newPwd);
			dao.updateUser(conn, user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	public void resetUserPwd(String userid)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			BeanSystemUser user = dao.loadUser(conn, userid);
			if(user==null) throw new BusinessException("用户不存在");
			user.setPwd(user.getUserid());
			dao.updateUser(conn, user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
	public void deleteUser(String userid)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			dao.deleteUser(conn, userid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
	public BeanSystemUser loadUser(String userid)throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			return dao.loadUser(conn, userid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		
	}
	public static void main(String[] args){
		BeanSystemUser user=new BeanSystemUser();
		user.setUserid("admin");
		user.setUsername("系统管理员");
		user.setUsertype("管理员");
		try {
			new SystemUserManager2().createUser(user);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
