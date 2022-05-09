package fourth;

import fourth.di.Component;
import fourth.ui.UserController;

public class Main {

    public static void main(String[] args) {
        Component.init();
        UserController controller = Component.getUserController();
        controller.start();
    }

}
