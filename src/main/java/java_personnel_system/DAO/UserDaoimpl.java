package java_personnel_system.DAO;

import java_personnel_system.pojo.User;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther Rachel
 * @date 2023/6/11 22:10
 */
public class UserDaoimpl implements UserDao {
    private static Scanner sc = new Scanner(System.in);
    //在对user表进行增删改的时候，保证只有一个线程能执行增删改
    Object o = new Object();
    //在对user表进行查询时，保证数据可见性
    Lock lock = new ReentrantLock();

    @Override
    public boolean userNameExist(String inputUserName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table where user_name = ? ";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, inputUserName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean userIdExist(int userId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table where user_id = ?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean userStaffIdExist(int inputStaffId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table where ustaff_id = ?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, inputStaffId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean userIsworkExist(int inputIswork) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table where is_onwork = ?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, inputIswork);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public int match(User inputUser) throws Exception {
        Connection c = MainView.cp.getConnection();
        String sql = "select * from user_table where user_name = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, inputUser.getUserName());
        ResultSet rs = ps.executeQuery();
        //用户名密码匹配
        while (rs.next()) {
            //用户名存在 判断密码
            if (rs.getString("user_name").equals(inputUser.getUserName())) {
                //密码和用户名匹配 判断员工状态
                if (rs.getString("user_password").equals(inputUser.getUserPassword())) {
                    //该用户是在职员工
                    if (rs.getInt("is_onwork") == 1) {
                        //将储存该用户用户名密码和用户权限的用户对象传入当前用户对象
                        MainView.currentUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                                rs.getInt(4), rs.getInt(6));
                        MainView.currentUser.setOnwork(true);
                        MainView.cp.returnConnection(c);
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    return 3;
                }
            }
        }
        MainView.cp.returnConnection(c);
        return 4;
    }

    @Override
    public void insert(User inputUser) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "insert into user_table(user_name,user_password,ustaff_id,is_onwork,user_authority) values(?,?,?,?,?) ";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, inputUser.getUserName());
            ps.setString(2, inputUser.getUserPassword());
            ps.setInt(3, inputUser.getUserStaffId());
            ps.setBoolean(4, inputUser.getIsOnwork());
            ps.setInt(5, inputUser.getUserAuthority());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void idRemove(int userId) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "delete from user_table where user_id = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void userTableAlter() throws Exception {
        Connection c = MainView.cp.getConnection();
        String sql = "ALTER TABLE user_table AUTO_INCREMENT = 1;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.execute();
        MainView.cp.returnConnection(c);
    }

    @Override
    public void nameRemove(String inputUserName) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "delete from user_table where user_name = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, inputUserName);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void staffIdRemove(int staffId) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "delete from user_table where ustaff_id = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, staffId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void isWorkRemove(int isWork) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "delete from user_table where is_onwork = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, isWork);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void userNameChange(int userId, String userName) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "update user_table set user_name = ? where user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setInt(2, userId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void userPasswordChange(int userId, String userPassword) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "update user_table set user_password = ? where user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userPassword);
            ps.setInt(2, userId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void userStaffidChange(int userId, int userStaffid) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "update user_table set ustaff_id = ? where user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userStaffid);
            ps.setInt(2, userId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void userIsOnworkChange(int userId, int userIswork) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "update user_table set is_onwork = ? where user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, userIswork);
            ps.setInt(2, userId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void changeUserAuthority(int userId, int authority) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "update user_table set user_authority = ? where user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, authority);
            ps.setInt(2, userId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void ordinaryUserMsgUpdate(String userName, String password) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "update user_table set user_name = ? ,user_password = ? where user_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setInt(3, MainView.currentUser.getUserId());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void userAllSelect() throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table";
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    boolean isWork = true;
                    if (rs.getInt("is_onwork") == 0) {
                        isWork = false;
                    }
                    Print.userAllMessagePrint(new User(rs.getInt("user_id"), rs.getString("user_name"),
                            rs.getString("user_password"), rs.getInt("ustaff_id"), isWork,
                            rs.getInt("user_authority")));
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    @Override
    public void userLikeNameSelect(String userName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table where user_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, userName);
                ResultSet rs = ps.executeQuery();
                boolean isFind = false;
                while (rs.next()) {
                    isFind = true;
                    boolean isWork = true;
                    if (rs.getInt("is_onwork") == 0) {
                        isWork = false;
                    }
                    Print.userAllMessagePrint(new User(rs.getInt("user_id"), rs.getString("user_name"),
                            rs.getString("user_password"), rs.getInt("ustaff_id"), isWork,
                            rs.getInt("user_authority")));
                }
                if (!isFind) {
                    Print.print("查无此人");
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    @Override
    public void userLikeIsworkSelect(int isWork) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table where is_onwork = ?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, isWork);
                ResultSet rs = ps.executeQuery();
                boolean isFind = false;
                while (rs.next()) {
                    isFind = true;
                    boolean isOnWork = true;
                    if (rs.getInt("is_onwork") == 0) {
                        isOnWork = false;
                    }
                    Print.userAllMessagePrint(new User(rs.getInt("user_id"), rs.getString("user_name"),
                            rs.getString("user_password"), rs.getInt("ustaff_id"), isOnWork,
                            rs.getInt("user_authority")));
                }
                if (!isFind) {
                    Print.print("查无此人");
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    @Override
    public int selectUserStaffId(String userName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from user_table where user_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, userName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("ustaff_id");
                }
                MainView.cp.returnConnection(c);
            } else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
        return 0;
    }
}

