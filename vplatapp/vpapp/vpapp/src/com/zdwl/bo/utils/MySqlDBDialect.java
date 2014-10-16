package com.zdwl.bo.utils;

/**
 * MySql 数据库分页
 * 
 * @author dbw
 * 
 */
public class MySqlDBDialect extends DbSqlDialect {

	@Override
	public String getDBPageSql(Page<?> page, StringBuffer sqlBuffer) {
		if(page == null || sqlBuffer == null)
			return null;
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",")
				.append(page.getPageSize());
		return sqlBuffer.toString();
	}

}
