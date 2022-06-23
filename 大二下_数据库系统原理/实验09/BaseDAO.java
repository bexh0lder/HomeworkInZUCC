package cn.edu.zucc.booklib.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zucc.booklib.model.BeanSystemUser;

public class BaseDAO<T> {
	/**
	 * 泛型查询
	 * 
	 * @param sql       SQL语句
	 * @param args      查询语句的参数
	 * @param className 类的完全限定名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> search(Connection conn, Class<T> clazz, String sql, Object... args) {
		// 存储返回的集合对象
		List<T> list = new ArrayList<T>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Field[] fields = clazz.getDeclaredFields();// 使用反射获取类的相关信息
			Map<String,Field> fieldMap = new HashMap<>();
			for(int i=0;i<fields.length;i++) fieldMap.put(fields[i].getName(), fields[i]);
			
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			rs = pstmt.executeQuery();
			ResultSetMetaData resmd = null;
			resmd = rs.getMetaData();// 获取结果集的元素据
			
			int columnCount = resmd.getColumnCount();
			while (rs.next()) {
				T obj = clazz.newInstance(); // 创建一个新对象
				for (int i = 1; i <= columnCount; i++) {
					String cname = resmd.getColumnName(i); // 获取每一列的名称
					int ctype = resmd.getColumnType(i);// 获取每一列的类型
					Field field = fieldMap.get(cname);
					if(field==null) continue;
					// 封装数据
					String firstLetter = field.getName().substring(0, 1).toUpperCase();
					String methodName = "set" + firstLetter + field.getName().substring(1);
					Method method = null;
					// 如果列时整型的
					if (ctype == Types.INTEGER) {
						method = clazz.getMethod(methodName, Integer.class);
						method.invoke(obj, rs.getInt(i));
					} else if (ctype == Types.VARCHAR) {
						method = clazz.getMethod(methodName, String.class);
						method.invoke(obj, rs.getString(i));
					} else if (ctype == Types.TIMESTAMP) {
						method = clazz.getMethod(methodName, Date.class);
						method.invoke(obj, rs.getDate(i));
					} else if (ctype == Types.NUMERIC) {
						method = clazz.getMethod(methodName, Double.class);
						method.invoke(obj, rs.getDouble(i));
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	public T load(Connection conn, Class<T> clazz, Map<String,Object> primaryKey) {
		//primayKey的key为主码的各个属性名称，value为主码值
		//假设对象的类名和表名一致，属性名和字段名一致
		
		String tableName = clazz.getSimpleName();
		String sql = "select * from "+tableName+" where ...."; //动态构建sql
		Object[] parmas = new Object[primaryKey.size()];
		//从map中获取参数值，并写入params
		
		
		
		return null;
	}
	public int executeUpdate(Connection conn, String sql, Object... params) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		return count;
	}

	

	protected void setParams(PreparedStatement ps, Object[] params) {
		if (params == null) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			try {
				ps.setObject(i + 1, params[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		BaseDAO<BeanSystemUser> dao = new BaseDAO<>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from BeanSystemUser";
			List<BeanSystemUser> lst = dao.search(conn, BeanSystemUser.class, sql);
			for(BeanSystemUser u:lst) {
				System.out.println(u.getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
