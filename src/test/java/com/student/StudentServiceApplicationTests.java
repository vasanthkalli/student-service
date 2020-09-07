package com.student;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.student.controller.StudentControllerTest;
import com.student.dao.impl.StudentDaoImplTest;
import com.student.service.impl.StudentServiceImpl;


@RunWith(Suite.class)
@SuiteClasses({StudentControllerTest.class,StudentDaoImplTest.class,StudentServiceImpl.class})
public class StudentServiceApplicationTests {


}
