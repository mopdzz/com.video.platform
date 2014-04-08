package com.video.platform.repository;

import com.video.platform.entity.Bill;
import com.video.platform.entity.BillCondition;

import java.util.List;

/**
 * User: yangl
 * Date: 13-7-28 下午10:46
 */
@MyBatisRepository
public interface BillDao {

    void save(Bill bill);

    int update(Bill bill);

    List<Bill> find(BillCondition billCondition);
}
