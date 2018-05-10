package com.znsd.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.znsd.entity.Student;

@Component
public class StudentDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 保存一个Student信息到student集合里
	 * 
	 * @param stu
	 */
	public void save(Student stu) {
		System.out.println("增加---------------");
		try {
			mongoTemplate.save(stu, "student");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 查询所有的Student信息
	 * 
	 * @return
	 */
	public List<Student> findAll() {
		return mongoTemplate.findAll(Student.class, "student");
	}

	/**
	 * 通过Student.name查询document
	 * 
	 * @param name
	 * @return
	 */
	public List<Student> findByName(String name) {
		List<Student> list = null;
		try {
			Query query = new Query(Criteria.where("name").is(name));
			list = mongoTemplate.find(query, Student.class, "student");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * 通过Student.StudentScore.des查询document
	 * 
	 * @param des
	 * @return
	 */
	public List<Student> findByStudentScoreDes(String des) {
		List<Student> list = null;
		try {
			Query query = new Query(Criteria.where("studentScore.des").is(des));
			list = mongoTemplate.find(query, Student.class, "student");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * 通过Student.name和Student.des查询document
	 * 
	 * @param name
	 * @param des
	 * @return
	 */
	public List<Student> searchByNameAndDes(String name, String des) {
		List<Student> list = null;
		try {
			Query query = new Query(Criteria.where("name").is(name).and("des").is(des));
			list = mongoTemplate.find(query, Student.class, "student");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * 通过Student.name和Student.Student.des查询document
	 * 
	 * @param name
	 * @param des
	 * @return
	 */
	public List<Student> searchByNameAndStudentScoreDes(String name, String des) {
		List<Student> list = null;
		try {
			Query query = new Query(Criteria.where("name").is(name).and("studentScore.des").is(des));
			list = mongoTemplate.find(query, Student.class, "student");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * 更新
	 * 
	 * @param stu
	 */
	public void update(Student stu) {
		try {
			Query query = new Query(Criteria.where("name").is(stu.getName()));
			Update update = new Update().set("des", stu.getDes()).set("studentScore.des",
					stu.getStudentScore().getDes());
			// 更新查询返回结果集的第一条
			mongoTemplate.updateFirst(query, update, Student.class, "student");
			// 更新查询返回结果集的所有
			// mongoTemplate.updateMulti(query,update,Student.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 删除
	 * 
	 * @param name
	 */
	public void remove(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		mongoTemplate.remove(query, Student.class, "student");
	}
}
