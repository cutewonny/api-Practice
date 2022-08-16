package com.api.repository;

import com.api.connection.DBConnectionUtil;
import com.api.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?,?)";
        Connection conn = null;
        PreparedStatement ps = null;


        try {
            conn = DBConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, member.getMemberId());
            ps.setInt(2, member.getMoney());
        } catch (SQLException e) {
            log.error("error", e);
            throw e;

        } finally{
            ps.close();
            conn.close();
        }

        return null;
    }


}
