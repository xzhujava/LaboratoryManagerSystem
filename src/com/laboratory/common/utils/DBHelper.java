package com.laboratory.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接工具类
 * @author 小柱IT
 * @date 2020/6/3 21:35
 */
public class DBHelper {
	private static String driver;
	private static String name;
	private static String password;
	private static String url;
	private static Connection conn = null;
	
	/**
	 * 初始化连接
	 */
	public static void init(){
		Properties prop = new Properties();
		InputStream in = DBHelper.class.getClassLoader().getResourceAsStream("info.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = prop.getProperty("driver");
		name = prop.getProperty("name");
		password = prop.getProperty("password");
		url = prop.getProperty("url");
	}
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		init();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, name, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	} 
	
	/**
	 * 关闭数据库连接
	 * @param stat
	 * @param rs
	 */
	public static void release(Statement stat,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
