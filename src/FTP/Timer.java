package FTP;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer extends Thread {

    private final int waitTime;
    private boolean active;
    private boolean destroy;
    private final ArrayList<Action> actionsList;

    public Timer() {
        this(500);
    }

    public Timer(int waitTime) {
        this.waitTime = waitTime;
        actionsList = new ArrayList<>();
        destroy = false;
    }

    public void activate() {
        active = true;
        if (!this.isAlive()) {
            this.start();
        }
    }

    public void deactivate() {
        active = false;
    }

    public void destroyTimer() {
        destroy = true;
    }

    public boolean activateMethod(Object object, String methodName) {

        try {
            synchronized (actionsList) {
                Method method = object.getClass().getMethod(methodName);
                Action action = new Action();
                action.setObject(object);
                action.setMethodName(methodName);
                actionsList.add(action);
            }
            return true;
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void run() {
        while (true) {

            while (active) {
                try {
                    synchronized (this) {
                        this.wait(waitTime);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                }
                synchronized (actionsList) {
                    actionsList.stream().forEach((_item) -> {
                        try {
                            Method method = _item.getObject().getClass().getMethod(_item.getMethodName());
                            method.invoke(_item.getObject());
                        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
            }
            if (destroy) {
                break;
            }
        }
    }

    private class Action {

        private Object object;
        private String methodName;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

    }
}
