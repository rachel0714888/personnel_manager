package java_personnel_system.service;

import java_personnel_system.DAO.StaffDao;
import java_personnel_system.DAO.StaffDaoimpl;
import java_personnel_system.pojo.Staff;
import java_personnel_system.pojo.User;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/12 10:20
 */
public class StaffService {
    private static StaffDao staffDao = new StaffDaoimpl();

    public synchronized static void addStaff(Staff staff) throws Exception {
        if (staffDao.staffIdentityIdNameExist(staff.getStaffIdentityId())) {
            Print.print("员工已存在");
            MainView.staffAddView();
        }
        if (staffDao.staffPhoneNumNameExist(staff.getStaffPhonenum())) {
            Print.print("员工已存在");
            MainView.staffAddView();
        }
        staffDao.staffAdd(staff);
    }

    public synchronized static void removeStaffId(int staffId) throws Exception {
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffIdView();
        }
        staffDao.removeStaffId(staffId);
        staffDao.tableAlter();
    }

    public synchronized static void removeStaffName(String staffName) throws Exception {
        if (!staffDao.staffNameExist(staffName)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.removeStaffName(staffName);
        staffDao.tableAlter();
    }

    public static void updateStaffName(int staffId,String staffName)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffName(staffId,staffName);
    }

    public static void updateStaffSex(int staffId,String staffSex)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffSex(staffId,staffSex);
    }

    public static void updateStaffDepartmentId(int staffId,int sdepartmentId)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffDepartmentId(staffId,sdepartmentId);
    }

    public static void updateStaffPositionId(int staffId,int spositionId)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffPositionId(staffId,spositionId);
    }

    public static void updateStaffNation(int staffId,String staffNation)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffNation(staffId,staffNation);
    }

    public static void updateStaffEducation(int staffId,String staffEducation)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffEducation(staffId,staffEducation);
    }

    public static void updateStaffIdentityid(int staffId,String staffIdentityId)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffIdentityid(staffId,staffIdentityId);
    }

    public static void updateStaffPhonenum(int staffId,String staffPhonenum)throws Exception{
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffPhonenum(staffId,staffPhonenum);
    }

    public static void updateStaffMsg(Staff staff) throws Exception {
        if (!staffDao.staffIdExist(staff.getStaffId())){
            Print.print("员工不存在");
            MainView.removeStaffNameView();
        }
        staffDao.updateStaffMsg(staff);
    }

    public static void ordinaryUserUpdateStaffMsg(String staffName,String staffIdentityId,String staffPhonenum) throws Exception {
        staffDao.ordinaryUserUpdateStaffMsg(staffName,staffIdentityId,staffPhonenum);
    }

    public static void selectAllStaffMsg() throws Exception {
        staffDao.selectAllStaffMsg();
    }

    public static void selectStaffId(int staffId) throws Exception {
        if (!staffDao.staffIdExist(staffId)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffId(staffId);
    }

    public static void selectStaffIdNoInput(int staffId) throws Exception {
        staffDao.selectStaffId(staffId);
    }

    public static void selectStaffName(String staffName) throws Exception {
        if (!staffDao.staffNameExist(staffName)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffName(staffName);
    }

    public static void selectStaffSex(String staffSex) throws Exception {
        if (!staffDao.staffSexExist(staffSex)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffSex(staffSex);
    }

    public static void selectStaffDepartmentId(int sdepartmentId) throws Exception {
        if (!staffDao.staffDepartmentIdExist(sdepartmentId)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffDepartmentId(sdepartmentId);
    }

    public static void selectStaffPrositionId(int spositionId) throws Exception {
        if (!staffDao.staffPositionIdExist(spositionId)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffPrositionId(spositionId);
    }

    public static void selectStaffNation(String staffNation) throws Exception {
        if (!staffDao.staffNationNameExist(staffNation)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffNation(staffNation);
    }

    public static void selectStaffEducation(String staffEducation) throws Exception {
        if (!staffDao.staffEducationNameExist(staffEducation)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffEducation(staffEducation);
    }

    public static void selectStaffIdentityId(String staffIdentityId) throws Exception {
        if (!staffDao.staffIdentityIdNameExist(staffIdentityId)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffIdentityId(staffIdentityId);
    }

    public static void selectStaffPhonenum(String staffPhonenum) throws Exception {
        if (!staffDao.staffPhoneNumNameExist(staffPhonenum)){
            Print.print("员工不存在");
            MainView.staffLikeSelectView();
        }
        staffDao.selectStaffPhonenum(staffPhonenum);
    }

}
