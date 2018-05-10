package com.znsd.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.znsd.entity.Student;
import com.znsd.entity.StudentScore;

@RestController
public class SpringbootMongo {

	@Autowired
	private StudentDao studentDao;

	/**
	 * 向mongondb添加一个document对象
	 */
	@RequestMapping("/add")
	public void add() {
		System.out.println("进来增加了");
		Student student = new Student();
		student.setName("hzb");
		student.setSex("man");
		student.setAge(31);
		student.setDes("hzb_father");
		StudentScore score = new StudentScore();
		score.setChinese("88");
		score.setEnglish("93");
		score.setDes("hzb_child");
		student.setStudentScore(score);

		Student student1 = new Student();
		student1.setName("xiaweihu");
		student1.setSex("man");
		student1.setAge(31);
		student1.setDes("xiaweihu_father");
		StudentScore score1 = new StudentScore();
		score1.setChinese("66");
		score1.setEnglish("54");
		score1.setDes("xiaweihu_child");
		student1.setStudentScore(score1);

		Student student2 = new Student();
		student2.setName("hzb");
		student2.setSex("man");
		student2.setAge(31);
		student2.setDes("hzb_father");
		StudentScore score2 = new StudentScore();
		score2.setChinese("77");
		score2.setEnglish("99");
		score2.setDes("hzb_child2");
		student2.setStudentScore(score2);

		studentDao.save(student);
		studentDao.save(student1);
		studentDao.save(student2);
	}

	/**
	 * 查询mongodb当中的所有document
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Student> findAll() {
		List<Student> list = studentDao.findAll();
		return list;
	}

	/**
	 * 通过名字查询document
	 * 
	 * @return
	 */
	@RequestMapping("/findByName")
	public List<Student> findByName() {
		List<Student> student = studentDao.findByName("hzb");
		return student;
	}

	/**
	 * 通过Student.Student.des查询document
	 * 
	 * @param des
	 * @return
	 */
	@RequestMapping("/findByStudentScoreDes")
	public List<Student> findByStudentScore_Des(String des) {
		List<Student> student = studentDao.findByStudentScoreDes("hzb_child2");
		return student;
	}

	/**
	 * 通过Student.name和Student.des查询document
	 * 
	 * @param des
	 * @return
	 */
	@RequestMapping("/findByNameAndDes")
	public List<Student> findByNameAndDes(String des) {
		List<Student> student = studentDao.searchByNameAndDes("hzb", "hzb_father");
		return student;
	}

	/**
	 * 通过Student.name和Student.Student.des查询document
	 * 
	 * @param des
	 * @return
	 */
	@RequestMapping("/findByNameAndStudentScoreDes")
	public List<Student> findByNameAndStudentScoreDes(String des) {
		List<Student> student = studentDao.searchByNameAndStudentScoreDes("hzb", "hzb_child2");
		return student;
	}

	/**
	 * 更新document
	 */
	@RequestMapping("/updateByName")
	public void ubdateByName() {
		List<Student> students = studentDao.findByName("xiaweihu");
		Student student = students.get(0);
		student.setDes("aaaaaaaaaaaaaaaaaaaa");
		student.getStudentScore().setDes("bbbbbbbbbbbbbbbbbbbbb");
		studentDao.update(student);
	}

	/**
	 * 删除document
	 */
	@RequestMapping("/deleteByName")
	public void deleteByName() {
		studentDao.remove("xiaweihu");
	}
}
