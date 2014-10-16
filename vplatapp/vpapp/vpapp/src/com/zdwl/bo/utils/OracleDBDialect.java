package com.zdwl.bo.utils;

/**
 * oracle 数据库分页
 * 
 * @author dbw
 * 
 */
public class OracleDBDialect extends DbSqlDialect {

	@Override
	public String getDBPageSql(Page<?> page, StringBuffer sqlBuffer) {
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
		sqlBuffer.insert(0, "select u.*, rownum r from (")
				.append(") u where rownum < ").append(offset + page.getPageSize());
		sqlBuffer.insert(0, "select * from (").append(") where r >= ")
				.append(offset);
		// 上面的Sql语句拼接之后大概是这个样子：
		// select * from (select u.*, rownum r from (select * from t_user) u
		// where rownum < 31) where r >= 16
		return sqlBuffer.toString();
	}

}
