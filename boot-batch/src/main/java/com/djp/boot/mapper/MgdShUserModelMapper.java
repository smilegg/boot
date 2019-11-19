package com.djp.boot.mapper;



import com.djp.boot.model.MgdShUserModel;

import java.util.List;

public interface MgdShUserModelMapper{

	List<MgdShUserModel> queryAll();

	int updateShUser(MgdShUserModel mgdShUserModel);
}