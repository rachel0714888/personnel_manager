package java_personnel_system.DAO;

import java_personnel_system.pojo.Staff;
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
 * @date 2023/6/12 10:15
 */
public class StaffDaoimpl implements StaffDao {
    Object o = new Object();
    Lock lock = new ReentrantLock();

    @Override
    public boolean staffExist(String staffIdentityId,String staffPhonenum) throws Exception {
        Connection c = MainView.cp.getConnection();
        String sql = "select staff_identityid,staff_phonenum from staff_table";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("staff_identityid").equals(staffIdentityId)||
            rs.getString("staff_phonenum").equals(staffPhonenum)){
                MainView.cp.returnConnection(c);
                return true;
            }
        }
        MainView.cp.returnConnection(c);
        return false;
    }

    @Override
    public void staffAdd(Staff staff) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "insert into staff_table(staff_name,staff_sex,sdepartment_id,sposition_id,staff_nation,staff_education,staff_identityid,staff_phonenum) values(?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, staff.getStaffName());
            ps.setString(2, staff.getStaffSex());
            ps.setInt(3, staff.getSdepartmentId());
            ps.setInt(4, staff.getSpositionId());
            ps.setString(5, staff.getStaffNation());
            ps.setString(6, staff.getStaffEducation());
            ps.setString(7, staff.getStaffIdentityId());
            ps.setString(8, staff.getStaffPhonenum());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void removeStaffId(int staffId) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "delete from staff_table where staff_id = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, staffId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void removeStaffName(String staffName) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "delete from staff_table where staff_name = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, staffName);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void tableAlter() throws Exception {
        Connection c = MainView.cp.getConnection();
        String sql = "ALTER TABLE staff_table AUTO_INCREMENT = 1;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.execute();
        MainView.cp.returnConnection(c);
    }

    @Override
    public void autoUpdateStaffPositionId(int staffId) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "update staff_table set sposition_id = 12 where staff_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,staffId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void autoUpdateStaffDepartmentId(int staffId) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "update staff_table set sdepartment_id = 6 where staff_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,staffId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void updateStaffMsg(Staff staff) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "update staff_table set staff_name = ?,staff_sex=?,sdepartment_id=?,sposition_id=?,staff_nation=?,staff_education=?,staff_identityid=?,staff_phonenum=? where staff_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,staff.getStaffName());
            ps.setString(2,staff.getStaffSex());
            ps.setInt(3,staff.getSdepartmentId());
            ps.setInt(4,staff.getSpositionId());
            ps.setString(5,staff.getStaffNation());
            ps.setString(6,staff.getStaffEducation());
            ps.setString(7,staff.getStaffIdentityId());
            ps.setString(8,staff.getStaffPhonenum());
            ps.setInt(9,staff.getStaffId());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void ordinaryUserUpdateStaffMsg(String staffName, String staffIdentityId, String staffPhonenum) throws Exception {
        synchronized (o){
            Connection c = MainView.cp.getConnection();
            String sql = "update staff_table set staff_name = ?,staff_identityid=?,staff_phonenum=? where staff_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,staffName);
            ps.setString(2,staffIdentityId);
            ps.setString(3,staffPhonenum);
            ps.setInt(4, MainView.currentUser.getUserStaffId());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void selectAllStaffMsg() throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked){
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table";
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),
                            rs.getString(3),rs.getInt(4),
                            rs.getInt(5),rs.getString(6),
                            rs.getString(7),rs.getString(8),
                            rs.getString(9)));
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
    public void selectStaffId(int staffId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where staff_id like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1,staffId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffName(String staffName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where staff_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,staffName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffSex(String staffSex) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where staff_sex like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,staffSex);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffDepartmentId(int sdepartmentId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where sdepartment_id like concat('%',?,'%') ";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1,sdepartmentId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffPrositionId(int spositionId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where sposition_id like concat('%',?,'%') ";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1,spositionId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffNation(String staffNation) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where staff_nation like concat('%',?,'%') ";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,staffNation);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffEducation(String staffEducation) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where staff_education like concat('%',?,'%') ";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,staffEducation);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffIdentityId(String staffIdentityId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where staff_identifyid like concat('%',?,'%') ";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,staffIdentityId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
    public void selectStaffPhonenum(String staffPhonenum) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_table where staff_phonenum like concat('%',?,'%') ";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1,staffPhonenum);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Print.staffAllMsgPrint(new Staff(rs.getInt(1),rs.getString(2),rs.getString(3),
                            rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7),
                            rs.getString(8),rs.getString(9)));
                }
                else {
                    Print.print("查无此人");
                }
            }
            else {
                Print.print("网络繁忙，请稍后再试");
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
}