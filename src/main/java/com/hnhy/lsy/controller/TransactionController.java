package com.hnhy.lsy.controller;

import cn.hutool.json.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hnhy.lsy.common.Result;
import com.hnhy.lsy.entity.FundForRecord;
import com.hnhy.lsy.entity.Transaction;
import com.hnhy.lsy.entity.UserCardsForRecord;
import com.hnhy.lsy.service.ITransactionService;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.PortableInterceptor.INACTIVE;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/records")
@RestController
public class TransactionController {
//    @Value("${server.port}")
//    private String port;
    //    IP写死
//    private static final String ip = "http://localhost";

    @Resource
    private ITransactionService transactionService;

    @RequestMapping("/cardaddunbind")
    public Integer cardAddRcd(Integer userId,
                              Integer mainMark,
                              Integer mainContentId,
                              Integer vmark,
                              Date tdate,
                              Double moneyAmount){
//        System.out.println(userId + " " + mainMark + " " + mainContentId + " " + vmark + " " + tdate + " " + moneyAmount);
        int res = transactionService.addRecord_cardAdd(userId, mainMark, mainContentId, vmark, tdate, moneyAmount);
        if(res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/pmoneymodother")
    public Integer moneyChangeRecOther(Integer userId,
                                       Integer mainMark,
                                       Integer vMark,
                                       Integer objMark,
                                       Double MoneyResult,
                                       Date tdate,
                                       Double moneyAmount){
//        System.out.println(userId + " " + mainMark + " " + vMark + " " + objMark + " " + MoneyResult + " " + tdate + " " + moneyAmount);
        int res = transactionService.addRecord_pmoney_other(userId, mainMark, vMark, objMark, MoneyResult, tdate, moneyAmount);
        if(res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/pmoneymodcard")
    public Integer moneyChangeRecCard(Integer userId,
                                      Integer mainMark,
                                      Integer vMark,
                                      Integer objMark,
                                      Integer objContentId,
                                      Double MoneyResult,
                                      Date tdate,
                                      Double moneyAmount){
        int res = transactionService.addRecord_pmoney_card(userId, mainMark, vMark, objMark, objContentId, MoneyResult, tdate, moneyAmount);
        if(res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/soldallother")
    public Integer fundsoldallother(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_allSold_other(userId, mainContentId, tdate, moneyAmount);
        if(res == 1){
            return res;
        }else {
            return 444;
        }
    }

    @RequestMapping("/soldallpocket")
    public Integer fundsoldallpocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount){
        int res = transactionService.addRecord_allSold_pocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/soldallcard")
    public Integer fundsoldallcard(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_allSold_card(userId, mainContentId, objContentId, tdate, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/soldpartother")
    public Integer fundsoldpartother(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_partSold_other(userId, mainContentId, tdate, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/soldpartpocket")
    public Integer fundsoldpartpocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount){
        int res = transactionService.addRecord_partSold_pocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/soldpartcard")
    public Integer fundsoldpartcard(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_partSold_card(userId, mainContentId, objContentId, tdate, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/buyother")
    public Integer fundbuyother(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_buy_other(userId, mainContentId, tdate, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/buypocket")
    public Integer fundbuypocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount){
        int res = transactionService.addRecord_buy_pocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/buycard")
    public Integer fundbuycard(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_buy_card(userId, mainContentId, objContentId, tdate, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/addother")
    public Integer fundaddother(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_add_other(userId, mainContentId, tdate, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/addpocket")
    public Integer fundaddpocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount){
        int res = transactionService.addRecord_add_pocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/addcard")
    public Integer fundaddcard(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount){
        int res = transactionService.addRecord_add_card(userId, mainContentId, objContentId, tdate, moneyAmount);
        if (res == 1){
            return res;
        }else{
            return 444;
        }
    }

    @RequestMapping("/funddetail")
    public Result<?> funddetailRecord(Integer userId, Integer mainId){
        List<Transaction> transactionList = transactionService.record_fund(userId, mainId);
//        System.out.println(transactionList);
        transactionList.forEach((item)->{
            // main肯定都是基金，也不需要信息
            if(item.getTransactionObj().gettOId() == 1){
                // System.out.println(item.getObjContentId());
                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getObjContentId());
                if (cardInfo == null){
                    cardInfo = transactionService.record_getCardInfo2(item.getObjContentId());
                }
                item.setObjInfoName(cardInfo.getCardName());
                item.setObjInfoCode(cardInfo.getCardKey());
            }
        });
        return Result.success(transactionList);
    }

    @RequestMapping("/cardinfo")
    public Result<?> getcardinfo(Integer objId){
        UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(objId);
        if (cardInfo == null){
            cardInfo = transactionService.record_getCardInfo2(objId);
        }
        if(cardInfo == null){
            return Result.error("-1","未找到该卡");
        }else{
            return Result.success(cardInfo);
        }
    }

    @RequestMapping("/pocketdetail")
    public Result<?> pocketdetailRecord(Integer userId){
        List<Transaction> transactionList = transactionService.record_pocket(userId);
        transactionList.forEach((item)->{
            if(item.getTransactionObj().gettOId() == 1){
                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getObjContentId());
                if (cardInfo == null){
                    cardInfo = transactionService.record_getCardInfo2(item.getObjContentId());
                }
                item.setObjInfoName(cardInfo.getCardName());
                item.setObjInfoCode(cardInfo.getCardKey());
            }
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
            }
        });
        return Result.success(transactionList);
    }

    @RequestMapping("/carddetail")
    public Result<?> carddetailrecord(Integer userId, Integer cardId){
        List <Transaction> transactionList = transactionService.record_card(userId, cardId);
        transactionList.forEach((item)->{
            if(item.getTransactionMain().gettMId() == 1){
                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getMainContentId());
                item.setMainInfoName(cardInfo.getCardName());
                item.setMainInfoCode(cardInfo.getCardKey());
            }
            if(item.getTransactionObj().gettOId() == 1){
                UserCardsForRecord cardInfo2 = transactionService.record_getCardInfo1(item.getObjContentId());
                item.setObjInfoName(cardInfo2.getCardName());
                item.setObjInfoCode(cardInfo2.getCardKey());
            }
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
            }
        });
        return Result.success(transactionList);
    }

    @RequestMapping("/myrecordallnum")
    public Integer getmyrecordnum(Integer userId,String beginDate, String endDate,Integer vMark){
        List<Integer> vMarkList = new ArrayList<>();
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
        }else{
            return -1;
        }
        int res = transactionService.myrecord_all_count(userId,beginDate,endDate,vMarkList);
        return res;
    }
    @RequestMapping("/myrecordall")
    public Result<?> getmyrecord(Integer userId,
                                 // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                 String beginDate, String endDate,
                                 // 根据前端传来标识生成(全部0/转入（买入）3/转出（卖出）4) List<Integer> vMarkList,
                                 Integer vMark,
                                 // 页码和每页显示数量
                                 // 第一页 0,10 第二页10,10......
                                 Integer anchor, Integer pageSize){
        List<Integer> vMarkList = new ArrayList<>();
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
        }else{
            return Result.error("-1","未知交易类型");
        }

        List<Transaction> recordList = transactionService.myrecord_all(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        recordList.forEach((item)->{
            if(item.getTransactionMain().gettMId() == 1){
                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getMainContentId());
                if (cardInfo == null){
                    cardInfo = transactionService.record_getCardInfo2(item.getMainContentId());
                }
                item.setMainInfoName(cardInfo.getCardName());
                item.setMainInfoCode(cardInfo.getCardKey());
            }
            if(item.getTransactionObj().gettOId() == 1){
                UserCardsForRecord cardInfo2 = transactionService.record_getCardInfo1(item.getObjContentId());
                if (cardInfo2 == null){
                    cardInfo2 = transactionService.record_getCardInfo2(item.getObjContentId());
                }
                item.setObjInfoName(cardInfo2.getCardName());
                item.setObjInfoCode(cardInfo2.getCardKey());
            }
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
            }
        });
        return Result.success(recordList);
    }

    @RequestMapping("/myrecordfundnum")
    public Integer getmyrecordfundnum(Integer userId,String beginDate, String endDate,Integer vMark){
        List<Integer> vMarkList = new ArrayList<>();
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
        }else{
            return -1;
        }
        int res = transactionService.myrecord_fund_count(userId,beginDate,endDate,vMarkList);
        return res;
    }
    @RequestMapping("/myrecordfund")
    public Result<?> getmyrecordfund(Integer userId,
                                 // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                 String beginDate, String endDate,
                                 // 根据前端传来标识生成(全部0/转入（买入）3/转出（卖出）4) List<Integer> vMarkList,
                                 Integer vMark,
                                 // 页码和每页显示数量
                                 // 第一页 0,10 第二页10,10......
                                 Integer anchor, Integer pageSize){
        List<Integer> vMarkList = new ArrayList<>();
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
        }else{
            return Result.error("-1","未知交易类型");
        }

        List<Transaction> recordList = transactionService.myrecord_fund(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        recordList.forEach((item)->{
            if(item.getTransactionMain().gettMId() == 1){
                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getMainContentId());
                if (cardInfo == null){
                    cardInfo = transactionService.record_getCardInfo2(item.getMainContentId());
                }
                item.setMainInfoName(cardInfo.getCardName());
                item.setMainInfoCode(cardInfo.getCardKey());
            }
            if(item.getTransactionObj().gettOId() == 1){
                UserCardsForRecord cardInfo2 = transactionService.record_getCardInfo1(item.getObjContentId());
                if (cardInfo2 == null){
                    cardInfo2 = transactionService.record_getCardInfo2(item.getObjContentId());
                }
                item.setObjInfoName(cardInfo2.getCardName());
                item.setObjInfoCode(cardInfo2.getCardKey());
            }
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
            }
        });
        return Result.success(recordList);
    }

    @RequestMapping("/myrecordpocketnum")
    public Integer getmyrecordpocketnum(Integer userId,String beginDate, String endDate,Integer vMark){
        List<Integer> vMarkList = new ArrayList<>();
        int res;
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
            res = transactionService.myrecord_pocket_count(userId,beginDate,endDate,vMarkList);
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
            res = transactionService.myrecord_pocket_count_fund(userId,beginDate,endDate,vMarkList);
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
            res = transactionService.myrecord_pocket_count_fund(userId,beginDate,endDate,vMarkList);
        }else{
            return -1;
        }
        return res;
    }
    @RequestMapping("/myrecordpocket")
    public Result<?> getmyrecordpocket(Integer userId,
                                 // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                 String beginDate, String endDate,
                                 // 根据前端传来标识生成(全部0/转入（买入）3/转出（卖出）4) List<Integer> vMarkList,
                                 Integer vMark,
                                 // 页码和每页显示数量
                                 // 第一页 0,10 第二页10,10......
                                 Integer anchor, Integer pageSize){
        List<Integer> vMarkList = new ArrayList<>();
        List<Transaction> recordList = new ArrayList<>();
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
            recordList = transactionService.myrecord_pocket(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
            recordList = transactionService.myrecord_pocket_fund(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
            recordList = transactionService.myrecord_pocket_fund(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        }else{
            return Result.error("-1","未知交易类型");
        }

        recordList.forEach((item)->{
            // 业务流程中不存在这种清空，银行卡不能主动转出到任何宾对象
//            if(item.getTransactionMain().gettMId() == 1){
//                System.out.println(666);
//                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getMainContentId());
//                if (cardInfo == null){
//                    cardInfo = transactionService.record_getCardInfo2(item.getMainContentId());
//                }
//                item.setMainInfoName(cardInfo.getCardName());
//                item.setMainInfoCode(cardInfo.getCardKey());
//            }
            // 操作余额转入转出到银行卡
            if(item.getTransactionObj().gettOId() == 1){
                UserCardsForRecord cardInfo2 = transactionService.record_getCardInfo1(item.getObjContentId());
                if (cardInfo2 == null){
                    cardInfo2 = transactionService.record_getCardInfo2(item.getObjContentId());
                }
                item.setObjInfoName(cardInfo2.getCardName());
                item.setObjInfoCode(cardInfo2.getCardKey());
            }
            // 基金买入卖出选择来源去向为余额
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
                if(item.getTransactionVerb().gettVId() == 1 || item.getTransactionVerb().gettVId() == 3){
                    item.getTransactionVerb().settVId(4);
                }else if(item.getTransactionVerb().gettVId() == 2 || item.getTransactionVerb().gettVId() == 4){
                    item.getTransactionVerb().settVId(3);
                }
            }
        });
        return Result.success(recordList);
    }

    @RequestMapping("/myrecordcardnum")
    public Integer getmyrecordcardnum(Integer userId,String beginDate, String endDate,Integer vMark){
        List<Integer> vMarkList = new ArrayList<>();
        int res;
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
            res = transactionService.myrecord_card_count(userId,beginDate,endDate,vMarkList);
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
            res = transactionService.myrecord_card_count_fund(userId,beginDate,endDate,vMarkList);
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
            res = transactionService.myrecord_card_count_fund(userId,beginDate,endDate,vMarkList);
        }else{
            return -1;
        }
        return res;
    }
    @RequestMapping("/myrecordcard")
    public Result<?> getmyrecordcard(Integer userId,
                                 // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                 String beginDate, String endDate,
                                 // 根据前端传来标识生成(全部0/转入（买入）3/转出（卖出）4) List<Integer> vMarkList,
                                 Integer vMark,
                                 // 页码和每页显示数量
                                 // 第一页 0,10 第二页10,10......
                                 Integer anchor, Integer pageSize){
        List<Integer> vMarkList = new ArrayList<>();
        List<Transaction> recordList = new ArrayList<>();
        if(vMark == 0){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4));
            vMarkList = arr;
            recordList = transactionService.myrecord_card(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        }else if(vMark == 3){
            List<Integer> arr = new ArrayList<>(Arrays.asList(1,3));
            vMarkList = arr;
            recordList = transactionService.myrecord_card_fund(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        }else if(vMark == 4){
            List<Integer> arr = new ArrayList<>(Arrays.asList(2,4));
            vMarkList = arr;
            recordList = transactionService.myrecord_card_fund(userId,beginDate,endDate,vMarkList,anchor,pageSize);
        }else{
            return Result.error("-1","未知交易类型");
        }

        recordList.forEach((item)->{
            if(item.getTransactionMain().gettMId() == 1){
                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getMainContentId());
                if (cardInfo == null){
                    cardInfo = transactionService.record_getCardInfo2(item.getMainContentId());
                }
                item.setMainInfoName(cardInfo.getCardName());
                item.setMainInfoCode(cardInfo.getCardKey());
            }
            if(item.getTransactionObj().gettOId() == 1){
                UserCardsForRecord cardInfo2 = transactionService.record_getCardInfo1(item.getObjContentId());
                if (cardInfo2 == null){
                    cardInfo2 = transactionService.record_getCardInfo2(item.getObjContentId());
                }
                item.setObjInfoName(cardInfo2.getCardName());
                item.setObjInfoCode(cardInfo2.getCardKey());
            }
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
            }
            // 基金买入卖出选择来源去向为银行卡
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
                if(item.getTransactionVerb().gettVId() == 1 || item.getTransactionVerb().gettVId() == 3){
                    item.getTransactionVerb().settVId(4);
                }else if(item.getTransactionVerb().gettVId() == 2 || item.getTransactionVerb().gettVId() == 4){
                    item.getTransactionVerb().settVId(3);
                }
            }
            // 余额转入转出选择来源去向为银行卡
            if(item.getTransactionMain().gettMId() == 2){
                if(item.getTransactionVerb().gettVId() == 3){
                    item.getTransactionVerb().settVId(4);
                }else if(item.getTransactionVerb().gettVId() == 4){
                    item.getTransactionVerb().settVId(3);
                }
            }
        });
        return Result.success(recordList);
    }

    @RequestMapping("/delrecord")
    public Integer delrecord(Integer recordId){
        int res = transactionService.myrecord_del(recordId);
        if(res == 1){
            return res;
        }else{
            return 555;
        }
    }

    @RequestMapping("/myrrccnum")
    public Integer getrrccnum(Integer userId){
        int res = transactionService.myrecord_rcc_count(userId);
        return res;
    }
    @RequestMapping("/myrrcc")
    public Result<?> getrrcc(Integer userId,
                                 Integer anchor, Integer pageSize){

        List<Transaction> recordList = transactionService.myrecord_rcc(userId,anchor,pageSize);
        recordList.forEach((item)->{
            if(item.getTransactionMain().gettMId() == 1){
                UserCardsForRecord cardInfo = transactionService.record_getCardInfo1(item.getMainContentId());
                if (cardInfo == null){
                    cardInfo = transactionService.record_getCardInfo2(item.getMainContentId());
                }
                item.setMainInfoName(cardInfo.getCardName());
                item.setMainInfoCode(cardInfo.getCardKey());
            }
            if(item.getTransactionObj().gettOId() == 1){
                UserCardsForRecord cardInfo2 = transactionService.record_getCardInfo1(item.getObjContentId());
                if (cardInfo2 == null){
                    cardInfo2 = transactionService.record_getCardInfo2(item.getObjContentId());
                }
                item.setObjInfoName(cardInfo2.getCardName());
                item.setObjInfoCode(cardInfo2.getCardKey());
            }
            if(item.getTransactionMain().gettMId() == 3){
                FundForRecord fundInfo = transactionService.record_getFundInfo(item.getMainContentId());
                item.setMainInfoName(fundInfo.getFundName());
                item.setMainInfoCode(fundInfo.getFundCode());
            }
        });
        return Result.success(recordList);
    }

    @RequestMapping("/rccrecord")
    public Integer recycle(Integer existState, String recordId){
        String[] arr = recordId.split(",");
        List<Integer> recordIdList = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            recordIdList.add(Integer.parseInt(arr[i]));
        }
        int res = transactionService.myrecord_rcc_upd(existState, recordIdList);
        if(res >= 1){
            return res;
        }else{
            return -1;
        }
    }
}
