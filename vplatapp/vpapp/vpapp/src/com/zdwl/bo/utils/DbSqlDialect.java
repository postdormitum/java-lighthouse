package com.zdwl.bo.utils;

/**
 * 类似hibernate的Dialect,现阶段只精简出分页部分
 * @author dbw
 *
 */
public abstract class DbSqlDialect {
	
	/**
	 * 获取对应数据库的分页语句
	 * @param page	分页对象
	 * @param sqlBuffer	包含原查询语句的StringBuffer
	 * @return
	 */
	public abstract String getDBPageSql(Page<?> page,StringBuffer sqlBuffer);

}
