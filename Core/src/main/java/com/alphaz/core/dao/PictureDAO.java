package com.alphaz.core.dao;

import com.alphaz.core.pojo.entity.AlphazPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by JiSan on 2017/6/14.
 */
public interface PictureDAO extends JpaRepository<AlphazPictureEntity,Long> {

}
