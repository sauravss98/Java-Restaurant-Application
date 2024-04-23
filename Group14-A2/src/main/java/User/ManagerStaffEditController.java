package User;

import javafx.stage.Stage;

import java.util.ArrayList;

public class ManagerStaffEditController {
    private ArrayList<Manager> managers = UserController.getManagers();
    private ArrayList<Waiter> waiters = UserController.getWaiters();
    private ArrayList<Chef> chefs = UserController.getChefs();
    private ArrayList<Driver> drivers = UserController.getDrivers();
    private Stage stage;
    private Staff activeStaff;
    private Chef activeChef;
    private Manager activeManager;
    private Waiter activeWaiter;
    private Driver activeDriver;

    public ManagerStaffEditController(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStaff(Staff staff) {
        this.activeStaff = staff;
        String userType = activeStaff.getUserType();

        if (userType.equals("Manager")) {
            for (Manager manager : managers) {
                if (activeStaff.getUserId() == manager.getUserId()) {
                    setActiveManager(manager);
                    return;
                }
            }
        }

        if (userType.equals("Chef")) {
            for (Chef chef : chefs) {
                if (activeStaff.getUserId() == chef.getUserId()) {
                    setActiveChef(chef);
                    return;
                }
            }
        }

        if (userType.equals("Driver")) {
            for (Driver driver : drivers) {
                if (activeStaff.getUserId() == driver.getUserId()) {
                    setActiveDriver(driver);
                    return;
                }
            }
        }

        if (userType.equals("Waiter")) {
            for (Waiter waiter : waiters) {
                if (activeStaff.getUserId() == waiter.getUserId()) {
                    setActiveWaiter(waiter);
                    return;
                }
            }
        }
    }

    public void setActiveChef(Chef activeChef) {
        this.activeChef = activeChef;
    }

    public void setActiveDriver(Driver activeDriver) {
        this.activeDriver = activeDriver;
    }

    public void setActiveManager(Manager activeManager) {
        this.activeManager = activeManager;
    }

    public void setActiveWaiter(Waiter activeWaiter) {
        this.activeWaiter = activeWaiter;
    }
}
