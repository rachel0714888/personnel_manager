package java_personnel_system.service;

import java_personnel_system.DAO.PositionDao;
import java_personnel_system.DAO.PositionDaoimpl;
import java_personnel_system.DAO.StaffDao;
import java_personnel_system.DAO.StaffDaoimpl;
import java_personnel_system.pojo.Department;
import java_personnel_system.pojo.Position;
import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.ManagerView;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/12 9:15
 */
public class PositionService {
    private static PositionDao positionDao = new PositionDaoimpl();
    private static StaffDao staffDao = new StaffDaoimpl();

    public static void addPosition(String positionName) throws Exception {
        if (positionDao.positionExist(positionName)){
            Print.print("职位已存在");
            ManagerView.positionView();
        }
        positionDao.addPosition(new Position(positionName));
    }

    public static void removePositionId(int positionId,int confirm) throws Exception {
        if (!positionDao.positionIdExist(positionId)){
            Print.print("职位不存在");
            ManagerView.positionView();
        }
        if (confirm==1){
            staffDao.autoUpdateStaffPositionId(positionId);
            positionDao.removePositionId(positionId);
            positionDao.positionAlter();
        }
        else if (confirm==2){
            ManagerView.positionView();
        }
        else {
            Print.print("输入有误，将为您返回职位界面");
            ManagerView.positionView();
        }
    }

    public static void removePositionName(String positionName,int confirm) throws Exception {
        if (!positionDao.positionExist(positionName)){
            Print.print("职位不存在");
            ManagerView.positionView();
        }
        if (confirm==1){
            staffDao.autoUpdateStaffPositionId(positionDao.selectLikePositionNameNoPrint(positionName));
            positionDao.removePositionName(positionName);
            positionDao.positionAlter();
        }
        else if (confirm==2){
            ManagerView.positionView();
        }
        else {
            Print.print("输入有误，将为您返回职位界面");
            ManagerView.positionView();
        }
    }

    public static void changePositionMsg(int positionId,String positionName) throws Exception {
        if (!positionDao.positionIdExist(positionId)){
            Print.print("职位不存在");
            ManagerView.positionView();
        }
        if (positionDao.positionExist(positionName)){
            Print.print("该职位已存在");
            ManagerView.positionView();
        }
        positionDao.changePositionMsg(new Position(positionId, positionName));
    }

    public static void selectAllPositionMsg() throws Exception {
        positionDao.selectAllPositionMsg();
    }

    public static void selectLikePositionName(String positionName) throws Exception {
        if (!positionDao.positionExist(positionName)){
            Print.print("职位不存在");
            ManagerView.positionSelectView();
        }
        positionDao.selectLikePositionName(positionName);
    }
}
