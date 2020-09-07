package com.student.config;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.student.dto.Student;
import com.student.dto.User;

@Configuration
@Order(1)
public class DBConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CodecRegistry getRegistry() {
		
       return  fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	}
	@Bean
	public MongoClientSettings getMongoSettings() {
		return MongoClientSettings.builder().codecRegistry(getRegistry()).build();
	}
	
	@Bean
	public MongoClient getMongoConnection() {
		return new MongoClient("localhost",27017);
	}
	
	@Bean
	public MongoDatabase getMongoStudentDataBase() {
		return getMongoConnection().getDatabase("studentDB");
	}
	@Bean
	public MongoCollection<Student> getMongoStudentCollection(){
		return getMongoStudentDataBase().getCollection("student",Student.class).withCodecRegistry(getRegistry());
	}
	
	public User getUser() {
		User user=new User();
		user.setUsername("user");
		user.setPassword(passwordEncoder().encode("password"));
		return user;
	}
	
	@Bean
	public MongoCollection<User> getMongoUserDetailsCollection(){
		MongoCollection<User> usersCollection= getMongoStudentDataBase().getCollection("users", User.class).withCodecRegistry(getRegistry());
		usersCollection.deleteMany(eq("username","user"));
		usersCollection.insertOne(getUser());
		return usersCollection;
	}
}
