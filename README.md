### Simple Spring Boot Rest application with JOOQ

##### Dependencies
* spring boot 2.0.5
* jooq 3.11.13
* java jdk 8u161-oracle

##### Description
Simple spring boot application with single rest point and custom exception handler.

##### How to

Run application from project root folder:
```shell
$ ./gradlew bootRun
```

See result:

[Go to localhost:8080](http://localhost:8080/user)

##### ExecuteListenerProvider configuration

Case 1 ( BEHAVIOUR NON EXPECTED ):

```java
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public ExecuteListenerProvider customeListenerProvider() {
		return new DefaultExecuteListenerProvider(new ApplicationExceptionTranslator());
	}
```

This is result when 
```json
{
   "errorMessage": "jOOQ; SQL [update \"users\" set \"users\".\"name\" = cast(? as varchar) where \"users\".\"id\" = cast(? as int)NULL not allowed for column \"name\"; SQL statement:\nupdate \"users\" set \"users\".\"name\" = cast(? as varchar) where \"users\".\"id\" = cast(? as int) [23502-197]; nested exception is org.h2.jdbc.JdbcSQLException: NULL not allowed for column \"name\"; SQL statement:\nupdate \"users\" set \"users\".\"name\" = cast(? as varchar) where \"users\".\"id\" = cast(? as int) [23502-197]",
   "rootException": "org.springframework.dao.DataIntegrityViolationException"
}
```

Case 2 ( BEHAVIOUR EXPECTED ):

```java
//	@Bean
//	@Order(Ordered.HIGHEST_PRECEDENCE)
//	public ExecuteListenerProvider customeListenerProvider() {
//		return new DefaultExecuteListenerProvider(new ApplicationExceptionTranslator());
//	}
	
	@Bean
	public static BeanPostProcessor jooqConfigurationPostProcessor() {
		return new BeanPostProcessor() {

			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName)
					throws BeansException {
				return bean;
			}

			@Override
			public Object postProcessAfterInitialization(Object bean, String beanName)
					throws BeansException {
				if (bean instanceof DefaultConfiguration) {
					((DefaultConfiguration) bean).set(new ApplicationExceptionTranslator());
				}
				return bean;
			}

		};
	}
```

```json
{
   "errorMessage": "jOOQ; SQL [update \"users\" set \"users\".\"name\" = cast(? as varchar) where \"users\".\"id\" = cast(? as int)NULL not allowed for column \"name\"; SQL statement:\nupdate \"users\" set \"users\".\"name\" = cast(? as varchar) where \"users\".\"id\" = cast(? as int) [23502-197]; nested exception is org.h2.jdbc.JdbcSQLException: NULL not allowed for column \"name\"; SQL statement:\nupdate \"users\" set \"users\".\"name\" = cast(? as varchar) where \"users\".\"id\" = cast(? as int) [23502-197]",
   "rootException": "com.mycompany.jooqdemo.CustomApplicationException"
}
```