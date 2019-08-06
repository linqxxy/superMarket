package com.linqxxy.service;

import com.linqxxy.dao.GoodsDao;
import com.linqxxy.entity.Goods;

import java.util.List;

public class GoodsService {
    public GoodsDao goodsDao;

    public GoodsService() {
        goodsDao = new GoodsDao();
    }

    public List<Goods> queryAllGoods() {
        return goodsDao.queryAllGoos();
    }

    public boolean goodPutAway(Goods goods) {
        return this.goodsDao.goodsPutAway(goods);
    }
    public Goods getGoods(int goodsId){
        return goodsDao.getGoods(goodsId);
    }

    public boolean modifyGood(Goods goods) {
        return this.goodsDao.modifyGood(goods);
    }

    public boolean deleteGoods(int goodsId) {
        return goodsDao.deleteGoods(goodsId);
    }
}
