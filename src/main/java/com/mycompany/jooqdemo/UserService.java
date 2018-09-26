package com.mycompany.jooqdemo;


import com.mycompany.jooq.model.tables.Users;
import com.mycompany.jooq.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn({"dbInitializer"})
public class UserService {
	
	private DSLContext dsl;
	
	public UserService(DSLContext dsl) {
		this.dsl = dsl;
	}
	
	public UsersRecord getUser() {
		return dsl.selectFrom(Users.USERS).where(Users.USERS.ID.eq(1)).fetchOne();
	}
}
