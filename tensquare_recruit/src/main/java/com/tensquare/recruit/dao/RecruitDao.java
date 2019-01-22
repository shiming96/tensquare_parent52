package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state); // where state = ? order by createtime

	List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state); // where state != 1 order by createtime
}
