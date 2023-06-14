package java_personnel_system.view;

import java_personnel_system.controller.PersonnelController;
import java_personnel_system.util.Print;

import java.util.Scanner;

/**
 * @auther Rachel
 * @date 2023/6/12 16:36
 */
public class PersonnelView {
    private static Scanner sc = new Scanner(System.in);

    public static void personnelView() throws Exception {
        Print.print("请输入你想访问的模块");
        Print.print("1.用户模块");
        Print.print("2.员工模块");
        Print.print("3.公告模块");
        String inputKey = sc.next();
        PersonnelController.personnelController(inputKey);
    }
}
