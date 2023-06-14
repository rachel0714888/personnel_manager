package java_personnel_system.controller;

import java_personnel_system.util.Print;
import java_personnel_system.view.MainView;
import java_personnel_system.view.PersonnelView;

/**
 * @auther Rachel
 * @date 2023/6/12 10:14
 */
public class PersonnelController {

    public static void personnelController(String key) throws Exception{
        switch (key){
            case "1":
                MainView.adminUserView();
                PersonnelView.personnelView();
                break;
            case "2":
                MainView.staffView();
                PersonnelView.personnelView();
                break;
            case "3":
                MainView.noticeSelectView();
                PersonnelView.personnelView();
                break;
            default:
                Print.print("输入有误");
                PersonnelView.personnelView();
        }
    }
}
