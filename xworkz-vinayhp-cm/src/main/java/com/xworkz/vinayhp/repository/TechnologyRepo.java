package com.xworkz.vinayhp.repository;

import java.util.Collections;
import java.util.List;
import com.xworkz.vinayhp.entity.TechnolgyListEntity;

public interface TechnologyRepo {

	boolean add(TechnolgyListEntity entity);

	default List<TechnolgyListEntity> listById(int id){
		return Collections.emptyList();
	}
}
