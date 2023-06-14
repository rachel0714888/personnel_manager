package java_personnel_system.service;

import java_personnel_system.DAO.DepartmentDao;
import java_personnel_system.DAO.DepartmentDaoimpl;
import java_personnel_system.DAO.StaffDao;
import java_personnel_system.DAO.StaffDaoimpl;
import java_personnel_system.pojo.Department;
import java_personnel_system.util.Print;
import java_personnel_system.view.ManagerView;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/12 1:24
 */
public class DepartmentService {
    private static DepartmentDao departmentDao = new DepartmentDaoimpl();
    private static StaffDao staffDao = new StaffDaoimpl();

    public static void addDepartment(String departmentName) throws Exception {
        if (departmentDao.departmentExist(departmentName)){
            Print.print("部门已存在，请重新输入");
            ManagerView.addDepartmentView();
        }
        departmentDao.addDepartment(new Department(departmentName));
    }

    public static void removeDepartmentId(int departmentId,int confirm) throws Exception{
        if (!departmentDao.departmentExist(departmentId)){
            Print.print("部门不存在，请重新输入");
            ManagerView.removeDepartmentIdView();
        }
        if (departmentDao.isdepartmentIdHaveStaff(departmentId)){
            if (confirm==1){
                staffDao.autoUpdateStaffDepartmentId(departmentId);
                departmentDao.removeDepartmentId(departmentId);
                departmentDao.departmentAlter();
            }
            if (confirm == 2){
            }
            else {
                Print.print("输入有误，重新输入");
                ManagerView.removeDepartmentIdView();
            }
        }
    }

    public static void removeDepartmentName(String departmentName,int confirm) throws Exception{
        if (!departmentDao.departmentExist(departmentName)){
            Print.print("部门不存在，请重新输入");
            ManagerView.removeDepartmentNameView();
        }
        if (departmentDao.isdepartmentNameHaveStaff(departmentName)){
            if (confirm==1){
                staffDao.autoUpdateStaffDepartmentId(departmentDao.selectLikeDepartmentNameNoPrint(departmentName));
                departmentDao.removeDepartmentName(departmentName);
                departmentDao.departmentAlter();
            }
            if (confirm==2){
            }
            else {
                Print.print("输入有误，重新输入");
                ManagerView.removeDepartmentNameView();
            }
        }
    }

    public static void changeDepartmentMsg(int departmentId,String departmentName) throws Exception{
        if (!departmentDao.departmentExist(departmentId)){
            Print.print("部门不存在，请重新输入");
            ManagerView.changeDepartmentMsgView();
        }
        if (departmentDao.departmentExist(departmentName)){
            Print.print("部门已存在，请重新输入");
            ManagerView.changeDepartmentMsgView();
        }
        departmentDao.changeDepartmentName(new Department(departmentId,departmentName));
    }

    public static void selectAllDepartmentMsg() throws Exception{
        departmentDao.selectAllDepartment();
    }

    public static void selectLikeDepartmentName(String departmentName) throws Exception{
        if (!departmentDao.departmentExist(departmentName)){
            Print.print("部门不存在，请重新输入");
            ManagerView.selectLikeDepartmentNameView();
        }
        departmentDao.selectLikeDepartmentName(departmentName);
    }

}
