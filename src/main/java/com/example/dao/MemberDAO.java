package com.example.dao;

import com.example.bean.BoardVO;
import com.example.bean.MemberVO;
import com.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
    Connection conn = null;
    static PreparedStatement stmt = null;
    static ResultSet rs = null;
    private final String M_INSERT = "insert into member (userid,password,username,email,photo,detail) values (?,?,?,?,?,?)";
    private final String M_UPDATE = "update member set userid=?, password=?, username=?, email=?, photo=?,detail=? where sid=?";
    private final String M_DELETE = "delete from member  where sid=?";
    private final String MEMBER_GET = "select * from member  where sid=?";
    private static final String M_SELECT = "select * from member  where sid=?";
    private final String M_LIST = "select * from member order by regdate desc";

//    private final String M_PHOTO_NAME = "select photo from member";

    public int insertMember(MemberVO data) {
        int result = 0;
//        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        conn = JDBCUtil.getConnection();
        try {
//            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(M_INSERT);
            stmt.setString(1, data.getUserid());
            stmt.setString(2, data.getPassword());
            stmt.setString(3, data.getUsername());
            stmt.setString(4, data.getEmail());
            stmt.setString(5, data.getPhoto());
            stmt.setString(6, data.getDetail());
            result  = stmt.executeUpdate();

//            stmt.executeUpdate();
//            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int updateMember(MemberVO data) {
//        System.out.println("===> JDBC로 updateBoard() 기능 처리");
        int result = 0;
        conn = JDBCUtil.getConnection();
        try {
//            conn = JDBCUtil.getConnection();
//            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(M_UPDATE);
            stmt.setString(1, data.getUserid());
            stmt.setString(2, data.getPassword());
            stmt.setString(3, data.getUsername());
            stmt.setString(4, data.getEmail());
            stmt.setString(5, data.getPhoto());
            stmt.setString(6, data.getDetail());
            stmt.setInt(7,data.getSid());
            stmt.executeUpdate();
//            stmt.setInt(5, vo.getSeq());



            System.out.println(data.getUserid() + "-" + data.getPassword() + "-" + data.getUsername() + "-" + data.getEmail() + "-" + data.getPhoto()+ "-" + data.getDetail() );
//            result  = stmt.executeUpdate();
//            stmt.executeUpdate();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteMember(MemberVO data) {
//        System.out.println("===> JDBC로 deleteBoard() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(M_DELETE);
            stmt.setInt(1, data.getSid());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberVO getMember(int sid) {
        MemberVO one = new MemberVO ();
        System.out.println("===> JDBC로 getMember() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_GET);
            stmt.setInt(1, sid);
            rs = stmt.executeQuery();
            if(rs.next()) {
                one.setSid(rs.getInt("sid"));
                one.setUserid(rs.getString("userid"));
                one.setUsername(rs.getString("username"));
                one.setPassword(rs.getString("password"));
                one.setEmail(rs.getString("email"));
                one.setBlogurl(rs.getString("blogurl"));
                one.setPhoto(rs.getString("photo"));
                one.setDetail(rs.getString("detail"));
                one.setRegdate(rs.getDate("regdate"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }


    public MemberVO getPhoto(int sid) {
        MemberVO one = null;
        conn = JDBCUtil.getConnection();
        try {
            stmt = conn.prepareStatement(M_SELECT);
            stmt.setInt(1, sid);
            rs = stmt.executeQuery();
            if(rs.next()) {
                one = new MemberVO();
                one.setSid(rs.getInt("sid"));
                one.setUserid(rs.getString("userid"));
                one.setUsername(rs.getString("username"));
                one.setEmail(rs.getString("email"));
                one.setPhoto(rs.getString("photo"));
                one.setDetail(rs.getString("detail"));
                one.setRegdate(rs.getDate("regdate"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }


    public ArrayList<MemberVO> getList() {
        ArrayList<MemberVO> list = null;
        MemberVO one = null;
        conn = JDBCUtil.getConnection();
        try {
            stmt = conn.prepareStatement(M_LIST);
            rs = stmt.executeQuery();
            list = new ArrayList<MemberVO>();
            while(rs.next()) {
                one = new MemberVO();
                one.setSid(rs.getInt("sid"));
                one.setUserid(rs.getString("userid"));
                one.setPhoto(rs.getString("photo"));
                one.setUsername(rs.getString("username"));
                one.setPassword(rs.getString("password"));
                one.setEmail(rs.getString("email"));
                one.setDetail(rs.getString("detail"));
                one.setRegdate(rs.getDate("regdate"));
                list.add(one);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static String getPhotoFilename(int sid) {
        String filename = null;
//        System.out.println("===> JDBC로 insertBoard() 기능 처리");
//        conn = JDBCUtil.getConnection();
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(M_SELECT);
            stmt.setInt(1, sid);
            rs = stmt.executeQuery();
            if(rs.next()){
                filename = rs.getString("photo");
            }
            rs.close();
//            stmt.executeUpdate();
//            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===> JDBC로 getPhotoFilename() 기능 처리");
        return filename;
    }



}
