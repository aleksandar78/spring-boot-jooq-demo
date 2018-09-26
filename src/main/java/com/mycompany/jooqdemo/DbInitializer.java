package com.mycompany.jooqdemo;

import com.mycompany.jooq.model.tables.Users;
import com.mycompany.jooq.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.impl.SQLDataType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.jooq.impl.DSL.constraint;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DbInitializer {
	
	public static final Users USERS = Users.USERS;
	private DSLContext dsl;
	
	public DbInitializer(DSLContext dsl) {
		this.dsl = dsl;
	}
	
	@PostConstruct
	public void init() {
	
		dsl.createTable(USERS)
				.column(USERS.ID, SQLDataType.INTEGER)
				.column(USERS.NAME, SQLDataType.VARCHAR(256).nullable(false))
				.column(USERS.EMAIL, SQLDataType.VARCHAR(256))
				.constraints(
						constraint("PK_USER").primaryKey(USERS.ID)
				).execute();
		
		dsl.insertInto(USERS).values(1, "master", "master@mycompany.com").execute();
		dsl.insertInto(USERS).values(2, "admin", "admin@mycompany.com").execute();
		dsl.insertInto(USERS).values(3, "user", "user@mycompany.com").execute();

	}
}
