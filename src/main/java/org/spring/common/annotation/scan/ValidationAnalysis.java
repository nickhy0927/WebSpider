package org.spring.common.annotation.scan;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.common.annotation.Column;
import org.spring.common.annotation.InheritedAnnotation;
import org.spring.common.annotation.Table;
import org.spring.common.annotation.interfaces.IWhat;

public class ValidationAnalysis {
	private static Logger logger = LoggerFactory.getLogger(ValidationAnalysis.class);
	private String packageName;
	private BasicDataSource dataSource;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void init() {
		ScanPackager.scanPackage(this.packageName, new IWhat() {
			@Override
			public void execute(File file, Class<?> entityClass) {
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("create table ");
				if (entityClass != null) {
					Table table = (Table) entityClass.getAnnotation(Table.class);
					stringBuffer.append(table.tableName()).append("(");
					try {
						Class<?> superclass = entityClass.getSuperclass();
						boolean annotationPresent = superclass.isAnnotationPresent(InheritedAnnotation.class);
						if (annotationPresent) {
							stringBuffer.append(validation(superclass.newInstance()));
						}
						StringBuffer validation = validation(entityClass.newInstance());
						String substring = validation.toString().substring(0, validation.toString().length() - 1);
						stringBuffer.append(substring);
						stringBuffer.append(")").append(" ").append("COMMENT").append(" ")
								.append("'" + table.comment() + "'").append(";");
						queryTableExsit(stringBuffer, table, entityClass.newInstance(), superclass.newInstance());
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

			private void queryTableExsit(StringBuffer stringBuffer, Table table, Object entityClass,
					Object superClass) {
				try {
					Connection connection = getDataSource().getConnection();
					DatabaseMetaData meta = (DatabaseMetaData) connection.getMetaData();
					ResultSet rs = meta.getTables(null, null, table.tableName(), null);// table为表名
					if (rs.next()) {
						rs = connection.createStatement().executeQuery("select * from " + table.tableName());
						ResultSetMetaData resultSetMetaData = rs.getMetaData();
						int columnCount = resultSetMetaData.getColumnCount();
						queryField(table, superClass, resultSetMetaData, columnCount);
						queryField(table, entityClass, resultSetMetaData, columnCount);
					} else {
						createSql(connection, stringBuffer);
					}
					rs.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			private void queryField(Table table, Object entityClass, ResultSetMetaData resultSetMetaData,
					int columnCount) throws SQLException {
				if (entityClass != null) {
					List<String> fieldList = new ArrayList<>();
					for (int i = 1; i <= columnCount; i++) {
						String columnName = resultSetMetaData.getColumnName(i);
						fieldList.add(columnName);
					}
					Field[] fields = entityClass.getClass().getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						if (field.isAnnotationPresent(Column.class)) {
							Column column = field.getAnnotation(Column.class);
							field.setAccessible(true);
							if (!fieldList.contains(field.getName())) {
								StringBuffer buffer = alter(field, column, table);
								createSql(getDataSource().getConnection(), buffer);
							}
						}
					}
				}
			}
		});
	}

	/**
	 * 创建表
	 * 
	 * @param connection
	 * @param stringBuffer
	 */
	private void createSql(Connection connection, StringBuffer stringBuffer) {
		try {
			logger.debug(stringBuffer.toString());
			PreparedStatement prepareStatement = connection.prepareStatement(stringBuffer.toString());
			boolean bool = prepareStatement.execute();
			if (bool) {
				prepareStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得一条创建表的sql语句
	 * 
	 * @param entityClass
	 * @param stringBuffer
	 * @throws Exception
	 */
	private StringBuffer validation(Object entityClass) throws Exception {
		Field[] fields = entityClass.getClass().getDeclaredFields();
		StringBuffer fieldType = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				field.setAccessible(true);
				fieldType.append(getFieldType(field, column)).append(",");
			}
		}
		return fieldType;
	}

	/**
	 * 组装一条添加字段的Sql语句
	 * 
	 * @param field
	 * @param column
	 * @param table
	 * @return
	 */
	private StringBuffer alter(Field field, Column column, Table table) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("ALTER TABLE").append(" ").append(table.tableName()).append(" ");
		stringBuffer.append("ADD").append(" ");
		String name = field.getType().getName();
		stringBuffer = stringBuffer.append(field.getName()).append(" ");
		if (java.lang.Integer.class.getName().equals(name))
			stringBuffer.append("int").append("(" + column.length() + ")").append(" ");
		else if (java.lang.Long.class.getName().equals(name))
			stringBuffer.append("double").append(" ");
		else if (java.lang.Boolean.class.getName().equals(name))
			stringBuffer.append("INT").append("(" + column.length() + ")").append(" ");
		else if (java.lang.Character.class.getName().equals(name))
			stringBuffer.append("INT").append("(" + column.length() + ")").append(" ");
		else if (java.lang.Byte.class.getName().equals(name))
			stringBuffer.append("long").append(" ");
		else if (java.lang.Short.class.getName().equals(name))
			stringBuffer.append("short").append(" ");
		else if (java.util.Date.class.getName().equals(name))
			stringBuffer.append("DATETIME").append(" ");
		else if (java.lang.String.class.getName().equals(name))
			stringBuffer.append("VARCHAR").append("(" + column.length() + ")").append(" ");
		stringBuffer.append(column.notNull() ? "NOT NULL" : "NULL").append(" ").append("COMMENT").append(" ")
				.append("'" + column.comment() + "'");

		System.out.println(stringBuffer.toString());
		return stringBuffer;
	}

	/**
	 * 组装创建表的语句
	 * 
	 * @param field
	 * @param column
	 * @return
	 */
	private StringBuffer getFieldType(Field field, Column column) {
		StringBuffer stringBuffer = new StringBuffer();
		String name = field.getType().getName();
		String fieldName = field.getName();
		stringBuffer = stringBuffer.append(fieldName).append(" ");
		if (java.lang.Integer.class.getName().equals(name))
			stringBuffer.append("int").append("(" + column.length() + ")").append(" ");
		else if (java.lang.Long.class.getName().equals(name))
			stringBuffer.append("double").append(" ");
		else if (java.lang.Boolean.class.getName().equals(name))
			stringBuffer.append("INT").append("(" + column.length() + ")").append(" ");
		else if (java.lang.Character.class.getName().equals(name))
			stringBuffer.append("INT").append("(" + column.length() + ")").append(" ");
		else if (java.lang.Byte.class.getName().equals(name))
			stringBuffer.append("long").append(" ");
		else if (java.lang.Short.class.getName().equals(name))
			stringBuffer.append("short").append(" ");
		else if (java.util.Date.class.getName().equals(name))
			stringBuffer.append("DATETIME").append(" ");
		else if (java.lang.String.class.getName().equals(name))
			stringBuffer.append("VARCHAR").append("(" + column.length() + ")").append(" ");
		stringBuffer.append(column.notNull() ? "NOT NULL" : "NULL").append(" ");
		if ("id".equals(fieldName))
			stringBuffer.append("PRIMARY KEY").append(" ");
		stringBuffer.append("COMMENT").append(" ").append("'" + column.comment() + "'");
		return stringBuffer;
	}
}
