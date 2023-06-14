package java_personnel_system.DAO;

import java_personnel_system.pojo.Position;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther Rachel
 * @date 2023/6/12 9:16
 */
public class PositionDaoimpl implements PositionDao {
    Object o = new Object();
    Lock lock = new ReentrantLock();

    @Override
    public void addPosition(Position position) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "insert into position_table(position_name) values(?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, position.getPositionName());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void removePositionId(int positionId) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "delete from position_table where position_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, positionId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void positionAlter() throws Exception {
        Connection c = MainView.cp.getConnection();
        String sql = "ALTER TABLE position_table AUTO_INCREMENT = 1;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.execute();
        MainView.cp.returnConnection(c);
    }

    @Override
    public void removePositionName(String positionName) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "delete from position_table where position_name like concat('%',?,'%')";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, positionName);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void changePositionMsg(Position position) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "update position_table set position_name = ? where position_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,position.getPositionName());
            ps.setInt(2,position.getPositionId());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void selectAllPositionMsg() throws Exception {
        boolean locked = false;
        try{
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked){
                Connection c = MainView.cp.getConnection();
                String sql = "select * from position_table";
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Print.positionAllMsgPrint(new Position(rs.getInt("position_id"),rs.getString("position_name")));
                }
                MainView.cp.returnConnection(c);
            }
            else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (locked){
                lock.unlock();
            }
        }
    }

    @Override
    public void selectLikePositionName(String positionName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked){
                Connection c = MainView.cp.getConnection();
                String sql = "select * from position_table where position_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,positionName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.positionAllMsgPrint(new Position(rs.getInt("position_id"),rs.getString("position_name")));
                }
                else {
                    Print.print("职位不存在");
                }
                MainView.cp.returnConnection(c);
            }
            else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (locked){
                lock.unlock();
            }
        }
    }

    @Override
    public boolean positionExist(String positionName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked){
                Connection c = MainView.cp.getConnection();
                String sql = "select * from position_table where position_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,positionName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }
                MainView.cp.returnConnection(c);
            }
            else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (locked){
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean positionIdExist(int positionId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked){
                Connection c = MainView.cp.getConnection();
                String sql = "select * from position_table where position_id like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1,positionId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return true;
                }
                MainView.cp.returnConnection(c);
            }
            else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (locked){
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public int selectLikePositionNameNoPrint(String positionName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked){
                Connection c = MainView.cp.getConnection();
                String sql = "select * from position_table where position_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,positionName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    return rs.getInt(1);
                }
                else {
                    Print.print("职位不存在");
                }
                MainView.cp.returnConnection(c);
            }
            else {
                Print.print("网络繁忙，请稍后再进行操作");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (locked){
                lock.unlock();
            }
        }
        return 0;
    }


}
