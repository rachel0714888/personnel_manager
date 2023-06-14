package java_personnel_system.DAO;

import java_personnel_system.pojo.Department;
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
 * @date 2023/6/12 1:26
 */
public class DepartmentDaoimpl implements DepartmentDao {
    Object o = new Object();
    Lock lock = new ReentrantLock();

    @Override
    public void addDepartment(Department department) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "insert into department_table(department_name) values(?)";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, department.getDepartmentName());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void removeDepartmentId(int departmentId) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "delete from department_table where department_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, departmentId);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void departmentAlter() throws Exception {
        Connection c = MainView.cp.getConnection();
        String sql = "ALTER TABLE department_table AUTO_INCREMENT = 1;";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.execute();
        MainView.cp.returnConnection(c);
    }

    @Override
    public void removeDepartmentName(String departmentName) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "delete from department_table where department_name like concat('%',?,'%')";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, departmentName);
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void changeDepartmentName(Department department) throws Exception {
        synchronized (o) {
            Connection c = MainView.cp.getConnection();
            String sql = "update department_table set department_name = ? where department_id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, department.getDepartmentName());
            ps.setInt(2, department.getDepartmentId());
            ps.execute();
            MainView.cp.returnConnection(c);
        }
    }

    @Override
    public void selectAllDepartment() throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from department_table";
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Print.departmentAllMsgPrint(new Department(rs.getInt("department_id"), rs.getString("department_name")));
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
    public boolean departmentExist(String departmentName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from department_table where department_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, departmentName);
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
    public boolean departmentExist(int departmentId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from department_table where department_id = ?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, departmentId);
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
    public void selectLikeDepartmentName(String departmentName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from department_table where department_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, departmentName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Print.departmentAllMsgPrint(new Department(rs.getInt("department_id"), rs.getString("department_name")));
                } else {
                    Print.print("部门不存在");
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
    public int selectLikeDepartmentNameNoPrint(String departmentName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from department_table where department_name like concat('%',?,'%')";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, departmentName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt("department_id");
                } else {
                    Print.print("部门不存在");
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

    @Override
    public boolean isdepartmentIdHaveStaff(int departmentId) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_msg where department_id = ?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setInt(1, departmentId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                } else {
                    Print.print("部门不存在");
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
    public boolean isdepartmentNameHaveStaff(String departmentName) throws Exception {
        boolean locked = false;
        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            if (locked) {
                Connection c = MainView.cp.getConnection();
                String sql = "select * from staff_msg where department_name = ?";
                PreparedStatement ps = c.prepareStatement(sql);
                ps.setString(1, departmentName);
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
}
