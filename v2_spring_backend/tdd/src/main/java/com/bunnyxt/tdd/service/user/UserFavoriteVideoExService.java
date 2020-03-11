package com.bunnyxt.tdd.service.user;

import com.bunnyxt.tdd.model.user.UserFavoriteVideoEx;

import java.util.List;

public interface UserFavoriteVideoExService {

    List<UserFavoriteVideoEx> queryUserFavoriteVideoExsMe(Long userid, String title,
                                                          String order_by, Integer desc, Integer pn, Integer ps);

    Integer queryUserFavoriteVideoExsMeCount(Long userid, String title);
}
