package com.tensquare.friend.Dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend, String> {

    Friend findByUseridAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend set islike = ? WHERE userid = ? AND friendid = ?", nativeQuery = true)
    void updateIslike(String islike, String userid, String friendid);

    @Modifying
    @Query(value = "delete from tb_friend WHERE userid = ? AND friendid = ?", nativeQuery = true)
    void deleteFriend(String userid, String friendid);
}
