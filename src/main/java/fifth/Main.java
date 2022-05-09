package fifth;

import common.Common;
import fifth.di.Component;
import fifth.ui.UserController;

public class Main {

    public static void main(String[] args) {
        String path = Common.input("Type path (if empty create from a list): ");
        Component.init(path);
        UserController controller = Component.getUserController();
        controller.start();
    }

}
