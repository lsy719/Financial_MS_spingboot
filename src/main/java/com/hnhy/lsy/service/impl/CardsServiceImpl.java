package com.hnhy.lsy.service.impl;

import com.hnhy.lsy.entity.CardNote;
import com.hnhy.lsy.entity.UserCards;
import com.hnhy.lsy.mapper.CardsMapper;
import com.hnhy.lsy.service.ICardsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CardsServiceImpl implements ICardsService {
    @Resource
    private CardsMapper cardsMapper;

    @Override
    public List<UserCards> queryCardsById(Integer id) {
        return cardsMapper.selectCardsById(id);
    }

    @Override
    public List<CardNote> queryCardNotes() {
        return cardsMapper.selectCardNote();
    }

    @Override
    public Integer changeCardNote(Integer cardId, Integer userId, String cardNote) {
        return cardsMapper.modifyCardNote(cardId,userId,cardNote);
    }

    @Override
    public UserCards getOneCard(Integer cardId, Integer userId) {
        return cardsMapper.selectOneCard(cardId, userId);
    }

    @Override
    public Integer modifyMoney(Double cardMoney, Integer cardId, Integer userId) {
        return cardsMapper.changeMoney(cardMoney, cardId, userId);
    }

    @Override
    public Integer checkHasCard(Integer userId, String cardKey) {
        return cardsMapper.checkCardExist(userId,cardKey);
    }

    @Override
    public Integer checkHasBank(String bankCode) {
        return cardsMapper.checkBankExist(bankCode);
    }

    @Override
    public Integer addUserCard(Integer userId, String cardKey, String cardName, Double cardMoney, String bankCode) {
        return cardsMapper.bindCard(userId, cardKey, cardName, cardMoney, bankCode);
    }

    @Override
    public Integer addBankInfo(String bankCode, String bankName, String bankImg) {
        return cardsMapper.addBank(bankCode, bankName, bankImg);
    }

    @Override
    public Integer checkHasAccount(String account) {
        return cardsMapper.checkAccountUsed(account);
    }

    @Override
    public Integer registerUser(String account, String password, String nick_name, String img_url, String phone, Double pocketMoney) {
        return cardsMapper.addUser(account, password, nick_name, img_url, phone, pocketMoney);
    }

    @Override
    public Integer addunbindCard(Integer userId, String cardKey, String cardName, Double disbindMoney, Date disbindDate, Integer cardId) {
        return cardsMapper.unbindcardAdd(userId, cardKey, cardName, disbindMoney, disbindDate, cardId);
    }

    @Override
    public Integer delcard(Integer cardId) {
        return cardsMapper.unbindcardDel(cardId);
    }

    @Override
    public Integer getIdCardJustAdd(Integer userId, String cardKey) {
        return cardsMapper.getCardIdJustAdd(userId, cardKey);
    }

    @Override
    public Integer checkHasUnbundCard(Integer userId, String cardKey) {
        return cardsMapper.checkUnbindCardExist(userId, cardKey);
    }

    @Override
    public Integer updUnbindCard(Double disbindMoney, Date disbindDate, Integer cardId, Integer userId, String cardKey) {
        return cardsMapper.updCardUnbind(disbindMoney, disbindDate, cardId, userId, cardKey);
    }

    @Override
    public Integer checkHasPhone(String phone) {
        return cardsMapper.checkHasPhone(phone);
    }
}
