package com.hnhy.lsy.controller;

import cn.hutool.json.*;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hnhy.lsy.common.Result;
import com.hnhy.lsy.entity.*;
import com.hnhy.lsy.service.IFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/finance")
@RestController
public class FinanceController {
//    @Value("${server.port}")
//    private String port;
    //    IP写死
//    private static final String ip = "http://localhost";

    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Resource
    private IFinanceService financeService;

    @PostMapping("/loadfonds")
    public Result<?> queryFonds(Integer userId){
        String userHoldKey = "userHold-" + userId;
        if(redisTemplate.hasKey(userHoldKey)){
//            System.out.println("get from redis!!!");
            List<UserHold> userHoldListRedis = redisTemplate.opsForList().range(userHoldKey,0,-1);
            return Result.success(userHoldListRedis);
        }else{
            List<UserHold> userHoldList = financeService.queryUserHolds(userId);
//            System.out.println("get from mysql!!!");
            for(UserHold userHold : userHoldList){
                redisTemplate.opsForList().rightPush(userHoldKey,userHold);
            }
            if(userHoldList == null){
                return Result.error("-1","未知错误");
            }
            return Result.success(userHoldList);
        }
    }

    @PostMapping("/loadfondstate")
    public Result<?> oneFondState(Integer userId, String financeCode){
        //每一次更改数据都会把这些key删掉，每改一次基金要删一次、添加一次，实际效率不高，分析用户行为描述类似这样的功能为什么不需要做缓存
        // 但是页面每次切换又会重新请求数据，还是要加上
        String fundStateKey = "fundState-" + userId + "-" + financeCode;
        if(redisTemplate.hasKey(fundStateKey)){
            List<FinanceState> stateListRedis = redisTemplate.opsForList().range(fundStateKey,0,-1);
//            System.out.println("get from redis!!!"+financeCode);
//            System.out.println("1" + stateListRedis);
            return Result.success(stateListRedis);
        }else{
            List<FinanceState> stateList = financeService.querySingelFondState(userId, financeCode);
//            System.out.println("2" + stateList);
            for (FinanceState state : stateList){
                // 注意，有时候由于基金刚刚添加，state为null，就不会往redis里加数据
                redisTemplate.opsForList().rightPush(fundStateKey,state);
            }
//            System.out.println("get from mysql!!!"+financeCode);
            return Result.success(stateList);
        }
    }

    @PostMapping("/bankcardMoney")
    public Result<?> countbankM(Integer userId){
        Double allMoney = financeService.figureBankBalance(userId);
        return Result.success(allMoney);
    }

    @PostMapping("/addfundState")
    public Integer addFState(Integer userId, String financeCode, Double thenState, @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")Date updDate){
        FinanceState financeState = new FinanceState();
        financeState.setUserId(userId);
        financeState.setFinanceCode(financeCode);
        financeState.setThenState(thenState);
        financeState.setUpdDate(updDate);
        Integer res = financeService.addFundState(financeState);
        String fundStateKey = "fundState-" + userId + "-" + financeCode;
        if(res == 1){
//            添加成功
            redisTemplate.delete(fundStateKey);
            return res;
        }else{
//            未知错误
            return 555;
        }
    }

    @PostMapping("/modfundState")
    public Integer modfundState(Integer userId, String financeCode, Integer fsId,Double thenState){
        Integer res = financeService.modFundStateToday(fsId,thenState);
        String fundStateKey = "fundState-" + userId + "-" + financeCode;
        if(res == 1){
//            修改成功
            redisTemplate.delete(fundStateKey);
            return res;
        }else{
//            未知错误
            return 555;
        }
    }

    @PostMapping("/reloadsolofund")
    public Result<?> reload1f(Integer userHoldId){
        UserHold userHold = financeService.reload1Fund(userHoldId);
        if(userHold == null){
            return Result.error("-1","未知错误");
        }else{
            return Result.success(userHold);
        }
    }

    @PostMapping("/soldallother")
    public Integer soldallo(Integer userId, String financeCode){
        int res1 = financeService.soldAllMoney0(userId, financeCode);
        int res2 = financeService.soldAllDelState(userId, financeCode);
        String userHoldKey = "userHold-" + userId;
        String fundStateKey = "fundState-" + userId + "-" + financeCode;
        String holdfundChooseKey = "holdFundToChoose-" + userId;
        if(res1 == 1){
            redisTemplate.delete(userHoldKey);
            redisTemplate.delete(fundStateKey);
            redisTemplate.delete(holdfundChooseKey);
            return 1;
        }else{
            return 555;
        }
    }

    @PostMapping("/soldpartother")
    public Integer soldparto(Integer userId, String financeCode, Integer holdId, Integer fsId, Double newMoney, Double newState, Integer changeState){
        int res1 = financeService.soldPartBaseMoney(newMoney, holdId);
        String userHoldKey = "userHold-" + userId;
        String fundStateKey = "fundState-" + userId + "-" + financeCode;
        //
        String holdfundChooseKey = "holdFundToChoose-" + userId;
        if(changeState == 1){
            int res2 = financeService.soldPartThenState(newState, fsId);
            if(res1 == 1 && res2 == 1){
                redisTemplate.delete(userHoldKey);
                redisTemplate.delete(fundStateKey);
                //
                redisTemplate.delete(holdfundChooseKey);
                return 1;
            }else{
                return 444;
            }
        }else{
            if(res1 == 1){
                redisTemplate.delete(userHoldKey);
                redisTemplate.delete(fundStateKey);
                //
                redisTemplate.delete(holdfundChooseKey);
                return 1;
            }else{
                return 444;
            }
        }
    }

    @PostMapping("/buy")
    public Integer buy(Integer userId, Integer holdId, Double newMoney){
        String userHoldKey = "userHold-" + userId;
        int res = financeService.buyAnywayBaseMoney(newMoney, holdId);
        if(res == 1){
            redisTemplate.delete(userHoldKey);
            return res;
        }else{
            return 444;
        }
    }
    @PostMapping("/holdhund")
    public Result<?> getholdfunchoice(Integer userId){
        // 选择添加基金时用来比对
        String holdfundChooseKey = "holdFundToChoose-" + userId;
        if(redisTemplate.hasKey(holdfundChooseKey)){
            // FinanceHold:都是不会变的数据，在全部卖出时删除即可(管理员更改基金名称时也要删除（模糊删除？？）)
            List<FinanceHold> financeListRedis = redisTemplate.opsForList().range(holdfundChooseKey,0,-1);
            return Result.success(financeListRedis);
        }
        List<FinanceHold> financeList = financeService.getHoldFundList(userId);
        for (FinanceHold financeHold : financeList){
            redisTemplate.opsForList().rightPush(holdfundChooseKey,financeHold);
        }
        if(financeList == null){
            return Result.error("-1","未知错误");
        }
        return Result.success(financeList);
    }

    public List<Finance> getAllFund(){
        return financeService.getAllFundList();
    }
    @RequestMapping("/allfund")
    public Result<?> getallfundchoice(){
        List<Finance> financeListRedis = redisTemplate.opsForList().range("fundAll",0,-1);
//        System.out.println(financeListRedis);
        if(financeListRedis.size() > 0){
//            System.out.println("get from redis!!");
            return Result.success(financeListRedis);
        }else{
            List<Finance> financeList = getAllFund();
            if (financeList == null) {
                return Result.error("-1", "未知错误");
            }
            for (Finance fundOne : financeList){
                redisTemplate.opsForList().rightPush("fundAll",fundOne);
            }
//            System.out.println("get from mysql!!");
            return Result.success(financeList);
        }
    }
    @PostMapping("/searchfund")
    public Result<?> getfundbyname(String searchName){
        List<Finance> financeList = financeService.getFundByName(searchName);
        if(financeList == null){
            return Result.error("-1","未知错误");
        }
        return Result.success(financeList);
    }

    @PostMapping("/fundnewhold")
    public Integer addnewfund(Integer userId, String financeCode, Double baseMoney, Date iniDate){
        String userHoldKey = "userHold-" + userId;
        int res = financeService.addUserHold(userId, financeCode, baseMoney, iniDate);
        if(res == 1){
            //这里删了之后，去rediscli看依然有时因为，该前端方法内在添加后里面有做了加载userHold
            redisTemplate.delete(userHoldKey);
            return res;
        }else{
            return 444;
        }
    }

    @PostMapping("/checkhadBought")
    public Integer cashiddsf(Integer userId, String fundCode){
        int res = financeService.meBought(userId, fundCode);
        if(res != 0){
            return 1;
        }else{
            return 0;
        }
    }

    @PostMapping("/fundnewholdupd")
    public Integer asjlsfjlds(Integer userId, String financeCode, Double baseMoney, Date iniDate){
        String userHoldKey = "userHold-" + userId;
        int res = financeService.soModify(userId, financeCode, baseMoney, iniDate);
        if(res == 1){
            redisTemplate.delete(userHoldKey);
            return res;
        }else{
            return 444;
        }
    }

    @PostMapping("/fundidjustadd")
    public Integer getFundIdJustAdd(Integer userId, String fundCode){
        return financeService.getFundId(userId, fundCode);
    }

    @PostMapping("/fundaddapply")
    public Integer addfundAddApply(Integer userId, String fundName, String fundCode, Date applyTime){
        int res = financeService.unIncludeFundAddApply(userId, fundName, fundCode, applyTime);
        if(res == 1){
            return res;
        }else {
            return 444;
        }
    }

    @PostMapping("/getmyappley")
    public Result<?> checkMyApply(Integer userId){
        List<FundAddApply> applyList = financeService.checkAddApply(userId);
        return Result.success(applyList);
    }

    @PostMapping("/delmyapply")
    public Integer delIt(Integer applyId){
        int res = financeService.deleteAddApply(applyId);
        if(res == 1){
            return  res;
        }else {
            return 444;
        }
    }
}
