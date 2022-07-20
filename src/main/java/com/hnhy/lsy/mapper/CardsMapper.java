package com.hnhy.lsy.mapper;

import com.hnhy.lsy.entity.CardNote;
import com.hnhy.lsy.entity.UserCards;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CardsMapper {
    List<UserCards> selectCardsById(Integer id);
    List<CardNote> selectCardNote();
    Integer modifyCardNote(@Param("cardId") Integer cardId,
                           @Param("userId") Integer userId,
                           @Param("cardNote") String cardNote);
    UserCards selectOneCard(@Param("cardId") Integer cardId,
                            @Param("userId") Integer userId);
    Integer changeMoney(@Param("cardMoney") Double cardMoney,
                        @Param("cardId") Integer cardId,
                        @Param("userId") Integer userId);

    Integer checkCardExist(@Param("userId") Integer userId,
                           @Param("cardKey") String cardKey);
    Integer checkBankExist(@Param("bankCode") String bankCode);
    Integer bindCard(@Param("userId") Integer userId,
                     @Param("cardKey") String cardKey,
                     @Param("cardName") String cardName,
                     @Param("cardMoney") Double cardMoney,
                     @Param("bankCode") String bankCode);
    Integer addBank(@Param("bankCode") String bankCode,
                    @Param("bankName") String bankName,
                    @Param("bankImg") String bankImg);
    Integer checkAccountUsed(@Param("account") String account);
    Integer addUser(@Param("account") String account,
                    @Param("password") String password,
                    @Param("nick_name") String nick_name,
                    @Param("img_url") String img_url,
                    @Param("phone") String phone,
                    @Param("pocketMoney") Double pocketMoney);
    Integer unbindcardAdd(@Param("userId") Integer userId,
                          @Param("cardKey") String cardKey,
                          @Param("cardName") String cardName,
                          @Param("disbindMoney") Double disbindMoney,
                          @Param("disbindDate") Date disbindDate,
                          @Param("agoId") Integer cardId);

    Integer unbindcardDel(@Param("cardId") Integer cardId);
    Integer getCardIdJustAdd(@Param("userId") Integer userId, @Param("cardKey") String cardKey);
    Integer checkUnbindCardExist(@Param("userId") Integer userId,
                                 @Param("cardKey") String cardKey);
    Integer updCardUnbind(@Param("disbindMoney") Double disbindMoney,
                          @Param("disbindDate") Date disbindDate,
                          @Param("cardId") Integer cardId,
                          @Param("userId") Integer userId,
                          @Param("cardKey") String cardKey);

    Integer checkHasPhone(@Param("phone") String phone);
}
