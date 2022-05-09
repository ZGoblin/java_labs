package fifth;

import fifth.di.Component;
import fifth.ui.UserController;

public class Main {

    public static void main(String[] args) {
        Component.init();
        UserController controller = Component.getUserController();
        controller.start();
    }

}
