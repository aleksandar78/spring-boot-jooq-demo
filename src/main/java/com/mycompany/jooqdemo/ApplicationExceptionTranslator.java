package com.mycompany.jooqdemo;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

public class ApplicationExceptionTranslator extends DefaultExecuteListener {
	
	@Override
	public void exception(ExecuteContext ctx) {
		
		System.out.println("CUSTOM EXECUTION LISTENER CALLED ...");
		
		if (ctx.sqlException() != null) {
			System.out.println("RUNTIME EXC CANONICAL NAME: " + ctx.exception().getClass().getCanonicalName());
			
			SQLDialect dialect = ctx.dialect();
			SQLExceptionTranslator translator = (dialect != null)
					? new SQLErrorCodeSQLExceptionTranslator(dialect.thirdParty().springDbName())
					: new SQLStateSQLExceptionTranslator();
			
			DataAccessException daoEx = translator.translate("jOOQ", ctx.sql(), ctx.sqlException());
			CustomApplicationException myExc = new CustomApplicationException(daoEx.getLocalizedMessage());
			ctx.exception(myExc);
		}
		
	}
}
